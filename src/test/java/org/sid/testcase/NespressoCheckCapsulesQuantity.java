package org.sid.testcase;/*
package org.example.testcase;

import io.github.artsok.RepeatedIfExceptionsTest;
import org.example.base.CloseSession;
import org.example.base.StartClass;
import org.example.pom.HomePage;
import org.example.pom.ProductPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class NespressoCheckCapsulesQuantityTest {

    private static ProductPage productPage;
    private static HomePage homePage;
    private static final StartClass startClass=new StartClass();


    public void init() throws IOException {
        startClass.setUp();
        productPage = new ProductPage();
        homePage=new HomePage(StartClass.getSession()).get();
    }

    @ParameterizedTest
    @MethodSource("org.example.utilities.ReadXLSData#getData")
    public void CheckCapsulesQuantityTest(String productName,String Quantity) throws InterruptedException, IOException {
        init();

        homePage.clickOnAcceptCookiesButton();
        homePage.hoverOnOrderCoffee();
        homePage.clickOnOrder();

        productPage.chooseCapsuleByNameAndClickOnAddToBagButton(productName);
        productPage.checkCapsulesQuantityAndAddToCart(Integer.parseInt(Quantity));
        productPage.clickOnButtonBasket();

        boolean result=productPage.checkCapsulesQuantityFromCart(Quantity);
        Assertions.assertTrue(result);

        close();

    }

    public void close() {
        StartClass.getSession().close();
    }



}
*/
