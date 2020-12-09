package Page;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class BuyMeBusiness extends BasePage
{
    public BuyMeBusiness(WebDriver driver, ExtentTest test)
    {
        this.driver = driver;
        this.test = test;
        this.screenShotPath = "D:\\\\screenshot_business.jpg";

    }
    private WebDriver driver;

    //Compares Url
    public void assertUrl()
    {
        String url = "https://buyme.co.il/search?budget=2&category=5&region=9";

        try
        {
            Thread.sleep(3000);


            Assert.assertEquals(driver.getCurrentUrl(), url);
            System.out.println(driver.getCurrentUrl());

        }
        catch(Exception e)
        {
            fail("Failed to assert url\n" + e.getMessage());
        }

    }

    //Chooses a business thumbnail and clicks it
    public void pickBusiness()
    {

        try
        {

            List<WebElement> list = driver.findElements(By.className("thumbnail"));
            list.get(1).click();

        }
        catch(Exception e)
        {
            fail("Failed to pick business\n" + e.getMessage());
        }

    }





}
