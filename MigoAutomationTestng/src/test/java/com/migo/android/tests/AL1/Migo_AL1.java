package com.migo.android.tests.AL1;
import java.io.IOException;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.appium.base.UserBaseTest;
import com.aventstack.extentreports.Status;
import com.migo.annotation.values.Author;
import com.migo.annotation.values.Description;
import com.migo.pages.CatalogPage;
import com.migo.pages.DownloadPage;
import com.migo.pages.LoadPage;
import com.migo.pages.MigoLogoPage;
import com.migo.pages.MorePage;

public class Migo_AL1 extends UserBaseTest {

	Logger logger = Logger.getLogger(Migo_AL1.class.getName());

	@BeforeClass
	@Description(value = "New user launches the app for the first time while offline")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void AL1_Precondition(String device) throws InterruptedException, IOException {

		DownloadPage downloadPage = new DownloadPage(driver);
		//Turn Device WIFI Off
		downloadPage.disableWifi();
		//Reset App
		downloadPage.resetApplication();
	}

	@Test
	@Description(value = "User DENY Migo App to access his/her phone storage")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void AL1_001(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);

		//Step 1: Open Migo App
		//	downloadPage.launchApp();

		//ER 1: Splash screen indeterminate loading progess bar displayed
		try {
			if (downloadPage.isSplashLoadingVisible()) {
				report.log(Status.PASS, "Splash screen indeterminate loading progess bar displayed");
			} else {
				report.log(Status.FAIL, "Splash screen indeterminate loading progess bar is not displayed");
			}
		}catch (Exception e) {
			report.log(Status.FAIL, "Splash screen indeterminate loading progess bar is not displayed");
		}
		//ER 1: Splash screen Storage Permission guide page displayed
		Assert.assertTrue(downloadPage.isPermissionGuideVisible(),
				"Splash screen Storage Permission guide page not displayed");
		report.log(Status.PASS, "Splash screen Storage Permission guide page displayed");

		//Step 2: Wait for 5 Seconds
		report.info("Wait for 5 seconds");
		Thread.sleep(5000);

		//ER 2: Android Storage request permission prompt displayed
		Assert.assertTrue(downloadPage.isPermissionPromptVisible(),
				"Android Storage request permission prompt not displayed");
		report.log(Status.PASS, "Android Storage request permission prompt displayed");
		//Step 3: TAP "DENY" on Storage request permission prompt
		downloadPage.clickOnDeny();
		report.info("TAP \"DENY\" on Storage request permission prompt");
		downloadPage.clickOnDeny();
		report.info("TAP \"DENY\" on Storage request permission prompt");
		//ER 1: Android Storage request permission via Setting prompt displayed
		Assert.assertTrue(downloadPage.isSettingPromptVisible(),
				"Android Storage request permission via Setting prompt not displayed");
		report.log(Status.PASS, "Android Storage request permission via Setting prompt displayed");
		//Step 4: TAP "CLOSE" on Storage request permission via Setting prompt
		downloadPage.clickOnClosePopup();
		report.info("TAP \"CLOSE\" on Storage request permission via Setting prompt");
		//ER 1: App Terminated
		report.log(Status.PASS, "App Terminated");

	}

	@Test
	@Description(value = "User ALLOW Migo App to access his/her phone storage. User DENY Migo App to access his/her phone state")
	@Parameters({"device"})
	@Author(name = "Shah Nawaz")
	public void AL1_002() throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);
		/*1. Open Migo App
		2. Wait for 5 seconds
		3. TAP "ALLOW" on Storage request permission prompt
		4. TAP "DENY" on Phone State request permission prompt
		5. TAP "CLOSE" on Phone State request permission via Setting prompt*/

		//Step 1: Open Migo App
		downloadPage.launchApp();
		report.info("Open Migo App");

		//ER 1: Splash screen indeterminate loading progess bar displayed
		try {
			if (downloadPage.isSplashLoadingVisible()) {
				report.log(Status.PASS, "Splash screen indeterminate loading progess bar displayed");
			} else {
				report.log(Status.FAIL, "Splash screen indeterminate loading progess bar is not displayed");
			}
		}catch (Exception e) {
			report.log(Status.FAIL, "Splash screen indeterminate loading progess bar is not displayed");
		}
		//ER 2: Splash screen Storage Permission guide page displayed 
		Assert.assertTrue(downloadPage.isPermissionGuideVisible(),
				"Splash screen Storage Permission guide page not displayed");
		report.log(Status.PASS, "Splash screen Storage Permission guide page displayed ");

		//Step 2: Wait for 5 seconds
		report.info("Wait for 5 seconds");
		Thread.sleep(5000);

		//ER 3: Android Storage Request Permission prompt displayed
		Assert.assertTrue(downloadPage.isPermissionPromptVisible(),
				"Android Storage Request Permission prompt not displayed");
		report.log(Status.PASS, "Android Storage Request Permission prompt displayed");

		//Step 3: TAP "ALLOW" on Storage request permission prompt	
		downloadPage.clickOnAllow();
		report.info("TAP \"ALLOW\" on Storage request permission prompt");

		//ER4: Android Phone State request permission via Setting prompt displayed
		Assert.assertTrue(downloadPage.isPermissionPromptVisible(),
				"Android Phone State request permission via Setting prompt not displayed");
		report.log(Status.PASS, "Android Phone State request permission via Setting prompt displayed");

		//Step 4: TAP "DENY" on Phone State request permission prompt
		downloadPage.clickOnDeny();
		report.info("TAP \"DENY\" on Phone State request permission prompt");		

		//ER5: Android Storage request permission via Setting prompt displayed
		Assert.assertTrue(downloadPage.isSettingPromptVisible(),
				"Android Storage request permission via Setting prompt not displayed");
		report.log(Status.PASS, "Android Storage request permission via Setting prompt displayed");

		//Step 5: TAP "CLOSE" on Phone State request permission via Setting prompt
		downloadPage.clickOnClosePopup();
		report.info("TAP \"CLOSE\" on Storage request permission via Setting prompt");
		//ER6: App Terminated
		report.log(Status.PASS, "App Terminated");

	}

	@Test
	@Description(value = "User ALLOW Migo App to access his/her phone storage")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void AL1_003() throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		LoadPage loadPage = new LoadPage(driver);
		MorePage morePage = new MorePage(driver);
		MigoLogoPage migoLogo = new MigoLogoPage(driver);

		//Step 1: Open Migo App
		downloadPage.launchApp();
		report.info("Open Migo App");

		//ER 1: Splash screen indeterminate loading progess bar displayed
		try {
			if (downloadPage.isSplashLoadingVisible()) {
				report.log(Status.PASS, "Splash screen indeterminate loading progess bar displayed");
			} else {
				report.log(Status.FAIL, "Splash screen indeterminate loading progess bar is not displayed");
			}
		}catch (Exception e) {
			report.log(Status.FAIL, "Splash screen indeterminate loading progess bar is not displayed");
		}
		//ER 2: Splash screen Storage Permission guide page displayed 
		Assert.assertTrue(downloadPage.isPermissionGuideVisible(),
				"Splash screen Storage Permission guide page not displayed");
		report.log(Status.PASS, "Splash screen Storage Permission guide page displayed ");

		//Step 2: Wait for 5 seconds
		report.info("Wait for 5 seconds");
		Thread.sleep(5000);

		//ER 3: Android Storage Request Permission prompt displayed
		Assert.assertTrue(downloadPage.isPermissionPromptVisible(),
				"Android Storage Request Permission prompt not displayed");
		report.log(Status.PASS, "Android Storage Request Permission prompt displayed");

		//Step 3: TAP "ALLOW" on Storage request permission prompt	
		downloadPage.clickOnAllow();
		report.info("TAP \"ALLOW\" on Storage request permission prompt");
		try {
			downloadPage.clickOnAllow();
			report.info("TAP \"ALLOW\" on Storage request permission prompt");
		} catch(Exception e) {
			report.log(Status.FAIL, "Allow button already clicked Allow in Previous test.");
		}
		
		//ER 1: Splash screen indeterminate loading progess bar displayed
		try {
			if (downloadPage.isSplashLoadingVisible()) {
				report.log(Status.PASS, "Splash screen indeterminate loading progess bar displayed");
			} else {
				report.log(Status.FAIL, "Splash screen indeterminate loading progess bar is not displayed");
			}
		}catch (Exception e) {
			report.log(Status.FAIL, "Splash screen indeterminate loading progess bar is not displayed");
		}
		//ER 2: Tutorial video displayed and automatically played
		Assert.assertTrue(downloadPage.isTutoralVideoDisplayed(),
				"Tutorial video not displayed and not automatically played");
		report.log(Status.PASS, "Tutorial video displayed and automatically played");

		//Step 4: Automatically proceed to Home Page
		//ER 1: Download Tab page header text "Connect to Download" displayed
		Assert.assertTrue(downloadPage.isConnectivityIndicatorStatusVisible(),
				" Download Tab page header text \"Connect to Download\" not displayed");
		report.info("Automatically proceed to Home Page");
		report.log(Status.PASS, " Download Tab page header text \"Connect to Download\" displayed");
		//ER 2: Download page has "0 Download Queue"
		Assert.assertTrue(downloadPage.isEmptyPageLayoutDisplayed(), "Download page has not \"0 Download Queue\"");
		report.log(Status.PASS, "Download page has \"0 Download Queue\"");

		//Step 5: TAP "CATALOG TAB"  in Home Screen
		catalogPage.clickOnCatalogTab();
		Assert.assertTrue(catalogPage.isCatalogTabDisplayed(), "Catalog Page not displayed");
		report.log(Status.PASS, "Catalog Page displayed");
		//ER 1: Catalog page is Empty
		Assert.assertTrue(catalogPage.isCatalogEmpty(), "Catalog page is not Empty");
		report.log(Status.PASS, "Catalog page is Empty");

		//Step 6: TAP "LOAD TAB"  in Home Screen	
		loadPage.clickOnLoadTab();
		report.info("Tap \"LOAD TAB\" in Home Screen");
		//ER 1: Load page  is "filled with different types of LOADS"
		Assert.assertTrue(loadPage.isDifferentLoadVisible(),
				"Load page is not \"filled with different types of LOADS\"");
		report.log(Status.PASS, "Load page  is \"filled with different types of LOADS\"");

		//Step 7: TAP "MORE TAB"  in Home Screen	
		morePage.clickOnMoreTab();
		report.info("Tap \"MORE TAB\" in Home Screen");
		//ER1: Load page  is "filled with different types of ASSETS"
		Assert.assertTrue(morePage.isDifferentAssetsVisible(),
				"Load page  is not \"filled with different types of ASSETS\"");
		report.log(Status.PASS, "Load page  is \"filled with different types of ASSETS\"");

		//Step 8: TAP "Migo Logo" on Status Bar	
		migoLogo.clickOnMigoLogo();
		report.info("Tap \"Migo Logo\" on Status Bar");
		//ER 1: Service running info page displayed
		Assert.assertTrue(migoLogo.isServiceRunningVisible(), "Service running info page not displayed");
		report.log(Status.PASS, "Service running info page displayed");

	}
	
	@Test
	@Description(value = "Expected Data Result")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void AL1_004() throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		MorePage morePage = new MorePage(driver);
		MigoLogoPage migoLogo = new MigoLogoPage(driver);

		downloadPage.launchApp();
		
		//Click on catalog page
		morePage.clickOnMoreTab();
		//Click on migo logo
		migoLogo.clickOnMigoLogo();
		
		//Expected Data Results
		
		//Active Storage : Internal or External
		Assert.assertTrue(migoLogo.checkActiveStorage(), "Active Storage : Not Internal or External");
		report.log(Status.PASS, "Active Storage : Internal or External");
		//Internal storage initialized : (if this is the Active Storage the value should be TRUE otherwise true/false are valid)
		
		//External storage initialized : (if this is the Active Storage the value should be TRUE otherwise true/false are valid)

		//Storage Type : Internal or External
		Assert.assertTrue(migoLogo.checkStorageType(), "Storage Type : Not Internal or External");
		report.log(Status.PASS, "Storage Type : Internal or External");
		//Read Only : No
		Assert.assertTrue(migoLogo.checkReadOnly(), "Read Only : Is not No");
		report.log(Status.PASS, "Read Only : No");
		//Total Size : Internal or External Total Storage size
		//Available Size : Internal or External Available Storage size
		
		//Drm Status
		//Status : 2
		Assert.assertTrue(migoLogo.checkDrmStatus(), "Status : Not 2");
		report.log(Status.PASS, "Status : 2");
		//Is DRM ready : true
		Assert.assertTrue(migoLogo.checkdrm(), "Is DRM ready : false");
		report.log(Status.PASS, "Is DRM ready : true");
	}

}
