package com.utilitise;

import java.text.DecimalFormat;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import com.base.TestBase;



public class ComparatorClass extends TestBase{
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	static double range;
	
	
	static {
		range = Double.valueOf(prop.getProperty("range"));
	}

	public  static boolean variece(double uiTemp, double apiTemp )
	{
		
		System.out.println("range is "+range);
		double difference;
		if(uiTemp >= apiTemp) {
		difference = Double.valueOf(df2.format(uiTemp-apiTemp));	
		System.out.println("difference is "+difference);
		}
		else {
		difference = Double.valueOf(df2.format(apiTemp-uiTemp));
		System.out.println("difference is "+difference);
		}
		if(difference<=range)
		return true;
		
		else
			return false; 

	}

	
	
}

	


