package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {

    public WebDriver driver;
    public Properties prop;

    public WebDriver initialiseDriver() throws IOException {

        // Creates the property object
        prop = new Properties();

        // This allows for you to read the file and it requires file path
        FileInputStream fips = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
        prop.load(fips);

        // Simple webdriver Set-up
        System.setProperty("wbdriver.chrome.driver", System.getProperty("user.dir") + "//webdrivers//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

}
