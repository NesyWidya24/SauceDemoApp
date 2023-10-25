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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class login extends env {

    @Given("The user accesses the website.")
    public void theUserAccessesTheWebsite() {
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

    @And("The user enters their valid username.")
    public void theUserEntersTheirValidUsername() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
    }

    @And("The user enters their valid password.")
    public void theUserEntersTheirValidPassword() {
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
    }

    @When("The user clicks the Login button to log in.")
    public void theUserClicksTheLoginButtonToLogIn() {
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @Then("User verify login result")
    public void userVerifyLoginResult() throws IOException {

        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span"))
        );
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/test/resources/screenshot/loginFailed.png"));
        driver.quit();
    }

    @And("The user enters their empty username.")
    public void theUserEntersTheirEmptyUsername() {
        driver.findElement(By.name("user-name")).sendKeys("");
    }

    @And("The user enters their empty password.")
    public void theUserEntersTheirEmptyPassword() {
        driver.findElement(By.name("password")).sendKeys("");
    }

    @Then("User verify failed login result")
    public void userVerifyFailedLoginResult() throws IOException {
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

        Duration duration = Duration.ofSeconds(10);
        WebDriverWait webDriverWait = new WebDriverWait(driver, duration);

        webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]"))
        );
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("src/test/resources/screenshot/loginFailed.png"));
        driver.quit();
    }
}
