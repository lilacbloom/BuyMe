package Page;

import Main.Constants;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class BuyMeRegistration extends BasePage
{


    public BuyMeRegistration(WebDriver driver, ExtentTest test)
    {
        this.driver = driver;
        this.test = test;
        this.screenShotPath = "D:\\\\screenshot_registration.jpg";
    }

    //Finds the first name field and enters first name
    public void setFirstName()
    {
        try {
            List<WebElement> signUpFieldList = driver.findElements(By.className("ember-text-field"));
            signUpFieldList.get(0).sendKeys(Constants.FirstName);
        }
        catch (Exception e)
        {
            fail("Failed to set first name field\n" + e.getMessage());
        }
    }

    //Finds the email field and enters email
    public void setEmail()
    {
        try {
            List<WebElement> signUpFieldList = driver.findElements(By.className("ember-text-field"));
            signUpFieldList.get(1).sendKeys(Constants.Email);
        }
        catch (Exception e)
        {
            fail("Failed to set email field\n" + e.getMessage());
        }
    }

    //Finds the password field and enters password
    public void setPassword()
    {
        try {
            List<WebElement> signUpFieldList = driver.findElements(By.className("ember-text-field"));
            signUpFieldList.get(2).sendKeys(Constants.Password);
        }
        catch (Exception e)
        {
            fail("Failed to set password field\n" + e.getMessage());
        }
    }

    //Finds the confirm password field and enters password
    public void confirmPassword()
    {
        try {
            List<WebElement> signUpFieldList = driver.findElements(By.className("ember-text-field"));
            signUpFieldList.get(3).sendKeys(Constants.Password);
        }
        catch (Exception e)
        {
            fail("Failed to confirm password\n" + e.getMessage());
        }
    }

    //asserts the info in all the fields
    public void assertInfo()
    {

        try {
            List <WebElement> fieldList = driver.findElements(By.className("ember-text-field"));


            String name = fieldList.get(0).getAttribute("value");

            String email = fieldList.get(1).getAttribute("value");
            String password = fieldList.get(2).getAttribute("value");
            String confirmPassword = fieldList.get(3).getAttribute("value");


            Assert.assertEquals(name, Constants.FirstName);
            Assert.assertEquals(email, Constants.Email);
            Assert.assertEquals(password, Constants.Password);
            Assert.assertEquals(confirmPassword, Constants.Password);
        }
        catch (Exception e)
        {
            fail("Failed to assert info:\n" + e.getMessage());
        }
    }

    //finds the Sign up button and clicks it
    public void pressSignUp()
    {

        try {
            driver.findElement(By.cssSelector("button[type=submit]")).click();

        }
        catch (Exception e)
        {
            fail("Failed to press signup: \n" + e.getMessage());
        }
    }



}
