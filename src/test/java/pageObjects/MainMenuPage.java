package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuPage extends BasePage {

	public MainMenuPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[text()='Dashboard']")
	WebElement DashboardOption;
	
	
	public boolean isDashboardOptionPresent() {
	    try {
	        // Wait until the element is present and visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOf(DashboardOption));

	        // Check if the element is displayed (only if it's not null)
	        if (DashboardOption != null && DashboardOption.isDisplayed()) {
	            return true;
	        }
	    } catch (Exception e) {
	        // Handle the case where the element is not found or visible
	        System.out.println("Dashboard option is not present or visible: " + e.getMessage());
	    }
	    
	    return false; // Element is either not present or not visible
	}
	
}
