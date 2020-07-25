package com.gui.pom.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.kohsuke.rngom.parse.host.Base;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilitise.TestUtil;

public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public static Properties prop;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, TestUtil.EXPLICIT_WAIT);
		
		try {
			
			//initialize prop
			prop = new Properties();
			
			FileInputStream fis = new FileInputStream("src/main/java/com/config/apiConfig.properties");
			
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void waitForVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void clickElement(WebElement element) {
		waitForVisibility(element);
		try {
			if (element.isDisplayed() || element.isEnabled()) {
				element.click();
			} else {
				System.out.println("element is not clicked");
			}
		} catch (Exception e) {
			System.out.println("Error is :" + e.getStackTrace());
		}

	}

	public void writeText(WebElement element, String text) {
		waitForVisibility(element);
		element.sendKeys(text);
	}

	public String readText(WebElement element) {
		waitForVisibility(element);
		return element.getText();
	}

	// javascript alerts
	public void isAlertPresent() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();

	}

	public void isAlertPresentDismiss() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();
	}

	public String getAlertText() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert.getText();

	}

}
