package Page;

import Main.Constants;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class BuyMeSenderReceiver extends BasePage
{
    public BuyMeSenderReceiver(WebDriver driver, ExtentTest test)
    {
        this.driver = driver;
        this.test = test;
        this.screenShotPath = "D:\\\\screenshot_senderreceiver.jpg";
        fieldAreaList = driver.findElements(By.className("mx2"));
    }
    private List <WebElement> fieldAreaList;

    //Finds the "someone else" button and clicks it
    public void clickSomeoneElse()
    {
        try {
            driver.findElement(By.cssSelector("label[data='forSomeone']")).click();

        }
        catch (Exception e)
        {
            fail("Failed to click for someone\n" + e.getMessage());
        }
    }

    //Finds the receiver name field and enters receiver's name
    public void setReceiverName()
    {
        try {
            WebElement uiCard =  fieldAreaList.get(0);
            List <WebElement> fieldList = uiCard.findElements(By.tagName("input"));
            fieldList.get(0).clear();
            fieldList.get(0).sendKeys(Constants.ReceiverName);

        }
        catch (Exception e)
        {
            fail("Failed to set receiver name\n" + e.getMessage());
        }
    }
    //Finds the sender name field clears it and enters sender's name
    public void setSenderName()
    {

        try {
            WebElement uiCard = fieldAreaList.get(0);
            List <WebElement> fieldList = uiCard.findElements(By.tagName("input"));

            fieldList.get(1).clear();
            fieldList.get(1).sendKeys(Constants.SenderName);

        }
        catch (Exception e)
        {
            fail("Failed to set sender name\n" + e.getMessage());
        }


    }
    //Finds the event drop list and chooses an event
    public void pickEvent()
    {

        try {
            WebElement uiCard = fieldAreaList.get(1);
            uiCard.findElement(By.className("ui-select")).click();



            List<WebElement> eventList = uiCard.findElements(By.tagName("li"));
            eventList.get(2).click();

        }
        catch (Exception e)
        {
            fail("Failed to pick event\n" + e.getMessage());
        }

    }

    //Finds the blessings field, clears it and writes a blessing
    public void writeBlessing()
    {

        try {
            WebElement uiCard = fieldAreaList.get(1);
            uiCard.findElement(By.tagName("textarea")).clear();
            uiCard.findElement(By.tagName("textarea")).sendKeys("Best wishes!");

        }
        catch (Exception e)
        {
            fail("Failed to write blessing\n" + e.getMessage());
        }
    }

    //Finds the picture upload button and uploads a picture
    public void uploadPicture()
    {

        try {
            WebElement uiCard = driver.findElement(By.className("media-fields"));

            // List<WebElement> mediaFieldList = uiCard.findElements(By.tagName("input"));
            //mediaFieldList.get(0).click();

            WebElement mediaInput = uiCard.findElement(By.name("fileUpload"));
            mediaInput.sendKeys("d:\\\\cookie.jpg");

        }
        catch (Exception e)
        {
            fail("Failed to upload picture\n" + e.getMessage());
        }
    }

    //Finds the button "send now" and clicks it
    public void sendNow()
    {

        try {
            driver.findElement(By.className("send-now")).click();

        }
        catch (Exception e)
        {
            fail("Failed to click send now\n" + e.getMessage());
        }
    }

    //Finds the "SMS" button and clicks it
    public void pickSMS()
    {
        try {
            driver.findElement(By.className("icon-sms")).click();

        }
        catch (Exception e)
        {
            fail("Failed to pick sms\n" + e.getMessage());
        }
    }

    //Finds the receiver's phone number field and enters the receiver's phone number
    public void setReceiverTel()
    {
        try {
            driver.findElement(By.id("resendReciver")).sendKeys("0521234567");

        }
        catch (Exception e)
        {
            fail("Failed to set receiver telephone\n" + e.getMessage());
        }
    }

    //Finds the sender's phone number field and enters the sender's phone number
    public void setSenderTel()
    {

        try {
            List<WebElement> telFieldList = driver.findElements(By.cssSelector("input[data-parsley-group='sms']"));
            telFieldList.get(0).sendKeys("0541234567");

        }
        catch (Exception e)
        {
            fail("Failed to set sender telephone\n" + e.getMessage());
        }
    }

    //Finds the "Save" button and clicks it
    public void saveNumbers()
    {
        try {
            driver.findElement(By.className("btn-save")).click();
        }
        catch (Exception e)
        {
            fail("Failed to set save numbers\n" + e.getMessage());
        }
    }

    //Asserts the senders and receivers details
    public void assertNames()
    {


        try {



            WebElement uiCard =  fieldAreaList.get(0);
            List <WebElement> fieldList = uiCard.findElements(By.tagName("input"));


            String nameSender = fieldList.get(1).getAttribute("value");

            String nameReceiver = fieldList.get(0).getAttribute("value");


            Assert.assertEquals(nameSender,Constants.SenderName);
            Assert.assertEquals(nameReceiver,Constants.ReceiverName);

        }
        catch (Exception e)
        {
            fail("Failed to assert names\n" + e.getMessage());
        }
    }









}
