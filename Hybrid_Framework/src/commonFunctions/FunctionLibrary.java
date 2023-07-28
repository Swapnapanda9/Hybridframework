package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
 //Method for login
	public static boolean pbLogin(String username,String password)
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String expected = "adminflow";
		String Actual = driver.getCurrentUrl();
		if(Actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login Successfull::"+expected+"  "+Actual,true);
			return true;
		}
		else
		{
		Reporter.log("Login Fail::"+expected+ "  "+Actual,true);
		return false;
		}
	}
	//Method for clock branches
	public static void PbBranches()
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjBranches"))).click();
	}
		//Method for new branch creation
		public static boolean PbBranchCreation(String BranchName,String Address1,String Address2,String Address3,String Area,
				String Zipcode,String Country,String State,String City) throws Throwable
		{
			driver.findElement(By.xpath(conpro.getProperty("ObjNewBranch"))).click();
			driver.findElement(By.xpath(conpro.getProperty("ObjBranchName"))).sendKeys(BranchName);
			driver.findElement(By.xpath(conpro.getProperty("ObjAddress1"))).sendKeys(Address1);
			driver.findElement(By.xpath(conpro.getProperty("ObjAddress2"))).sendKeys(Address2);
			driver.findElement(By.xpath(conpro.getProperty("ObjAddress3"))).sendKeys(Address3);
			driver.findElement(By.xpath(conpro.getProperty("ObjArea"))).sendKeys(Area);
			driver.findElement(By.xpath(conpro.getProperty("ObjZipcode"))).sendKeys(Zipcode);
			new Select(driver.findElement(By.xpath(conpro.getProperty("ObjCountry")))).selectByVisibleText(Country);
			new Select(driver.findElement(By.xpath(conpro.getProperty("ObjState")))).selectByVisibleText(State);
			new Select(driver.findElement(By.xpath(conpro.getProperty("ObjCity")))).selectByVisibleText(City);
			driver.findElement(By.xpath(conpro.getProperty("ObjCity"))).click();
			//Capture Alert message
			String expected_Alert =driver.switchTo().alert().getText();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			String Actual_Alert = "New Branch with id";
			if (expected_Alert.toLowerCase().contains(Actual_Alert.toLowerCase()))
			{
		
			Reporter.log(expected_Alert,true);
			return true;
			}
			else
			{
				Reporter.log("Failed to create new branch::",true);
				return false;
			}
	}	
	//Method for Branch Updation
		public static boolean pbBranchUpdate (String BranchName,String Address1,
				String Area,String Zip) throws Throwable
		{
			driver.findElement(By.xpath(conpro.getProperty("ObjEdit"))).click();
			WebElement element1 = driver.findElement(By.xpath(conpro.getProperty("ObjBranch")));
			element1.clear();
			element1.sendKeys(BranchName); 
			WebElement element2 = driver.findElement(By.xpath(conpro.getProperty("ObjAdress")));
			element2.clear();
			element2.sendKeys(Address1); 
			WebElement element3 = driver.findElement(By.xpath(conpro.getProperty("ObjAreaName")));
			element3.clear();
			element3.sendKeys(Area); 
			WebElement element4 = driver.findElement(By.xpath(conpro.getProperty("ObjZip")));
			element4.clear();
			element4.sendKeys(Zip); 
			driver.findElement(By.xpath(conpro.getProperty("ObjUpdate"))).click();
			String expected_Alert = driver.switchTo().alert().getText();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			String Actual_Alert ="Branch updated Sucessfully";
			if(expected_Alert.toLowerCase().contains(Actual_Alert.toLowerCase()))
			{
				Reporter.log(expected_Alert,true);
				return true;
			}
			else
			{
				Reporter.log(Actual_Alert,true);
				return false;
			}
			
		}
		//Method for logout
		public static boolean Pblogout()
		{
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			if(driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).isDisplayed())
			{
				Reporter.log("Logout Success::",true);
				return true;
			}
			else
			{
				Reporter.log("Logout Fail::",true);
				return false;
			}
		}
}







