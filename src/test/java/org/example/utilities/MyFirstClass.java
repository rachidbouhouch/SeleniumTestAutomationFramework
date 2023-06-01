package org.example.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MyFirstClass {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("C:\\Users\\rbouhouch\\Desktop\\SeleniumTestAutomationFramework\\src\\test\\resources\\configfiles\\config.properties");
        Properties properties= new Properties();
        properties.load(fr);

        System.out.println(properties.get("testurl"));

    }
}
