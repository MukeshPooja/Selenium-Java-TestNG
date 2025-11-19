package pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.JsClickUtils;
import utils.Log;
import utils.WaitUtils;

public class ProductList {
	private WebDriver driver;

	public ProductList(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath="(//*[contains(@class,'right fas fa-angle-left ')])[1]")
	List<WebElement> productList;
	@FindBy(xpath="(//*[contains(@class, 'right') and contains(@class, 'fas') and contains(@class, 'fa-angle-left')])[1]")
	WebElement product;
	@FindBy(xpath="//a[@class='nav-link' and text()='Catalog']")
	WebElement catalogarrow;

	
	@FindBy(xpath="//*[contains(text(),'Add your store info')]")
	WebElement addstore;
	
	
	public void addStoreInfo() throws InterruptedException {
		Log.info("click to add store info.");
		WaitUtils wait= new WaitUtils(driver, 10);
		wait.wait(10);
		addstore.click();
		
	}
	
	
	public void clickCatalog() throws InterruptedException {
		Log.info("Clicking catalog ..");
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    wait.until(ExpectedConditions.elementToBeClickable(catalogarrow));
		WaitUtils wait= new WaitUtils(driver, 10);
		wait.wait(10);
	    JsClickUtils js= new JsClickUtils(driver);
	    js.clickWithScroll(catalogarrow);
	    // THEN click
//	    catalogarrow.click();
		
	}
	
	public void clickProduct() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(product));
		product.click();
		
//		Select catalog = new Select(product);
//		catalog.selectByVisibleText("Products");
		
	}
	public void printList() {

		for (WebElement item : productList) {
			String itemName = item.getText();
			System.out.println("Item Name: " + itemName);
		}
	}

	
}
