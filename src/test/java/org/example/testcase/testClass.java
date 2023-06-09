package org.example.testcase;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class testClass
{
    @ParameterizedTest
    @MethodSource("org.example.utilities.ReadXLSData#getData")
    public void CheckCapsulesQuantityTest(String productName,String Quantity) throws InterruptedException {
        System.out.println(productName);
        System.out.println(Quantity);
    }
}
