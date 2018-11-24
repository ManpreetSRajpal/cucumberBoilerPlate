package com.project.cucumber.pages;

import com.project.cucumber.utils.MessageLoader;
import org.openqa.selenium.By;

import static com.project.cucumber.utils.TestConfiguration.getLoginURL;

public class LoginPage extends BasePage {
    private By email = By.id("email");
    private By password = By.id("pass");
    private By signUpButton = By.id("loginbutton");
    private By accessRestrictedMessage = By.id("error_box");


    public void visitGitHub() {
        MessageLoader.setMessages("english");
        get(getLoginURL());
    }

    public void login(String email, String password) {
        loggingIn(email, password);
    }

    private void loggingIn(String emailId, String psd) {

        if (isElementVisible(email) && isElementVisible(password)) {
            setText(email, emailId);
            setText(this.password, psd);
        }
        click(signUpButton);
    }

    public boolean isAccessRestrictedMessageVisible() {
        return isElementVisible(accessRestrictedMessage);
    }

}
