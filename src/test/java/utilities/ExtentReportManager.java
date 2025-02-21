package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	
	public void onStart(ITestContext testContext) {
		
		
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());


		repName = "Test-Report-" + timestamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the
		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report 
		sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter (sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser"); 
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();

		if(!includedGroups.isEmpty()) { 
			
			extent.setSystemInfo("Groups", includedGroups.toString());
		
		}
		
		
	}
	
	
	  public void onTestSuccess(ITestResult result) {
	        // This method is triggered when a test is successful
	        test = extent.createTest(result.getTestClass().getName());
	        
	        test.assignCategory(result.getMethod().getGroups()); // to display groups in report
	        
	        // Log the success information
	        test.log(Status.PASS, result.getName() + " got successfully executed");
	        
	    }
	  
	  
	  public void onTestFailure (ITestResult result) {
		  
		  test = extent.createTest(result.getTestClass().getName());	
		  test.assignCategory(result.getMethod().getGroups());
		  
		  test.log(Status.FAIL, result.getName()+" got failed");	  	
		  test.log(Status.INFO, result.getThrowable().getMessage());
		  
		  try {
		  String imgPath = new BaseClass().captureScreen(result.getName());
		  test.addScreenCaptureFromPath(imgPath);
		  } 
		  catch (IOException e1) {
		  e1.printStackTrace();
		  }
		  }
	  
	  public void onTestSkip (ITestResult result) {
		  test = extent.createTest(result.getTestClass().getName());	
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.SKIP, result.getName()+" got Skipped");	  	
		  test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	  
	  
	  public void onFinish (ITestContext testContext) {
		  
		  extent.flush();
		  
		  // to open report automatically after execution
		  String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		  File extentReport = new File(pathofExtentReport);
		  
		  try {
		  Desktop.getDesktop().browse(extentReport.toURI());
		  } 
		  catch (IOException e) {
		  e.printStackTrace();
		  }
		  
		  
		  // optional // to send extend report to people on email (if you do it multiple times you can get some restrictions )
//		  try {
//			    URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
//
//			    // Create the email message
//			    ImageHtmlEmail email = new ImageHtmlEmail();
//			    email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			    email.setHostName("smtp.googlemail.com");
//			    email.setSmtpPort(465);
//			    email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
//			    email.setSSLOnConnect(true);
//			    email.setFrom("pavanoltraining@gmail.com"); // Sender
//			    email.setSubject("Test Results");	
//			    email.setMsg("Please find Attached Report....");
//			    email.addTo("pavankumar.busyqa@gmail.com"); // Receiver
//			    email.attach(url, "extent report", "please check report...");
//			    email.send(); // send the email
//			} catch (Exception e) {
//			    e.printStackTrace();
//			}
		  
		  
		  
		  
		  }


}
