package selenium;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeTrue;

public class TestBasicForm {

    WebDriver driver;
    String expected;
    String actual;
    Browser browser;
    BasicFormPOM basicFormPOM;
    String message;

    @Given("I am on the page")
    public void i_am_on_the_page_and_i_enter_a_message() {
        browser = Browser.CHROME;
        if(browser.equals(Browser.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "/Users/janineparham/selenium/chromedriver");
            driver = new ChromeDriver();
            driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

    @And("I enter a message")
    public void i_enter_a_message(){
        basicFormPOM = new BasicFormPOM(driver);
        message = "This is my message.";
        basicFormPOM.setMessageInput(message);
    }

    @When("I click the button")
    public void i_click_the_button() {
       assumeTrue(browser.equals(Browser.CHROME));
       basicFormPOM.clickMessageButton();
    }
    @Then("I should have the message returned to me")
    public void i_should_have_the_message_returned_to_me() {
        actual = basicFormPOM.getReturnMessage();
        expected = message;
        assertThat(actual, equalTo(expected));
    }

    @After
    public void tearDown(){
        driver.close();
    }

}
