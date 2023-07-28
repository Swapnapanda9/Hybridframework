package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class PbEmployeeCreationPage {
	WebDriver driver;
	public PbEmployeeCreationPage(WebDriver driver)
	{
		this.driver= driver;
	}
	//Define Repository
		@FindBy(xpath="(//img)[8]")
		WebElement Click_Employee;
		@FindBy(xpath="//input[@type='image']")
		WebElement Click_NewEmployee;
		@FindBy(name="txtUname")
		WebElement EnterEmpName;
		@FindBy(name="txtLpwd")
		WebElement EnterLoginPass;
		@FindBy(name ="lst_Roles")
		WebElement SelectRole;
		@FindBy(name = "lst_Branch")
		WebElement SelectBranch;
		@FindBy(name="BtnSubmit")
		WebElement CLickSubmit;
		public boolean verify_Emp(String EmployeeName,String LoginPass,String Role,String Branch) throws Throwable
		{
			this.Click_Employee.click();
			this.Click_NewEmployee.click();
			this.EnterEmpName.sendKeys(EmployeeName);
			this.EnterLoginPass.sendKeys(LoginPass);
			new Select(this.SelectRole).selectByVisibleText(Role);
			new Select(this.SelectBranch).selectByVisibleText(Branch);
			this.CLickSubmit.click();
			String Expected = driver.switchTo().alert().getText();
			Thread.sleep(4000);
			driver.switchTo().alert().accept();
			String Actual= "New Employer Created";
			if(Expected.toLowerCase().contains(Actual.toLowerCase(null)))
			{
				Reporter.log(Expected,true);
				return true;
			}
			else
			{
				Reporter.log("Unanle to create new employee",true);
				return false;
			}
		
		
		
}
}
