package com.my.salesforce.sandbox.browsers;

import com.my.salesforce.sandbox.enums.Browsers;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BrowserSupplier {

    private static final Logger logger = LogManager.getLogger(BrowserSupplier.class);

    private BrowserSupplier() {
    }

    private static final EnumMap<Browsers, Supplier<WebDriver>> map = new EnumMap<>(Browsers.class);

    private static final Supplier<WebDriver> chrome = () -> {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--remote-allow-origins=*");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        logger.info("Initializing Chrome browser...");
        return new ChromeDriver(chromeOptions);
    };

    private static final Supplier<WebDriver> firefox = () -> {
        WebDriverManager.firefoxdriver().setup();
        logger.info("Initializing Firefox browser...");
        return new FirefoxDriver();
    };

    private static final Supplier<WebDriver> edge = () -> {
        WebDriverManager.edgedriver().setup();
        logger.info("Initializing Edge browser...");
        return new EdgeDriver();
    };

    private static final Supplier<WebDriver> headlessChrome = () -> {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        logger.info("Initializing Headless Chrome browser...");
        return new ChromeDriver(options);
    };

    private static final Supplier<WebDriver> headlessFirefox = () -> {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        logger.info("Initializing Headless Firefox browser...");
        return new FirefoxDriver(options);
    };

    private static final Supplier<WebDriver> remoteChrome = () -> {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--remote-allow-origins=*");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        try {
            return new RemoteWebDriver
                    (new URL(UserConfig.getProperties().hubProtocol() + "://"
                            + UserConfig.getProperties().hubHost() + ":"
                            + UserConfig.getProperties().hubPort()), chromeOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Please check the Hub url, its malformed");
        }
    };

    private static final Supplier<WebDriver> remoteFirefox = () -> {
        {
            try {
                return new RemoteWebDriver
                        (new URL(UserConfig.getProperties().hubProtocol() + "://"
                                + UserConfig.getProperties().hubHost() + ":"
                                + UserConfig.getProperties().hubPort()), new FirefoxOptions());
            } catch (MalformedURLException e) {
                throw new RuntimeException("Please check the Hub url, its malformed");
            }
        }
    };

    private static final Supplier<WebDriver> remoteEdge = () -> {
        {
            try {
                return new RemoteWebDriver
                        (new URL(UserConfig.getProperties().hubProtocol() + "://"
                                + UserConfig.getProperties().hubHost() + ":"
                                + UserConfig.getProperties().hubPort()), new EdgeOptions());
            } catch (MalformedURLException e) {
                throw new RuntimeException("Please check the Hub url, its malformed");
            }
        }
    };

    static {
        map.put(Browsers.chrome, chrome);
        map.put(Browsers.firefox, firefox);
        map.put(Browsers.edge, edge);
        map.put(Browsers.headlessChrome, headlessChrome);
        map.put(Browsers.headlessFirefox, headlessFirefox);
        map.put(Browsers.remoteChrome, remoteChrome);
        map.put(Browsers.remoteFirefox, remoteFirefox);
        map.put(Browsers.remoteEdge, remoteEdge);
    }

    public static WebDriver launch(Browsers browserType) {
        ThreadLocal<WebDriver> browser = ThreadLocal.withInitial(map.get(browserType));
        logger.info("User has request to launch:" + browserType.toString().toUpperCase());
        return browser.get();
    }
}