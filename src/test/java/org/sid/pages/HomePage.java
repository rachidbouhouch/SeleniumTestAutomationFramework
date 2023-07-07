package org.sid.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.sid.base.StartClassCloud;

public class HomePage extends LoadableComponent<HomePage> {
    WebDriver driver;

    @FindBy(xpath = "//a[contains(@href,'/order/capsules') and contains(@class,'AccessibleLink HeaderNavigationBar')]")
    WebElement btn_order;

    @FindBy(xpath = "//a[contains(@href,'/order/capsules') and contains(@class,'AccessibleLink')]")
    WebElement link_order;
    @FindBy(xpath = "//button[contains(@id,'accept')]")
    WebElement accept_button;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get(StartClassCloud.getReadConfigProperties().getPropertiesConfig().getProperty("testurl")+ StartClassCloud.getReadConfigProperties().getPropertiesConfig().getProperty("language"));
        PageFactory.initElements(driver,this);

    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.endsWith("nespresso.com/fr/en/"));
    }

    public void clickOnAcceptCookiesButton(){
        accept_button.click();
    }
    public void hoverOnOrderCoffee(String categoryName){
        WebElement link_order=driver.findElement(By.xpath("//a[contains(@href,'/order/"+categoryName+"') and contains(@class,'AccessibleLink')]"));
        moveToElement(link_order);
    }

    public void clickOnOrder(String categoryName){
        WebElement btn_order= driver.findElement(By.xpath("//a[contains(@href,'/order/"+categoryName+"') and contains(@class,'AccessibleLink HeaderNavigationBar')]"));

        btn_order.click();
    }

    public void moveToElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }
}
