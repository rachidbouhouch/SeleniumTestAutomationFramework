package org.sid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class MachinePage extends ProductPage{
    @FindBy(xpath = "//button[@id='ta-product-details__add-to-bag-button']")
    WebElement btn_to_basket;

    public MachinePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnViewDetails(String machineName) throws InterruptedException {
        Thread.sleep(3000);
        WebElement productName = waitForElementToBeClickable("//*[text()='"+machineName+"']//ancestor::article//a[contains(@class,'ProductListElement__details-link--machine')]");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions action=new Actions(driver);
        action.moveToElement(productName).click().build().perform();
    }
    public void clickOnAddToBasket() throws InterruptedException {
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions action=new Actions(driver);
        action.moveToElement(btn_to_basket).click().build().perform();
    }

    public boolean chooseColor(String colorName){
        WebElement colorElement=waitForElement("//a[contains(@href,'machines/original')]/span[@class='element-visually-hidden' and text()='"+colorName+"']");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions action=new Actions(driver);
        action.moveToElement(colorElement).click().build().perform();
        return colorElement != null;
    }


}
