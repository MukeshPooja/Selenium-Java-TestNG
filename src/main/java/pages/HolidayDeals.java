package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HolidayDeals {
	private WebDriver driver;
	private WebDriverWait wait;

	public HolidayDeals(WebDriver driver) {
		this.setDriver(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	@FindBy(xpath = "//a[contains(@id,'nav-holiday')]")
	private WebElement hBtn;
	
	@FindBy(xpath = "(//span[contains(text(),'Electronics')])[1]")
	private WebElement elect;
	
	public void electClick() {
		wait.until(ExpectedConditions.elementToBeClickable(elect));
		elect.click();
	}
	
	public void holybtn() {
		wait.until(ExpectedConditions.elementToBeClickable(hBtn));
		hBtn.click();
	}
}
