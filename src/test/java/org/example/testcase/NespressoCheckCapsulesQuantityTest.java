package org.example.testcase;

import org.example.base.CloseSession;
import org.example.pom.ProductPage;
import org.junit.jupiter.api.*;

public class NespressoCheckCapsulesQuantityTest extends CloseSession {

    private static ProductPage productPage;
    @BeforeAll
    public static void init(){
        productPage = new ProductPage();
    }

    @Test
    public void CheckCapsulesQuantityTest() throws InterruptedException {
        productPage.chooseRandomCapsule();
        productPage.clickOnAddToBagButton();
        productPage.checkCapsulesQuantityAndAddToCart();
        if(productPage.checkCapsulesQuantityFromCart()){
            System.out.println("YES");
        }else {
            System.out.println("NOO");
        }
    }

}
