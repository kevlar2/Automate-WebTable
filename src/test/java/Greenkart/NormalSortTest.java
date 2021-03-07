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
import resources.base;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class NormalSortTest extends base {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(NormalSortTest.class.getName());

    @BeforeTest
    public void startUp() throws IOException {
        driver = initialiseDriver();
        log.info("Stared browser successfully");
        driver.get(prop.getProperty("url"));
        log.info("Navigating to " + prop.getProperty("url"));
    }

    @Test
    public void getTableListSortAndCompare(){
        log.info("Validating normal sort");
        // 1. Click on column sorting
        driver.findElement(By.xpath("//tr/th[1]")).click();

        // 2. Capture all WebElement into list
        List<WebElement> listOfElements = driver.findElements(By.xpath("//tr/td[1]"));

        // 3. Capture text for all WebElement into new (Original) list
        List<String> originalList = listOfElements.stream().map(s -> s.getText()).collect(Collectors.toList());
        log.info("Original list: " + originalList);

        // 4. Sort original list from step 3 -> (sorted list)
        List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
        System.out.println();
        log.info("Sorted list: " + sortedList);

        // 5. Compare original list vs sorted list
        Assert.assertTrue(originalList.equals(sortedList),"The original list does not equal sorted list");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        log.info("Closed browser successfully");
    }


}
