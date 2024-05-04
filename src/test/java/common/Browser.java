package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Browser {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public static void open(String browser){
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless=new");
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }else {
            throw new IllegalArgumentException("Unexpected value" + browser);
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        actions = new Actions(driver);
    }
    public static void closeBrowser() {
        driver.quit();
    }

    public static void visit(String url) {
        driver.get(url);
    }

    public static void click(By locator) {
        driver.findElement(locator).click();
    }

    public static void fill(By locator, String withText) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(withText);
    }

    public static String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public static boolean isDisplayed(By locator){
       return driver.findElements(locator).size() > 0;
    }

    public static void hover(By locator){
        actions.moveToElement(driver.findElement(locator)).perform();
    }
    public static WebElement getElement(By locator){
        return driver.findElement(locator);
    }
    public static void doubleClick(By locator){
        actions.doubleClick(driver.findElement(locator)).perform();
    }
    public static void excuteScript(String scripts, Object ... arguments){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(scripts,arguments);
    }
}


