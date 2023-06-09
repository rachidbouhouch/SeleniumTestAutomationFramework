package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utilities.ReadConfigProperties;
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
    private static ReadConfigProperties readConfigProperties;
    public static WebDriver getSession() {
        return driver;
    }
    public static ReadConfigProperties getReadConfigProperties() {
        return readConfigProperties;
    }


    public void setUp() throws IOException {
        if(driver==null){
           readConfigProperties=new ReadConfigProperties("config");
        }
        if(readConfigProperties.getPropertiesConfig().getProperty("browser").equalsIgnoreCase("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(readConfigProperties.getPropertiesConfig().getProperty("status.accessdenied"));
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        }
        else if(readConfigProperties.getPropertiesConfig().getProperty("browser").equalsIgnoreCase("edge")){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(readConfigProperties.getPropertiesConfig().getProperty("status.accessdenied"));
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
        }
        driver.manage().window().maximize();
    }


}
