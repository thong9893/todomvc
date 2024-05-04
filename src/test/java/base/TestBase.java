package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static common.Browser.driver;

public class TestBase {

    @AfterClass(alwaysRun = true)
    protected void captureScreenShot(ITestResult testResult){
        Instant instant = Instant.ofEpochMilli(testResult.getStartMillis());
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();

        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File destFile= new  File(String.format("target/%s-%s.png", testResult.getName(),localDate));
        try{
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }


}
