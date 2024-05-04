package base;

import common.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static common.Browser.driver;

public class TestBase {


    @AfterMethod(alwaysRun = true)
    protected void captureScreenShot(ITestResult testResult){
        Browser.captureScreenshot(testResult);
    }
    @AfterClass
    void tearDown(){
        Browser.quit();
    }

}
