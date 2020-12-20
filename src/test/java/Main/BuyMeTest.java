package Main;

import Main.DriverSingleton;
import Main.Extra;
import Main.XMLBasedTest;
import Page.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BuyMeTest
{
    private static ExtentReports extent ;
    private static ExtentTest test ;
    private static WebDriver webDriver;

    private static String getXMLData (String keyName) throws Exception
    {
        ClassLoader classLoader = XMLBasedTest.class.getClassLoader();
        String xmlFilePath = String.valueOf(new File(classLoader.getResource("data.xml").getFile()));
        File fXmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();

    }

    public static String getData(String keyName) throws Exception
    {
        String value = getDBData(keyName);
        if (value == null)
        {
          value = getXMLData(keyName);
        }
        return value;
    }


    public static String getDBData(String keyName)  throws Exception
    {
        try
        {
            Connection con = DBConnectionSingleton.getConnectionInstance();
            String statementToExecute = "SELECT * FROM " + Constants.databaseName + ".config WHERE config_name = '" + keyName + "';";
            System.out.println("Read config: " + statementToExecute);

            ResultSet rs = con.createStatement().executeQuery(statementToExecute);
            rs.next();
            String value = rs.getString("config_data");
            System.out.println("value: " + value);

            return value;
        }
        catch (Exception e)
        {
            System.out.println("Failed to read " + keyName + " from datbase");
        }

        return null;
    }



    public static void saveResults (int testId)
    {
        boolean value = saveResultsDB(testId);
        //if ( value == false)
        //{
           saveResultsText(testId);
        //}

    }


    public static void saveResultsText(int testId)
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        String logText = "Test "+testId+" finished on date: "+strDate;

        try
        {
            FileWriter fw = new FileWriter("D:\\\\buyme_test_results.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n" + logText);
            bw.close();
        }
        catch (IOException e)
        {

        }
    }


    public static boolean saveResultsDB (int testId)
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        Connection con = null;

        try
        {
            con = DBConnectionSingleton.getConnectionInstance();
        }
        catch (Exception e)
        {
            return false;
        }

        try
        {
            String statementToExecute = "INSERT INTO " + Constants.databaseName + ".results (test_Id, test_date) VALUES ( " + testId + ", '" + strDate +"');";
            System.out.println("Insert result: " + statementToExecute);
            con.createStatement().execute(statementToExecute);

            return true;
        }
        catch (Exception e)
        {
            System.out.println("exception after insert");
        }

        try
        {
            //String statementToExecute = "INSERT INTO " + Constants.databaseName + ".results (test_Id, test_date) VALUES ( " + testId + ", '" + strDate +"');";
            String statementToExecute = "UPDATE " + Constants.databaseName + ".results SET test_date='" + strDate +"' WHERE test_id="+testId+" ;";
            System.out.println("Insert result: " + statementToExecute);
            con.createStatement().execute(statementToExecute);

            return true;
        }
        catch (Exception e)
        {
            System.out.println("exception after update");
        }
        return false;

    }



    @BeforeClass
    public static void openDriver() throws Exception
    {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(  "d:\\\\extent.html");
        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("Main.BuyMeTest", "BuyMe sanity test");
        // add custom system info
        extent.setSystemInfo("Tester", "Lilac");
        // log results
        test.log(Status.INFO, "@Before class");

        String browserType = getData("BROWSER");

        webDriver = DriverSingleton.getDriverInstance(browserType);

        if (webDriver != null)
            test.log(Status.PASS, "@Before class");
        else
            test.log(Status.FAIL, "@Before class - webDriver not initialized");

    }


    @Test (testName = "openWebsiteTest", priority = 1)
    public void openWebsiteTest()
    {
        if (webDriver == null)
        {
            test.log(Status.FAIL, "webDriver not initialized");
            return;
        }


        String url = "";
        try
        {
             url = getData("URL");
        }
        catch (Exception e)
        {

        }

        webDriver.get(url);
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


        test.log(Status.PASS, "Test passed - open website");
        saveResults(1);
    }



    @Test (testName = "extraAssertErrMsgTest",priority = 2)
    public void extraAssertErrMsgTest()
    {
        if (webDriver == null)
        {
            test.log(Status.FAIL, "webDriver not initialized");
            return;
        }
        Extra extra = new Extra(webDriver);
        BuyMeIntro intro = new BuyMeIntro(webDriver, test);
        intro.pressSignUpLogin();
        extra.loginBtn();
        extra.assertErrorMsg();
        extra.pressCloseButton();
        test.log(Status.PASS, "Test passed - assert error messages");
        saveResults(2);
    }

    @Test (testName = "signupLoginButtonTest", priority = 3)
    public void signupLoginButtonTest()
    {
        if (webDriver == null)
        {
            test.log(Status.FAIL, "webDriver not initialized");
            return;
        }

        BuyMeIntro intro = new BuyMeIntro(webDriver, test);
        intro.pressSignUpLogin();


        test.log(Status.PASS, "Test passed - signup/login button");
        saveResults(3);
    }


    @Test (testName = "SignUpButtonTest",priority = 4)
    public void SignUpButtonTest()
    {
        if (webDriver == null)
        {
            test.log(Status.FAIL, "webDriver not initialized");
            return;
        }

        BuyMeSignUpLogin signUpLogin = new BuyMeSignUpLogin (webDriver, test);
        signUpLogin.pressSignUp();

        test.log(Status.PASS, "Test passed - signup button");
        saveResults(4);
    }



    @Test (testName = "RegistrationTest", priority = 5)
    public void RegistrationTest()
    {
        if (webDriver == null)
        {
            test.log(Status.FAIL, "webDriver not initialized");
            return;
        }

        BuyMeRegistration registration = new BuyMeRegistration (webDriver,test);
        registration.setFirstName();
        registration.setEmail();
        registration.setPassword();
        registration.confirmPassword();
        registration.assertInfo();
        registration.pressSignUp();
        test.log(Status.PASS, "Test passed - registration");
        saveResults(5);

    }


    @Test (testName = "HomeScreenSearchTest",priority = 6)
    public void HomeScreenSearchTest()
    {
        if (webDriver == null)
        {
            test.log(Status.FAIL, "webDriver not initialized");
            return;
        }

        BuyMeHome home = new BuyMeHome (webDriver, test);
        home.pickPrice();
        home.pickRegion();
        home.pickCategory();



        home.pressFindMeGift();
        test.log(Status.PASS, "Test passed - home screen search");
        saveResults(6);
    }



    @Test (testName = "chooseBusinessTest",priority = 7)
    public void chooseBusinessTest()
    {
        if (webDriver == null)
        {
            test.log(Status.FAIL, "webDriver not initialized");
            return;
        }
      BuyMeBusiness business = new BuyMeBusiness (webDriver, test) ;
      business.assertUrl();
      business.pickBusiness();

      test.log(Status.PASS, "Test passed - pick business");
        saveResults(7);

    }



    @Test  (testName = "pickPriceTest",priority = 8)
    public void pickPriceTest()
    {
        if (webDriver == null)
        {
            test.log(Status.FAIL, "webDriver not initialized");
            return;
        }
        BuyMePickPrice price = new BuyMePickPrice(webDriver, test);
        price.choosePrice();
        price.submitPrice();
        test.log(Status.PASS, "Test passed - choose and submit price");
        saveResults(8);
    }


    @Test (testName = "senderReceiverDetailsTest",priority = 9)
    public void senderReceiverDetailsTest()
    {
        if (webDriver == null)
        {
            test.log(Status.FAIL, "webDriver not initialized");
            return;
        }
        BuyMeSenderReceiver senderReceiver = new BuyMeSenderReceiver(webDriver, test);
        senderReceiver.clickSomeoneElse();
        senderReceiver.setReceiverName();
        senderReceiver.setSenderName();
        senderReceiver.pickEvent();
        senderReceiver.writeBlessing();
        senderReceiver.uploadPicture();
        senderReceiver.sendNow();
        senderReceiver.pickSMS();
        senderReceiver.setReceiverTel();
        senderReceiver.setSenderTel();
        senderReceiver.assertNames();
        senderReceiver.saveNumbers();
        test.log(Status.PASS, "Test passed - sender receiver enter details");
        saveResults(9);



    }



    @AfterClass
    public static void afterClass()
    {
        if (webDriver != null)
        {
            webDriver.quit();
        }
        test.log(Status.INFO, "@After class");
        extent.flush();
    }


}
