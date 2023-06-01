package org.example.base;

import org.junit.jupiter.api.AfterAll;

public class CloseSession {

    @AfterAll
    public static void close() throws InterruptedException {
        Thread.sleep(5000);
        StartClass.getSession().close();
    }
}
