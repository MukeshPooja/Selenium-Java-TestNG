package pages;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginAmazon {

	private WebDriver driver;
	private WebDriverWait wait;

	public LoginAmazon(WebDriver driver) {
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

	@FindBy(xpath = "//div/span[@id='nav-link-accountList-nav-line-1']")
	private WebElement signonBtn;
	@FindBy(xpath = "//input[@id='ap_email_login']")
	private WebElement email;
	@FindBy(xpath = "//span[@id='continue']")
	private WebElement continueBtn;
	@FindBy(xpath = "//input[@id='ap_password']")
	private WebElement enterpassword;
	@FindBy(xpath = "//input[@id='signInSubmit']")
	private WebElement signInSbtn;
	@FindBy(xpath = "//div[contains(text(),'Invalid email address')]")
	private WebElement errorinvalidEmail;

	@FindBy(id = "nav-link-accountList")
	private WebElement accountGreeting; // Amazon shows "Hello, Name" here after login

	// Optional: Error message locators
	@FindBy(xpath = "//div[contains(@class, 'auth-error') or contains(@id, 'error')]")
	private WebElement authErrorBox;

	// Handle cookies
	@FindBy(id = "sp-cc-accept")
	private WebElement cookies;
	
	@FindBy(xpath="//a[text()='Your Account']")
	private WebElement yourAcct;
	
	public void handleyourAcc() {
		wait.until(ExpectedConditions.elementToBeClickable(yourAcct));
		yourAcct.click();
	}

	public void handleCookieBanner() {
		try {
			cookies.click();
		} catch (Exception e) {
			// Ignore if no cookie banner
		}
	}

	// Handle Captcha
	@FindBy(id = "auth-captcha-image")
	private WebElement captchaImage;

	public boolean isCaptchaPresent() {
		return captchaImage.isDisplayed();
	}

	// Verify successful login
	public boolean isLoginSuccessful() {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.textToBePresentInElement(accountGreeting, "Hello"));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	
	//  Get error message (if login fails)
	public String getErrorMessage() {
		try {
			return authErrorBox.findElement(By.tagName("h4")).getText();
		} catch (Exception e) {
			return "No error message found";
		}
	}

	public void signOn() {
		wait.until(ExpectedConditions.elementToBeClickable(signonBtn));
		signonBtn.click();
	}

	public void enteremail(String username) {
		wait.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys(username);

	}
	// Method to enter email and click Continue
	public void enterEmailclick(String emailID){
		wait.until(ExpectedConditions.visibilityOf(email));
		email.clear();
		email.sendKeys(emailID);
		continueBtn.click();
		
	}

	// CRITICAL: Check for "Invalid email" error
	
	public boolean isInvalidErrorDisplayed() {
		
		try {
			//wait for error box to appear (max 5 seconds)
			wait.until(ExpectedConditions.visibilityOf(authErrorBox));
			//Get error text and check for "Invalid email"
			String errorMsg = authErrorBox.getText();
			System.out.println("signFail: Wrong emailId => "+errorMsg);
			return errorMsg.contains("Invalid email address")||
					errorMsg.contains("Enter a valid email");
		}catch (Exception e) {
			// Error box didn't appear → no error
		}
		return false;
		
	}
	

	public void continueclick() {
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		continueBtn.click();
	}

	public void enterpassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(enterpassword));
		enterpassword.sendKeys(password);
	}

	public void clicksignOn() {
		wait.until(ExpectedConditions.visibilityOf(signInSbtn));
		signInSbtn.click();
	}
}
