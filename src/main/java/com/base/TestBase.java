package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.report.ReportManager;
import com.utilitise.TestUtil;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {

	public static WebDriver driver;
	public WebDriverWait wait;
	public static Properties prop;
	public ExtentReports report;
	public ExtentTest test;

	public TestBase() {
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
	
	
	@BeforeMethod
	public void init(ITestResult result) {
		report = ReportManager.generateReport();
		// this line of code will print the methods name which is calling this init
		// method.
		test = report.createTest(result.getMethod().getMethodName());
		result.setAttribute("ExtentTestObject", test);
	}

	@AfterMethod
	public void reportFlush() {
		report.flush();
	}

	public void log(Status status, String str) {
		System.out.println(str); //print in console
		test.log(status, str);
	}
	@BeforeClass
	public static void init()
	{
		String browserName = prop.getProperty("browser");
		
		
			if(browserName.equalsIgnoreCase("chrome")){
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.addArguments("disable-notification");
				driver = new ChromeDriver(options);

			}else if(browserName.equalsIgnoreCase("firefox")){
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				
				
			}else if(browserName.equalsIgnoreCase("edge")){
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.manage().window().maximize();
				
			}else {
				System.out.println(("browser : " + browserName + " is invalid, Launching chrome as browser of choice.."));
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
		
		driver.manage().deleteAllCookies();
		
		//opening url of NDTV Home page
		driver.get(prop.getProperty("NDTV_url"));
		
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
	}


	@AfterClass
	public void tearDown()
	{
		driver.quit();

	}

}
