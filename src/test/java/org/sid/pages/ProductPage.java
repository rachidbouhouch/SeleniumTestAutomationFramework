package org.sid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ProductPage {
    public WebDriver driver;

    @FindBy(xpath = "//button[@id='ta-mini-basket__checkout']")
    WebElement btn_checkout;
    @FindBy(xpath = "//input[contains(@id,'ta-quantity-selector__custom-field')]")
    WebElement inputField;

    @FindBy(xpath = "//button[@id='ta-quantity-selector__custom-ok']")
    WebElement button_ok;
    @FindBy(xpath = "//button[@id='ta-mini-basket__open']")
    WebElement btn_basket;


    public void clickOnButtonBasket() throws InterruptedException {
        Thread.sleep(5000);
        Actions action = new Actions(driver);
        moveAndClickToElement(btn_basket,action);
    }
    public ProductPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void chooseProductByName(String productName) {
        WebElement productToScrollTo = waitForElement("//*[text()='" + productName + "']//ancestor::div");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", productToScrollTo);

    }

    public void checkCapsulesQuantityAndAddToCart(int quantity) throws InterruptedException{
        WebElement result = waitForElement("//button[@class='PredefinedQuantityList__quantity-button']/span[@class='notranslate' and text()='"+quantity+"']");
        System.out.println(result);
        if(result!=null){
            System.out.println("Cas 1 : exist in my list");
            result.click();
        }
        else {
            //Display
            System.out.println(String.valueOf(quantity));
            inputField.sendKeys(String.valueOf(quantity));
            Actions action = new Actions(driver);
            moveAndClickToElement(button_ok,action);

            if(quantity%10!=0)  {
                System.out.println("Cas 2 : different multiple 10");
                //moveAndClickToElement(button_ok,action);
            }
        }

    }
    public boolean checkCapsulesQuantityFromCart(String capsuleName,String quantity) throws InterruptedException {
        // Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement quantity_on_basket = waitForElement("//span[contains(text(),'"+capsuleName+"')]/following-sibling::span/span[@class='MiniBasketItemPriceAndName__price-calc']");
        //display
        //System.out.println(quantity_on_basket.getText());
        boolean tmp=false;
        if(quantity_on_basket.getText().contains(quantity)){
            Actions action = new Actions(driver);
            moveAndClickToElement(btn_checkout,action);
            Thread.sleep(5000);
            tmp=true;
        }
        return tmp;

    }


    public WebElement waitForElement(String elementText) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(5000))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementText)));
        return driver.findElement(By.xpath(elementText));
    }

    public WebElement waitForElementToBeClickable(String elementText){
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(5000))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementText)));
        return driver.findElement(By.xpath(elementText));

    }
    public void moveAndClickToElement(WebElement element,Actions action){
        action.moveToElement(element).click().build().perform();
    }
}
