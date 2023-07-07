package org.sid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CapsulePage extends ProductPage{

    public CapsulePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddToBagButton(String capsuleName) throws InterruptedException {
        WebElement productName = waitForElement("//h3[text()='"+capsuleName+"']/ancestor::article//button[contains(@class,'AddToBagButtonSmall')]");
        Actions action=new Actions(driver);
        action.moveToElement(productName).click().build().perform();
    }



    public void specifyQuantity(String quantity){
        inputField.sendKeys(quantity);
    }

    public String checkCapsulesInvalidQuantity() throws InterruptedException{
        Actions action = new Actions(driver);
        moveAndClickToElement(button_ok,action);
        return waitForElement("//div[@class='CustomQuantityError__error-message']").getText();
    }






//    public void scrollUntilFoundTheElement(WebElement webElement){
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
//
//    }


}
