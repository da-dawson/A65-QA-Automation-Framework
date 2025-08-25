package org.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.BaseTest;

public class Hooks extends BaseTest {
    
    @Before
    public void setupTest() {
        setUp(); // This calls the BaseTest's setUp method
    }
    
    @After
    public void teardownTest() {
        tearDown(); // This calls the BaseTest's tearDown method
    }
}
