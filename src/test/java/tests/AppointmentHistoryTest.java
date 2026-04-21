package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.HistoryPage;
import utils.ConfigReader;

public class AppointmentHistoryTest extends BaseTest {
	
	@BeforeMethod
    private void login() {
        ConfigReader config=new ConfigReader();

        HomePage hp=new HomePage(getDriver());
        hp.goToLoginFromMenu();

        LoginPage lp=new LoginPage(getDriver());
        lp.login(config.getUsername(), config.getPassword());
    }

    //Verify history page loads after login
    @Test
    public void verifyHistoryPageLoads() {
        HomePage hp=new HomePage(getDriver());
        HistoryPage history=hp.goToHistory();

        Assert.assertTrue(history.isHistoryPageLoaded(),"History page did not load");
    }

    //Verify latest booked appointment appears with correct facility & date
    @Test
    public void verifyLatestAppointmentInHistory() {
        HomePage hp=new HomePage(getDriver());
        String facility="Tokyo CURA Healthcare Center";
        String date="29/12/2026";

        hp.selectFacility(facility);
        hp.enterVisitDate(date);
        hp.enterComment("History validation");
        hp.bookAppointment();

        HistoryPage history=hp.goToHistory();

        Assert.assertEquals(history.getLatestFacility(),facility,"Facility mismatch in history");
        Assert.assertEquals(history.getLatestDate(),date,"Date mismatch in history");
    }

    //Verify history content
    @Test
    public void verifyHistoryContentDisplayed() {
        HomePage hp=new HomePage(getDriver());

        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.enterVisitDate("30/12/2026");
        hp.enterComment("Content check");
        hp.bookAppointment();

        HistoryPage history=hp.goToHistory();

        Assert.assertTrue(history.getLatestFacility().length() > 0,"Facility not displayed");
        Assert.assertTrue(history.getLatestDate().length() > 0,"Date not displayed");
        Assert.assertTrue(history.getReadmissionStatus().length() > 0,"Readmission status not displayed");
    }
}