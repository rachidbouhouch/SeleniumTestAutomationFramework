package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class StartClass {
    protected static WebDriver driver;
    private static final Properties propertiesConfig=new Properties();

    private static final Properties propertiesLocators=new Properties();

    private static final Properties propertiesData=new Properties();
    public static WebDriver getSession() {
        return driver;
    }

    public static Properties getPropertiesConfig() {
        return propertiesConfig;
    }

    public static Properties getPropertiesLocators() {
        return propertiesLocators;
    }
    public static Properties getPropertiesData() {
        return propertiesData;
    }

    @BeforeAll
    public static void setUp() throws IOException {
        if(driver==null){
            FileReader fileReaderConfig = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\config.properties");
            FileReader fileReaderLocators = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\locators.properties");
            FileReader fileReaderData = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\data.properties");
            propertiesData.load(fileReaderData);
            propertiesConfig.load(fileReaderConfig);
            propertiesLocators.load(fileReaderLocators);
        }
        if(propertiesConfig.getProperty("browser").equalsIgnoreCase("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(propertiesConfig.getProperty("status.accessdenied"));
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        }
        else if(propertiesConfig.getProperty("browser").equalsIgnoreCase("edge")){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(propertiesConfig.getProperty("status.accessdenied"));
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
        }
        driver.manage().window().maximize();
    }



}
