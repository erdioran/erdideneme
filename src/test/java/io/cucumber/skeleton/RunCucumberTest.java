package io.cucumber.skeleton;


import base.AutomationMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.Scenario;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import supplementler.base.DriverManager;
import supplementler.utils.ConfigManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;


@CucumberOptions(
        publish = true,
        features = {"src/test/resources/io/cucumber/features/web_case.feature", "src/test/resources/io/cucumber/features/admin_case.feature", "src/test/resources/io/cucumber/features/mweb_case.feature", "src/test/resources/io/cucumber/features/mvitaminler.feature",},
        glue = "io.cucumber.skeleton",
        tags = "@erdi",
        monochrome = true,
        plugin = {"summary", "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failedrerun.txt"}
)

public class RunCucumberTest extends AbstractTestNGCucumberTests {
    private static final Logger LOGGER = LogManager.getLogger(RunCucumberTest.class);
    @BeforeMethod
    public void startBrowser(ITestResult result, Method method) throws MalformedURLException {

        DriverManager.launchBrowser(ConfigManager.getBrowser());
    }

    @AfterMethod
    public void closeBrowser(ITestResult result) {
        if (!result.isSuccess()) {
            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(AutomationMethods.getBase64Screenshot()).build());
        }
        DriverManager.quitDriver();
    }


    @BeforeTest(alwaysRun = true)
    public void beforeTestReport() {

    }


    @AfterTest(alwaysRun = true)
    public void afterTestReport() {


    }


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        DriverManager.quitDriver();

    }


}
