package Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class testBase {

    static WebDriver driver = null;
    public String baseUrl = "https://www.saucedemo.com/";

    @BeforeTest
    @Parameters("browser")
    public void browserSetup(String browser) throws Exception {
        try
        {
            if(browser.equalsIgnoreCase("chrome"))
            {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            else if(browser.equalsIgnoreCase("safari"))
            {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
            }
            else if(browser.equalsIgnoreCase("Microsoft edge"))
            {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            else if(browser.equalsIgnoreCase("firefox"))
            {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
        } catch (Exception e) {
            throw new Exception("Incorrect browser");
        }
    }
    @BeforeSuite
    public void init()
    {
        try
        {
            driver.get(baseUrl);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            //Verify URL
            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://www.saucedemo.com/";
            Assert.assertEquals(actualUrl,expectedUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Close browser
    @AfterSuite
    public void closeBrowser()
    {
        driver.quit();
    }
}