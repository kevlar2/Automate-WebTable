package Greenkart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.base;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFilterTest extends base {

    public WebDriver driver;

    @BeforeTest
    public void startUp() throws IOException {
        driver = initialiseDriver();
        driver.get(prop.getProperty("url"));
    }

    @Test(dataProvider = "vegetables")
    public void validateSearchFilter(String vegetableName){
        // 1. Enter search keyword in search bar
        driver.findElement(By.cssSelector("#search-field")).clear();
        driver.findElement(By.cssSelector("#search-field")).sendKeys(vegetableName);

        // 2. Validate item in result page
        List<WebElement> veggies=driver.findElements(By.xpath("//tr/td[1]"));
        List<WebElement> filteredList=veggies.stream().filter(veggie->veggie.getText().contains(vegetableName)).collect(Collectors.toList());

        Assert.assertEquals(veggies.size(),filteredList.size(),"The veggies.size() list is not equals to filteredList.size().");
        Assert.assertEquals(filteredList.get(0).getText(),vegetableName, "The vegetable name from filtered list does not equal to the vegetable name from test data.");
        System.out.println(vegetableName);

    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @DataProvider(name = "vegetables")
    public Object[] getVegetableData(){
        Object[] vegetableData =new Object[5];
        vegetableData[0] = "Rice";
        vegetableData[1] = "Tomato";
        vegetableData[2] = "Carrot";
        vegetableData[3] = "Potato";
        vegetableData[4] = "Mango";

        return vegetableData;
    }

}
