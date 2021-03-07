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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseSortTest extends base {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(ReverseSortTest.class.getName());

    @BeforeTest
    public void startUp() throws IOException {
        driver = initialiseDriver();
        log.info("Stared browser successfully");
        driver.get(prop.getProperty("url"));
        log.info("Navigating to " + prop.getProperty("url"));
    }

    @Test
    public void getReversedTableListAndCompare(){
        log.info("Validating reversed sort");

        /* 1. Click on column sorting (Reversed) */
        driver.findElement(By.xpath("//tr/th[1]")).click();
        driver.findElement(By.xpath("//tr/th[1]")).click();

        /* 2. Capture all WebElement into list */
        List<WebElement>  listOfElements = driver.findElements(By.xpath("//tr/td[1]"));

        /* 3. Capture text for all WebElement into new (Original) list */
        List<String> originalList = listOfElements.stream().map(s -> s.getText()).collect(Collectors.toList());
        System.out.println();
        log.info("Original list: " + originalList);

        /* 4. Reverse original list from step 3 -> Reversed list */
        List<String> reverseSortList=originalList.stream().sorted().collect(Collectors.toList());
        System.out.println();
        log.info("Reverse sort list: " + reverseSortList);
        Collections.reverse(reverseSortList);
        System.out.println();
        log.info("Collections reversed: " + reverseSortList);

        /* 5. Compare original list vs Reversed sorted list */
        Assert.assertTrue(originalList.equals(reverseSortList), "The original list does not equal the reversed sorted list.");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        log.info("Closed browser successfully");
    }

}
