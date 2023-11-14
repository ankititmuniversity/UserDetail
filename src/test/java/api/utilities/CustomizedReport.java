package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CustomizedReport implements ITestListener {
	ExtentSparkReporter testReporter;//For UI View of the Report
	ExtentReports reports;// To Set Common Information
	ExtentTest test; //To Create entries in Report

	public void configureReport() {
		String timestamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportname="customReport-"+timestamp+".html";
		testReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//"+reportname);
		reports=new ExtentReports();
		reports.attachReporter(testReporter);

		reports.setSystemInfo("MachineName", "Windows11");
		reports.setSystemInfo("Tester", "AnkitKumar");

		testReporter.config().setDocumentTitle("My Customized Report");
		testReporter.config().setReportName("My First Customized Report");
		testReporter.config().setTheme(Theme.DARK);


	}
	public void onStart(ITestContext result) {
		configureReport();
		System.out.println("On Start Method Callled");
	}
	public void onFinish(ITestContext result) {
		System.out.println("On Finish Method Called");
		reports.flush();
	}
	public void onTestStart(ITestResult Result) {
		System.out.println("Name of the test method started : "+Result.getName());
	}
	public void onTestFailure(ITestResult Result) {
		System.out.println("Name Of Test Method Failed :"+Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name Of the Failed Test Case is :"+Result.getName(),ExtentColor.RED));
	}
	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name Of Test Method Skipped :"+Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name Of the Skipped Test Case is :"+Result.getName(),ExtentColor.YELLOW));	
	}
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name Of Test Method Passed :"+Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name Of the Passed Test Case is :"+Result.getName(),ExtentColor.GREEN));	
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		
	}
}
