package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductList;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {
	


	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException{
		
		String filePath = System.getProperty("user.dir")+"/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount-1][2];
		
		for(int i=1; i<rowCount; i++) {
			
			data[i-1][0] = ExcelUtils.getCellData(i, 0);	// Username
			data[i-1][1] = ExcelUtils.getCellData(i, 1);	// Password
		}
		ExcelUtils.closeExcel();
		return data;
	}
	
	
//	@DataProvider(name="LoginData2")
//	public Object[][] getData(){
//		
//		
//		return new Object[][] {
//			{"user1","pass1"},
//			{"user2","pass2"},
//			{"user3","pass3"}
//		};
//	}
	
	

//	@Test(dataProvider = "LoginData2")
//	@Test
//	@Parameters({"username","password"})
	
//	@Test
//	public void testValidLogin() {
//
//		Log.info("Starting login test...");
//		test = ExtentReportManager.createTest("Login Test - ");
//
//		test.info("Navigating to URL");
//		LoginPage loginPage = new LoginPage(driver);
//
//		Log.info("Adding credentials");
//		test.info("Adding Credentails");
////		loginPage.enterUsername("admin@yourstore.com");
////		loginPage.enterPassword("admin");
////		loginPage.enterUsername(username);
////		loginPage.enterPassword(password);
////		test.info("Clicking on Login button");
//		loginPage.clickLogin();
//
//		System.out.println("Title of the page is : " + driver.getTitle());
//		Log.info("Verifying page title");
//		test.info("Verifying page title");
//		Assert.assertEquals(driver.getTitle(), "Just a moment...");
//
//		test.pass("Login Successful");
//
//	}

//	@Test
//	public void testLoginWithInvalidCredentials() {
//
//		Log.info("Starting login test...");
//		test = ExtentReportManager.createTest("Login Test with Invalid Credentials");
//
//		test.info("Navigating to URL");
//		LoginPage loginPage = new LoginPage(driver);
//
//		Log.info("Adding credentials");
//		test.info("Adding Credentails");
//		loginPage.enterUsername("admin1234@yourstore.com");
//		loginPage.enterPassword("admin123");
//		test.info("Clicking on Login button");
//		loginPage.clickLogin();
//
//		System.out.println("Title of the page is : " + driver.getTitle());
//		Log.info("Verifying page title");
//		test.info("Verifying page title");
//		Assert.assertEquals(driver.getTitle(), "Just a moment...123");
//
//		test.pass("Login Successful");
//
//	}
	
	
	@Test(dataProvider = "LoginData")
	public void testValidLogin(String username, String password) {

		Log.info("Starting login test...");
		test = ExtentReportManager.createTest("Login Test - "+ username);
		LoginPage loginPage = new LoginPage(driver);
		test.info("Navigating to login page");
		
		
		loginPage.enterUsername(username);
		
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		test.info("Entered credentails and click login");
		System.out.println("Title of the page is : " + driver.getTitle());
		Log.info("Verifying page title");
		test.info("Verifying page title");
		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
		Log.info("Login test completed");
		test.pass("Login Successful");

	}
	
	public void productlist() throws InterruptedException {
		Log.info("Starting login test...");
		test = ExtentReportManager.createTest("Product List");
		ProductList pdList =new ProductList(driver);
		test.info("Print Product List");	
		pdList.clickCatalog();
//		pdList.clickProduct();
//		pdList.printList();
		
	}
	@Test
	public void addstore() throws InterruptedException {
		Log.info("add store info...");
		test = ExtentReportManager.createTest("store info");
		ProductList pdList =new ProductList(driver);
		pdList.addStoreInfo();
		
	}

}
