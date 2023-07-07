package org.sid.stepsdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.sid.base.StartClassCloud;
import org.sid.pages.CapsulePage;
import org.sid.pages.HomePage;
import org.sid.pages.MachinePage;
import org.sid.pages.ProductPage;

import java.io.IOException;

public class AddProductsToCart {
    protected static ProductPage capsulePage;
    protected static ProductPage machinePage;
    protected static HomePage homePage;

    protected static ProductPage productPage;
    protected static final StartClassCloud startClass=new StartClassCloud();

    @Before
    public void init() throws IOException {
        startClass.setUp();
    }
    @Given("I'm on the page home")
    public void goToHomePage() throws IOException {
        homePage=new HomePage(StartClassCloud.getSession()).get();
        productPage=new ProductPage(StartClassCloud.getSession());
        homePage.clickOnAcceptCookiesButton();
        Assert.assertTrue(StartClassCloud.getSession().getTitle().contains("Nespresso"));
    }

    @When("I click on the {string} order link")
    public void clickOrderLink(String categoryName) {
        // Code pour cliquer sur le lien d'ordre spécifié
        homePage.hoverOnOrderCoffee(categoryName);
        homePage.clickOnOrder(categoryName);
    }

    @Then("the {string} page is displayed")
    public void thePageIsDisplayed(String categoryName) {
        if(categoryName.equalsIgnoreCase("capsules")){
            capsulePage=new CapsulePage(StartClassCloud.getSession());
        }
        else {
            machinePage=new MachinePage(StartClassCloud.getSession());
        }
    }

    @Given("I'm on the {string} page")
    public void imOnTheProductPage(String categoryName) {
        Assert.assertTrue(StartClassCloud.getSession().getTitle().toUpperCase().contains(categoryName.toUpperCase()));
    }

    @When("I search for the {string}")
    public void searchProduct(String productName) throws InterruptedException {
            productPage.chooseProductByName(productName);

    }
    @And("I click on the button add to bag capsule {string}")
    public void clickOnAddToBagButton(String productName) throws InterruptedException {
        ((CapsulePage) capsulePage).clickOnAddToBagButton(productName);
    }

    @Then("the product is displayed")
    public void verifyProductDisplayed() {
        System.out.println("product diplay");
    }


    @When("I specify the quantity as {string} and I add the selected quantity of capsules to my cart")
    public void specifyValidQuantity(String quantity) throws InterruptedException {
        productPage.checkCapsulesQuantityAndAddToCart(Integer.parseInt(quantity));
    }

    @Then("The product named {string} with the quantity {string} have been added to my cart")
    public void theCapsuleNamedWithTheQuantityHaveBeenAddedToMyCart(String capsule,String quantity) throws InterruptedException {
        productPage.clickOnButtonBasket();
        boolean result=productPage.checkCapsulesQuantityFromCart(capsule,quantity);
        Assert.assertTrue(result);
        StartClassCloud.getSession().quit();
    }

    @When("I choose a machine color {string}")
    public void chooseMachineColor(String colorName) {
        Assert.assertTrue(((MachinePage) machinePage).chooseColor(colorName));
    }

    @And("I add the selected quantity as {string} of machine to my cart")
    public void checkCapsulesQuantityAndAddToCart(String quantity) throws InterruptedException {
        ((MachinePage) machinePage).clickOnAddToBasket();
        productPage.checkCapsulesQuantityAndAddToCart(Integer.parseInt(quantity));
        Thread.sleep(2000);

        //System.out.println("jsjsjs");
    }

    @And("I click on view details machine button named {string}")
    public void clickOnViewDetails(String productName) throws InterruptedException {
        ((MachinePage) machinePage).clickOnViewDetails(productName);
    }


}
