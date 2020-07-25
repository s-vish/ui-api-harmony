package com.api.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.internal.BaseTestMethod;

import com.api.endpoints.Endpoints;
import com.api.pojo.weatherresponsepojo.ResponseBody;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class WeatherByCityNameHelper  {
	
	String path = System.getProperty("user.dir");
	FileInputStream fis;
	Properties prop;
	
	/**
	 * 
	 * @param city_name - Name of the City
	 * @return - returns the Response object
	 */
	public Response searchByCityName(String city_name){
		
		try
		{
		fis= new FileInputStream(path+"\\src\\main\\java\\com\\config\\apiConfig.properties");
		prop = new Properties();
		prop.load(fis);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		//fetching URL from properties file
		String url = prop.getProperty("url");
		
		RestAssured.baseURI=url;
		
		RequestSpecification httpRequest = RestAssured.given()
				.basePath(Endpoints.ENDPOINT)
				.queryParam("q", city_name)
				.queryParam("appid", prop.getProperty("appid"));
		
		return httpRequest.request(Method.GET);
	}
	
}
