package org.sid.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sid.utilities.ReadConfigProperties;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class StartClass {

    protected static WebDriver driver;
    private static ReadConfigProperties readConfigProperties;
    private final MutableCapabilities capabilities = new MutableCapabilities();

    public static final String UserName="rachidbouhouch_yAohdv";
    public static final String AutomateKey="fsC61kNxFeqUABvqxLUB";
    public static final String url="https://"+UserName+":"+AutomateKey+"@hub-cloud.browserstack.com/wd/hub";

    private final String nameFile="config";

    public static WebDriver getSession() {
        return driver;
    }
    public static ReadConfigProperties getReadConfigProperties() {
        return readConfigProperties;
    }


    public void setUp() throws IOException {
        if(driver==null){
            readConfigProperties=new ReadConfigProperties(nameFile);
        }
        if(readConfigProperties.getPropertiesConfig().getProperty("browser").equalsIgnoreCase("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(readConfigProperties.getPropertiesConfig().getProperty("status.accessdenied"));
            capabilities.setCapability("browserName", readConfigProperties.getPropertiesConfig().getProperty("browser"));
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            HashMap<String, Object> browserstackOptions = getBrowserStackOptions();
            capabilities.setCapability("bstack:options", browserstackOptions);
            driver=new RemoteWebDriver(new URL(url),capabilities);

        }
        else if(readConfigProperties.getPropertiesConfig().getProperty("browser").equalsIgnoreCase("edge")){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(readConfigProperties.getPropertiesConfig().getProperty("status.accessdenied"));
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
        }
        driver.manage().window().maximize();
    }
    public HashMap<String,Object> getBrowserStackOptions(){
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", readConfigProperties.getPropertiesConfig().getProperty("os"));
        browserstackOptions.put("osVersion", readConfigProperties.getPropertiesConfig().getProperty("osVersion"));
        browserstackOptions.put("browserVersion", readConfigProperties.getPropertiesConfig().getProperty("browserVersion"));
        // browserstackOptions.put("local", "false");
        browserstackOptions.put("seleniumVersion", readConfigProperties.getPropertiesConfig().getProperty("seleniumVersion"));

        return browserstackOptions;
    }

}
