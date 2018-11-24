package com.project.cucumber.stepdefs;

import com.project.cucumber.pages.LoginPage;
import cucumber.api.java.en.Given;

public class UserProfileSteps {

    private final LoginPage loginPage;

    public UserProfileSteps() {
        loginPage = new LoginPage();
    }

    @Given("^the user launches github login page$")
    public void userIsOnHomePageAs() throws Throwable {
        loginPage.visitGitHub();
    }

}
