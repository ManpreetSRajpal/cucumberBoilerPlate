package com.project.cucumber.stepdefs;

import com.project.cucumber.utils.FormFactor;
import com.project.cucumber.utils.TestDriver;
import cucumber.api.java.en.Given;

public class SetupSteps {

    @Given("^the browser is resized to (tiny|mobile|tablet|desktop)$")
    public void theBrowserIsResizedToTiny(String size) {
        FormFactor browserSize = FormFactor.valueOf(size.toUpperCase());
        TestDriver.getInstance().setWindowSize(browserSize.asDimension());
    }
}
