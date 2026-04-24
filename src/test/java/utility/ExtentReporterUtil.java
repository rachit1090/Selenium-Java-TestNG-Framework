package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterUtil 
{
		
		
		public static ExtentReports getExtentReport()
		{
			String path ="C:\\Users\\shrut\\neweclipse-workspace\\selenium-java-testng-framework\\reports\\AutomationReport.html";
			ExtentSparkReporter extent = new ExtentSparkReporter(path);
			extent.config().setReportName("Web Report");
			extent.config().setDocumentTitle("Automation Report");
			extent.config().setTheme(Theme.DARK);
			
			ExtentReports reports = new ExtentReports();
			reports.attachReporter(extent);
			reports.setSystemInfo("Tester", "Rachit");
			reports.setSystemInfo("WebApp", "Automation Exercise");
			reports.setSystemInfo("Platform", "Chrome, Edge");
			
			return reports;
		}
}
