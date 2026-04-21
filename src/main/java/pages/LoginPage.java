package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class LoginPage extends BasePage {
    private By usernameField=By.id("txt-username");
    private By passwordField=By.id("txt-password");
    private By loginBtn=By.id("btn-login");
    private By errorMsg=By.xpath("//p[@class='lead text-danger']");
    private By makeAppointmentBtn=By.id("btn-make-appointment");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void login(String username,String password) {
        type(usernameField,username);
        type(passwordField,password);
        click(loginBtn);
    }
    
    public boolean isHomePageDisplayed() {
        return isDisplayed(makeAppointmentBtn);
    }
    
    public boolean isLoginPageDisplayed() {
        return isDisplayed(usernameField);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }
}