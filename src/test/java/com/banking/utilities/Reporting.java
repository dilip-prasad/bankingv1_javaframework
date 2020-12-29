package com.banking.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest rlogger;
	
	public void onStart(ITestContext testcontext) {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repname="test-report-"+timestamp+".html";
		htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repname); 
		htmlreporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Dilip");
		
		htmlreporter.config().setDocumentTitle("banking test project");
		htmlreporter.config().setReportName("functional test automation report");
		htmlreporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlreporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr) {
		rlogger=extent.createTest(tr.getName());
		rlogger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr) {
		rlogger=extent.createTest(tr.getName());
		rlogger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenshotpath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		File f=new File(screenshotpath);
		
		if(f.exists()) {
			try {
				rlogger.fail("Screensot is below "+ rlogger.addScreenCaptureFromPath(screenshotpath));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void onTestSkipped(ITestResult tr) {
		rlogger=extent.createTest(tr.getName());
		rlogger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testcontext) {
		extent.flush();
	}

}
