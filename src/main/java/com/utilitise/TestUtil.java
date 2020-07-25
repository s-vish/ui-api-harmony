package com.utilitise;

import java.text.DecimalFormat;

public class TestUtil {

	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 20;
	public static long EXPLICIT_WAIT = 20;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public static String kelvinToCelsius(double kelvinValue)
	{
		return df2.format(kelvinValue - 273.15);
		
	}
	
	public static String kelvinToFahrenheit(double kelvinValue)
	{
		return df2.format((kelvinValue - 273.15)*(9/5)+32);
	}
	
}