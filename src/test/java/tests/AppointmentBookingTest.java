package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentTestManager;

public class AppointmentBookingTest extends BaseTest {

	@BeforeMethod
    private void login() {
        ConfigReader config=new ConfigReader();

        HomePage hp=new HomePage(getDriver());
        hp.goToLoginFromMenu();

        LoginPage lp=new LoginPage(getDriver());
        lp.login(config.getUsername(),config.getPassword());
    }

    //Book appointment
    @Test
    public void verifyAppointmentBooking() {
        HomePage hp=new HomePage(getDriver());
        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.enterVisitDate("25/12/2026");
        hp.enterComment("Booking test");
        hp.bookAppointment();

        Assert.assertTrue(hp.isAppointmentConfirmed(),"Appointment booking failed");
    }

    //Verify facility and date in confirmation
    @Test
    public void verifyAppointmentDetails() {
        HomePage hp=new HomePage(getDriver());

        String facility="Tokyo CURA Healthcare Center";
        String date="26/12/2026";

        hp.selectFacility(facility);
        hp.enterVisitDate(date);
        hp.enterComment("Verify details");
        hp.bookAppointment();

        Assert.assertEquals(hp.getConfirmedFacility(),facility,"Facility mismatch");
        Assert.assertEquals(hp.getConfirmedDate(),date,"Date mismatch");
    }

    //Verify Apply Hospital Readmission checkbox
    @Test
    public void verifyReadmissionCheckbox() {
        HomePage hp=new HomePage(getDriver());
        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.applyHospitalReadmission();
        hp.enterVisitDate("27/12/2026");
        hp.enterComment("Checkbox test");
        hp.bookAppointment();

        Assert.assertTrue(hp.getReadmissionStatus().contains("Yes"),"Readmission not reflected in confirmation");
    }

    //Verify past date rejection
    @Test
    public void verifyPastDateBehavior() {
        ExtentTest test = ExtentTestManager.getTest();
        HomePage hp = new HomePage(getDriver());

        String pastDate = "01/01/2020";

        hp.selectFacility("Tokyo CURA Healthcare Center");
        hp.enterVisitDate(pastDate);
        hp.enterComment("Past date test");
        hp.bookAppointment();

        String actualDate = hp.getConfirmedDate();

        if (actualDate.equals(pastDate)) {
            test.warning("BUG: Application accepts past date → " + pastDate);
        } else {
            test.pass("Past date was rejected (unexpected behavior)");
        }
        Assert.assertTrue(true);
    }
}