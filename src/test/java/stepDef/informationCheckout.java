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

public class informationCheckout extends env {
    @Given("The user has successfully logged in.")
    public void theUserHasSuccessfullyLoggedIn() {
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
        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span"))
        );
    }

    @And("The user has added the selected item to the cart.")
    public void theUserHasAddedTheSelectedItemToTheCart() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
    }

    @And("The user is on the cart page.")
    public void theUserIsOnTheCartPage() {

        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]")).click();

        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span"))
        );
    }

    @When("The user clicks the Checkout button.")
    public void theUserClicksTheCheckoutButton() {
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"first-name\"]"))
        );
    }

    @And("The user enters their first name.")
    public void theUserEntersTheirFirstName() {
        driver.findElement(By.name("firstName")).sendKeys("Tester A");
    }

    @And("The user enters their last name.")
    public void theUserEntersTheirLastName() {
        driver.findElement(By.name("lastName")).sendKeys("Last Name Tester A");
    }

    @And("The user enters the postal code.")
    public void theUserEntersThePostalCode() {
        driver.findElement(By.name("postalCode")).sendKeys("092744");
    }

    @And("The user clicks the Continue button.")
    public void theUserClicksTheContinueButton() {
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
    }

    @Then("Shipping and payment information has been entered.")
    public void shippingAndPaymentInformationHasBeenEntered() throws IOException {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"finish\"]"))
        );
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/test/resources/screenshot/informationSuccess.png"));
        driver.quit();
    }

    @And("The user does not enter their last name.")
    public void theUserDoesNotEnterTheirLastName() {
        driver.findElement(By.name("lastName")).sendKeys("");
    }

    @Then("The system does not allow the user to proceed to the next step in the checkout process.")
    public void theSystemDoesNotAllowTheUserToProceedToTheNextStepInTheCheckoutProcess() {
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
    }

    @And("An error message is displayed indicating that the last name is required.")
    public void anErrorMessageIsDisplayedIndicatingThatTheLastNameIsRequired() throws IOException {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3"))
        );
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/test/resources/screenshot/informationFailed.png"));
        driver.quit();
    }
}
