package org.example.pom;

import org.example.base.StartClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProductPage {

    WebDriver driver;

    @FindBy(xpath = "//input[contains(@id,'ta-quantity-selector__custom-field')]")
    WebElement inputField;


    @FindBy(xpath = "//button[@id='ta-quantity-selector__custom-ok']")
    WebElement button_ok;

    @FindBy(xpath = "//button/div[@class='AddToBagButtonSmall__quantity']")
    WebElement capsules_quantity;

    @FindBy(xpath = "//button[@id='ta-mini-basket__open']")
    WebElement btn_basket;

    @FindBy(xpath = "//span[@class='MiniBasketItemPriceAndName__price-calc']")
    WebElement quantity_on_basket;

    @FindBy(xpath = "//button[@id='ta-mini-basket__checkout']")
    WebElement btn_checkout;


    public ProductPage() {
        this.driver = StartClass.getSession();
        PageFactory.initElements(driver,this);

    }

    public void chooseCapsuleByNameAndClickOnAddToBagButton(String capsuleName) throws InterruptedException {
        scrollToBottom();
        WebElement productName = createWebElement("//h3[text()='"+capsuleName+"']/ancestor::article//button[contains(@class,'AddToBagButtonSmall')]");
        Actions action=new Actions(driver);
        action.moveToElement(productName).click().build().perform();
    }


    /*public void clickOnAddToBagButton() throws InterruptedException {
        Actions action=new Actions(driver);
        action.moveToElement(productName).click().build().perform();

    }*/
    public void checkCapsulesQuantityAndAddToCart(int quantity) throws InterruptedException{
        WebElement result = createWebElement("//button[@class='PredefinedQuantityList__quantity-button']/span[@class='notranslate' and text()='"+quantity+"']");
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
                moveAndClickToElement(button_ok,action);
            }
        }

    }
    public void clickOnButtonBasket() throws InterruptedException {
        Thread.sleep(5000);
        Actions action = new Actions(driver);
        moveAndClickToElement(btn_basket,action);
        waitFor(quantity_on_basket);
    }
    public boolean checkCapsulesQuantityFromCart(String quantity) {
        //display
        System.out.println(quantity_on_basket.getText());
        if(quantity_on_basket.getText().contains(quantity)){
            btn_checkout.click();
            return true;
        }
        else {
            return false;
        }

    }

    public void waitFor(WebElement webElement){
        WebDriverWait element = new WebDriverWait(driver,Duration.ofSeconds(10));
        element.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void moveAndClickToElement(WebElement element,Actions action){
        action.moveToElement(element).click().build().perform();
    }

    public WebElement createWebElement(String elementText) {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementText)));
            element = driver.findElement(By.xpath(elementText));
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e);
        }
        return element;
    }

//    public void scrollUntilFoundTheElement(WebElement webElement){
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
//
//    }

    public void scrollToBottom() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long windowHeight = (long) js.executeScript("return window.innerHeight");
        long fullPageHeight = (long) js.executeScript("return document.documentElement.scrollHeight");
        long scrollHeight = 0;

        while (scrollHeight < fullPageHeight) {
            scrollHeight += windowHeight;
            js.executeScript("window.scrollTo(0, " + scrollHeight + ");");
            Thread.sleep(1000);
        }
    }

}
