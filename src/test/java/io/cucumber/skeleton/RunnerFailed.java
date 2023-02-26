package io.cucumber.skeleton;


import base.AutomationMethods;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import supplementler.base.DriverManager;
import supplementler.utils.ConfigManager;

import java.lang.reflect.Method;
import java.net.MalformedURLException;


@CucumberOptions(
        features = "@target/failedrerun.txt",
        glue = "io.cucumber.skeleton",
        monochrome = true,
        plugin = {"summary", "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failedrerun.txt"}
)

public class RunnerFailed extends AbstractTestNGCucumberTests {
    private static final Logger LOGGER = LogManager.getLogger(RunnerFailed.class);

    @BeforeMethod
    public void startBrowser(ITestResult result, Method method) throws MalformedURLException {

        DriverManager.launchBrowser(ConfigManager.getBrowser());
    }

    @AfterMethod
    public void closeBrowser(ITestResult result) {
        if (!result.isSuccess() && !ConfigManager.isHeadless()) {
            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(AutomationMethods.getBase64Screenshot()).build());
        }
        DriverManager.quitDriver();
    }


    @BeforeTest(alwaysRun = true)
    public void beforeTestReport() {
    }


    @AfterTest(alwaysRun = true)
    public void afterTestReport() {

/*
        try {
            sendEmail();
        } catch (Exception e) {
            LOGGER.info("The report could not be sent.");
        }


 */


    }


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        DriverManager.quitDriver();

    }

}
