package StepDefination;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qainfotech.cucumber.GoogleSheetAutoSample_Hris.GoogleSheetApi;

import ActionClasses.Login;
import ActionClasses.Timesheet;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class HrisLoginTest {
	
	WebDriver driver;
	Login login;
	Timesheet timeSheet;
	GoogleSheetApi api;
	
	@Given("^i have opened hris link$")
	public void i_have_opened_hris_link() throws Throwable {
		
		System.setProperty("webdriver.chrome.driver","//home//qainfotech//Downloads//chromedriver");
		driver = new ChromeDriver();
		driver.get("https://hris.qainfotech.com/login.php");
		login = new Login(driver);
	}

	@When("^i enter valid credentials$")
	public void i_enter_valid_credentials() throws Throwable {
	   timeSheet=  login.Valid_Crendentials("Adityaagrawal", "Aditya@321#");
	}

	@Then("^time sheet should be displayed$")
	public void time_sheet_should_be_displayed() throws Throwable {
	   Assert.assertFalse(timeSheet.isloginpage());
	   api.Update("Hris_01");
	   
	}



	@When("^i enter invalid credentials$")
	public void i_enter_invalid_credentials() throws Throwable {
	    login.Invalid_Password("Invalid_USername","Invalid_PAssword");
	}
	
	@Then("^Error message should be displayed$")
	public void error_message_should_be_displayed() throws Throwable {
	    Assert.assertTrue(login.isloginpage());
	    api.Update("Hris_02");
	}


}
