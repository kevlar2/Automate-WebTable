package resources;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {

    private WebDriver driver;
    public Properties prop;
    private FileInputStream fips;
    private static Logger log = LogManager.getLogger(base.class.getName());

    public WebDriver initialiseDriver() throws IOException {
        log.info("Initialising driver");

        // Creates the property object
        prop = new Properties();

        // This allows for you to read the file and it requires file path
        fips = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
        prop.load(fips);


        String BrowserName = prop.getProperty("browser");

        switch (BrowserName){

            case "chrome":
                // System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\webdriver\\chromedriver.exe");
                // ChromeOptions browserOptions = new ChromeOptions();
                //browserOptions.setCapability("browserVersion", "89.0");

                // New webdriver manager implemented to manage the drivers used by the this test.
                log.info("Starting chrome driver");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                log.info("Starting firefox driver");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "microsoft edge":
                log.info("Starting microsoft edge driver");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                log.info("Browser driver entered is not valid, please check data.properties file and trying again.");
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;

    }

}
