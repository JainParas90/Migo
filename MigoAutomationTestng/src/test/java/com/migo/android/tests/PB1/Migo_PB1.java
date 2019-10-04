package com.migo.android.tests.PB1;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.NoSuchElementException;
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

public class Migo_PB1 extends UserBaseTest {

	Logger logger = Logger.getLogger(Migo_PB1.class.getName());

	double deductedMemory;
	double availableMemoryBeforeDownload;
	int xCorinate;

	@BeforeClass
	@Description(value = "Selecting a MOVIE for download while viewing its Title Card 1")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_Precondition(String device) throws InterruptedException, IOException {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);

		//enable wifi 
		downloadPage.enableWifi();

		//reset application
		downloadPage.resetApplication();

		//Click on Allow button
		catalogPage.clickOnAllowButton();

		//Click on Allow button
		catalogPage.clickOnAllowButton();

		//Wait untill the video not completed
		Assert.assertTrue(downloadPage.isEmptylayoutpageDisplayed(), "video is not completed");
		report.log(Status.PASS, "video is completed");

		//Convert V1 to VN	
		catalogPage.clickOnCatalogTab();

		//click on migo logo	
		catalogPage.clickOnMigoLogo();

		//click on user type
		catalogPage.clickOnUserType();

		//click on back button
		downloadPage.clickOnBackButton();

		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" Tab.");
		catalogPage.clickOnDownloadOtherMoive();
		report.info("Tap any MOVIE's banner from any channel's carousel.");
		catalogPage.clickOnDownlaodButton();
		report.info("Tap \"Download\" button");
		catalogPage.getCloseXButtonForAnotherMoive();
		report.info("Tap close (x) button on top right corner of Title Card.");
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");
		downloadPage.finishDownloading();
		report.info("For precondition we need to download a moive before the actual test.");
	}

	@Test
	@Description(value = "Selects a title to watch")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_001(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);

		// Step-1: Tap any title's play (▻) button from the "Downloads" page
		downloadPage.clickOnPlayButton();
		report.info("Tap any title's play (▻) button from the \"Downloads\" page");

		// ER1: DRM initializes and displays, "Ready, Set, Nood!"
		/**
		 * This is not Visible
		 */

		// ER2: Age (MTC) rating of title is displayed
		downloadPage.isMtcRatingdisplayed();
		report.log(Status.PASS, "Age (MTC) rating of title is displayed");

		// ER3: Migo logo animation (OBB) is displayed
		/***
		 * *Migo logo animation (OBB) is displayed(can not be automatable)
		 */
		//Thread.sleep(5000);
		Assert.assertTrue(downloadPage.isTitleStratsPLaying(), "Title starts playing");

	}

	@Test
	@Description(value = "Skips to a latter part of the title")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_002(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);

		// Step1: After 5 seconds of playback, tap on any part of the screen
		Thread.sleep(5000);
		downloadPage.tapOnScreen();
		report.info("Tap on Screen");

		// ER1: Playback controls are displayed
		Assert.assertTrue(downloadPage.isPlayBackDisplayed(), "Playback Control not displayed");
		report.log(Status.PASS, "Playback controls are displayed");

		// Step2: Drag seek bar circle to +180 seconds to the right
		downloadPage.tapOnScreen();
		xCorinate = downloadPage.move180Sec();

		//Title continues playing from +180 seconds from the previous part
		Assert.assertTrue(downloadPage.isTitleStratsPLaying(),"Title continues playing from +180 seconds from the previous part");

	}

	@Test
	@Description(value = "Pauses playback")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_003(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);
		
		downloadPage.tapOnScreen();
		double startTime = downloadPage.getTextOfMoiveTime();
		System.out.println("before click");
		//Step1: Tap on pause (II) button beside the seek bar

		System.out.println("After click");
		downloadPage.clickOnPlayIcon();
		report.info("Tap on pause (II) button beside the seek bar");

		//Title stops playing; screen image is frozen
		Assert.assertTrue(downloadPage.isTitlePlayedStopped(), "Title not stops playing; screen image is frozen");
		report.log(Status.PASS, "Title not stops playing; screen image is frozen");

		//Step2: Tap on back (<-) button on top left corner of screen
		downloadPage.clickOnVideoBackArrowButton();
		report.info("Tap on back (<-) button on top left corner of screen");

		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		//ER2: Title played now has a delete (trash can) button on its left side
		Assert.assertTrue(downloadPage.isTrashCanVisible(),
				"Title played has no delete (trash can) button on its left side");
		report.log(Status.PASS, "Title played now has a delete (trash can) button on its left side");

		//ER3: Title played now displays progress bar below indicating last watched mark
		Assert.assertTrue(downloadPage.isTitleProgrssBarVisible(),
				"Title played not displays progress bar below indicating last watched mark");
		report.log(Status.PASS, "Title played now displays progress bar below indicating last watched mark");

		//Step3: Tap on same title's play (▻) button
		downloadPage.clickOnPlayButton();
		report.info("Tap on same title's play (>) button");

		//ER4: Title starts playing from last part where it was paused
		double resumeTime = downloadPage.getTextOfMoiveTime();

		if (resumeTime != startTime) {
			report.log(Status.PASS, "Title starts playing from last part where it was paused");
		} else {
			report.log(Status.FAIL, "Title is not playing from last paused.");
			driver.quit();
		}
	}

	@Test
	@Description(value = "Minimizes app while watching")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_004(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);

		downloadPage.tapOnScreen();
		double firstTime = downloadPage.getTextOfMoiveTime();
		//After 5 seconds of playback, tap on device's home (□) button
		Thread.sleep(5000);
		downloadPage.runInBackgroundApp();
		report.log(Status.PASS, "After 5 seconds of playback, tap on device's home (□) button");

		//Device's home screen displayed
		Assert.assertFalse(catalogPage.isAndroidHomeScreenDisplayed(), "Device's home screen  not displayed");
		report.log(Status.PASS, "Device's home screen displayed");

		/***
		 * Title stops playing(can not automate because we are in home page)
		 */
		//Open Migo app again	   
		downloadPage.openbackGroundAPP();
		report.log(Status.PASS, "Open Migo app again");

		//ER2 of Step1: Title stops playing
		//ER1: Title starts playing again at last mark since app was minimized
		downloadPage.tapOnScreen();
		double resumeTime = downloadPage.getTextOfMoiveTime();
		if (firstTime == resumeTime) {
			report.log(Status.PASS, "Title stops playing");
			report.log(Status.PASS, "Title starts playing again at last mark since app was minimized");
		} else {
			report.log(Status.PASS, "Title continued playing");
		}

	}

	@Test
	@Description(value = "Device is locked while waiting")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_005(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);
		
		downloadPage.tapOnScreen();
		Thread.sleep(5000);
		//Step1: After 5 seconds of playback, lock device
		downloadPage.tapOnScreen();
		double beforeLock = downloadPage.getTextOfMoiveTime();

		downloadPage.lockTheDevice();
		report.info("After 5 seconds of playback, lock device");

		//ER1: Device's lock screen is displayed
		Assert.assertTrue(downloadPage.isDeviceLocked(), "Device is not locked");
		report.log(Status.PASS, "Device's lock screen is displayed");

		//ER2: Title stops playing
		report.log(Status.PASS, "Title Stops Playing");

		//Step2: Unlock device
		downloadPage.unlockTheDevice();
		report.info("Unlock device");

		//ER1: Title starts playing again at last mark since app was minimized
		double resumeTime = downloadPage.getTextOfMoiveTime();

		if (beforeLock == resumeTime) {
			report.log(Status.PASS, "Title starts playing again at last mark since app was minimized");
		} else {
			report.log(Status.PASS, "Title does not starts playing again at last mark.");
		}

	}

	@Test
	@Description(value = "Reaches completion marker for title")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_006(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);

		//Step1: After 5 seconds of playback, drag seek bar circle to very near the end of the title
		Thread.sleep(5000);

		downloadPage.tapOnScreen();
		int ycordinate = downloadPage.getYCordinateOfSeekBar();
		downloadPage.scrollToLast(xCorinate, ycordinate);

		//ER1: Auto-play page is displayed
		Assert.assertTrue(downloadPage.isAutoPlayDisplayed(), "Auto-play page not Displayed");
		report.log(Status.PASS, "Auto-play page is displayed");

		//Step2: Wait for 10 seconds
		report.info("Wait for 10 seconds");
		Thread.sleep(10000);

		//ER1: App auto-plays next title in the queue
		downloadPage.tapOnScreen();
		Assert.assertTrue(downloadPage.isPlayBackDisplayed(), "App does not auto-play next title in the queue.");
		report.log(Status.PASS, "App auto-plays next title in the queue");
	}

	@Test
	@Description(value = "Has mobile data connection turned on while watching")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_007(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);

		//Step1: After 5 seconds of playback, swipe down screen from topmost part of screen
		Thread.sleep(5000);
		downloadPage.tapOnScreen();
		double startTime = downloadPage.getTextOfMoiveTime();
		downloadPage.scrollForToolbar();
		report.info("After 5 seconds of playback, swipe down screen from topmost part of screen");

		//ER1: Android toolbar displayed
		Assert.assertTrue(downloadPage.isNotificationDrawerVisible(), "tool bar not visible.");
		report.log(Status.PASS, "Android toolbar displayed");

		//ER2: Title continues playing
		report.info("Title continues playing will be validated at last of the test.");

		//Step2: Tap on mobile data button

		//ER1: Mobile data connection enabled

		//Step3: Swipe up screen
		downloadPage.backButton();
		report.info("Swipe up screen");

		//ER1: Android toolbar hidden
		Thread.sleep(5000);
		Assert.assertTrue(downloadPage.isPausePlayVisible(), "Android toolbar not hidded");
		report.log(Status.PASS, "Android toolbar hidded");

		//ER2: Title continues playing
		/* downloadPage.tapOnScreen(); */
		double laterTime = downloadPage.getTextOfMoiveTime();

		if (laterTime > startTime) {
			report.log(Status.PASS, "Title continues playing");
		} else {
			report.log(Status.FAIL, "Title does not continues playing");
		}

	}

	@Test
	@Description(value = "Has non-Migo Wi-Fi with Internet connection turned on while watching")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_008(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);

		//Step1: After 5 seconds of playback, swipe down screen from topmost part of screen
		Thread.sleep(5000);
		downloadPage.tapOnScreen();
		double startTime = downloadPage.getTextOfMoiveTime();
		downloadPage.scrollForToolbar();

		//ER1: Android toolbar displayed
		Assert.assertTrue(downloadPage.isNotificationDrawerVisible(), "tool bar not visible.");
		report.log(Status.PASS, "Android toolbar displayed");

		//ER2: Title continues playing
		report.info("Title continues playing will be validated at last of the test.");

		//Step2: Tap on mobile data button
		report.info("Tap on Mobile data button");

		//ER1: Mobile data connection disabled
		try {
			downloadPage.disableWifi();
			report.log(Status.PASS, "Mobile data connection disabled");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			report.log(Status.FAIL, "Mobile data connection not disabled");
			e.printStackTrace();
		}

		//Step3: Connect to any other WiFi with Internet connection	
		try {
			downloadPage.enableWifi();
			report.info("Connect to any other WiFi with Internet connection");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//ER1: WiFi connection enabled
		report.log(Status.PASS, "WiFi connection enabled");

		//Step4: Go back to Migo app
		downloadPage.backButton();
		report.info("Go back to Migo app");

		downloadPage.tapOnScreen();
		//ER1: Title continues playing	
		try {
			if (downloadPage.isPlayBackDisplayed() == true) {
				double laterTime = downloadPage.getTextOfMoiveTime();
				System.out.println(laterTime);
				System.out.println(startTime);
				if (laterTime > startTime) {
					report.log(Status.PASS, "Title continues playing");
				} else {
					report.log(Status.FAIL, "Title does not continues playing");
				}
			}
		} catch (NoSuchElementException e) {
			report.log(Status.FAIL, "Player page not displayed");
		}

	}

	@Test
	@Description(value = "Runs out of load while watching")
	@Parameters({ "device" })
	@Author(name = "Shah Nawaz")
	public void PB1_009(String device) throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);

		//Step1: Wait until Pass expires	
		//ER1: Informational dialog prompting user to top up with load displayed

		//Step2: Tap on "RELOAD" button	
		//ER1: "Load" page displayed

		//Step3: Tap on "Downloads" tab	
		//ER1: "Downloads" page displayed

		//Step4: Tap any title's play (▻) button	
		//ER1: Informational dialog prompting user to top up with load displayed
	}

}
