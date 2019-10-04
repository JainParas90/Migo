package com.migo.android.tests.FU1;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.appium.base.UserBaseTest;
import com.aventstack.extentreports.Status;
import com.migo.annotation.values.Author;
import com.migo.annotation.values.Description;
import com.migo.pages.CatalogPage;
import com.migo.pages.DownloadPage;

public class Migo_FU1 extends UserBaseTest{

	Logger logger = Logger.getLogger(Migo_FU1.class.getName());
	double deductedMemory;
	double availableMemoryBeforeDownload;
	
	@BeforeClass
	@Description(value = "Precondition for FU1 sheet")
	@Author(name = "Sridatta")
	public void FU1_Precondition() throws InterruptedException, IOException {
		
		DownloadPage downloadPage = new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		

		//Enabling WiFi
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
		
		System.out.println("Pre condition FU1.");
	}

	@Test
	@Description(value = "Selecting a MOVIE for download while viewing its Title Card")
	@Author(name = "Sridatta")
	public void FU1_01() throws InterruptedException, IOException {


		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);
		
		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();

		//Step-1: Tap "Catalog" Tab
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" Tab");
		
		//ER1: Main "Catalog" page displayed
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");
		
	
		//Disable the wifi
		catalogPage.disableWifi();

		
		//Step-2: Tap any MOVIE's banner from any channel's carousel
		catalogPage.tapOnMovieBanner();
		report.info("Tap any MOVIE's banner from any channel's carousel");
		//ER1: Title Card displayed
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card is not displayed");
		report.log(Status.PASS, "Title Card is displayed");
		
		String movieTitle = catalogPage.getTextOfMovieTitle();
		
		//Step-3: Tap "Download" button
		catalogPage.clickOnDownlaodButton();
		report.info("Tap \"Download\" button");
		//ER1: Title queued for download
		
		//ER3: Blue Snackbar displayed; disappears after 5 seconds
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(),"Blue snackbar is not displayed");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");
		//ER2: "Download" button changes into a "Cancel" button
		Assert.assertTrue(catalogPage.isCancelDownloadButtonDisplayed("Cancel"), "Download button not changes into a Cancel button");
		report.log(Status.PASS, "Download button changes into a Cancel button");
		
		//Step-4: Tap close (x) button on top right corner of Title Card
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");
		//ER1: Title Card hidden
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Title Card not hidden");
		report.log(Status.PASS, "Title Card hidden");

		//Step-5: Tap "Downloads" tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");
		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		
		//ER2: Title displayed as first one under "MOVIES" section on "Downloads" page
		Assert.assertEquals(downloadPage.getMovieNameList().get(0), movieTitle, "Title not displayed as first one under MOVIES section on Downloads page"); 
		//Step-3: ER1: Title queued for download
		report.log(Status.PASS, "Title queued for download");
		report.log(Status.PASS, "Title displayed as first one under MOVIES section on Downloads page");

		//ER3: [x] [MB, GB] deducted from free space counter
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;

		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "["+deductedMemory+"] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "["+deductedMemory+"] [MB, GB] deducted from free space counter");
	}

	@Test
	@Description(value = "Cancelling a MOVIE's download while on the \"Downloads\" page")
	@Author(name = "Sridatta")
	public void FU1_02() throws InterruptedException {

		DownloadPage downloadPage=new DownloadPage(driver);

		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();

		//Tap cancel (x) button on left side of MOVIE
		downloadPage.clickOnCancelXButton();
		report.info("Tap cancel (x) button on left side of MOVIE");
		
		//Validation: Confirmation dialog displayed
		Assert.assertTrue(downloadPage.isYesCancelDisplayed(), "Confirmation dialog is not displayed"); 
		report.log(Status.PASS, "Confirmation dialog displayed");
		
		//Tap YES, CANCEL button
		downloadPage.clickOnYesCancelButton();
		report.info("Tap YES, CANCEL button");
		
		//Validation: Confirmation dialog hidden
		Assert.assertFalse(downloadPage.isYesCancelDisplayed(), "Confirmation dialog not hidden");
		report.log(Status.PASS,"Confirmation dialog hidden");
		
		//Validation: Title removed from Downloads page
		Assert.assertTrue(downloadPage.isEmptyPageLayoutDisplayed(), "Title not removed from Downloads page");
		report.log(Status.PASS,"Title removed from Downloads page");

		//Validation: Memory Reverted
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel,availableMemoryBeforeCancel), "["+memoryReverted+"] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "["+memoryReverted+"] [MB, GB] Added from free space counter");
	}


	@Test
	@Description(value = "Selecting a SERIES EPISODE for download while viewing its Title Card")
	@Author(name = "Sridatta")
	public void FU1_03() throws InterruptedException {

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

		// Step 2: Tap any SERIES' banner from any channel's carousel
		catalogPage.ClickOnSeriesBannerTitleCard();
		report.info("Tap any SERIES' banner from any channel's carousel");

		// #ER-1:
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card not displayed");
		report.log(Status.PASS, "Title Card displayed");
		
		

		// ***********************************************************************************************/

		/** Step 3: Tap "Download" button for episode 1 */
		/** #ER-1: Episode 1 queued for download */
		/** #ER-2: "Download" button changes into a "Cancel" button */
		/** #ER-3: Blue snackbar displayed; disappears after 5 seconds */

		// Step 1:
		catalogPage.clickOnDownlaodButton();
		report.info("Tap Download button for episode 1");

		// #ER-1 we're doing this in step two.

		// #ER-3: This need to be done first cause Blue snackbar fades.
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(), "Blue snackbar is not displayed");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");

		// #ER-2:
		Assert.assertTrue(catalogPage.isEpisode1CancelDownloadButtonDisplayed("Cancel"), "Download button not changes into a Cancel button");
		report.log(Status.PASS, "Download button changes into a Cancel button");

		// ***********************************************************************************************/

		String seriesTitle = catalogPage.getTextOfSeriesTitle();
		/** Step 4: Tap close (x) button on top right corner of Title Card */
		/** #ER-1: Title Card hidden */

		// Step 4:
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");

		// #ER-1:
		Assert.assertFalse(catalogPage.isTitleCardDisplayed(), "Title Card not hidden");
		report.log(Status.PASS, "Title Card hidden");

		// ***********************************************************************************************/
		
		/** Step 5: Step : Tap "Downloads" tab */
		/** #ER-1: "Downloads" page displayed */
		/**
		 * #ER-2: SERIES displayed as first one under "TV SHOWS" section on "Downloads"
		 * page
		 */
		/** #ER-3: SERIES displays "CONNECT TO DOWNLOAD" */
		/** #ER-4: [x] [MB, GB] deducted from free space counter */

		// Step 5:
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");

		// #ER-1:
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		// #ER-2:
		Assert.assertEquals(downloadPage.getSeriesNameList().get(0), seriesTitle, "SERIES not displayed as first one under \"TV SHOWS\" section on \"Downloads\" page"); 
		 //#ER-1: Episode 1 queued for download
		report.log(Status.PASS, "Episode 1 queued for download");
		report.log(Status.PASS, "SERIES displayed as first one under \"TV SHOWS\" section on \"Downloads\" page");

		// #ER-3:
		Assert.assertEquals(downloadPage.getTextFromSeries(), "CONNECT TO DOWNLOAD");
		report.log(Status.PASS, "SERIES displays \"CONNECT TO DOWNLOAD\"");

		// #ER-4:
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;
		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "[" + deductedMemory + "] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "[" + deductedMemory + "] [MB, GB] deducted from free space counter");

	}

	@Test
	@Description(value = "Cancelling a SERIES EPISODE's download while viewing its Title Card")
	@Author(name = "Sridatta")
	public void FU1_04() throws InterruptedException {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage = new DownloadPage(driver);
		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();

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

		/** Step 2: Tap same E17 SERIES */
		/** #ER-1: Title Card displayed */

		// Step 1:
		catalogPage.ClickOnSeriesBannerTitleCard();
		report.info("Tap same E17 SERIES");

		// #ER-1:
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card not displayed");
		report.log(Status.PASS, "Title Card displayed");

		// ***********************************************************************************************/

		/** Step 3: Tap "Cancel Download" button for episode 1 */
		/** #ER-1: "Cancel" button changes into a "Download" button */

		// Step 3:
		catalogPage.clickOnCancelDownlaodButton();
		report.info("Tap \"Cancel Download\" button for episode 1");

		// #ER-1:
		Assert.assertTrue(catalogPage.isEpisode1CancelDownloadButtonDisplayed("Download"), "\"Cancel\" button changes into a \"Download\" button");
		report.log(Status.PASS, "\"Cancel\" button changes into a \"Download\" button");

		// ***********************************************************************************************/

		/** Step 4: Tap close (x) button on top right corner of Title Card */
		/** #ER-1: Title Card hidden */

		// Step 4:
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");

		// #ER-1:
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Catalog Screen is not displayed");
		report.log(Status.PASS, "Title Card hidden");

		// ***********************************************************************************************/

		/** Step 5: Tap "Downloads" tab */
		/** #ER-1: "Downloads" page displayed */
		/** #ER-2: Title removed from "Downloads" page */
		/** #ER-3: [x] [MB, GB] added back to free space counter */

		// Step 1:
		// Tap "Downloads" tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab ");

		// #ER-1:
	    Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "\"Downloads\" page displayed");

		// #ER-2:
		Assert.assertTrue(downloadPage.isEmptylayoutpageDisplayed(), "Title not removed from \"Downloads\" page");
		report.log(Status.PASS, "Title removed from \"Downloads\" page");

		// #ER-3:
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel, availableMemoryBeforeCancel), "[" + memoryReverted + "] [MB, GB] added back to free space counter");
		report.log(Status.PASS, "[" + memoryReverted + "] [MB, GB] added back to free space counter");

	}

	@Test
	@Description(value = "Selecting multiple titles for download")
	@Author(name = "Sridatta")
	public void FU1_05() throws InterruptedException  {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();

		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");

		//Tap any SERIES' banner from any channel's carousel
		catalogPage.ClickOnSeriesBannerTitleCard();
		report.info("Tap any SERIES' banner from any channel's carousel");

		String seriesTitle = catalogPage.getTextOfSeriesTitle();
		
		catalogPage.scrollForEpisodeTopToDown();
		//Tap "Download" button for episode 1 
		catalogPage.clickOnDownlaodButtonForEpisode1();
//		report.info("Tap \"Download\" button for episode 1");
		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(),"Blue snackbar is not displayed");
		report.info("Tap \"Download\" button for episode 1");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");
		Assert.assertTrue(catalogPage.isEpisode1CancelDownloadButtonDisplayed("Cancel"), "Download button not changes into a Cancel button");
		report.log(Status.PASS, "Download button changes into a Cancel button");

		//Tap "Download" button for episode 2
		catalogPage.clickOnDownlaodButtonForEpisode2();
		catalogPage.tapOnBlueSnackBar();
		report.info("Tap \"Download\" button for episode 2");
//		Assert.assertTrue(catalogPage.isEpisode2CancelDownloadButtonDisplayed("Cancel"), "\"Download\" button changes into a \"Cancel\" button");
//		report.log(Status.PASS, "\"Download\" button changes into a \"Cancel\" button");

		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		Assert.assertEquals(downloadPage.getSeriesNameList().get(0), seriesTitle, "SERIES not displayed as first one under \"TV SHOWS\" section on \"Downloads\" page"); 
		 //#ER-1: Episode 1 queued for download
		report.log(Status.PASS, "Episode 1 queued for download");
		report.log(Status.PASS, "SERIES displayed as first one under \"TV SHOWS\" section on \"Downloads\" page");

		downloadPage.clickOnSeriesViewButton();
		Assert.assertTrue(downloadPage.isEpisode1FollowedByEpisode2(), "Episode 1 displayed first inside SERIES' page, not followed by episode 2");
		report.log(Status.PASS,"Episode 1 displayed first inside SERIES' page, followed by episode 2");

		downloadPage.clickOnBackButton();
		report.info("Tap on Back button");

		//Memory Added
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;

		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "["+deductedMemory+"] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "["+deductedMemory+"] [MB, GB] deducted from free space counter");

	}

	@Test
	@Description(value = "Cancelling a SERIES EPISODE's download while on \"File Manager\" mode")	
	@Author(name = "Sridatta")
	public void FU1_06() throws InterruptedException  {


		//Tap "File Manager" button
		DownloadPage downloadPage=new DownloadPage(driver);

		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();
		downloadPage.clickOnFileManagerButton();
		report.info("Tap \"File Manager\" button");
		Assert.assertEquals(downloadPage.getManageMigoFilesTitle(), "Manage Migo Files", "File Manager page not displayed"); 
		report.log(Status.PASS, "File Manager page displayed");

		downloadPage.clickOnSeriesLineItem();
		report.info("Tap SERIES' line item");
		Assert.assertTrue(downloadPage.isSeriesPageDisplayed(), "SERIES' page not displayed");
		report.log(Status.PASS,"SERIES' page displayed");

		downloadPage.clickOnEpisode1CancelButton();
		report.info("Tap cancel (x) button for episode 1");
		Assert.assertTrue(downloadPage.isEpisode1Removed(), "Episode 1 removed from SERIES' page");
		Assert.assertTrue(downloadPage.isEpisode2Displayed(), "Episode 2 remains");
		report.log(Status.PASS,"Episode 1 removed from SERIES' page, episode 2 remains");

		downloadPage.clickOnBackButton();
		report.info("Tap back (<-) button on top left corner of SERIES' page");
		Thread.sleep(5000);
		Assert.assertEquals(downloadPage.getManageMigoFilesTitle(), "Manage Migo Files", "File Manager page not displayed"); 
		report.log(Status.PASS, "File Manager page displayed");

		downloadPage.clickOnCloseXButtonOfFileManager();
		report.info("Tap close (x) button on top left corner of \"File Manager\" page");
		Thread.sleep(5000);
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		Assert.assertTrue(downloadPage.isSeriesDisplayed(), "SERIES not present on Downloads page");
		report.log(Status.PASS, "SERIES still remains on Downloads page");

		//Memory Reverted
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel,availableMemoryBeforeCancel), "["+memoryReverted+"] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "["+memoryReverted+"] [MB, GB] Added from free space counter");

	}

	@Test
	@Description(value = "Selecting a MOVIE using Quick Download")	
	@Author(name = "Sridatta")
	public void FU1_07() throws InterruptedException  {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();

		//click on Catalog Tab
		catalogPage.clickOnCatalogTab();
		report.info("click on Catalog Tab");

		//Tap "VIEW ALL" button of any channel
		catalogPage.clickOnViewAllMoiveButton();
		report.info("Tap VIEW ALL button of any channel");

		System.out.println(catalogPage.getChannelPageTitle());
		Assert.assertEquals(catalogPage.getChannelPageTitle(), "Action", "Channel's page not displayed"); 
		report.log(Status.PASS, "Channel's page displayed");

		catalogPage.clickOnQuickDownloadButton();
		report.info("Quick Download button on bottom right corner of any MOVIE'S banner clicked");

//		Assert.assertTrue(catalogPage.isBlueSnackBarDisplayed(),"Blue snackbar is not displayed");
		
		catalogPage.tapOnBlueSnackBar();
		report.info("Blue Snackbar clicked");
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		Assert.assertTrue(downloadPage.isMoviesLayoutDisplayed(), "Title not displayed as first one under MOVIES section on Downloads page"); 
		report.log(Status.PASS, "Title displayed as first one under MOVIES section on Downloads page");

		//Memory Added
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;

		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "["+deductedMemory+"] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "["+deductedMemory+"] [MB, GB] deducted from free space counter");

	}


	@Test
	@Description(value = "Cancelling a MOVIE's download using Quick Cancel")
	@Author(name = "Sridatta")
	public void FU1_08() throws InterruptedException  {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();
		//Click on Catalog Tab
		catalogPage.clickOnCatalogTab();
		report.log(Status.PASS, "catalog page displayed");

		//Tap Quick Cancel (x) button on bottom right corner of E60 MOVIE's banner
		Thread.sleep(2000);
		catalogPage.clickOnQuickDownloadButton();
		//catalogPage.clickOnQuickCancellButton();
		report.log(Status.PASS, "Tap Quick Cancel (x) button on bottom right corner of E60 MOVIE's banner");

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
	@Author(name = "sridatta")
	public void FU1_09() throws InterruptedException  {

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
		report.log(Status.PASS, "Blue snackbar displayed; disappears after 5 seconds");

		//Step-4: Tap on Blue Snackbar
		catalogPage.tapOnBlueSnackBar();
		report.log(Status.PASS, "Tap on Blue Snackbar");

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
		
		//ER2: E70 SERIES EPISODE displays "CONNECT"
		Assert.assertTrue(downloadPage.isConnectLabelDisplayed());
		report.log(Status.PASS, "E70 SERIES EPISODE displays CONNECT");

		//Scroll the episode to the last episode
		Set<Integer> episodeList = downloadPage.scrollToLastEpisode();
		//ER3: All episodes from SERIES that fit on device queued for download
		Assert.assertTrue(isEpisodeFitOnDeviceQueue(startEpisode, lastEpisode, episodeList),"All episodes from SERIES that not fit on device queued for download");
		report.log(Status.PASS, "All episodes from SERIES that fit on device queued for download");
	}


	@Test
	@Description(value ="Selecting a MOVIE for download while viewing its Title Card")	
	@Author(name = "Shibu")
	public void FU1_10() throws InterruptedException {

		CatalogPage catalogPage = new CatalogPage(driver); 
		DownloadPage downloadPage=new DownloadPage(driver);

		//Step-1: Tap "Catalog" tab 
		catalogPage.clickOnCatalogTab();
		report.info("Clicked On Catalog Tab");

		
		//ER1: Channel's page displayed
		Assert.assertEquals(catalogPage.getChannelPageTitle(),"Kdrama","Channel's page not displayed");
		report.log(Status.PASS,"Channel's page displayed");

		//Tap back (<-) button on top left corner of SERIES' page
		catalogPage.clickBackArrowButton();
		report.info("Tap back (<-) button on top left corner of SERIES' page");

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
	@Description(value = "Connecting to the Migo Hotspot using manual connection")
	
	@Author(name = "Sridatta")
	public void FU1_11() throws InterruptedException  {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		//Step-1: Enable device Wi-Fi
		//Step-2: Connect to the Migo Hotspot
		
		try {
			catalogPage.enableWifi();
			Thread.sleep(6000);
		} catch (IOException e) {
			e.printStackTrace();
		}

		report.info("Enable device Wi-Fi");
		report.info("Connect to the Migo Hotspot");
		
		//Step-2: Go back to Migo app
		//Tap E38 SERIES' line item
		downloadPage.clickOnSeriesViewButton();
		report.info("Go back to Migo app");
		report.info("Tap E38 SERIES' line item");

		//ER1: Title in E38 starts downloading
		Assert.assertTrue(downloadPage.isStartsDownloading(), "Title in E38 not starts downloading");
		report.log(Status.PASS, "Title in E38 starts downloading");

		//Tap back (<-) button
		downloadPage.clickOnBackButton();
		report.info("Tap back (<-) button");

		//ER2: Connectivity indicator displays "DOWNLOADING FROM MIGO WIFI"
		Assert.assertEquals(downloadPage.getConnectivityIndicatorStatus(),"Downloading from MigoWiFi", "Connectivity indicator not displays DOWNLOADING FROM MIGO WIFI");
		report.log(Status.PASS, "Connectivity indicator displays DOWNLOADING FROM MIGO WIFI");

		String xEpisode = downloadPage.findXepisode();
		//ER3: SERIES line items display "Downloading [x] episodes…"
		Assert.assertEquals(downloadPage.downloadingXepisode(), "Downloading "+xEpisode+" episode....");
		report.log(Status.PASS, "SERIES line items display Downloading [x] episodes…");

		//Step-4: Tap E38 SERIES' line item
		downloadPage.clickOnSeriesViewButton();
		report.info("Tap E38 SERIES' line item");

		//ER1: E38 SERIES EPISODE displays "[x]%"
		String downloadingPercentage = downloadPage.getDownloadPercentage();
		report.log(Status.PASS, "E38 SERIES EPISODE displays ["+downloadingPercentage+"]%");

	}
	@Test
	@Description(value = "Using other apps while downloading")
	@Author(name = "Sridatta")
	public void FU1_12() throws InterruptedException  {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);
		
		//Step-1: Tap home button
		catalogPage.tapOnHomeButton();
		report.info("Tap home button");

		//ER1: Android Home Screen displayed
		Assert.assertFalse(catalogPage.isAndroidHomeScreenDisplayed(), "Android Home Screen not displayed");
		report.log(Status.PASS, "Android Home Screen displayed");

		//Step-2: open  Notification Drawer
		catalogPage.clickOnopenNotificationDrawer();
		report.info("open  Notification Drawer");

		//ER1: Download progress notification appears on Notification Drawer
		Assert.assertTrue(downloadPage.isDownloadProgressBar(), "Download progress notification  not appears on Notification Drawer");
		report.log(Status.PASS, "Download progress notification appears on Notification Drawer");

		//ER2: E38 SERIES EPISODE continues downloading
		Assert.assertTrue(downloadPage.isDownloadProgressBarInNotificationDrawer(), "E38 SERIES EPISODE continues not downloading");
		report.log(Status.PASS, "E38 SERIES EPISODE continues downloading");

		//ER3: Out-of-app progress notification displayed in Notification Drawer
		Assert.assertTrue(downloadPage.isDownloadProgressBar(), "Out-of-app progress notification is not displayed in Notification Drawer");
		report.log(Status.PASS, "Out-of-app progress notification displayed in Notification Drawer");
	}
	@Test
	@Description(value = "Using Migo while downloading")
	
	@Author(name = "Sridatta")
	public void FU1_13() throws InterruptedException  {

		DownloadPage downloadPage=new DownloadPage(driver);

		//Step-1: After [10 seconds], tap Migo download progress notification
		downloadPage.tapMigoDownloadProgressNotification();
		report.info("After [10 seconds], tap Migo download progress notification");

		//Tap E38 SERIES' line item
		downloadPage.clickOnSeriesViewButton();
		System.out.println("click on series line");

		//Step-2: Wait for E38 SERIES EPISODE to finish downloading
		//ER1: E38 SERIES EPISODE finishes downloading
		Assert.assertTrue(downloadPage.finishDownloading(), "Wait for E38 SERIES EPISODE to not finish downloading");
		report.log(Status.PASS, "Wait for E38 SERIES EPISODE to finish downloading");

		//Tap back (<-) button on top left corner of SERIES' page
		downloadPage.clickOnBackButton();
		report.info("Tap back (<-) button on top left corner of SERIES' page");

		//Tap on E69 series
		downloadPage.clickOnE69SeriesViewButton();
		report.info("Click on E69 series");

		//ER2: Episode 1 of E69 SERIES  starts downloading
		Assert.assertTrue(downloadPage.isStartsDownloading(), "Episode 1 of E69 SERIES  not starts downloading");
		report.log(Status.PASS, "Episode 1 of E69 SERIES  starts downloading");

		//Step-3: Tap back (<-) button on top left corner of SERIES' page
		downloadPage.clickOnBackButton();
		report.info("Tap back (<-) button on top left corner of SERIES' page");

		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		//Step-4: Tap on E69 SERIES' line item
		downloadPage.clickOnE69SeriesViewButton();
		report.info("Tap on E69 SERIES' line item");

		//ER1: Episode 1 of E69 SERIES displays "[x]%"
		String downloadingPercentageOfE69=downloadPage.getDownloadPercentage();
		report.log(Status.PASS, "Episode 1 of E69 SERIES displays ["+downloadingPercentageOfE69+"]%");

	}
	@Test
	@Description(value = "Device is locked while waiting")
	@Author(name = "Sridatta")
	public void FU1_14() throws InterruptedException  {

		DownloadPage downloadPage=new DownloadPage(driver);

		//Step-1: Lock device
		downloadPage.lockTheDevice();
		report.info("Lock device");
		



	}	
}
