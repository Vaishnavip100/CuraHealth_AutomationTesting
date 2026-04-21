package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class HistoryPage extends BasePage {
    private By historyHeader=By.xpath("//h2[text()='History']");
    private By facility=By.id("facility");
    private By visitDate=By.xpath("//div[@class='panel-heading']");
    private By readmission=By.id("hospital_readmission");
    
    private By allFacilities=By.id("facility");
    private By allDates=By.cssSelector(".panel-heading");
    
    private By comment=By.id("comment");

    
    public HistoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isHistoryPageLoaded() {
        return isDisplayed(historyHeader);
    }

    public String getLatestFacility() {
        return getText(facility);
    }

    public String getLatestDate() {
        return getText(visitDate).trim();
    }
    
    public String getReadmissionStatus() {
        return getText(readmission);
    }
    
    public List<String> getAllFacilities() {
        List<WebElement> elements = driver.findElements(allFacilities);
        List<String> facilities = new ArrayList<>();

        for (WebElement e : elements) {
            facilities.add(e.getText().trim());
        }
        return facilities;
    }
    
    public List<String> getAllDates() {
        List<WebElement> elements = driver.findElements(allDates);
        List<String> dates = new ArrayList<>();

        for (WebElement e : elements) {
            dates.add(e.getText().trim());
        }

        return dates;
    }
    
    public String getLatestComment() {
        return getText(comment).trim();
    }
}