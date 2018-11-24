package com.project.cucumber.utils;

import com.thoughtworks.selenium.webdriven.JavascriptLibrary;
import cucumber.api.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TestDriver {

    private static TestDriver testDriver = null;
    private static WebDriver driver;
    private static final boolean remoteExecution = false;
    private int waitTimeForObjectToLoad;
    private int waitTimeForPageToLoad;
    private static final Logger LOGGER = LogManager.getLogger(TestDriver.class);
    private static final JavascriptLibrary JAVASCRIPT_LIBRARY = new JavascriptLibrary();

    private TestDriver() {
        driver = createDriver();
        //driver.manage().window().maximize();    // breaking here, need to check and uncomment after fixing it
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setTimeOut(int pageTimeOut) {
        waitTimeForPageToLoad = pageTimeOut;
    }

    public static synchronized TestDriver getInstance() {
        if (testDriver == null) {
            testDriver = new TestDriver();
        }
        return testDriver;
    }

    public void clearLocalStorage() {
        LOGGER.info("Clearing local storage");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format("window.localStorage.clear();"));
        sleep(1);
    }

    public void clearCookies() {
        LOGGER.info("Clearing Cookies");
        driver.manage().deleteAllCookies();
        sleep(1);
    }

    public void listCookies() {
        LOGGER.info("Getting all Cookies ");
        LOGGER.info("====================");
        for (Cookie cookie : driver.manage().getCookies()) {
            LOGGER.info(cookie.toString());
        }
        LOGGER.info("====================");
    }

    public void setWindowSize(Dimension dimension) {
        driver.manage().window().setSize(dimension);
    }

    public void quit() {
        driver.quit();
        testDriver = null;
        driver = null;
    }

    public void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void click(By element) {
        waitForTheElementToLoad(element);
        driver.findElement(element).click();
    }

    public void get(String url) {
        LOGGER.info("Loading the URL: " + url);
        driver.get(url);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public boolean isSelected(By element) {
        waitForTheElementToPresent(element);
        return driver.findElement(element).isSelected();
    }

    public void selectElement(By element, int index) {
        Select select = new Select(driver.findElement(element));
        select.selectByIndex(index);
    }

    public void navigateTo(By element) {
        waitForThePageToLoad(element);
        driver.findElement(element).click();
        sleep(1);
        waitForAjax();
    }

    public void clickFromTheList(By elements, int index) {
        waitForAllTheElementToLoad(elements);
        driver.findElements(elements).get(index).click();
    }

    public WebElement pickFromTheList(By elements, int index) {  //todo: throw exception when element not found
        if (driver.findElements(elements).size() > 0)
            return driver.findElements(elements).get(index);
        return null;
    }

    public WebElement findElement(By element) {
        return driver.findElement(element);
    }

    public String getText(By element) {
        waitForTheElementToLoad(element);
        return driver.findElement(element).getText();
    }

    public void submit(By element) {
        waitForTheElementToLoad(element);
        driver.findElement(element).submit();
    }

    public List<String> getAllText(By element) {
        waitForAllTheElementToLoad(element);
        return driver.findElements(element).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getAttribute(By element, String attributeName) {
        waitForTheElementToPresent(element);
        return driver.findElement(element).getAttribute(attributeName);
    }

    public void setText(By element, String value) {
        waitForTheElementToLoad(element);
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void tabOutOfElement(By element) {
        waitForTheElementToLoad(element);
        final WebElement webElement = driver.findElement(element);
        JAVASCRIPT_LIBRARY.callEmbeddedSelenium(driver, "triggerEvent", webElement, "blur");
    }

    public void focusOnElement(By element) {
        waitForTheElementToLoad(element);
        final WebElement webElement = driver.findElement(element);
        JAVASCRIPT_LIBRARY.callEmbeddedSelenium(driver, "triggerEvent", webElement, "focus");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isElementVisible(By element) {
        try {
            (new WebDriverWait(driver, waitTimeForObjectToLoad)).until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        } catch (Exception ex) {
            LOGGER.info(ex.fillInStackTrace());
            return false;
        }
    }

    public boolean isTextVisible(By element, String expectedText) {
        try {
            new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(element));
            driver.findElement(element).getText().equals(expectedText);
            return true;
        } catch (Exception ex) {
            LOGGER.info(ex.fillInStackTrace());
            return false;
        }
    }

    public boolean isElementDisabled(By element) {
        String[] listOfClasses = getAttribute(element, "class").split(" ");
        return Arrays.asList(listOfClasses).contains("disabled") ||
                Arrays.asList(listOfClasses).contains("disabled-button");
    }

    public boolean isElementEnabled(By element) {
        if (isElementVisible(element)) {
            try {
                (new WebDriverWait(driver, waitTimeForObjectToLoad)).until(ExpectedConditions.elementToBeClickable(element));
                return true;
            } catch (Exception ex) {
                LOGGER.info(ex.fillInStackTrace());
                return false;
            }
        }
        return false;
    }

    public boolean isElementNotPresent(By element) {
        return driver.findElements(element).size() == 0;
    }

    public boolean isDisplayed(By element) {
        return driver.findElement(element).isDisplayed();
    }

    public int getCount(By element) {
        waitForTheElementToLoad(element);
        return driver.findElements(element).size();
    }

    public HashMap<String, String> createMapFromDLElements(By tableIdentifier) {
        waitForTheElementToLoad(tableIdentifier);
        WebElement dl = driver.findElement(tableIdentifier);
        HashMap<String, String> map = new HashMap<>();

        List<WebElement> dt = dl.findElements(By.cssSelector("dt"));
        List<WebElement> dd = dl.findElements(By.cssSelector("dd"));
        for (WebElement element : dt) {
            int indexOf = dt.indexOf(element);
            String dtValue = element.getText();
            String ddValue = dd.get(indexOf).getText();
            map.put(dtValue, ddValue);
        }
        return map;
    }

    public void waitForTheElementToGoInvisible(By element) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void sleep(int timeOutInSeconds) {
        try {
            Thread.sleep(timeOutInSeconds * 1000);
        } catch (InterruptedException e) {
            LOGGER.info(e.fillInStackTrace());
        }
    }

    private static WebDriver createDriver() {
        String browser = TestConfiguration.getBrowser();
        return getDriver(browser);
    }

    private void waitForTheElementToLoad(By element) {
        new WebDriverWait(driver, waitTimeForObjectToLoad).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    private void waitForAllTheElementToLoad(By element) {
        new WebDriverWait(driver, waitTimeForObjectToLoad).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    private void waitForThePageToLoad(By element) {
        new WebDriverWait(driver, waitTimeForPageToLoad).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    private void waitForTheElementToPresent(By element) {
        new WebDriverWait(driver, waitTimeForObjectToLoad).until(ExpectedConditions.presenceOfElementLocated(element));
    }

    private void waitForAjax() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (true) {
            Long noOfCalls = (Long) js.executeScript("return angular.element(document.body).injector().get('$http').pendingRequests.length");
            if (noOfCalls == 0) {
                break;
            }
        }
    }

    private static DesiredCapabilities getDesiredCapabilities(String browser) {
        DesiredCapabilities capabilities;
        switch (browser.toLowerCase()) {
            case "ie":
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            case "safari":
                capabilities = DesiredCapabilities.safari();
                break;
            case "firefox":
            default:
                capabilities = DesiredCapabilities.firefox();
        }
        return capabilities;
    }

    private static WebDriver getDriver(String browser) {
        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "ie":
                webDriver = new InternetExplorerDriver();
                break;
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "safari":
                webDriver = new SafariDriver();
                break;
            case "firefox":
            default:
                webDriver = new FirefoxDriver();
        }
        return webDriver;
    }
}
