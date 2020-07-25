package com.gui.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.ArrayList;
import java.util.List;

public class WeatherPage extends BasePage {

	String city_name = prop.getProperty("city_name");

	
	//Object repo
	@FindBy(xpath = "//input[@id='searchBox']")
	private WebElement inputSearch;

	@FindBy(xpath = "//input[@class='defaultChecked' and @checked='checked']")
	private List<WebElement> checkedCities;

	@FindBy(xpath = "//div[@class='leaflet-popup-content-wrapper']//child::span[@class='heading']")
	private List<WebElement> popData;
	
	@FindBy(xpath="//div[@class='leaflet-popup-content-wrapper']")
	private WebElement popup;

	//constructor
	public WeatherPage(WebDriver driver) {
		super(driver);

	}

	
	//methods
	public void enterCity(String city) {

		inputSearch.clear();
		writeText(inputSearch, city);
	}

	public void selectCity() {

		clickElement(driver.findElement(By.xpath("//input[@id='" + city_name + "']")));
	}
	
	
	public void selectCityOnMap() {
		clickElement(driver.findElement(By.xpath("//div[@title='" + city_name + "']")));
	}
	
	public boolean verifyCityInfoPopupOnMap()
	{
		return popup.isDisplayed();
	}

	public List<String> weatherPopUp()

	{
		String condition = popData.get(0).getText();
		String wind = popData.get(1).getText();
		String humidity = popData.get(2).getText();
		String tempInC = popData.get(3).getText();
		String tempInF = popData.get(4).getText();

		List<String> infoData = new ArrayList<String>();
		infoData.add(condition.substring(11));
		infoData.add(wind.substring(5));
		infoData.add(humidity.substring(10));
		infoData.add(tempInC.substring(16));
		infoData.add(tempInF.substring(19));

		return infoData;
	}
	
	public boolean verifyCityOnMap()
	{
	
		return driver.findElement(By.xpath("//div[@title='"+city_name+"']")).isDisplayed();
		
	}
	
	public boolean verifyCityTempInCelsius()
	{
		return driver.findElement(By.xpath("//div[@title='"+city_name+"']//child::div/span[@class='tempRedText']")).isDisplayed();
		
			
	}

	public boolean verifyCityTempInFahrenheit()
	{
		return driver.findElement(By.xpath("//div[@title='"+city_name+"']//child::div/span[@class='tempWhiteText']")).isDisplayed();
	}
	
	public void getCityList() {

		for (int i = 0; i < checkedCities.size(); i++) {
			Boolean is_selected = checkedCities.get(i).isSelected();
			try {
				if (is_selected == true) {
					checkedCities.get(i).click();
				} else {
					System.out.println("values are not default selected");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
