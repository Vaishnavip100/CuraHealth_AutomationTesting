package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExcelUtil;

public class LoginTest extends BaseTest{
	
	ConfigReader config;
	
	@DataProvider(name="loginData")
    public Object[][] getLoginData() {
        String path=System.getProperty("user.dir")+"/src/test/resources/testdata/LoginData.xlsx";
        return ExcelUtil.getData(path,"Sheet1");
    }
	
	@Test(dataProvider = "loginData")
    public void verifyLogin(String username, String password, String expected) {
        HomePage hp=new HomePage(getDriver());
        hp.goToLoginFromMenu();

        LoginPage loginPage=new LoginPage(getDriver());
        loginPage.login(username, password);

        if (expected.equalsIgnoreCase("valid")) {
            HomePage home=new HomePage(getDriver());
            Assert.assertTrue(home.isLoginSuccessful(),"Expected successful login but failed");
        }else if (expected.equalsIgnoreCase("invalid")) {
            Assert.assertTrue(loginPage.getErrorMessage().contains("Login failed"),"Expected error message not displayed");
        }
    }
	
	@Test
	public void verifyLogoutRedirectsToHomePage() {
	    config=new ConfigReader();
	    HomePage hp=new HomePage(getDriver());
	    hp.goToLoginFromMenu();

	    LoginPage lp=new LoginPage(getDriver());
	    lp.login(config.getUsername(), config.getPassword());

	    LoginPage login=hp.logout();
	    Assert.assertTrue(login.isHomePageDisplayed(),"Logout failed - Login page not displayed");
	}
	
	@Test
	public void verifyProtectedPageRedirectToLogin() {
		HomePage hp=new HomePage(getDriver());
	    hp.clickMakeAppointment();
	    
	    Assert.assertTrue(getDriver().getCurrentUrl().contains("profile.php#login"),"User was not redirected to login page");
	}
}
