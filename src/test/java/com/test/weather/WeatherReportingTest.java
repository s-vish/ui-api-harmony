package com.test.weather;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.helper.WeatherByCityNameHelper;
import com.api.pojo.weatherresponsepojo.ResponseBody;
import com.aventstack.extentreports.Status;
import com.base.TestBase;
import com.gui.pom.pages.HomePage;
import com.gui.pom.pages.WeatherPage;
import com.utilitise.ComparatorClass;
import com.utilitise.TestUtil;

import io.restassured.response.Response;

public class WeatherReportingTest extends TestBase {
	//api
	private Response response;
	private WeatherByCityNameHelper objHelper;
	private ResponseBody objResp;
	private double tempInCelsiusAPI;
	private double tempInFahrenheitAPI;
	
	// ui 
	private HomePage objHomePage;
	private WeatherPage weatherPage;
	private List<String> popupData;
	private double tempInCelsiusUI;
	private double tempInFahrenheitUI;

	@Test(priority = 1)
	public void redirectToWeatherPage() {
		log(Status.INFO, "UI Test Started");
		objHomePage = new HomePage(driver);
		try {
			objHomePage.closeSubscriptionAlert();
			log(Status.INFO, "Alert closed.");
		} catch (NoSuchFieldError e) {
			e.printStackTrace();
			log(Status.INFO, "Alert was not present.");
		}
		
		objHomePage.clickOnWeatherMenu();
		log(Status.INFO,"Clicked on Weather Link.");
		weatherPage = new WeatherPage(driver);
		log(Status.INFO,"Redirected to Weather Page.");


		// to clear all preselected cities.
		log(Status.INFO, "De-selecting all cities");
		weatherPage.getCityList();
		
		weatherPage.enterCity(prop.getProperty("city_name"));
		log(Status.INFO,"text entered -> "+prop.getProperty("city_name")+"");
		
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		weatherPage.selectCity();
		log(Status.INFO, "Selecting City from dropdown -> "+prop.getProperty("city_name")+"");
		
		//verify city name present on map
		Assert.assertTrue(weatherPage.verifyCityOnMap(),"ERROR!!! City is not seen on Map");
		
		//verify temp in Celsius
		Assert.assertTrue(weatherPage.verifyCityTempInCelsius(), "ERROR!!! Temprature in Celsius is not seen on Map");
		
		//verify temp in Fahrenheit
		Assert.assertTrue(weatherPage.verifyCityTempInFahrenheit(),"ERROR!!! Temprature in Fahrenheit is not seen on Map");
		
		weatherPage.selectCityOnMap();
		log(Status.INFO, "Clicking City from Map -> "+prop.getProperty("city_name")+"");
		
		//verify city popup opens
		Assert.assertTrue(weatherPage.verifyCityInfoPopupOnMap(),"ERROR!!! City info popup is not displayed.");
		
		// collecting all popup info
		popupData = weatherPage.weatherPopUp();
		log(Status.INFO, "Popup Opened.");

		tempInCelsiusUI = Double.valueOf(popupData.get(3));
		tempInFahrenheitUI = Double.valueOf(popupData.get(4));

		log(Status.INFO,"Temprature in Celsius is "+tempInCelsiusUI+"degree");
		

	}

	// api class
	@Test(priority = 2)
	public void weatherByCityName() throws IOException {
		objHelper = new WeatherByCityNameHelper();
		response = objHelper.searchByCityName(prop.getProperty("city_name"));
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Error!! Status code is mismatched.");
		objResp = response.as(ResponseBody.class);
		double tempInKelvin = Double.valueOf(objResp.getMain().getTemp());
		double Humidity = Double.valueOf(objResp.getMain().getHumidity());

		log(Status.INFO,"Converting temprature from Kelvin to Celsius");
		tempInCelsiusAPI = Double.valueOf(TestUtil.kelvinToCelsius(tempInKelvin));
		log(Status.INFO,"Converting temprature from Kelvin to Fahrenheit");
		tempInFahrenheitAPI = Double.valueOf(TestUtil.kelvinToFahrenheit(tempInKelvin));
		log(Status.INFO,"Temprature in Celsius shown in API is "+tempInCelsiusAPI+"");
		
	}

	@Test(priority = 3, dependsOnMethods = { "redirectToWeatherPage", "weatherByCityName" })
	public void compareResult() {

		log(Status.INFO, "Temprature captured from UI is "+tempInCelsiusUI+"");
		log(Status.INFO, "Temprature captured from API is "+tempInCelsiusAPI+"");
		log(Status.INFO, "specified range is "+prop.getProperty("range")+"");
		if(ComparatorClass.variece(tempInCelsiusUI, tempInCelsiusAPI)) {
			log(Status.PASS,"Temprature difference is in specified range");
		}
		else
			log(Status.FAIL,"temperature difference is NOT within a specified range");
	}

}
