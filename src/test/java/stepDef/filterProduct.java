package stepDef;

import config.env;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class filterProduct extends env {
    @Given("The user has successfully logged in as User A.")
    public void theUserHasSuccessfullyLoggedInAsUserA() throws IOException {
        //set location driver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        //maximize display
        driver.manage().window().maximize();
        //set url
        driver.get(baseUrl);

        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-button\"]"))
        );
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @And("The user chooses to filter the products.")
    public void theUserChoosesToFilterTheProducts() throws IOException {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span"))
        );
        WebElement filter = driver.findElement(By.xpath("//div[@id='header_container']/div[2]/div/span/select"));
        Select select = new Select(filter);
        select.selectByValue("lohi");
    }

    @Then("The products are successfully displayed in order from the lowest price to the highest price.")
    public void theProductsAreSuccessfullyDisplayedInOrderFromTheLowestPriceToTheHighestPrice() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/test/resources/screenshot/filterSuccessLowToHigh.png"));
        driver.quit();
    }

    @Given("The user has successfully logged in as User B.")
    public void theUserHasSuccessfullyLoggedInAsUserB() {
        //set location driver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        //maximize display
        driver.manage().window().maximize();
        //set url
        driver.get(baseUrl);

        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-button\"]"))
        );
        driver.findElement(By.name("user-name")).sendKeys("error_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @Then("The user receives an error message indicating that the filter cannot be applied at this time.")
    public void theUserReceivesAnErrorMessageIndicatingThatTheFilterCannotBeAppliedAtThisTime() throws IOException, AWTException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Robot robot = new Robot();

        // Mendapatkan resolusi layar
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Rectangle screenRect = new Rectangle(toolkit.getScreenSize());

        // Mengambil tangkapan layar
        BufferedImage screenshot = robot.createScreenCapture(screenRect);

        // Menyimpan tangkapan layar ke file
        File output = new File("src/test/resources/screenshot/filterFailedLowToHigh.png");
        ImageIO.write(screenshot, "png", output);

        driver.quit();
    }

}
