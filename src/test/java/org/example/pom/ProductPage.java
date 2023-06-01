package org.example.pom;

import org.example.base.StartClass;
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

public class ProductPage {

    WebDriver driver;
    private static int int_random;

    @FindBy(xpath = "//article[@class='ProductCard']/a/footer/h3")
    List<WebElement> productsName;

    @FindBy(xpath = "//div/button[contains(@class,'AddToBagButton')]")
    List<WebElement> elementsButton;

    @FindBy(xpath = "//button[@class='PredefinedQuantityList__quantity-button']/span[contains(@class,'notranslate')]")
    List<WebElement> capsulesQuantityList;

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

    public void chooseRandomCapsule(){
        Random rand = new Random();
        int_random=rand.nextInt(productsName.size());
        WebElement productName=productsName.get(int_random);
        System.out.println(productName.getText());
    }


    public void clickOnAddToBagButton() throws InterruptedException {
        //BaseClass.getSession().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //JavascriptExecutor js=(JavascriptExecutor)BaseClass.getSession();

        scrollSmooth();
        WebElement elementButton=elementsButton.get(int_random);
        //Display
        System.out.println(elementsButton.size());

        Actions action = new Actions(driver);
        action.moveToElement(elementButton).click().build().perform();

        //elementButton.click();
    }
    public void checkCapsulesQuantityAndAddToCart() throws InterruptedException{
        int capsules_quantity=Integer.parseInt(StartClass.getPropertiesData().getProperty("capsules_quantity"));

        WebElement result=capsulesQuantityList.stream().filter((e)-> Integer.parseInt(e.getText())==capsules_quantity).findFirst().orElse(null);
        //System.out.println(result);
        if(result!=null){
            System.out.println("Cas 1 : exist in my list");
            result.click();

        }
        else {
            //Display
            System.out.println(StartClass.getPropertiesData().getProperty("capsules_quantity"));

            //WebElement inputField = new WebDriverWait(BaseClass.getSession(), Duration.ofSeconds(10)).until(driver -> driver.findElement(By.xpath("//input[contains(@id,'ta-quantity-selector__custom-field')]")));
            inputField.sendKeys(StartClass.getPropertiesData().getProperty("capsules_quantity"));
            Actions action = new Actions(driver);
            moveToElement(button_ok,action);

            if(capsules_quantity%10!=0)  {
                System.out.println("Cas 2 : different multiple 10");
                moveToElement(button_ok,action);
                //System.out.println(inputField.getText());
                //click on button again
            }
        }

        Thread.sleep(5000);

    }

    public boolean checkCapsulesQuantityFromCart() throws InterruptedException {
        Thread.sleep(5000);

        Actions action = new Actions(driver);
        action.moveToElement(btn_basket).click().build().perform();
        waitFor(quantity_on_basket);
        //display
        System.out.println(quantity_on_basket.getText());
        if(quantity_on_basket.getText().contains(capsules_quantity.getText())){
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

    public void moveToElement(WebElement element,Actions action){
        action.moveToElement(element).perform();
    }

    public void scrollSmooth(){
        for(int i=0;i<2000;i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,8)", "");
        }
    }

}
