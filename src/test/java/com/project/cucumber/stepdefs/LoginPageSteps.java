package com.project.cucumber.stepdefs;

import com.itextpdf.text.pdf.codec.Base64;
import com.project.cucumber.pages.LoginPage;
import com.project.cucumber.utils.MessageLoader;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class LoginPageSteps {
    private static final String PASSWORD = "SGFwcHkxMjM=";

    private LoginPage loginPage;

    public LoginPageSteps() {
        this.loginPage = new LoginPage();
    }


    @Given("^the customer login as \"([^\"]*)\"$")
    public void theCustomerLoginAs(String userId) {
        loginPage.visitGitHub();
        loginPage.login(userId, String.valueOf(Base64.decode(PASSWORD)));
        assertEquals(MessageLoader.getMessages().WELCOME_TITLE, loginPage.getTitle());
    }

    @When("^the user tries to login as \"([^\"]*)\"$")
    public void theUserTriesToLoginAs(String userId) {
        loginPage.visitGitHub();
        loginPage.login(userId, String.valueOf(Base64.decode(PASSWORD)));
    }

    @Then("^the user should see access restricted page$")
    public void theUserShouldSeeAccessRestrictedPage() {
        assertTrue("Access restricted message is not visible", loginPage.isAccessRestrictedMessageVisible());
    }

}
