package applicationLayer;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PBLoginpage {
// Define Repository
	@FindBy(name = "txtuId")
	WebElement objUser;
	@FindBy(name ="txtPword")
	WebElement objPass;
	@FindBy(name = "login")
	WebElement objLogin;
	//Write method for login
	public void verify_Login(String userName,String Pass)
	{
		objUser.sendKeys(userName);
		objPass.sendKeys(Pass);
		objLogin.click();
	}
	
	}
	











