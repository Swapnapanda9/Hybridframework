package driverFactory;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import applicationLayer.PBLoginpage;
import applicationLayer.PbEmployeeCreationPage;
import applicationLayer.PbLogoutPage;
import utilies.ExcelFileUtil;

public class TestScript {
//Iniciate obj for webderiver
	WebDriver  driver;
	String inputpath ="./FileInput/EmpData.xlsx";
	String outputpath =",/FileOutput/EmpResults.xlsx";
	@BeforeTest
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("http://primusbank.qedgetech.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		PBLoginpage login = PageFactory.initElements(driver, PBLoginpage.class);
		login.verify_Login("Admin", "Admin");
		
	}

@Test
public void startTest() throws Throwable
{
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//Count no of rows in Emp sheet
	int rc = xl.rowCount("Emp");
	Reporter.log("No of rows are ::"+rc,true);
	for(int i =1;i<=rc;i++)
	{
	String EmployeeName = xl.getCellData("Emp", i, 0);
	String LoginPassword = xl.getCellData("Emp", i, 1);
	String Role = xl.getCellData("Emp", i, 2);
	String Branch = xl.getCellData("Emp", i, 3);
	
	PbEmployeeCreationPage emp = PageFactory.initElements(driver, PbEmployeeCreationPage.class);
	boolean res = emp.verify_Emp(EmployeeName, LoginPassword, Role, Branch);
	if(res)
	{
		xl.setCellData("Emp", i, 4, "Pass", outputpath);
	}
	else
	{
		xl.setCellData("Emp", i, 4, "fail", outputpath);
	}
	}
}
@AfterTest
public void tearDown()
{
	PbLogoutPage logout = PageFactory.initElements(driver, PbLogoutPage.class);
	logout.verify_Logout();
	driver.quit();
}
}





