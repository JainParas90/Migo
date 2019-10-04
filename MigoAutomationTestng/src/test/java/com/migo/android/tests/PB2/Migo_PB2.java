package com.migo.android.tests.PB2;

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

public class Migo_PB2 extends UserBaseTest{
	

	Logger logger = Logger.getLogger(Migo_PB2.class.getName());
	
	double deductedMemory;
	double availableMemoryBeforeDownload;
	int xCorinate;
	
	@BeforeClass
	@Description(value = "Selecting a MOVIE for download while viewing its Title Card 1")
	@Parameters({"device"})
	@Author(name = "Sridatta")
	public void PB2_Precondition(String device) throws InterruptedException, IOException   {
		
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
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "video is not completed"); 
		report.log(Status.PASS, "video is completed");
				
		
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" Tab");
		catalogPage.clickOnDownloadOtherMoive();
		report.info("Tap any MOVIE's banner from any channel's carousel");
		catalogPage.clickOnDownlaodButton();
		report.info("Tap \"Download\" button");
		catalogPage.getCloseXButtonForAnotherMoive();
		report.info("Tap close (x) button on top right corner of Title Card");
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");
		downloadPage.finishDownloading();
		report.info("For precondition we need to download a moive before");
	}
	
	@Test
	@Description(value = "Selecting a MOVIE for download while viewing its Title Card")
	@Parameters({"device"})
	@Author(name = "Sridatta")
	public void PB2_001(String device) throws InterruptedException  {
	
		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);
		
		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();
		
		/** Step 1: Tap "Catalog" tab */
		/** #ER-1: Main "Catalog" page displayed */

		// Step 1:
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" Tab");

		// #ER-1:
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");

		/** Step 2: Tap any MOVIE's banner from any channel's carousel */
		/** #ER-1: Title Card displayed */

		// Step 2:
		catalogPage.tapOnMovieBanner();
		report.info("Tap any MOVIE's banner from any channel's carousel");
		

		// #ER-1:
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card is not displayed");
		report.log(Status.PASS, "Title Card is displayed");

		/** Step 3: Tap "Download" button */
		/** #ER-1: Title starts downloading */
		/** #ER-2: "Download" button changes into a "Cancel" button */

		// Step 3:
		catalogPage.clickOnDownlaodButton();
		report.info("Tap \"Download\" button");
		
		
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(),"Blue snackbar is not displayed");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");

		// #ER-2:
		Assert.assertTrue(catalogPage.isCancelDownloadButtonDisplayed("Cancel"), "Confirmation dialog is not displayed");
		report.log(Status.PASS, "\"Download\" button changes into a \"Cancel\" button");

		/** Step 4: Tap close (x) button on top right corner of Title Card */
		/** #ER-1: Title Card hidden */
		/** #ER-2: Progress bar displayed on "Downloads" tab */
		
		// Step 4:
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");

		// #ER-1:
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Title Card not hidden");
		report.log(Status.PASS, "Title Card hidden");

		// #ER-2:
		// #ER-2: Progress bar displayed on "Downloads" tab
		Assert.assertTrue(catalogPage.isProgressBarIsDisplayed(), "Progress bar is not displayed on \"Downloads\" tab");
		report.log(Status.PASS, "Progress bar displayed on \"Downloads\" tab");

		/** Step 5: Tap "Downloads" tab */
		/** #ER-1: "Downloads" page displayed */
		/**
		 * #ER-2: Title displayed as first one under "MOVIES" section on "Downloads"
		 * page
		 */
		/** #ER-3: [x] [MB, GB] dedud again we are getting below popup which is blcted from free space counter */

		// Step 5:
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");

		// #ER-1:
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		
		// #ER-2: Title starts downloading.
		Assert.assertTrue(downloadPage.isStartDownloading(), "Title not starts downloading");
		report.log(Status.PASS, "Title starts downloading");
				
		// #ER-3:
		Assert.assertEquals(downloadPage.getMoiveTag(), "MOVIES", "Title not displayed as first one under MOVIES section on Downloads page");
		report.log(Status.PASS, "Title displayed as first one under MOVIES section on Downloads page");

		// #ER-4:
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;
		System.out.println(availableMemoryBeforeDownload);
		System.out.println(availableMemoryAfterDownload);
		System.out.println(deductedMemory);
		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "[" + deductedMemory + "] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "[" + deductedMemory + "] [MB, GB] deducted from free space counter");
	}
	
	@Test
	@Description(value = "Selects a title to watch")
	@Parameters({"device"})
	@Author(name = "Sridatta")
	public void PB2_002() throws InterruptedException {
		
		DownloadPage downloadPage=new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		
		
		
		downloadPage.clickOnPlayButton();
		report.log(Status.PASS,"Tap any other downloaded title's play (▻) button from the \"Downloads\" page");
		
		/*DRM initializes and displays, "Ready, Set, Nood!"
		 *Age (MTC) rating of title is displayedreport.log(Status.PASS, "Title starts downloading");
		 *Migo logo animation (OBB) is displayed
	     *Title starts playing*/
		/***
		 * DRM initializes and displays, "Ready, Set, Nood!"(Doubt)
		 */
			
		//Age (MTC) rating of title is displayed
		Assert.assertTrue(downloadPage.isMtcRatingdisplayed(), "Age (MTC) rating of title is not displayed");
		report.log(Status.PASS,"Age (MTC) rating of title is displayed");
		
		
		/***
		 * *Migo logo animation (OBB) is displayed(can not be automatable)
		 */	
	
		//Title starts playing
		Assert.assertTrue(downloadPage.isTitleStratsPLaying(), "Title starts playing");
		
	}
	@Test
	@Description(value = "Skips to a latter part of the title")
	@Parameters({"device"})
	@Author(name = "Sridatta")
	public void PB2_003() throws InterruptedException {
		
		DownloadPage downloadPage=new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		
		
		Thread.sleep(5000);
		downloadPage.clickOnVideoScreen();
		report.log(Status.PASS,"After 5 seconds of playback, tap on any part of the screen");
		
		Assert.assertTrue(downloadPage.isPlayBackControlsDisplayed(), "Playback controls are displayed");
		report.log(Status.PASS, "Playback controls are displayed");
		
		/***
		 * DRM initializes and displays, "Ready, Set, Nood!"(Doubt)
		 */
		//Title continues playing from +180 seconds from the previous part
		
		

		// Step2: Drag seek bar circle to +180 seconds to the right
		downloadPage.clickOnVideoScreen();
		xCorinate=downloadPage.move180Sec();
		
		//Title continues playing from +180 seconds from the previous part
		Assert.assertTrue(downloadPage.isTitleStratsPLaying(), "Title continues playing from +180 seconds from the previous part");
				
		
		
		
		
	}
	
	@Test
	@Description(value = "Pauses playback")
	@Parameters({"device"})
	@Author(name = "Sridatta")
	public void PB2_004() throws InterruptedException {
	
		DownloadPage downloadPage=new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		
		downloadPage.tapOnScreen();
		double startTime=downloadPage.getTextOfMoiveTime();
		
		//Tap on pause (II) button beside the seek bar
		downloadPage.clickOnPlayIcon();
		report.log(Status.PASS,"Tap on pause (II) button beside the seek bar");
		
		//Title stops playing; screen image is frozen
		Assert.assertTrue(downloadPage.isTitlePlayedStopped(), "Title not stops playing; screen image is frozen");
		report.log(Status.PASS, "Title not stops playing; screen image is frozen");
		
		//Tap on back (<-) button on top left corner of screen
		downloadPage.clickOnVideoBackArrowButton();
		report.log(Status.PASS,"Tap on back (<-) button on top left corner of screen");
		
		//"Downloads" page displayed	downloadPage.disableWifi();
	
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		
		//Title played now has a delete (trash can) button on its left side
		Assert.assertTrue(downloadPage.isTrashCanVisible(), "Title not played now has a delete (trash can) button on its left side");
		report.log(Status.PASS, "Title played now has a delete (trash can) button on its left side");
		
		//Title played now displays progress bar below indicating last watched mark
		Assert.assertTrue(downloadPage.isTitleProgrssBarVisible(), "Title not played now displays progress bar below indicating last watched mark");
		report.log(Status.PASS, "Title played now displays progress bar below indicating last watched mark");
		
		//Tap on same title's play (▻) button
		downloadPage.clickOnPlayButton();
		report.log(Status.PASS,"Tap on same title's play (▻) button");
		
		//Title starts playing from last part where it was paused
		downloadPage.tapOnScreen();
		double endtime=downloadPage.getTextOfMoiveTime();
		if(startTime==endtime)
		{
			report.log(Status.PASS,"Title starts playing from last part where it was paused");
		}
		
	}
	
	@Test
	@Description(value = "Minimizes app while watching")
	@Parameters({"device"})
	@Author(name = "Sridatta")
	public void PB2_005() throws InterruptedException {
		
		DownloadPage downloadPage=new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		
		downloadPage.tapOnScreen();
		double startTime=downloadPage.getTextOfMoiveTime();
		
		//After 5 seconds of playback, tap on device's home (□) button
		Thread.sleep(5000);
		downloadPage.runInBackgroundApp();
		report.log(Status.PASS,"After 5 seconds of playback, tap on device's home (□) button");
		
		//Device's home screen displayed
		Assert.assertFalse(catalogPage.isAndroidHomeScreenDisplayed(), "Device's home screen  not displayed");
		report.log(Status.PASS, "Device's home screen displayed");
		
		/***
		 * Title stops playing(can not automate because we are in home page)
		 */
		
		//Open Migo app again	    
		downloadPage.openbackGroundAPP();
		report.log(Status.PASS, "Open Migo app again");
		
		
		//Title stops playing
		downloadPage.tapOnScreen();
		double endtime=downloadPage.getTextOfMoiveTime();
		if(startTime==endtime)
		{
			report.log(Status.PASS, "Title stops playing");
		}
		
		
		//Title starts playing again at last mark since app was minimized
		Assert.assertTrue(downloadPage.isplayingagainatlastmark(), "Title  not starts playing again at last mark since app was minimized");
		report.log(Status.PASS, "Title starts playing again at last mark since app was minimized");
		
		
	}
	
	@Test
	@Description(value = "Device is locked while waiting")
	@Parameters({"device"})
	@Author(name = "Sridatta")
	public void PB2_006() throws InterruptedException {
		
		DownloadPage downloadPage=new DownloadPage(driver);
				
		double startTime=downloadPage.getTextOfMoiveTime();
		
		//After 5 seconds of playback, lock device
		Thread.sleep(5000);;
		downloadPage.lockTheDevice();
		report.log(Status.PASS, "After 5 seconds of playback, lock device");
		
		//Device's lock screen is displayed
		Assert.assertTrue(downloadPage.isDeviceLocked(),"Device's lock screen is not displayed");	
		report.log(Status.PASS, "Device's lock screen is displayed");
		
		//Unlock device
		downloadPage.unlockTheDevice();
		report.log(Status.PASS, "Unlock device");
		
		//Title stops playing
		downloadPage.tapOnScreen();
		double endtime=downloadPage.getTextOfMoiveTime();
		if(startTime==endtime)
		{
		report.log(Status.PASS, "Title stops playing");
		}
		//Title starts playing again at last mark since app was minimized
		Assert.assertTrue(downloadPage.isplayingagainatlastmark(), "Title not starts playing again at last mark since app was minimized");
		report.log(Status.PASS, "Title starts playing again at last mark since app was minimized");
		
	}
	
	@Test
	@Description(value = "Reaches completion marker for title")
	@Parameters({"device"})
	@Author(name = "Sridatta")
	public void PB2_007() throws InterruptedException {
		
		DownloadPage downloadPage=new DownloadPage(driver);
		
		System.out.println("Scroll to last...............");
		//After 5 seconds of playback, drag seek bar circle to very near the end of the title
		Thread.sleep(5000);
		
		downloadPage.tapOnScreen();
		int ycordinate=downloadPage.getYCordinateOfSeekBar();
		downloadPage.scrollToLast(xCorinate,ycordinate);
		
		//Auto-play page is displayed
		Assert.assertTrue(downloadPage.isAutoPlayDisplayed(), "Auto-play page is not displayed");
		report.log(Status.PASS, "Auto-play page is not displayed");
		
		//App auto-plays next title in the queue
		
		/***
		 * Autoplay feature is not working manually also
		 */		
	}
		

	@Test
	@Description(value = "Disconnects from Migo Hotspot while watching")
	@Parameters({"device"})
	@Author(name = "Sridatta")
	public void PB2_008() throws InterruptedException, IOException {
		
		DownloadPage downloadPage=new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		
		
		downloadPage.tapOnScreen();
		double startTime=downloadPage.getTextOfMoiveTime();
		
		//After 5 seconds of playback, swipe down screen from topmost part of screen
		
		Thread.sleep(5000);
		catalogPage.clickOnopenNotificationDrawer();
		report.log(Status.PASS, "After 5 seconds of playback, swipe down screen from topmost part of screen");
		
		//WiFi connection disabled
		downloadPage.disableWifi();
		report.log(Status.PASS, "WiFi connection disabled");
		
		//Title continues playing

		Assert.assertTrue(downloadPage.isTitleStratsPLaying(), "Title continues playing");
		report.log(Status.PASS, "Title continues playing");
		
		
			
	}
	
	}

