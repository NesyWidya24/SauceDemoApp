package stepDef;

import config.env;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class addCart extends env {
    @Given("The user has opened the login page.")
    public void theUserHasOpenedTheLoginPage() {
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
    }

    @And("The user has entered a valid username.")
    public void theUserHasEnteredAValidUsername() {
        driver.findElement(By.name("user-name")).sendKeys("problem_user");
    }

    @And("The user has entered a valid password.")
    public void theUserHasEnteredAValidPassword() {
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
    }

    @When("The user clicks the Login button and successfully logs in.")
    public void theUserClicksTheLoginButtonAndSuccessfullyLogsIn() {
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span"))
        );
    }


    @When("The user select item and clicks the Add to Cart button")
    public void theUserSelectItemAndClicksTheAddToCartButton() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
    }

    @Then("The added item successfully appears in the cart.")
    public void theAddedItemSuccessfullyAppearsInTheCart() throws IOException {
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span"))
        );
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/test/resources/screenshot/addCartSuccess.png"));
        driver.quit();
    }

    @And("The user has selected an item to remove.")
    public void theUserHasSelectedAnItemToRemove() {
        driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).click();
    }

    @When("User clicks Remove with no response.")
    public void userClicksRemoveWithNoResponse() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"))
        );
    }

    @Then("The item is not removed from the cart.")
    public void theItemIsNotRemovedFromTheCart() throws IOException {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"))
        );
        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"))
        );
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/test/resources/screenshot/removeItemFailed.png"));
        driver.quit();
    }
}
