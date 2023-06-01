package org.example.pom;

import org.example.base.StartClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends LoadableComponent<HomePage> {
    WebDriver driver;

    @FindBy(xpath = "//a[contains(@href,'/order/capsules/') and @class='AccessibleLink HeaderNavigationBarDropdown__medium-link']")
    WebElement btn_order;

    @FindBy(xpath = "//a[contains(@href,'/order/capsules/') and contains(@class,'AccessibleLink')]")
    WebElement link_order;
    @FindBy(xpath = "//button[contains(@id,'accept')]")
    WebElement accept_button;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get(StartClass.getPropertiesConfig().getProperty("testurl"));
        PageFactory.initElements(driver,this);

    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue("Not on the issue entry page: " + url, url.endsWith("www.nespresso.com/fr/en"));
    }

    public void clickOnAcceptButton(){
        accept_button.click();
    }
    public void hoverOnOrderCoffee(){
        moveToElement(link_order);
    }

    public void clickOnOrder(){
        btn_order.click();
    }

    public void moveToElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }
}
