package io.cucumber.skeleton;



import org.testng.ITestResult;
import org.testng.annotations.*;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import supplementler.drivers.DriverManager;
import supplementler.utils.ConfigManager;


import java.net.MalformedURLException;



@CucumberOptions(
        publish = true,
        features = {"src/test/resources/io.cucumber.features/android.feature"},
        glue = "io.cucumber.skeleton",
        tags = "@Android",
        monochrome = true,
        plugin = {"summary", "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class RunCucumberTest extends AbstractTestNGCucumberTests {


    @BeforeMethod
    public void startBrowser() throws MalformedURLException {
        DriverManager.getDriver();

    }

    @AfterMethod
    public void closeBrowser(ITestResult result) {
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
        //DriverManager.quitDriver();

    }

}