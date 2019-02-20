package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class BasePage {


    protected WebDriver webDriver;
    protected WebDriverWait waitDriver;


    protected BasePage(WebDriver driver, WebDriverWait wait) {

        webDriver = driver;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        waitDriver = wait;

    }

}

