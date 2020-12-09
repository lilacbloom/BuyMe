package Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton
{
    private static WebDriver driver;

    public static WebDriver getDriverInstance(String browserType)
    {





        if(driver == null)
        {
            if ( browserType.equalsIgnoreCase("chrome"))
            {
                System.setProperty("webdriver.chrome.driver", "D:\\\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            else if (browserType.equalsIgnoreCase("firefox"))
            {
                System.setProperty("webdriver.gecko.driver", "D:\\\\geckodriver.exe");
                driver = new FirefoxDriver();
            }

            else
                driver = new ChromeDriver();
        }

        return driver;
    }
}
