package ActionClasses;

import org.openqa.selenium.WebDriver;

public class Timesheet {
	
	WebDriver driver;
	
	public Timesheet(WebDriver driver) {
		this.driver=driver;
	}
	
	public boolean isloginpage() {
		return this.driver.getCurrentUrl().equals("https://hris.qainfotech.com/login.php");
	}
	

}
