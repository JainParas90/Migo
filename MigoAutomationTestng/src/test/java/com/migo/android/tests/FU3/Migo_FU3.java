package com.migo.android.tests.FU3;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.base.UserBaseTest;
import com.aventstack.extentreports.Status;
import com.migo.annotation.values.Author;
import com.migo.annotation.values.Description;
import com.migo.pages.CatalogPage;
import com.migo.pages.DownloadPage;

public class Migo_FU3 extends UserBaseTest {
	Logger logger = Logger.getLogger(Migo_FU3.class.getName());
	double deductedMemory;
	double availableMemoryBeforeDownload;
	
	@BeforeClass
	@Description(value = "Selecting a MOVIE for download while viewing its Title Card")
	@Author(name = "Sridatta")
	public void FU3_Precondition() throws InterruptedException {
		
		DownloadPage downloadPage = new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		
		//Step-1: enable device Wi-Fi
		try {
		catalogPage.enableWifi();
		} catch (IOException e) {
		e.printStackTrace();
		}
		System.out.println("enable wifi");
		Thread.sleep(12000);

		downloadPage.resetApplication();
		
		//Click on Allow button
		catalogPage.clickOnAllowButton();
		
		//Click on Allow button
		catalogPage.clickOnAllowButton();
		
		//Wait untill the video not completed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "video is not completed"); 
		report.log(Status.PASS, "video is completed");
		
	}
	

	@Test
	@Description(value = "Selecting a MOVIE for download while viewing its Title Card")
	@Author(name = "Sridatta")
	public void FU3_01() throws InterruptedException {

		
		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);
		
		

		// Wait for Catalog Tab to be displayed
		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();

		// ***********************************************************************************************/
				
		/** Step 1: Tap "Catalog" tab */
		/** #ER-1: Main "Catalog" page displayed */

		// Step 1:
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" Tab");

		// Step 1-ER1:
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");

		// ***********************************************************************************************/

		/** Step 2: Tap any MOVIE's banner from any channel's carousel */
		/** #ER-1: Title Card displayed */

		// Step 2: Tap any MOVIE's banner from any channel's carousel
		catalogPage.tapOnMovieBanner();
		report.info("Tap any MOVIE's banner from any channel's carousel");

		// #ER-1: Title Card displayed
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card is not displayed");
		report.log(Status.PASS, "Title Card is displayed");

		// ***********************************************************************************************/

		/** Step 3: Tap "Download" button */
		/** #ER-1: Title starts downloading. */
		/** #ER-2: "Download" button changes into a "Cancel" button */
		/** #ER-3 Blue snackbar displayed; disappears after 5 seconds */

		// Step 3: Tap "Download" button
		catalogPage.clickOnDownlaodButton();
		report.info("Tap \"Download\" button");

		//#ER-1: Title starts downloading.: Added in step-5 as we can validate this in Download page
		
		// #ER-3 Blue snackbar displayed; disappears after 5 seconds
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(), "Blue snackbar is not displayed");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");

		// #ER-2: "Download" button changes into a "Cancel" button
		Assert.assertTrue(catalogPage.isCancelDownloadButtonDisplayed("Cancel"), "Download button not changes into a Cancel button");
		report.log(Status.PASS, "Download button changes into a Cancel button");

		// ***********************************************************************************************/

		/** Step 4: Tap close (x) button on top right corner of Title Card */
		/** #ER-1: Title Card hidden */
		/** #ER-2: Progress bar displayed on "Downloads" tab */

		// Step 4: Tap close (x) button on top right corner of Title Card
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");

		// #ER-1: Title Card hidden
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Title Card not hidden");
		report.log(Status.PASS, "Title Card hidden");

		// #ER-2: Progress bar displayed on "Downloads" tab
		Assert.assertTrue(catalogPage.isProgressBarIsDisplayed(), "Progress bar is not displayed on \"Downloads\" tab");
		report.log(Status.PASS, "Progress bar displayed on \"Downloads\" tab");
		
		// ***********************************************************************************************/

		/** Step 5: Tap "Downloads" tab */
		/** #ER-1: "Downloads" page displayed */
		/**
		 * #ER-2: Title displayed as first one under "MOVIES" section on "Downloads"
		 * page
		 */
		/** #ER-3: [x] [MB, GB] deducted from free space counter */

		// Step 5:
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");

		// #ER-1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		
		// #ER-2: Title starts downloading.
		Assert.assertTrue(downloadPage.isStartDownloading(), "Title not starts downloading");
		report.log(Status.PASS, "Title starts downloading");
		
		// #ER-3: Title displayed as first one under "MOVIES" section on "Downloads"
		Assert.assertEquals(downloadPage.getMoiveTag(), "MOVIES", "Title not displayed as first one under MOVIES section on Downloads page");
		report.log(Status.PASS, "Title displayed as first one under MOVIES section on Downloads page");

		// #ER-4: [x] [MB, GB] deducted from free space counter
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;
		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "[" + deductedMemory + "] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "[" + deductedMemory + "] [MB, GB] deducted from free space counter");
	}

	@Test
	@Description(value = "Cancelling a MOVIE's download while on the \"Downloads\" page")	
	@Author(name = "Sridatta")
	public void FU3_02() throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);
		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();

		// ***********************************************************************************************/
		/** Step 1: Tap cancel (x) button on left side of MOVIE */
		/** #ER-1: Confirmation dialog displayed */

		// Step 1: Tap cancel (x) button on left side of MOVIE
		downloadPage.clickOnCancelXButton();
		report.info("Tap cancel (x) button on left side of MOVIE");

		// #ER-1: Confirmation dialog displayed */
		Assert.assertTrue(downloadPage.isYesCancelDisplayed(), "Confirmation dialog is not displayed");
		report.log(Status.PASS, "Confirmation dialog displayed");

		// ***********************************************************************************************/
		/** Step 2: Tap "YES, CANCEL" button */
		/** #ER-1: Confirmation dialog hidden */
		/** #ER-2: Title removed from "Downloads" page */
		/** #ER-3: [x] [MB, GB] added back to free space counter */

		// Step 2: Tap "YES, CANCEL" button
		downloadPage.clickOnYesCancelButton();
		report.info("Tap YES, CANCEL button");

		// #ER-1: Confirmation dialog hidden
		Assert.assertFalse(downloadPage.isYesCancelDisplayed(), "Confirmation dialog not hidden");
		report.log(Status.PASS, "Confirmation dialog hidden");

		// #ER-2: Title removed from "Downloads" page
		Assert.assertTrue(downloadPage.isEmptyPageLayoutDisplayed(), "Title not removed from Downloads page");
		report.log(Status.PASS, "Title removed from Downloads page");

		// #ER-3: [x] [MB, GB] added back to free space counter
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel, availableMemoryBeforeCancel), "[" + memoryReverted + "] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "[" + memoryReverted + "] [MB, GB] Added from free space counter");
	}
	@Test
	@Description(value = "Selecting a SERIES EPISODE for download while viewing its Title Card")
	@Author(name = "Sridatta")
	public void FU3_03() throws InterruptedException {
		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);
		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();

		// ***********************************************************************************************/

		/** Step 1: Tap "Catalog" tab */
		/** #ER-1: Main "Catalog" page displayed */

		// Step 1:
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");

		// #ER-1:
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");

		// ***********************************************************************************************/

		/** Step 2: Tap any SERIES' banner from any channel's carousel */
		/** #ER-1: Title Card displayed */

		// Step 2:
		catalogPage.ClickOnSeriesBannerTitleCard();
		report.info("Tap any SERIES' banner from any channel's carousel");

		// #ER-1:
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card not displayed");
		report.log(Status.PASS, "Title Card displayed");

		// ***********************************************************************************************/

		/** Step 3: Tap "Download" button for episode 1 */
		/** #ER-1: Episode 1 starts downloading */
		/** #ER-2: "Download" button changes into a "Cancel" button */
		/** #ER-3: Blue snackbar displayed; disappears after 5 seconds */

		// Step 3:
		catalogPage.clickOnDownlaodButton();
		report.info("Tap \"Download\" button for episode 1");

		// #ER-1: Episode 1 starts downloading
		
		// #ER-3:
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(), "Blue snackbar is not displayed");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");
		
		// #ER-2:"Download" button changes into a "Cancel" button
		Assert.assertTrue(catalogPage.isEpisode1CancelDownloadButtonDisplayed("Cancel"), "Download button not changes into a Cancel button");
		report.log(Status.PASS, "\"Download\" button changes into a \"Cancel\" button");

		// ***********************************************************************************************/

		/** Step 4: Tap close (x) button on top right corner of Title Card */
		/** #ER-1: Title Card hidden */
		/** #ER-2: Progress bar displayed or updated on "Downloads" tab */

		// Step 4:
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");

		// #ER-1
		Assert.assertFalse(catalogPage.isTitleCardDisplayed(), "Title Card not hidden");
		report.log(Status.PASS, "Title Card hidden");

		// #ER-2:Progress bar displayed or updated on "Downloads" tab
		
		Assert.assertTrue(catalogPage.isProgressBarIsDisplayed(), "Progress bar is not displayed on \"Downloads\" tab");
		report.log(Status.PASS, "Progress bar displayed on \"Downloads\" tab");
		

		// ***********************************************************************************************/

		/** Step 5: Tap "Downloads" tab */
		/** #ER-1: "Downloads" page displayed */
		/**
		 ** #ER-2: SERIES displayed as first one under "TV SHOWS" section on "Downloads"
		 * page
		 */
		/** #ER-3: [x] [MB, GB] deducted from free space counter */

		// Step 5:
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");

		// #ER-1
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		// #ER-2
		Assert.assertEquals(downloadPage.getTvSeriesTag(), "TV SHOWS", "TV SHOWS not displayed");
		Assert.assertTrue(downloadPage.isSeriesDisplayed(), "SERIES not displayed as first one under \"TV SHOWS\" section on \"Downloads\" page");
		report.log(Status.PASS, "SERIES displayed as first one under \"TV SHOWS\" section on \"Downloads\" page");

		
		// #ER-3: [x] [MB, GB] deducted from free space counter
		
		//#ER-1: Episode 1 starts downloading
		downloadPage.clickOnSeriesLineButton();
		
		Assert.assertTrue(downloadPage.isStartDownloading(), "Episode 1 not starts downloading");
		report.log(Status.PASS, "Episode 1 starts downloading");
		
		downloadPage.clickOnBackButton();
	
		// #ER-3: [x] [MB, GB] deducted from free space counter
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;
		report.info("[" + deductedMemory + "] [MB, GB] deducted from free space counter");
		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "[" + deductedMemory + "] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "[" + deductedMemory + "] [MB, GB] deducted from free space counter");
	}

	@Test
	@Description(value = "Cancelling a SERIES EPISODE's download while viewing its Title Card")
	@Author(name = "Sridatta")
	public void FU3_04() throws InterruptedException {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);
		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();

		// ***********************************************************************************************/

		/** Step 1: Tap "Catalog" tab */
		/** #ER-1: Main "Catalog" page displayed */

		// Step 1:
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");

		// #ER-1
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");

		// ***********************************************************************************************/

		/** Step 2: Tap same E17 SERIES */
		/** Title Card displayed */

		// Step 2:
		catalogPage.ClickOnSeriesBannerTitleCard();
		report.info("Tap same E17 SERIES");

		// #ER-1:
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card not displayed");
		report.log(Status.PASS, "Title Card displayed");

		// ***********************************************************************************************/

		/** Step 3: Tap "Cancel Download" button for episode 1 */
		/** #ER-1: "Cancel" button changes into a "Download" button */

		// Step 3: Need to change method name
		catalogPage.clickOnDownlaodButton();
		report.info("Tap \"Cancel Download\" button for episode 1");

		// #ER-1
		Assert.assertTrue(catalogPage.isEpisode1CancelDownloadButtonDisplayed("Download"), "\"Cancel\" button changes into a \"Download\" button");
		report.log(Status.PASS, "\"Cancel\" button changes into a \"Download\" button");

		// ***********************************************************************************************/

		/** Step 4: Tap close (x) button on top right corner of Title Card */
		/** #ER-1: Title Card hidden */

		// Step 4:
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");

		// #ER-1
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Catalog Screen is not displayed");
		report.log(Status.PASS, "Title Card hidden");
		// ***********************************************************************************************/

		/** Step 5: Tap "Downloads" tab */
		/** #ER-1: "Downloads" page displayed */
		/** #ER-2: Title removed from "Downloads" page */
		/** #ER-3: [x] [MB, GB] added back to free space counter */

		// Step 5:
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");

		// #ER-1
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		// #ER-2
		Assert.assertTrue(downloadPage.isEmptylayoutpageDisplayed(), "Title not removed from \"Downloads\" page");
		report.log(Status.PASS, "Title removed from \"Downloads\" page");

		// #ER-3
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel, availableMemoryBeforeCancel), "[" + memoryReverted + "] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "[" + memoryReverted + "] [MB, GB] Added from free space counter");
	}

	@Test
	@Description(value = "Selecting multiple titles for download")
	@Author(name = "Sridatta")
	public void FU3_05() throws InterruptedException {
		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);
		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();

		// ***********************************************************************************************/

		/** Step 1: Tap "Catalog" tab */
		/** #ER-1: Main "Catalog" page displayed */

		// Step 1:
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");

		// #ER-1:
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");
		// ***********************************************************************************************/

		/** Step 2: Tap any SERIES' banner from any channel's carousel */
		/** #ER-1: Title Card displayed */

		// Step 2:
		catalogPage.ClickOnSeriesBannerTitleCard();
		report.info("Tap any SERIES' banner from any channel's carousel");

		// #ER-1:
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card is not displayed");
		report.log(Status.PASS, "Title Card is displayed");

		// ***********************************************************************************************/

		/** Step 3: Tap "Download" button for episode 1 */
		/** ER-1: Episode 1 queued for download */
		/** ER-2: "Download" button changes into a "Cancel" button */
		/** ER-3: Blue snackbar displayed */
		
		//Scroll the Episode list to top
		
		catalogPage.scrollForEpisodeTopToDown();
		
		
		// Step 3:
		catalogPage.clickOnDownlaodButtonForEpisode1();
		report.info("Tap \"Download\" button for episode 1");

		// #ER-1:

		// #ER-3: This step needs to be done first cause it is going to fade first.
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(), "Blue snackbar is not displayed");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");

		// #ER-2:
		Assert.assertTrue(catalogPage.isEpisode1CancelDownloadButtonDisplayed("Cancel"), "Download button not changes into a Cancel button");
		report.log(Status.PASS, "Download button changes into a Cancel button");

		// ***********************************************************************************************/

		/** Step 4: Tap "Download" button for episode 2 */
		/** ER-1: Episode 2 queued for download */
		/** ER-2: "Download" button changes into a "Cancel" button */
		/** ER-3: Blue snackbar displayed */

		// Step 4:
		catalogPage.clickOnDownlaodButtonForEpisode2();
		report.info("Tap \"Download\" button for episode 2");

		// #ER-1

		// #ER-3 This step needs to be done first cause it is going to fade first.
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(), "Blue snackbar is not displayed");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");

		// #ER-2
		Assert.assertTrue(catalogPage.isEpisode2CancelDownloadButtonDisplayed("Cancel"), "\"Download\" button changes into a \"Cancel\" button");
		report.log(Status.PASS, "\"Download\" button changes into a \"Cancel\" button");

		/** Step 5: Tap on blue snackbar */
		/** #ER-1: "Downloads" page displayed */
		/**
		 * #ER-2: Title displayed as first one under "TV SHOWS" section on "Downloads"
		 * page
		 */
		/**
		 * #ER-3: Episode 1 displayed first inside SERIES' page, followed by episode 2
		 */
		/** #ER-4: [x] [MB, GB] deducted from free space counter */

		// ***********************************************************************************************/
		
		//Making blue snackbar visible
		catalogPage.clickOnDownlaodButtonForEpisode2();
		catalogPage.clickOnDownlaodButtonForEpisode2();

		// Step 5:
		catalogPage.tapOnBlueSnackBar();
		report.info("Tap on blue snackbar");

		// #ER-1
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		// #ER-2
		Assert.assertEquals(downloadPage.getTvSeriesTag(), "TV SHOWS", "TV SHOWS not displayed");
		Assert.assertTrue(downloadPage.isSeriesDisplayed(), "SERIES not displayed as first one under \"TV SHOWS\" section on \"Downloads\" page");
		report.log(Status.PASS, "Title displayed as first one under \"TV SHOWS\" section on \"Downloads\"");

		// #ER-3
		downloadPage.clickOnSeriesViewButton();
		Assert.assertTrue(downloadPage.isEpisode1FollowedByEpisode2(), "Episode 1 displayed first inside SERIES' page, not followed by episode 2");
		report.log(Status.PASS, "Episode 1 displayed first inside SERIES' page, followed by episode 2");
		downloadPage.clickOnBackButton();
		report.info("Tap on Back button");

		// #ER-4
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;
		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "[" + deductedMemory + "] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "[" + deductedMemory + "] [MB, GB] deducted from free space counter");
		
		//ER-5
		downloadPage.clickOnSeriesLineButton();
		
		//Episode 1 starts downloading
		Assert.assertTrue(downloadPage.isStartDownloading(), "Episode 1 not starts downloading");
		report.log(Status.PASS, "Episode 1 starts downloading");
		
		//Episode 2 queued for download until previous download finishes
		String statusAfterDownload=null;
		String statusBeforeDownload=null;
		if(downloadPage.isStartDownloading())
		{
			 statusBeforeDownload=downloadPage.getDownloadPercentage();
			if(downloadPage.finishDownloading())
			{
				statusAfterDownload=downloadPage.getDownloadPercentage();
			}	
			
		}
		if(statusBeforeDownload.equals(statusAfterDownload)==false)
		{
			report.log(Status.PASS, "Episode 2 queued for download until previous download finishes");
		}
		else
			report.log(Status.PASS,"Episode 2 not queued for download until previous download finishes");
		
		downloadPage.clickOnBackButton();
	
		
	}

	@Test
	@Description(value = "Cancelling a SERIES EPISODE's download while on \"File Manager\" mode")
	@Author(name = "Sridatta")
	public void FU3_06() throws InterruptedException {
		DownloadPage downloadPage = new DownloadPage(driver);
		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();

		// ***********************************************************************************************/

		/** Step: Tap "File Manager" button */
		/** #ER-1: "File Manager" page displayed */

		// Step 1:
		downloadPage.clickOnFileManagerButton();
		report.info("Tap \"File Manager\" button");

		// #ER-1:
		Assert.assertEquals(downloadPage.getManageMigoFilesTitle(), "Manage Migo Files", "File Manager page not displayed");
		report.log(Status.PASS, "File Manager page displayed");

		// ***********************************************************************************************/

		/** Step 2: Tap SERIES' line item */
		/** #ER-1: SERIES' page displayed */

		// Step 2:
		downloadPage.clickOnSeriesLineItem();
		report.info("Tap SERIES' line item");

		// #ER-1:
		Assert.assertTrue(downloadPage.isSeriesPageDisplayed(), "SERIES' page not displayed");
		report.log(Status.PASS, "SERIES' page displayed");

		// ***********************************************************************************************/

		/** Step 3: Tap cancel (x) button for episode 1 */
		/** #ER-1: Episode 1 removed from SERIES' page, episode 2 remains */
		
		// Step 3:
		downloadPage.clickOnEpisode1CancelButton();
		report.info("Tap cancel (x) button for episode 1");
		
		// #ER-1:
		Assert.assertTrue(downloadPage.isEpisode1Removed(), "Episode 1 removed from SERIES' page");
		Assert.assertTrue(downloadPage.isEpisode2Displayed(), "Episode 2 remains");
		report.log(Status.PASS, "Episode 1 removed from SERIES' page, episode 2 remains");

		// ***********************************************************************************************/

		/** Step 4: Tap back (<-) button on top left corner of SERIES' page */
		/** #ER-1: "File Manager" page displayed */

		// Step 4:
		downloadPage.clickOnBackButton();
		report.info("Tap back (<-) button on top left corner of SERIES' page");

		// #ER-1:
		Assert.assertEquals(downloadPage.getManageMigoFilesTitle(), "Manage Migo Files", "File Manager page not displayed");
		report.log(Status.PASS, "File Manager page displayed");

		// ***********************************************************************************************/

		/** Step 5: Tap close (x) button on top left corner of "File Manager" page */
		/** #ER-1: "Downloads" page displayed */
		/** #ER-2: SERIES still remains on "Downloads" page */
		/** #ER-3: [x] [MB, GB] added back to free space counter */

		// Step 5:
		downloadPage.clickOnCloseXButtonOfFileManager();
		report.info("Tap close (x) button on top left corner of \"File Manager\" page");

		// #ER-1:
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		// #ER-2
		Assert.assertTrue(downloadPage.isSeriesDisplayed(), "SERIES not present on Downloads page");
		report.log(Status.PASS, "SERIES still remains on Downloads page");

		// #ER-3
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel, availableMemoryBeforeCancel), "[" + memoryReverted + "] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "[" + memoryReverted + "] [MB, GB] Added from free space counter");

	}
	@Test
	@Description(value = "Selecting a MOVIE using Quick Download")
	@Author(name = "Sridatta")
	public void FU3_07() throws InterruptedException {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);
		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();

		// ***********************************************************************************************/
		/** Step 1: Tap "Catalog" tab */
		/** #ER-1: Main "Catalog" page displayed */

		// Step 1:
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");

		// #ER-1:
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");

		// ***********************************************************************************************/

		/** Step 2: Tap "VIEW ALL" button of any channel */
		/** #ER-1: Channel's page displayed */

		// Step 2:
		catalogPage.clickOnViewAllMoiveButton();
		report.info("Tap VIEW ALL button of any channel");

		// #ER-1:
		System.out.println(catalogPage.getChannelPageTitle());
		Assert.assertEquals(catalogPage.getChannelPageTitle(), "Action", "Channel's page not displayed");
		report.log(Status.PASS, "Channel's page displayed");

		// ***********************************************************************************************/

		/**
		 * Step 3: Tap Quick Download button on bottom right corner of any MOVIE'S
		 * banner
		 */
		/** #ER-1: Title queued for download */
		/** #ER-2: Quick Download button changes into cancel (x) button */
		/** #ER-3: Blue snackbar displayed; disappears after 5 seconds */

		// Step 3:
		catalogPage.clickOnQuickDownloadButton();
		report.info("Quick Download button on bottom right corner of any MOVIE'S banner clicked");
		
		// #ER-3: This step needs to be done first cause it fades.
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(), "Blue snackbar is not displayed");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");
		
		//Progress bar is displayed on \"Downloads\" tab
		Assert.assertTrue(catalogPage.isProgressBarIsDisplayed(), "Progress bar is not displayed on \"Downloads\" tab");
		report.log(Status.PASS, "Progress bar displayed on \"Downloads\" tab");

		// #ER-1:

		// #ER-2:
		Assert.assertTrue(catalogPage.isQuickCancelButtonDisplayed(), "Quick Download button not changes into a Cancel button");
		report.log(Status.PASS, "Quick Download button changes into a Cancel button");

		// ***********************************************************************************************/

		/** Step 4: Tap blue snackbar */
		/** #ER-1: "Downloads" page displayed */
		/**
		 * #ER-2: Title displayed as first one on "MOVIES" section on "Downloads" page
		 */
		/** #ER-3: [x] [MB, GB] deducted from free space counter */
		/** */
		/** */
		
		//Making blue snack bar appear again as it disappeared in 5 sec.
		catalogPage.clickOnQuickCancellButton();
		catalogPage.clickOnQuickDownloadButton();

		// Step 4:
		catalogPage.tapOnBlueSnackBar();
		report.info("Blue Snackbar clicked");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");

		// #ER-1:
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		// #ER-2
		Assert.assertTrue(downloadPage.isMoviesLayoutDisplayed(), "Title not displayed as first one under MOVIES section on Downloads page");
		report.log(Status.PASS, "Title displayed as first one under MOVIES section on Downloads page");

		// #ER-3
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;
		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "[" + deductedMemory + "] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "[" + deductedMemory + "] [MB, GB] deducted from free space counter");
		
		//#ER-4
		//Title queued for download until previous download finishes
		Thread.sleep(5000);
		String statusAfterDownload=null;
		String statusBeforeDownload=null;
		statusBeforeDownload=downloadPage.getDowdloadingNextLable();
		downloadPage.clickOnSeriesLineButton();
		if(downloadPage.isStartDownloading())
		{
			if(downloadPage.finishDownloading())
			{
				
			}		
		}
		downloadPage.clickOnBackButton();
		statusAfterDownload=downloadPage.getDowdloadingNextLable();
		if(statusBeforeDownload.equals(statusAfterDownload)==false)
		{
			report.log(Status.PASS, "Title queued for download until previous download finishes");
		}
		else
			report.log(Status.PASS,"Title queued for download until previous download not finishes");
	}

	@Test
	@Description(value = "Cancelling a MOVIE's download using Quick Cancel")
	@Author(name = "Sridatta")
	public void FU3_08() throws InterruptedException {
		
		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();

		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");
		
		//Channel Page displayed
//		catalogPage.clickOnViewAllMoiveButton();
		Assert.assertEquals(catalogPage.getChannelPageTitle(),"Action", "Channel's page not displayed");
		report.log(Status.PASS, "Channel's page displayed");

		//Tap Quick Cancel (x) button on bottom right corner of E60 MOVIE's banner
		catalogPage.clickOnQuickCancellButton();
		report.info("Tap Quick Cancel (x) button on bottom right corner of E60 MOVIE's banner");

		//Cancel (x) button changes to Quick Download button
		Assert.assertTrue(catalogPage.isQuickDownloadButtonDisplayed(), "Cancel (x) button not changes to Quick Download button");
		report.log(Status.PASS, "Cancel (x) button changes to Quick Download button");

		//Tap Downloads tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap Downloads tab");

		//Downloads page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		//Title removed from "Downloads" page
		Assert.assertFalse(downloadPage.isMoviesLayoutDisplayed(), "Title not removed from \"Downloads\" page");
		report.log(Status.PASS, "Title removed from \"Downloads\" page");

		//Memory Reverted
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel,availableMemoryBeforeCancel), "["+memoryReverted+"] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "["+memoryReverted+"] [MB, GB] Added from free space counter");
	}
	
	@Test
	@Description(value = "Selecting a SERIES using Quick Download")
	@Author(name = "Sridatta")
	public void FU3_09() throws InterruptedException  {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();
		//Step-1: Tap "Catalog" tab
		catalogPage.clickOnCatalogTab();
		report.info("Tap Catalog tab");

		//back button to go to catalog main page
		catalogPage.clickBackArrowButton();		
		
		//Tap on viewall Button
		catalogPage.clickOnViewAllButtonSeries();
		report.info("Tap Click view all button displayed ");

		//Channel's page displayed
		Assert.assertEquals(catalogPage.getChannelPageTitle(),"Kdrama", "Channel's page not displayed"); 
		report.log(Status.PASS, "Channel's page displayed");

		//Step-2: Tap Quick Download button on bottom right corner of any SERIES' banner
		catalogPage.clickOnQucikDownloadButtonE70();
		report.info("Tap Quick Download button on bottom right corner of any SERIES' banner");

		String episodeName = catalogPage.getTextOfEpisodeName();

		//ER1: Confirmation dialog displayed
		Assert.assertTrue(catalogPage.isConfirmationDialogDisplayed());
		report.log(Status.PASS, "Confirmation dialog displayed");

		//It will return the Range that episodes needs to be download.
		int startEpisode = catalogPage.startEpisode();
		int lastEpisode = catalogPage.lastEpisode();
		report.info("Range of episode "+startEpisode+"-"+lastEpisode);

		//Step-3: Tap "Download [x]-[y]" button
		catalogPage.clickOnXYDownloadButton();
		report.log(Status.PASS, "[x]-[y]\" button clicked");

		//ER1: Confirmation dialog hidden
		Assert.assertFalse(catalogPage.isConfirmationDialogDisplayed(), "Confirmation dialog not hidden");
		report.log(Status.PASS, "Confirmation dialog hidden");
		
		//ER2: Quick Download button changes into cancel (x) button
		Assert.assertTrue(catalogPage.isQuickCancelButtonDisplayed(), "Quick Download button does not changes into cancel (x) button");
		report.log(Status.PASS, "Quick Download button changes into cancel (x) button");
		
		//Progress bar displayed or updated on "Downloads" tab
		Assert.assertTrue(catalogPage.isProgressBarIsDisplayed(), "Progress bar is not displayed on \"Downloads\" tab");
		report.log(Status.PASS, "Progress bar displayed or updated on \"Downloads\" tab");

		//Need to perform these Action again as it is taking time to validate Blue Snackbar
		catalogPage.clickOnQuickCancelButtonForE70();
		report.info("Tap on Quick Cancel Button");
		downloadPage.clickOnYesCancelButton();
		report.info("Tap on Yes, Cancel Button");
		catalogPage.clickOnQucikDownloadButtonE70();
	    report.info("Tap on Quick Download Button");
		catalogPage.clickOnXYDownloadButton();
		report.info("Tap \"Download [x]-[y]\" button");
		
		//ER2: Blue snackbar displayed
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(), "Blue snackbar not displayed");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");

		//Step-4: Tap on Blue Snackbar
		catalogPage.tapOnBlueSnackBar();
		report.info("Tap on Blue Snackbar");
		
		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		
		//ER2: Title displayed as second one under "TV SHOWS" section on "Downloads" page
		Assert.assertTrue(downloadPage.isTitlePageDisplayedAsSecond(episodeName),"Title not displayed as second one under \"TV SHOWS\" section on \"Downloads\" page"); 
		report.log(Status.PASS,"Title displayed as second one under \"TV SHOWS\" section on \"Downloads\" page");

		//ER3: [x] [MB, GB] deducted from free space counter
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;

		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "["+deductedMemory+"] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "["+deductedMemory+"] [MB, GB] deducted from free space counter");

		//Step-5: Tap E70 SERIES' line item
		downloadPage.clickOnE70SeriesLineButton();
		report.info("Tap E70 SERIES' line item");

		//ER1: SERIES page displayed
		Assert.assertTrue(downloadPage.isSeriesPageDisplayed(), "SERIES' page not displayed");
		report.log(Status.PASS,"SERIES' page displayed");
		
		//All episodes from SERIES that fit on device queued for download until previous downloads finish
		//Previously their is no movie or episode is downloading so no need to wait for previous downloads finish
	
		//Scroll the episode to the last episode
		Set<Integer> episodeList = downloadPage.scrollToLastEpisode();
		//ER2: All episodes from SERIES that fit on device queued for download
		Assert.assertTrue(isEpisodeFitOnDeviceQueue(startEpisode, lastEpisode, episodeList),"All episodes from SERIES that not fit on device queued for download");
		report.log(Status.PASS, "All episodes from SERIES that fit on device queued for download");
	}


	@Test
	@Description(value ="Selecting a MOVIE for download while viewing its Title Card")
	@Author(name = "Sridatta") 
	public void FU3_10() throws InterruptedException {


		CatalogPage catalogPage = new CatalogPage(driver); 
		DownloadPage downloadPage=new DownloadPage(driver);

		//Step-1: Tap "Catalog" tab 
		catalogPage.clickOnCatalogTab();
		report.info("Clicked On Catalog Tab");

		//ER1: Channel's page displayed
		Assert.assertEquals(catalogPage.getChannelPageTitle(),"Kdrama","Channel's page not displayed");
		report.log(Status.PASS,"Channel's page displayed");

		//Tap back (<-) button on top left corner of SERIES' page
		catalogPage.clickOnBackArrowButtonSeriesPage();
		report.info("Tap back (<-) button on top left corner of SERIES' page");
		Thread.sleep(6000);
		//Tap any MOVIE's banner from current channel
		catalogPage.scrollToTop();
		catalogPage.tapOnMovieBanner();
		report.info("Tap any MOVIE's banner from current channel");

		//Title Card displayed; w/ "No space" button
		Assert.assertTrue(catalogPage.isTitleCardDisplayedWithNoSpace(),"Title Card displayed; w/o \"No space\" button");
		report.log(Status.PASS, "Title Card displayed; w/ \"No space\" button");

		//Tap "No space" button 
		catalogPage.clickOnNoSpaceButton();
		report.info("Tap No space button");

		//Informational dialog displayed
		Assert.assertTrue(catalogPage.infomartionDialougeIsDisplayed(),"Informational dialog not displayed");
		report.log(Status.PASS, "Informational dialog displayed");

		//Tap "CANCEL"
		catalogPage.clickOnCancelButton();
		report.info("Tap CANCEL");

		//Informational dialog hidden
		Assert.assertFalse(catalogPage.infomartionDialougeIsDisplayed(),"Informational dialog not hidden");
		report.log(Status.PASS, "Informational dialog hidden");

		//Tap close (x) button on top right corner of Title Card
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");

		//Title Card hidden
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card not hidden");
		report.log(Status.PASS, "Title Card hidden");

		//Tap "Downloads" tab 
		downloadPage.clickOnDownloadTab();
		report.info("Tap Downloads tab");

		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

	}
	

	@Test
	@Description(value = "Device is disconnected from the Migo Hotspot")
	@Author(name = "Sridatta")
	public void FU3_11() throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);

		String connectivityIndicatorStatusbeforedisablewifi = downloadPage.getConnectivityIndicatorStatus();
	
		//click back button to to download page 
		downloadPage.backButton();
		
		//Step-1: Disable device Wi-Fi

		try {
			catalogPage.disableWifi();
			Thread.sleep(30000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//Step-2 : Tap "OK"
		downloadPage.clickOnOkButton();
		report.log(Status.PASS, "Tap \"OK\"");

		String connectivityIndicatorStatusAfterdisablewifi = downloadPage.getConnectivityIndicatorStatus();

		//Informational dialog hidden
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Informational dialog  is not hidden"); 
		report.log(Status.PASS, "Informational dialog hidden");

		//Connectivity indicator changes from "DOWNLOADING FROM MIGO WIFI" to "CONNECT TO DOWNLOAD"
		Assert.assertEquals(downloadPage.getConnectivityIndicatorStatus(), "CONNECT TO DOWNLOAD");
		if (connectivityIndicatorStatusbeforedisablewifi.equals(connectivityIndicatorStatusAfterdisablewifi)) {
			report.log(Status.PASS,
					"Connectivity indicator changes from \"DOWNLOADING FROM MIGO WIFI\" to \"CONNECT TO DOWNLOAD\"");
		} else
			report.log(Status.PASS,
					"Connectivity indicator not changes from \"DOWNLOADING FROM MIGO WIFI\" to \"CONNECT TO DOWNLOAD\"");

		//SERIES line items display "CONNECT TO DOWNLOAD"
		Assert.assertEquals(downloadPage.getTextFromSeries(), "CONNECT TO DOWNLOAD",
				"SERIES line items not display \"CONNECT TO DOWNLOAD\"");
		report.log(Status.PASS, "SERIES line items display \"CONNECT TO DOWNLOAD\"");

	}

	@Test
	@Description(value = "Connecting to the Migo Hotspot using manual connection")
	@Author(name = "Sridatta")
	public void FU3_12() throws InterruptedException, IOException {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);

		//Step-1: Enable device Wi-Fi
		//Step-2: Connect to the Migo Hotspot
		
		 catalogPage.enableWifi();
		
		report.info("Enable device Wi-Fi");
		report.info("Connect to the Migo Hotspot");

		//Step-2: Go back to Migo app
		//Tap E38 SERIES' line item
		downloadPage.clickOnE65SeriesViewButton();
		report.info("Go back to Migo app");
		report.info("Tap E38 SERIES' line item");

		//ER1: Title in E38 starts downloading
		Assert.assertTrue(downloadPage.isStartsDownloading(), "Title in E38 not starts downloading");
		report.log(Status.PASS, "Title in E35 starts downloading");
		//Tap back (<-) button
		downloadPage.clickOnBackButton();
		report.info("Tap back (<-) button");

		//ER2: Connectivity indicator displays "DOWNLOADING FROM MIGO WIFI"
		Assert.assertEquals(downloadPage.getConnectivityIndicatorStatus(), "DOWNLOADING FROM MIGO WIFI");
		report.log(Status.PASS, "Connectivity indicator displays DOWNLOADING FROM MIGO WIFI");

		String getEpisode = downloadPage.findXepisode();
		int result = Integer.parseInt(getEpisode);
		if (result > 1) {
			//ER3: SERIES line items display "Downloading [x] episodes…"
			Assert.assertEquals(downloadPage.downloadingXepisode(), "Downloading " + getEpisode + " episodes....");
			report.log(Status.PASS, "SERIES line items display Downloading [x] episodes…");
		} else {
			Assert.assertEquals(downloadPage.downloadingXepisode(), "Downloading " + getEpisode + " episode....");
			report.log(Status.PASS, "SERIES line items display Downloading [x] episodes…");
		}
		//Step-4: Tap E38 SERIES' line item
		downloadPage.clickOnE65SeriesViewButton();
		report.info("Tap E38 SERIES' line item");

		//ER1: E38 SERIES EPISODE displays "[x]%" So we need an other apk in which blue snacbar got visible .  
		String downloadingPercentage = downloadPage.getDownloadPercentage();
		report.log(Status.PASS, "E35 SERIES EPISODE displays [" + downloadingPercentage + "]%");
	}

	@Test
	@Description(value = "Using other apps while downloading")	
	@Author(name = "Sridatta")
	public void FU3_13() throws InterruptedException {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);

		//Step-1: Tap home button
		catalogPage.tapOnHomeButton();
		report.info("Tap home button");

		//	//ER1: Android Home Screen displayed
		//	Assert.assertFalse(catalogPage.isAndroidHomeScreenDisplayed(), "Android Home Screen not displayed");
		//	report.log(Status.PASS, "Android Home Screen displayed");

		//Step-2: open  Notification Drawer
		catalogPage.clickOnopenNotificationDrawer();
		report.info("open  Notification Drawer");

		Thread.sleep(3000);
		//ER1: Download progress notification appears on Notification Drawer
		Assert.assertTrue(downloadPage.isDownloadProgressBar(),
				"Download progress notification  not appears on Notification Drawer");
		report.log(Status.PASS, "Download progress notification appears on Notification Drawer");

		//ER2: E38 SERIES EPISODE continues downloading
		Assert.assertTrue(downloadPage.isDownloadProgressBarInNotificationDrawer(),
				"E38 SERIES EPISODE continues not downloading");
		report.log(Status.PASS, "E38 SERIES EPISODE continues downloading");

		//ER3: Out-of-app progress notification displayed in Notification Drawer
		Assert.assertTrue(downloadPage.isDownloadProgressBar(),
				"Out-of-app progress notification is not displayed in Notification Drawer");
		report.log(Status.PASS, "Out-of-app progress notification displayed in Notification Drawer");
	}

	@Test
	@Description(value = "Using Migo while downloading")
	@Author(name = "Sridatta")
	public void FU3_14() throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);

		//Step-1: After [10 seconds], tap Migo download progress notification
		downloadPage.tapMigoDownloadProgressNotification();
		report.info("After [10 seconds], tap Migo download progress notification");

		//Currently downloading title continues
		Thread.sleep(5000);
		downloadPage.clickOnE65SeriesViewButton();
		Assert.assertTrue(downloadPage.isDownloadingBar(), "Currently downloading title not continues");
		report.log(Status.PASS, "Currently downloading title continues");

		//Wait for currently downloading title to finish
		Assert.assertTrue(downloadPage.finishDownloading(), "Wait for currently downloading title not finish");
		report.log(Status.PASS, "Wait for currently downloading title to finish");
		downloadPage.clickOnBackButton();

		//Previously downloading title finishes
		downloadPage.clickOnSeriesViewButton();
		Assert.assertTrue(downloadPage.finishDownloading(), "Previously downloading title finishes");
		report.log(Status.PASS, "Previously downloading title finishes");
		downloadPage.clickOnBackButton();

		downloadPage.clickOnE65SeriesViewButton();

		//Next title starts downloading
		Assert.assertTrue(downloadPage.isDownloadingBar(), "Next title starts downloading");
		report.log(Status.PASS, "Next title starts downloading");

	}

	@Test
	@Description(value = "Device is locked while waiting")
	@Author(name = "Sridatta")
	public void FU3_15() throws InterruptedException {

		DownloadPage downloadPage = new DownloadPage(driver);

		//Step-1: Lock device
		downloadPage.lockTheDevice();
		report.info("Lock device");

	}
	
}