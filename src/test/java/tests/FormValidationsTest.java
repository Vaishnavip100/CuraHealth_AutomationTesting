package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.HistoryPage;
import utils.ConfigReader;

public class FormValidationsTest extends BaseTest {

	private void login() {
        ConfigReader config=new ConfigReader();

        HomePage hp=new HomePage(getDriver());
        hp.goToLoginFromMenu();

        LoginPage lp=new LoginPage(getDriver());
        lp.login(config.getUsername(), config.getPassword());
    }

    //Empty Date Validation
    @Test
    public void verifyEmptyDateValidation() {
    	login();
        HomePage hp=new HomePage(getDriver());
        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.enterComment("Test without date");
        hp.bookAppointment();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("#appointment"),"Form submitted even when date is empty");
    }

    //Empty Login Validation
    @Test
    public void verifyEmptyLoginValidation() {
        HomePage hp=new HomePage(getDriver());
        hp.goToLoginFromMenu();

        LoginPage lp=new LoginPage(getDriver());
        lp.login("","");

        Assert.assertTrue(lp.isLoginPageDisplayed(),"Login allowed with empty credentials");
    }

    //Long Comment Validation
    @Test
    public void verifyLongCommentAccepted() {
    	login();
        HomePage hp=new HomePage(getDriver());
        String longComment="This is a very long comment ".repeat(20);

        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.enterVisitDate("25/12/2026");
        hp.enterComment(longComment);

        hp.bookAppointment();

        HistoryPage history=hp.goToHistory();

        String actualComment=history.getLatestComment();

        Assert.assertTrue(actualComment.contains("This is a very long comment"),"Long comment was truncated or not saved properly");
    }
}