package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class HomePage extends BasePage {
    private By loginLink=By.linkText("Login");
    private By menuToggle=By.id("menu-toggle");
    private By logoutBtn=By.linkText("Logout");
    private By historyLink = By.linkText("History");
    private By goHomeBtn = By.xpath("//a[normalize-space()='Go to Homepage']");
    
    private By makeAppointmentBtn=By.id("btn-make-appointment");
    private By appointmentHeader=By.xpath("//h2[contains(text(),'Make Appointment')]");
    
    private By facilityDropdown=By.id("combo_facility");
    private By applyHospitalReadmission=By.id("chk_hospotal_readmission");
    private By medicaidRadio=By.id("radio_program_medicaid");
    private By visitDate=By.id("txt_visit_date");
    private By comment=By.id("txt_comment");
    private By bookAppointmentBtn=By.id("btn-book-appointment");

    private By confirmationHeader=By.xpath("//h2[contains(text(),'Appointment Confirmation')]");

    private By confirmFacility=By.id("facility");
    private By confirmDate=By.id("visit_date");
    private By confirmReadmission=By.id("hospital_readmission");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void goToLoginFromMenu() {
        click(menuToggle);
        click(loginLink);
    }
    
    public void clickMakeAppointment() {
        click(makeAppointmentBtn);
    }

    public boolean isLoginSuccessful() {
        return isDisplayed(appointmentHeader);
    }

    public boolean isAppointmentConfirmed() {
        return isDisplayed(confirmationHeader);
    }

    public void selectFacility(String facility) {
        selectByVisibleText(facilityDropdown,facility);
    }

    public void selectMedicaid() {
        click(medicaidRadio);
    }

    public void applyHospitalReadmission() {
        click(applyHospitalReadmission);
    }

    public void enterVisitDate(String date) {
        type(visitDate,date);
    }

    public void enterComment(String text) {
        type(comment,text);
    }

    public void bookAppointment() {
        click(bookAppointmentBtn);
    }

    public LoginPage logout() {
        click(menuToggle);
        waitForVisibility(logoutBtn);
        click(logoutBtn);
        return new LoginPage(driver);
    }
    
    public String getConfirmedFacility() {
        return getText(confirmFacility);
    }

    public String getConfirmedDate() {
        return getText(confirmDate);
    }

    public String getReadmissionStatus() {
        return getText(confirmReadmission);
    }
    
    public HistoryPage goToHistory() {
        click(menuToggle);
        click(historyLink);
        return new HistoryPage(driver);
    }
    
    public void goToHomePage() {
        click(goHomeBtn);
        waitForVisibility(makeAppointmentBtn);
    }
    
    public boolean isAppointmentPageLoaded() {
        return isDisplayed(By.id("combo_facility"));
    }
}