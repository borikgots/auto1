import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SearchPage;
import java.io.File;
import static pages.SearchPage.openSearchPage;
import static webui.Properties.PATHNAME;



public class SortTest {

        public WebDriver driver;
        public WebDriverWait wait;


        @BeforeTest
        public void setup() {

                System.setProperty("webdriver.chrome.driver",
                        new File(PATHNAME + "chromedriver").getAbsolutePath());

                driver = new ChromeDriver();
                wait = new WebDriverWait(driver,30);


        }

        @Test(description = "Sorting the cars")
        public void sortCars(){

            SearchPage searchPage = openSearchPage(driver, wait);
            searchPage.applyFromYearFilter("2015");
            searchPage.applySortByFilter("Höchster Preis");
            searchPage.verifyInSearchAllCarsFromYear(2015);
            searchPage.verifyCarPriceSortedByDesc();

        }


        @Test
        public void cleanTest(){

            System.out.print("console");
            
        }





}
