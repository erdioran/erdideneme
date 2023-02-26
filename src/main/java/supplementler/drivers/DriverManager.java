package supplementler.drivers;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {


    private static final InheritableThreadLocal<AppiumDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();

    private DriverManager() {
    }

    public static AppiumDriver getDriver() {
        return (AppiumDriver) WEB_DRIVER_THREAD_LOCAL.get();
    }

    public static void setDriver(AppiumDriver driver) {
        WEB_DRIVER_THREAD_LOCAL.set(driver);
    }

    public static void quitDriver() {
        try {
            if (WEB_DRIVER_THREAD_LOCAL.get() != null) {
                WEB_DRIVER_THREAD_LOCAL.get().quit();
            }
        } catch (Exception e) {

        }
    }


    public static  String userName = System.getenv("LT_USERNAME") == null ?
            "username" : System.getenv("alperenyilmaz");
   public static String accessKey = System.getenv("LT_ACCESS_KEY") == null ?
            "accessKey" : System.getenv("h0DvGSbb2cTGRmoC5wQTHlA5vDu31wfmZiIYXEXObg2TT22VAA");

    public static String gridURL = "@mobile-hub.lambdatest.com/wd/hub";
    //https://alperenyilmaz:h0DvGSbb2cTGRmoC5wQTHlA5vDu31wfmZiIYXEXObg2TT22VAA@mobile-hub.lambdatest.com/wd/hub

   // AppiumDriver driver;



    public static AppiumDriver launchBrowser(String platform, String device, String version) throws MalformedURLException {
        String userName = System.getenv("LT_USERNAME") == null ?
                "username" : System.getenv("alperenyilmaz");

        String accessKey = System.getenv("LT_ACCESS_KEY") == null ?
                "accessKey" : System.getenv("h0DvGSbb2cTGRmoC5wQTHlA5vDu31wfmZiIYXEXObg2TT22VAA");

        String gridURL = "@mobile-hub.lambdatest.com/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equalsIgnoreCase("android")) {
            capabilities.setCapability("build","Java TestNG Android FitModa");
            capabilities.setCapability("name",platform+" "+device+" "+version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion",version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);

            capabilities.setCapability("app", "lt://APP10160521021676031895508839");
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("console", true);
            capabilities.setCapability("network", true);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            //capabilities.setCapability-AlperenYilmazTestMobile

        }else if (platform.equalsIgnoreCase("ios")) {
            capabilities.setCapability("build","Java TestNG iOS FitModa");
            capabilities.setCapability("name",platform+" "+device+" "+version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion",version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("app", "lt://APP10160521021676031895508839");
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("console", true);
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            //capabilities.setCapability---Alperen.Yilmaz

        }

        String hub = "https://alperenyilmaz:h0DvGSbb2cTGRmoC5wQTHlA5vDu31wfmZiIYXEXObg2TT22VAA@mobile-hub.lambdatest.com/wd/hub";
        return new AppiumDriver(new URL(hub), capabilities);
    }

}
