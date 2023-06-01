package org.example;


import org.example.testcase.NespressoCheckCapsulesQuantityTest;
import org.example.testcase.NespressoClickOnOrderTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Unit test for simple App.
 */
@Suite
@SelectClasses({
        NespressoClickOnOrderTest.class,
        NespressoCheckCapsulesQuantityTest.class
})
public class AppTest 
{
}
