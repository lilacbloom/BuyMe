package Page;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuyMeIntro extends BasePage
{
    public BuyMeIntro(WebDriver driver, ExtentTest test)
    {
        this.driver = driver;
        this.test = test;
        this.screenShotPath = "D:\\\\screenshot_intro.jpg";

    }

    //Finds the Login button and clicks it
   public void pressSignUpLogin()
   {

       int attempts = 0;
       while(attempts < 6) {
           try {
               driver.findElement(By.className("seperator-link")).click();
               return;
           } catch(Exception e) {
               if (attempts == 5)
                   fail("Failed to press sign login button\n" + e.getMessage());
           }
           attempts++;
       }



   }










}
