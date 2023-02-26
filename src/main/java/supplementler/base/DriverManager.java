package supplementler.base;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.emulation.Emulation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import supplementler.listener.SeleniumListener;
import supplementler.utils.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);
    private static final InheritableThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return WEB_DRIVER_THREAD_LOCAL.get();
    }

    public static void setDriver(WebDriver driver) {
        WEB_DRIVER_THREAD_LOCAL.set(driver);
    }

    public static void quitDriver() {
        try {
            if (WEB_DRIVER_THREAD_LOCAL.get() != null) {
                WEB_DRIVER_THREAD_LOCAL.get().quit();
            }
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }

    public static void launchBrowser(String browser) throws MalformedURLException {
        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            if (ConfigManager.isHeadless()) {    //headless mode, background tests
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.setHeadless(true);
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
            } else {
                chromeOptions.addArguments("--window-size=1920,1080");
            }
            chromeOptions.addArguments("--disable-gpu");  // for windows headless mode
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("mobile")) {
            WebDriverManager.chromedriver().setup();
            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", 360);
            deviceMetrics.put("height", 740);
            deviceMetrics.put("pixelRatio", 3.0);
            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
            //  mobileEmulation.put("deviceName", "Nexus 5");

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

            driver = new ChromeDriver();
            driver.manage().window().maximize();
            DevTools devTools = ((ChromeDriver) driver).getDevTools();
            devTools.createSession();
            devTools.getDomains().network().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 11.12; rv:68.0) Gecko/20100101 Firefox/67.0");

            devTools.send(Emulation.setDeviceMetricsOverride(360,
                    740,
                    50,
                    false,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()));
        } else {
            throw new IllegalArgumentException(
                    String.format("%s is invalid value. Enter valid browser value in config.properties", browser));
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getIntValue("implicit.wait.time", 0)));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.getIntValue("page.load.time.out", 60)));
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new SeleniumListener());
        DriverManager.setDriver(eventFiringWebDriver);
    }

}
