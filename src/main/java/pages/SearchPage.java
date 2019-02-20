package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.*;

import static webui.Properties.URL;

public class SearchPage extends BasePage {


    public SearchPage(WebDriver driver, WebDriverWait wait) {

        super(driver, wait);

    }


    By firstRegistration = By.xpath("//span[text()='Erstzulassung ab']");
    By dropDownList = By.xpath("//select[@class='form-control select___1VVkB' and @name='yearRange.min']");
    By sortBy = By.xpath("//select[@class='form-control select___1VVkB' and @name='sort']");
    By carDate = By.xpath("//ul[@class='specList___2i0rY']/child::li[1]");
    By totalPrice = By.xpath("//div[@class='totalPrice___3yfNv']");



    public static SearchPage openSearchPage(WebDriver driver, WebDriverWait wait){

        driver.get(URL);
        return new SearchPage(driver, wait);

    }


    public void applyFromYearFilter(String filter){

        webDriver.findElement(firstRegistration).click();
        webDriver.findElement(dropDownList).click();
        Select dropdown = new Select(webDriver.findElement(dropDownList));
        dropdown.selectByVisibleText(filter);

    }


    public void applySortByFilter(String filter){

        webDriver.findElement(sortBy).click();
        Select dropdown = new Select(webDriver.findElement(sortBy));
        dropdown.selectByVisibleText(filter);
        webDriver.findElement(sortBy).sendKeys(Keys.ESCAPE);

    }


    public void verifyInSearchAllCarsFromYear(int fromYear)  {

        waitDriver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(carDate));
        List <WebElement> elementList = webDriver.findElements(carDate);
        ArrayList <Integer> list = new ArrayList ();

        for (WebElement element : elementList){

            String text = element.getAttribute("textContent").substring(5,9);
            int data = Integer.parseInt(text);
            list.add(data);

        }


        for (int date : list){

            boolean isTrue;
            if (date >= fromYear) isTrue = true;
            else isTrue = false;
            Assert.assertTrue(isTrue);

        }
    }


    public void verifyCarPriceSortedByDesc(){

        waitDriver.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(totalPrice));
        List <WebElement> elementList = webDriver.findElements(totalPrice);
        LinkedList <Double> list = new LinkedList ();

        for (WebElement element : elementList){

            String text = element.getAttribute("textContent").substring(0,6);
            double price = Double.parseDouble(text);
            list.add(price);

        }

        LinkedList<Double> tmp = new LinkedList(list);
        Collections.sort(tmp, Collections.reverseOrder());
        Assert.assertTrue(tmp.equals(list));

    }

}