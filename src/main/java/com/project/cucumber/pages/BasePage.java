package com.project.cucumber.pages;

import com.project.cucumber.utils.TestDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class BasePage {

    private final TestDriver testDriver;
    static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    BasePage() {
        this.testDriver = TestDriver.getInstance();
    }

    public String getCurrentURL() {
        return testDriver.getCurrentURL();
    }

    public String getTitle() {
        return testDriver.getTitle();
    }

    void click(By element) {
        testDriver.click(element);
    }

    void get(String url) {
        testDriver.get(url);
    }

    void refresh() {
        testDriver.refresh();
    }

    void selectElement(By element, int index) {
        testDriver.selectElement(element, index);
    }

    boolean isSelected(By element) {
        return testDriver.isSelected(element);
    }

    void navigateTo(By element) {
        testDriver.navigateTo(element);
    }

    void clickFromTheList(By elements, int index) {
        testDriver.clickFromTheList(elements, index);
    }

    void scrollIntoView(By element) {
        executeJS("arguments[0].scrollIntoView(true);", testDriver.findElement(element));
    }

    WebElement pickFromTheList(By elements, int index) {
        return testDriver.pickFromTheList(elements, index);
    }

    String getText(By element) {
        return testDriver.getText(element);
    }

    void selectRadioButtonAs(By element) {
        testDriver.click(element);
    }

    void submit(By element) {
        testDriver.submit(element);
    }

    List<String> getAllText(By element) {
        return testDriver.getAllText(element);
    }

    String getAttribute(By element, String attributeName) {
        return testDriver.getAttribute(element, attributeName);
    }

    void setText(By element, String value) {
        testDriver.setText(element, value);
    }

    void tabOutOfElement(By element) {
        testDriver.tabOutOfElement(element);
    }

    void focusOnElement(By element) {
        testDriver.focusOnElement(element);
    }

    boolean isElementVisible(By element) {
        return testDriver.isElementVisible(element);
    }

    boolean isTextVisible(By element, String expectedText) {
        return testDriver.isTextVisible(element, expectedText);
    }

    boolean isElementNotPresent(By element) {
        return testDriver.isElementNotPresent(element);
    }

    boolean isElementDisabled(By element) {
        return testDriver.isElementDisabled(element);
    }

    boolean isElementEnabled(By element) {
        return testDriver.isElementEnabled(element);
    }

    boolean isDisplayed(By element) {
        return testDriver.isDisplayed(element);
    }

    int getCount(By element) {
        return testDriver.getCount(element);
    }

    HashMap<String, String> createMapFromDLElements(By tableIdentifier) {
        sleep(3);
        return testDriver.createMapFromDLElements(tableIdentifier);
    }

    void waitForTheElementToGoInvisible(By element) {
        testDriver.waitForTheElementToGoInvisible(element);
    }

    void sleep(int timeOutInSeconds) {
        testDriver.sleep(timeOutInSeconds);
    }

    void clearLocalStorage() {
        testDriver.clearLocalStorage();
    }

    JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) testDriver.getDriver();
    }

    void executeJS(String script, Object... variables) {
        getJSExecutor().executeScript(script, variables);
    }
}
