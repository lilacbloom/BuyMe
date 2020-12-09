package Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLBasedTest {
    private static WebDriver driver;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String type = getData("browserType");
        if(type.equals("Chrome")){
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if(type.equals("FF")){
            System.setProperty("webdriver.firefox.driver", "C:\\geckodriver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }

    @Test
    public void openURL() {
        driver.get("https://translate.google.com");
    }

    private static String getData (String keyName) throws Exception
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

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
