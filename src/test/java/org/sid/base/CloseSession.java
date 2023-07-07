package org.sid.base;

import io.cucumber.java.After;

public class CloseSession {

    @After
    public void close() throws InterruptedException {
        StartClassCloud.getSession().close();
    }
}
