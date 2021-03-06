package Greenkart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.VegetableAndPriceData;
import resources.base;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GetItemPriceTest extends base {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(GetItemPriceTest.class.getName());

    @BeforeTest
    public void startUp() throws IOException {
        driver = initialiseDriver();
        log.info("Stared browser successfully");
        driver.get(prop.getProperty("url"));
        log.info("Navigating to " + prop.getProperty("url"));
    }

    @Test(dataProviderClass = VegetableAndPriceData.class,dataProvider = "vegetable")
    public void getItemPrice(String vegetableName, String vegetablePrice) throws InterruptedException {
        // Example of pagination
        log.info("Validating item price");
        System.out.println();
        List<String> price;
        do {
            //1. Scan the first column
            List<WebElement> listOfElements = driver.findElements(By.xpath("//tr/td[1]"));

            // 2. Get text of item (Vegetable)
            price = listOfElements.stream().filter(s -> s.getText().contains(vegetableName)).map(GetItemPriceTest::getPriceOfVeggie)
                    .collect(Collectors.toList());

            // 3. Get the price of the item
            price.forEach(s -> log.info( vegetableName +" Price: " + s));
            price.forEach(s->Assert.assertTrue(vegetablePrice.equalsIgnoreCase(s),"False: got a different Vegetable item"));
            if (price.size() < 1) {
                Thread.sleep(1500);
                driver.findElement(By.cssSelector("[aria-label='Next']")).click();
            }
        } while (price.size() < 1);

    }

    @AfterTest
    public void tearDown(){
        driver.close();
        log.info("Closed browser successfully");
    }


        // Methods
        private static String getPriceOfVeggie(WebElement s) {
            log.info("Getting the price of the vegetable");
            return s.findElement(By.xpath("following-sibling::td[1]")).getText();

        }
}
