package Page;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class BuyMeHome extends BasePage
{
    private WebElement searchArea;
    private List<WebElement> searchSelections;

    public BuyMeHome(WebDriver driver, ExtentTest test)
    {
        this.driver = driver;
        this.test = test;
        this.screenShotPath = "D:\\\\screenshot_home.jpg";

    }

    //Finds the price range drop list and chooses a price range
    public void pickPrice()
    {
        int attempts = 0;
        while(attempts < 6)
        {
            try
            {
                searchArea = driver.findElement(By.cssSelector("div[class='ember-view header-search-bar home']"));
                searchArea = searchArea.findElement(By.tagName("form"));
                searchSelections = searchArea.findElements(By.className("chosen-container"));
            }
            catch(Exception e)
            {
                if (attempts == 5)
                    fail("Failed to set search options\n" + e.getMessage());
            }
            attempts++;
        }

        attempts = 0;
        while(attempts < 6)
        {
            try
            {
                WebElement priceSelection = searchSelections.get(0);
                priceSelection.findElement(By.className("chosen-single")).click();
                List<WebElement> list = priceSelection.findElements(By.tagName("li"));
                list.get(2).click();
                System.out.println("Selected price");
                return;

            }
            catch(Exception e)
            {
                if (attempts == 5)
                    fail("Failed to set price\n" + e.getMessage());
            }
            attempts++;
        }

    }

    //Finds the regions drop list and chooses a region
    public void pickRegion()
    {
        int attempts = 0;
        while(attempts < 6)
        {
            try
            {
                WebElement regionSelection = searchSelections.get(1);
                regionSelection.findElement(By.className("chosen-single")).click();
                List<WebElement> list = regionSelection.findElements(By.tagName("li"));
                list.get(3).click();
                System.out.println("Selected region");
                return;

            }
            catch(Exception e)
            {
                if (attempts == 5)
                    fail("Failed to set region\n" + e.getMessage());
            }
            attempts++;
        }




    }




    //Finds the category drop list and chooses a category
    public void pickCategory()
   {
       int attempts = 0;
       while(attempts < 6)
       {
           try
           {
               WebElement categorySelection = searchSelections.get(2);
               categorySelection.findElement(By.className("chosen-single")).click();
               List<WebElement> list = categorySelection.findElements(By.tagName("li"));
               list.get(5).click();
               System.out.println("Selected category");
               return;

           }
           catch(Exception e)
           {

               if (attempts == 5)
                   fail("Failed to set category\n" + e.getMessage());
           }
           attempts++;
       }

    }







    //Finds the "find me a gift" button, and clicks it
    public void pressFindMeGift()
    {
        try {
            driver.findElement(By.cssSelector("a[class='ui-btn search ember-view']")).click();
            System.out.println("Clicked search");

        }
        catch (Exception e)
        {
            fail("Failed to click search\n" + e.getMessage());
        }



    }




}

