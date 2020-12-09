package Page;

import Main.Constants;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuyMePickPrice extends BasePage
{
    public BuyMePickPrice(WebDriver driver, ExtentTest test)
    {
        this.driver = driver;
        this.test = test;
        this.screenShotPath = "D:\\\\screenshot_pickprice.jpg";
    }



    //Finds the price field and enters price
    public void choosePrice()
    {
        try {
            driver.findElement(By.className("input-cash")).sendKeys(Constants.Price);

        }
        catch (Exception e)
        {
            fail("Failed to choose price\n" + e.getMessage());
        }
    }

    //Presses the submit price button
    public void submitPrice()
    {

        try {
            driver.findElement(By.cssSelector("button[type='submit']")).click();

        }
        catch (Exception e)
        {
            fail("Failed to submit price\n" + e.getMessage());
        }
    }

}
