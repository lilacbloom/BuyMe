package Page;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BuyMeSignUpLogin extends BasePage
{

    public BuyMeSignUpLogin(WebDriver driver, ExtentTest test)
    {
        this.driver = driver;
        this.test = test;
        this.screenShotPath = "D:\\\\screenshot_signup.jpg";

    }

//Finds the Sign up button and clicks it
    public void pressSignUp()
    {

        try {
            driver.findElement(By.className("text-btn")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            fail("Failed to sign up\n" + e.getMessage());
        }

    }

}
