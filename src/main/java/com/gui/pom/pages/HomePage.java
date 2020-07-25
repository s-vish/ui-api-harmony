package com.gui.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// object repo
	@FindBy(id = "h_sub_menu")
	WebElement ellipsis;

	@FindBy(xpath = "//a[text()='WEATHER']")
	WebElement menu_Weather;
	
	@FindBy(xpath="//a[text()='No Thanks']")
	WebElement noti_No_Link;

	
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}


	//methods
	public void closeSubscriptionAlert()
	{
		clickElement(noti_No_Link);
	}
	public WeatherPage clickOnWeatherMenu(){
		clickElement(ellipsis);
		clickElement(menu_Weather);
		return new WeatherPage(driver);
	}



}
