package com.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

	public static ExtentReports report;
	public static ExtentReports generateReport() {


		if (report == null) {
			String path = System.getProperty("user.dir");

			// code to create a folder
			Date d = new Date();
			SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss z");
			String folderName = dFormat.format(d);
			
			File screenshotPath = new File(path+"/extentReport/"+folderName+"/screenshots");
			screenshotPath.mkdirs();
			
			report = new ExtentReports();

			ExtentSparkReporter reporter = new ExtentSparkReporter(path + "/extentReport/" + folderName);
			reporter.config().setDocumentTitle("Testing Report");
			reporter.config().setEncoding("utf-8");
			reporter.config().setReportName("Automation Test Results");
			reporter.config().setTheme(Theme.DARK);

			report.attachReporter(reporter);
		}
		return report;
	}
}
