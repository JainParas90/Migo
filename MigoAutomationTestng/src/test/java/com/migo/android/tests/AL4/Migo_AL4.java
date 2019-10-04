package com.migo.android.tests.AL4;
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

public class Migo_AL4 extends UserBaseTest {

	Logger logger = Logger.getLogger(Migo_AL4.class.getName());

	@BeforeClass
	@Description(value = "Returning user launches app while offline")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void AL4_Precondition(String device) throws InterruptedException, IOException {

		DownloadPage downloadPage = new DownloadPage(driver);

		//Turn Device WIFI Off
		downloadPage.disableWifi();

	}

	@Test
	@Description(value = "User can Open his/her Migo App for the [x] times while offline")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void AL4_001() throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		LoadPage loadPage = new LoadPage(driver);
		MorePage morePage = new MorePage(driver);
		MigoLogoPage migoLogo = new MigoLogoPage(driver);

		//Step 1: Open Migo App
		report.info("Open Migo App");
		//ER 1: Splash screen indeterminate loading progess bar displayed
		if (downloadPage.isSplashLoadingVisible()) {
			report.log(Status.PASS, "Splash screen indeterminate loading progess bar displayed");
		} else {
			report.log(Status.FAIL, "Splash screen indeterminate loading progess bar is not displayed");
		}

		//Step 2: Automatically proceed to Home Page
		//ER 1: Download Tab page header text "Connect to Download" displayed
		Assert.assertTrue(downloadPage.isConnectivityIndicatorStatusVisible(),
				" Download Tab page header text \"Connect to Download\" not displayed");
		report.info("Automatically proceed to Home Page");
		report.log(Status.PASS, " Download Tab page header text \"Connect to Download\" displayed");
		//ER 2: Download page has "0 Download Queue"
		Assert.assertTrue(downloadPage.isEmptyPageLayoutDisplayed(), "Download page has not \"0 Download Queue\"");
		report.log(Status.PASS, "Download page has \"0 Download Queue\"");

		//Step 3: TAP "CATALOG TAB"  in Home Screen
		catalogPage.clickOnCatalogTab();
		Assert.assertTrue(catalogPage.isCatalogTabDisplayed(), "Catalog Page not displayed");
		report.log(Status.PASS, "Catalog Page displayed");
		//ER 1: Catalog page is "filled with different types of CHANNELS"
		Assert.assertTrue(catalogPage.isMovieBannerDisplayed(),
				"Catalog page is not \"filled with different types of CHANNELS\"");
		report.log(Status.PASS, "Catalog page is \"filled with different types of CHANNELS\"");

		//Step 4: TAP "LOAD TAB"  in Home Screen	
		loadPage.clickOnLoadTab();
		report.info("Tap \"LOAD TAB\" in Home Screen");
		//ER 1: Load page  is "filled with different types of LOADS"
		Assert.assertTrue(loadPage.isDifferentLoadVisible(),
				"Load page is not \"filled with different types of LOADS\"");
		report.log(Status.PASS, "Load page  is \"filled with different types of LOADS\"");

		//Step 5: TAP "MORE TAB"  in Home Screen	
		morePage.clickOnMoreTab();
		report.info("Tap \"MORE TAB\" in Home Screen");
		//ER1: Load page  is "filled with different types of ASSETS"
		Assert.assertTrue(morePage.isDifferentAssetsVisible(),
				"Load page  is not \"filled with different types of ASSETS\"");
		report.log(Status.PASS, "Load page  is \"filled with different types of ASSETS\"");

		//Step 6: TAP "Migo Logo" on Status Bar	
		migoLogo.clickOnMigoLogo();
		report.info("Tap \"Migo Logo\" on Status Bar");
		//ER 1: Service running info page displayed
		Assert.assertTrue(migoLogo.isServiceRunningVisible(), "Service running info page not displayed");
		report.log(Status.PASS, "Service running info page displayed");

	}

}
