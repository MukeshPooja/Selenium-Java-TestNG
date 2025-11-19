package tests;

import java.io.IOException;

import pages.HolidayDeals;
import pages.LoginAmazon;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class AmazonLogin extends BaseTest {

//	@DataProvider(name = "LoginData")
//	public Object[][] getLoginData() throws IOException {
//		String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
//		ExcelUtils.loadExcel(filePath, "Sheet2");
//
//		try {
//			int rowCount = ExcelUtils.getRowCount();
//			if (rowCount <= 1) {
//				throw new RuntimeException("No test data found in Sheet2!");
//			}
//
//			Object[][] data = new Object[rowCount - 1][1];
//			for (int i = 1; i < rowCount; i++) {
//				data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Username from Column A
//			}
//			return data;
//		} finally {
//			ExcelUtils.closeExcel();
//		}
//	}
//
//	@DataProvider(name = "LoginpasswordData")
//	public Object[][] getpasswordData() throws IOException {
//		String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
//		ExcelUtils.loadExcel(filePath, "Sheet2");
//
//		try {
//			int rowCount = ExcelUtils.getRowCount();
//			if (rowCount <= 1) {
//				throw new RuntimeException("No test data found in Sheet2!");
//			}
//
//			Object[][] data = new Object[rowCount - 1][1];
//			for (int i = 1; i < rowCount; i++) {
//				data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password from Column B
//			}
//			return data;
//		} finally {
//			ExcelUtils.closeExcel();
//		}
//	}
//
//	@Test(dataProvider = "LoginData")
//	public void LoginWithUsername(String username) {
//		Log.info("Starting login test for user: " + username);
//		test = ExtentReportManager.createTest("Login Test - " + username);
//
//		test.info("Navigating to Amazon homepage");
//
//		test.info("Clicking Sign In");
//		LoginAmazon loginPage = new LoginAmazon(driver);
//
//		test.info("Navigating to Amazon homepage");
//
//		test.info("Clicking Sign In");
//		loginPage.signOn();
//
//		test.info("Entering email: " + username);
//		loginPage.enteremail(username);
//
//		test.info("Clicking continue button ");
//		loginPage.continueclick();
//
//	}
//
//	@Test(dataProvider = "LoginpasswordData")
//	public void Enetrpassword(String password) {
//		Log.info("Enetr Password: " + password);
//		test = ExtentReportManager.createTest("Login Test - " + password);
//
//		LoginAmazon loginPage = new LoginAmazon(driver);
//		test.info("Enter valid password");
//		loginPage.enterpassword(password);
//		test.info("Clicking Sign In");
//		loginPage.clicksignOn();
//
//	}

	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet2");

		try {
			int rowCount = ExcelUtils.getRowCount();
			if (rowCount <= 1) {
				throw new RuntimeException("No test data found in Sheet2!");
			}

			// ✅ 2 columns: [username, password]
			Object[][] data = new Object[rowCount - 1][2];
			for (int i = 1; i < rowCount; i++) {
				data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Column A: Username
				data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Column B: Password
			}
			return data;
		} finally {
			ExcelUtils.closeExcel();
		}
	}

	@Test(dataProvider = "LoginData")
	public void testLogin(String username, String password) {
		Log.info("Starting login test for user: " + username);
		test = ExtentReportManager.createTest("Login Test - " + username);

		// Handle cookie banner (EU users)

		// Initialize Page Object
		
		LoginAmazon loginPage = new LoginAmazon(driver);
		
			
		// Step 1: Click Sign In
		test.info("Clicking 'Account & Lists'");
		loginPage.handleyourAcc();
		loginPage.signOn();

		// Step 2: Enter Email
		test.info("Entering email: " + username);
		loginPage.enteremail(username);
		loginPage.handleCookieBanner();
		loginPage.continueclick();

		// Step 3: Enter Password
		test.info("Entering password for: " + username);
		loginPage.enterpassword(password);
		loginPage.clicksignOn();
		

		test.info("Verifying page title");

		test.pass("Login Successful");
		System.out.println("Page title :=> " + driver.getTitle());

		// CRITICAL: ASSERT LOGIN SUCCESS
		if (loginPage.isLoginSuccessful()) {
			Log.info("Login successful for: " + username);
			test.pass("Login passed for " + username);
			Assert.assertTrue(true); // Explicit pass
		} else {
			String errorMsg = loginPage.getErrorMessage();
			Log.error("Login failed for " + username + ". Error: " + errorMsg);
			test.fail("Login failed for " + username + ". Error: " + errorMsg);
			System.out.println("Error Message => "+errorMsg);
			Assert.fail("Login failed. Error: " + errorMsg);
		}

		// Assertion
		Assert.assertTrue(loginPage.isLoginSuccessful(),
				"Login failed for " + username + ". Error: " + loginPage.getErrorMessage());
		test.pass("Successfully logged in as " + username);
		
		
	}
	@Test
	public void prodclick() {
		Log.info("click on holiday deals: " );
		test = ExtentReportManager.createTest("Click on holiday deals btn " );
		
		HolidayDeals holidaydeals = new HolidayDeals(driver);
		
		// Step 1: Click on holybtn
		test.info("Clicking 'Account & Lists'");
		holidaydeals.holybtn();;
		holidaydeals.electClick();;
	}
}
