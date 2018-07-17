package StepDefination;

import java.util.Collection;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.qainfotech.cucumber.GoogleSheetAutoSample_Hris.GoogleSheetApi;

import ActionClasses.Login;
import ActionClasses.Timesheet;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


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
		api = new GoogleSheetApi();
	}

	@When("^i enter valid credentials$")
	public void i_enter_valid_credentials() throws Throwable {
	   timeSheet=  login.Valid_Crendentials("Adityaagrawal", "Aditya@321#");
	}

	@Then("^time sheet should be displayed$")
	public void time_sheet_should_be_displayed() throws Throwable {
	   Assert.assertFalse(timeSheet.isloginpage());
	   driver.close();
	   
	}



	@When("^i enter invalid credentials$")
	public void i_enter_invalid_credentials() throws Throwable {
	    login.Invalid_Password("Invalid_USername","Invalid_PAssword");
	}
	
	@Then("^Error message should be displayed$")
	public void error_message_should_be_displayed() throws Throwable {
	    Assert.assertTrue(login.isloginpage());
	    driver.close();
	}
	
	@When("^enter only username and click signin$")
	public void enter_only_username_and_click_signin() throws Throwable {
           login.Blank_Password_Field("Adityaagrawal","");
	}

	@Then("^password become highlighted with red boundary$")
	public void password_become_highlighted_with_red_boundary() throws Throwable {
	   Assert.assertTrue( login.isloginpage());
	   driver.close();
	}
	
	@After
	public void PrintStatus(Scenario scenario) throws Exception {
		
		
	
		String Id  =scenario.getSourceTagNames().toString();
		Id = Id.substring(Id.indexOf("@")+1);
		Id = Id.substring(0,Id.length()-1);
		api.Update(Id, scenario.getStatus());
	
	}


}
