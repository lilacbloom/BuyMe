package Page;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class BasePage
{
    protected WebDriver driver;
    protected ExtentTest test;
    public String screenShotPath;

    //Sends fail messages to report
    protected void fail (String errorMsg)
    {
        screenShot();
        test.fail(errorMsg,MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());

    }

    //Takes screenshots
    protected void screenShot()
    {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenShotPath);
        try
        {
            FileUtils.copyFile(screenShotFile, destinationFile);
        }
        catch (IOException e)
        {
           System.out.println(e.getMessage());
        }

    }





}
