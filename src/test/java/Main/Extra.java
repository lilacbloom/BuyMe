package Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class Extra
{
    private WebDriver driver;
    public Extra(WebDriver driver)
    {
        this.driver = driver;
    }




    //Finds the Login button and clicks it
    public void loginBtn()
    {
        driver.findElement(By.cssSelector("button[type='submit']")).click();

    }
    //asserts the error messages text
    public void assertErrorMsg()
    {
        List <WebElement> textElement = driver.findElements(By.className("parsley-required"));
        String text = textElement.get(0).getText();
        Assert.assertEquals(text, Constants.ErrorMsgTxt1);
        text = textElement.get(1).getText();
        Assert.assertEquals(text,Constants.ErrorMsgTxt2);

    }

    //Closes the pop up
    public void pressCloseButton()
    {
        WebElement closeElement = driver.findElement(By.className("lightbox-close"));
        closeElement.findElement(By.tagName("img")).click();
    }














}
