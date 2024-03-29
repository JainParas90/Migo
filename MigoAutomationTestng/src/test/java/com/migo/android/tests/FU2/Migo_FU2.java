package com.migo.android.tests.FU2;

import java.io.IOException;
import java.util.Set;
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

public class Migo_FU2 extends UserBaseTest{

	Logger logger = Logger.getLogger(Migo_FU2.class.getName());
	double deductedMemory;
	double availableMemoryBeforeDownload;
	
	@BeforeClass
	@Description(value = "Precondition for FU2 sheet")
	@Author(name = "Sridatta")
	public void FU2_Precondition() throws InterruptedException {
		
		DownloadPage downloadPage = new DownloadPage(driver);
		CatalogPage catalogPage = new CatalogPage(driver);
		
		//reset application
		downloadPage.resetApplication();
		
		//Click on Allow button
		catalogPage.clickOnAllowButton();
		
		//Click on Allow button
		catalogPage.clickOnAllowButton();
		
		//wait to complete the video
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "video is not completed"); 
		report.log(Status.PASS, "video is completed");
		
		//Convert V1 to VN	
		catalogPage.clickOnCatalogTab();
		
		//click on migo logo	
		catalogPage.clickOnMigoLogo();
		
		//click on user type
		catalogPage.clickOnUserType();
		
		//click on back button
		downloadPage.clickOnBackButton();
		
		//click on download page
		downloadPage.clickOnDownloadTab();
		
		//Make Wifi off
		//Disable the wifi
		try {
			catalogPage.disableWifi();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	

	@Test
	@Description(value = "Selecting a MOVIE for download while viewing its Title Card")
	@Author(name = "Sridatta")
	public void FU2_01() throws InterruptedException {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();
		//Step-1: Tap "Catalog" tab
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" Tab");
		//ER: Main "Catalog" page displayed
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");

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
		//ER1: Title queued for download:: needs to validate in Step-5
		
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
	public void FU2_02() throws InterruptedException {

		DownloadPage downloadPage=new DownloadPage(driver);

		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();

		//Step-1: Tap cancel (x) button on left side of MOVIE
		downloadPage.clickOnCancelXButton();
		report.info("Tap cancel (x) button on left side of MOVIE");
		//ER1: Confirmation dialog displayed
		Assert.assertTrue(downloadPage.isYesCancelDisplayed(), "Confirmation dialog is not displayed"); 
		report.log(Status.PASS, "Confirmation dialog displayed");

		//Step-2: Tap "YES, CANCEL" button
		downloadPage.clickOnYesCancelButton();
		report.info("Tap YES, CANCEL button");
		//ER1: Confirmation dialog hidden
		Assert.assertFalse(downloadPage.isYesCancelDisplayed(), "Confirmation dialog not hidden");
		report.log(Status.PASS,"Confirmation dialog hidden");
		//ER2: Title removed from "Downloads" page
		Assert.assertTrue(downloadPage.isEmptyPageLayoutDisplayed(), "Title not removed from Downloads page");
		report.log(Status.PASS,"Title removed from Downloads page");
		//ER3: [x] [MB, GB] added back to free space counter
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel,availableMemoryBeforeCancel), "["+memoryReverted+"] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "["+memoryReverted+"] [MB, GB] Added from free space counter");

	}
	@Test
	@Description(value = "Selecting a SERIES EPISODE for download while viewing its Title Card")
	@Author(name = "Sridatta")
	public void FU2_03() throws InterruptedException {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();

		//Step-1: Tap "Catalog" tab
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");
		//ER1: Main "Catalog" page displayed
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");

		//Step-2: Tap any SERIES' banner from any channel's carousel 
		catalogPage.ClickOnSeriesBannerTitleCard();
		report.info("Tap any SERIES' banner from any channel's carousel");
		//ER1: Title Card displayed
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card not displayed");
		report.log(Status.PASS, "Title Card displayed");

		//Step-3: Tap "Download" button for episode 1
		catalogPage.clickOnDownlaodButton();
		report.info("Tap Download button for episode 1");
		//ER1: Episode 1 queued for download
		/**
		 * Needs to add
		 */
		//ER2: "Download" button changes into a "Cancel" button
		Assert.assertTrue(catalogPage.isEpisode1CancelDownloadButtonDisplayed("Cancel"), "Download button not changes into a Cancel button");
		report.log(Status.PASS, "Download button changes into a Cancel button");

		//Step-4: Tap close (x) button on top right corner of Title Card
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");
		//ER1: Title Card hidden
		Assert.assertFalse(catalogPage.isTitleCardDisplayed(), "Title Card not hidden");
		report.log(Status.PASS, "Title Card hidden");

		//Step-5: Tap "Downloads" tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");
		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		//ER2: SERIES displayed as first one under "TV SHOWS" section on "Downloads" page
		/**
		 * Needs to add
		 */
		//ER3: SERIES displays "CONNECT TO DOWNLOAD"
		Assert.assertEquals(downloadPage.getTextFromSeries(), "CONNECT TO DOWNLOAD");
		report.log(Status.PASS, "SERIES displays \"CONNECT TO DOWNLOAD\"");

		//ER4: [x] [MB, GB] deducted from free space counter
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;
		report.info("["+deductedMemory+"] [MB, GB] deducted from free space counter");
		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "["+deductedMemory+"] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "["+deductedMemory+"] [MB, GB] deducted from free space counter");

	}

	@Test
	@Description(value = "Cancelling a SERIES EPISODE's download while viewing its Title Card")	
	@Author(name = "Sridatta")
	public void FU2_04() throws InterruptedException {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();

		//Step1: Tap "Catalog" tab
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");

		//ER1: Main "Catalog" page displayed
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");

		//Step-2: Tap same E17 SERIES
		catalogPage.ClickOnSeriesBannerTitleCard();
		report.info("Tap same E17 SERIES");

		//ER1: Title Card displayed
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card not displayed");
		report.log(Status.PASS, "Title Card displayed");

		//Step-3: Tap "Cancel Download" button for episode 1
		catalogPage.clickOnCancelDownlaodButton();
		report.info("Tap \"Cancel Download\" button for episode 1");

		//ER1: "Cancel" button changes into a "Download" button
		Assert.assertTrue(catalogPage.isEpisode1CancelDownloadButtonDisplayed("Download"), "\"Cancel\" button changes into a \"Download\" button");
		report.log(Status.PASS, "\"Cancel\" button changes into a \"Download\" button");

		//Step-4: Tap close (x) button on top right corner of Title Card
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");

		//ER1: Title Card hidden
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Catalog Screen is not displayed");
		report.log(Status.PASS, "Title Card hidden");

		//Step-5: Tap "Downloads" tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");

		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		//ER2: Title removed from "Downloads" page
		Assert.assertTrue(downloadPage.isEmptylayoutpageDisplayed(), "Title not removed from \"Downloads\" page");
		report.log(Status.PASS, "Title removed from \"Downloads\" page");

		//ER3: [x] [MB, GB] added back to free space counter
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel,availableMemoryBeforeCancel), "["+memoryReverted+"] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "["+memoryReverted+"] [MB, GB] Added from free space counter");

	}
	
	@Test
	@Description(value = "Selecting a SERIES EPISODE for download while viewing its Title Card")	
	@Author(name = "Sridatta")
	public void FU2_05() throws InterruptedException  {
		
		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();
		
		//Step-1: Tap "Catalog" tab
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");
		//ER1: Main "Catalog" page displayed
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");
		
		//Step-2: Tap any SERIES' banner from any channel's carousel
		catalogPage.ClickOnSeriesBannerTitleCard();
		report.info("Tap any SERIES' banner from any channel's carousel");
		//ER1: Title Card displayed
		Assert.assertTrue(catalogPage.isTitleCardDisplayed(), "Title Card not displayed");
		report.log(Status.PASS, "Title Card displayed");
		
		catalogPage.scrollForEpisodeTopToDown();
		//Step-3: Tap "Download" button for episode 1
		catalogPage.clickOnDownlaodButtonForEpisode1();
		report.info("Tap \"Download\" button for episode 1");
		//ER1: Episode 1 queued for download
		//ER2: "Download" button changes into a "Cancel" button
		Assert.assertTrue(catalogPage.isEpisode1CancelDownloadButtonDisplayed("Cancel"), "Download button not changes into a Cancel button");
		report.log(Status.PASS, "Download button changes into a Cancel button");
		
		//Step-4: Tap "Download" button for episode 2
		catalogPage.clickOnDownlaodButtonForEpisode2();
		report.info("Tap \"Download\" button for episode 2");
		//ER1: Episode 2 queued for download
		//ER2: "Download" button changes into a "Cancel" button
		Assert.assertTrue(catalogPage.isEpisode2CancelDownloadButtonDisplayed("Cancel"), "\"Download\" button changes into a \"Cancel\" button");
		report.log(Status.PASS, "\"Download\" button changes into a \"Cancel\" button");
		
		//Step-5: Tap close (x) button on top right corner of Title Card
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");
		//ER1: Title Card hidden
		Assert.assertFalse(catalogPage.isTitleCardDisplayed(), "Title Card not hidden");
		report.log(Status.PASS, "Title Card hidden");
		
		//Step-6: Tap "Downloads" tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");
		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		//ER2: Title displayed as first one under "TV SHOWS" section on "Downloads" page
		Assert.assertEquals(downloadPage.getTvSeriesTag(), "TV SHOWS", "TV SHOWS not displayed"); 
//		Assert.assertTrue(downloadPage.isSeriesDisplayed(), "SERIES not displayed as first one under \"TV SHOWS\" section on \"Downloads\" page");
		report.log(Status.PASS, "Title displayed as first one under \"TV SHOWS\" section on \"Downloads\" page");
		//ER3: Episode 1 displayed first inside SERIES' page, followed by episode 2
		downloadPage.clickOnSeriesViewButton();
		Assert.assertTrue(downloadPage.isEpisode1FollowedByEpisode2(), "Episode 1 displayed first inside SERIES' page, not followed by episode 2");
		//ER1: Episode 1 queued for download
		report.log(Status.PASS, "Episode 1 queued for download");
		//ER1: Episode 2 queued for download
		report.log(Status.PASS, "Episode 2 queued for download");
		//ER1: Episode 1 displayed first inside SERIES' page, followed by episode 2
		report.log(Status.PASS,"Episode 1 displayed first inside SERIES' page, followed by episode 2");
		
		downloadPage.clickOnBackButton();
		//ER4: [x] [MB, GB] deducted from free space counter
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;

		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "["+deductedMemory+"] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "["+deductedMemory+"] [MB, GB] deducted from free space counter");
		
	}
	
	@Test
	@Description(value = "Cancelling a SERIES EPISODE's download while on \"File Manager\" mode")
	@Author(name = "Sridatta")
	public void FU2_06() throws InterruptedException  {
		
		DownloadPage downloadPage=new DownloadPage(driver);

		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();
		
		//Step-1: Tap "File Manager" button
		downloadPage.clickOnFileManagerButton();
		report.info("Tap \"File Manager\" button");
		
		//ER1: "File Manager" page displayed
		Assert.assertEquals(downloadPage.getManageMigoFilesTitle(), "Manage Migo Files", "File Manager page not displayed"); 
		report.log(Status.PASS, "File Manager page displayed");
		
		//Step-2: Tap SERIES' line item
		downloadPage.clickOnSeriesLineItem();
		report.info("Tap SERIES' line item");
		
		//ER1: SERIES' page displayed
		Assert.assertTrue(downloadPage.isSeriesPageDisplayed(), "SERIES' page not displayed");
		report.log(Status.PASS,"SERIES' page displayed");
		
		//Step-3: Tap cancel (x) button for episode 1
		downloadPage.clickOnEpisode1CancelButton();
		report.info("Tap cancel (x) button for episode 1");
		
		//ER1: Episode 1 removed from SERIES' page, episode 2 remains
		Assert.assertTrue(downloadPage.isEpisode1Removed(), "Episode 1 removed from SERIES' page");
		Assert.assertTrue(downloadPage.isEpisode2Displayed(), "Episode 2 remains");
		report.log(Status.PASS,"Episode 1 removed from SERIES' page, episode 2 remains");
		
		//Step-4: Tap back (<-) button on top left corner of SERIES' page
		downloadPage.clickOnBackButton();
		report.info("Tap back (<-) button on top left corner of SERIES' page");
		
		//ER1: "File Manager" page displayed
		Assert.assertEquals(downloadPage.getManageMigoFilesTitle(), "Manage Migo Files", "File Manager page not displayed"); 
		report.log(Status.PASS, "File Manager page displayed");
		
		//Step-5: Tap close (x) button on top left corner of "File Manager" page
		downloadPage.clickOnCloseXButtonOfFileManager();
		report.info("Tap close (x) button on top left corner of \"File Manager\" page");
		
		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		
		//ER2: SERIES still remains on "Downloads" page
		Assert.assertTrue(downloadPage.isSeriesDisplayed(), "SERIES not present on Downloads page");
		report.log(Status.PASS, "SERIES still remains on Downloads page");

		//[x] [MB, GB] added back to free space counter
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel,availableMemoryBeforeCancel), "["+memoryReverted+"] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "["+memoryReverted+"] [MB, GB] Added from free space counter");
		
	}
	@Test
	@Description(value = "Selecting a MOVIE using Quick Download")
	@Author(name = "Sridatta")
	public void FU2_07() throws InterruptedException  {
		
		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();
		//Step-1: Tap "Catalog" tab
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");
		//ER1: Main "Catalog" page displayed
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Main \"Catalog\" page displayed");
		
		//Step-2: Tap "VIEW ALL" button of any channel
		catalogPage.clickOnViewAllMoiveButton();
		report.info("Tap VIEW ALL button of any channel");
		//ER1: Channel's page displayed
		Assert.assertEquals(catalogPage.getChannelPageTitle(), "Action", "Channel's page not displayed"); 
		report.log(Status.PASS, "Channel's page displayed");
		
		//Step-3: Tap Quick Download button on bottom right corner of any MOVIE'S banner
		catalogPage.clickOnQuickDownloadButton();
		report.info("Tap Quick Download button on bottom right corner of any MOVIE'S banner");
		//ER1: Title queued for download
		/**
		 * Needs to add
		 */
		//ER2: Quick Download button changes into cancel (x) button
		Assert.assertTrue(catalogPage.isQuickCancelButtonDisplayed(), "Quick Download button not changes into cancel (x) button");
		report.log(Status.PASS, "Quick Download button changes into cancel (x) button");
		
		//Step-4: Tap "Downloads" tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");
		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		//ER2: Title displayed as first one on "MOVIES" section on "Downloads" page
		/**
		 * Needs to add
		 */
		//ER3: [x] [MB, GB] deducted from free space counter
		double availableMemoryAfterDownload = downloadPage.getAvailableStorageSpaceAfterDownload();
		deductedMemory = availableMemoryBeforeDownload - availableMemoryAfterDownload;

		Assert.assertTrue(isMemoryDeducted(availableMemoryBeforeDownload, availableMemoryAfterDownload), "["+deductedMemory+"] [MB, GB] not deducted from free space counter");
		report.log(Status.PASS, "["+deductedMemory+"] [MB, GB] deducted from free space counter");
		
	}
	
	@Test
	@Description(value = "Cancelling a MOVIE's download using Quick Cancel")
	@Author(name = "Sridatta")
	public void FU2_08() throws InterruptedException  {
		
		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		double availableMemoryBeforeCancel = downloadPage.getAvailableStorageSpaceBeforeDownload();
		
		//Step-1: Tap "Catalog" tab
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab");
		//ER1: Channel's page displayed
		
		Assert.assertEquals(catalogPage.getChannelPageTitle(), "Action", "Channel's page not displayed"); 
		report.log(Status.PASS, "Channel's page displayed");
		
		//Step-2: Tap Quick Cancel (x) button on bottom right corner of E51 MOVIE's banner
		catalogPage.clickOnQuickCancellButton();
		report.log(Status.PASS, "Tap Quick Cancel (x) button on bottom right corner of E51 MOVIE's banner");
		//ER1: Cancel (x) button changes to Quick Download button
		Assert.assertTrue(catalogPage.isQuickDownloadButtonDisplayed(), "Cancel (x) button not changes to Quick Download button");
		report.log(Status.PASS, "Cancel (x) button changes to Quick Download button");
		
		//Step-3: Tap "Downloads" tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap Downloads tab");
		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");
		//ER2: Title removed from "Downloads" page
		Assert.assertFalse(downloadPage.isMoviesLayoutDisplayed(), "Title not removed from \"Downloads\" page");
		report.log(Status.PASS, "Title removed from \"Downloads\" page");
		//ER3: [x] [MB, GB] added back to free space counter
		double availableMemoryAfterCancel = downloadPage.getAvailableStorageSpaceAfterDownload();
		double memoryReverted = availableMemoryAfterCancel - availableMemoryBeforeCancel;
		Assert.assertTrue(isMemoryReverted(availableMemoryAfterCancel,availableMemoryBeforeCancel), "["+memoryReverted+"] [MB, GB] not Added from free space counter");
		report.log(Status.PASS, "["+memoryReverted+"] [MB, GB] Added from free space counter");
		
	}
	@Test
	@Description(value = "Selecting a SERIES using Quick Download")
	
	@Author(name = "Sridatta")
	public void FU2_09() throws InterruptedException  {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		availableMemoryBeforeDownload = downloadPage.getAvailableStorageSpaceBeforeDownload();
		
		//********************************************************//
		
		//Step-1: Tap "Catalog" tab
		catalogPage.clickOnCatalogTab();
		report.info("Tap \"Catalog\" tab\n");
		
		//back button to go to catalog main page
		catalogPage.clickBackArrowButton();

		catalogPage.clickOnViewAllButtonSeries();
		report.info("Tap Click view all button displayed ");
		
		//ER1: Channel's page displayed
		Assert.assertEquals(catalogPage.getChannelPageTitle(), "Kdrama", "Channel's page not displayed"); 
		report.log(Status.PASS, "Channel's page displayed");
		//********************************************************//

		//Step-2: Tap Quick Download button on bottom right corner of any SERIES' banner
		catalogPage.clickOnQucikDownloadButtonE70();
		report.info("Tap Quick Download button on bottom right corner of any SERIES' banner");
		
		String episodeName = catalogPage.getTextOfEpisodeName();

		//ER1: Confirmation dialog displayed
		Assert.assertTrue(catalogPage.isConfirmationDialogDisplayed(), "Confirmation dialog not displayed");
		report.log(Status.PASS, "Confirmation dialog displayed");
		
		//It will return the Range that episodes needs to be download.
		int startEpisode = catalogPage.startEpisode();
		int lastEpisode = catalogPage.lastEpisode();
		report.info("Range of episode "+startEpisode+"-"+lastEpisode);
		
		//********************************************************//
		
		//Step-3: Tap "Download [x]-[y]" button 
		catalogPage.clickOnXYDownloadButton();
		report.info("Tap \"Download [x]-[y]\" button");
		
		//ER1: Confirmation dialog hidden
		Assert.assertFalse(catalogPage.isConfirmationDialogDisplayed(), "Confirmation dialog not hidden");
		report.log(Status.PASS, "Confirmation dialog hidden");
		
		//ER2: Quick Download button changes into cancel (x) button
		Assert.assertTrue(catalogPage.isQuickDownloadButtonDisplayed(), "Quick Download button changes into cancel (x) button\n");
		report.log(Status.PASS, "Quick Download button changes into cancel (x) button\n");
		
		catalogPage.clickOnBackArrowButtonSeriesPage();
		
		//********************************************************//
		
		//Step-4: Tap Downloads tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap Downloads tab");
		
		//ER1: Downloads page displayed
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
		
		//********************************************************//
		
		//Tap E35 SERIES' line item
		downloadPage.clickOnE70SeriesLineButton();
		report.info("Tap E35 SERIES' line item");
		
		//SERIES' page displayed
		Assert.assertTrue(downloadPage.isSeriesPageDisplayed(), "SERIES' page not displayed");
		report.log(Status.PASS,"SERIES' page displayed");
		
		//All episodes from SERIES that fit on device queued for download
		Set<Integer> episodeList = downloadPage.scrollToLastEpisode();
		Assert.assertTrue(isEpisodeFitOnDeviceQueue(startEpisode, lastEpisode, episodeList),"All episodes from SERIES that not fit on device queued for download");
		report.log(Status.PASS, "All episodes from SERIES that fit on device queued for download");
		
		//E35 SERIES EPISODE displays "CONNECT"
		Assert.assertTrue(downloadPage.isConnectLabelDisplayed());
		report.log(Status.PASS, "E35 SERIES EPISODE displays \"CONNECT\"");
		
		downloadPage.clickOnBackButton();
	}


	@Test
	@Description(value = "Selecting a MOVIE for download while viewing its Title Card")	
	@Author(name = "Sridatta")
	public void FU2_10() throws InterruptedException  {

		CatalogPage catalogPage = new CatalogPage(driver);
		DownloadPage downloadPage=new DownloadPage(driver);

		
		//********************************************************//
		
		//Click on Catalog Tab
		catalogPage.clickOnCatalogTab();
		report.info("catalog page displayed");
		
		//Channel's page displayed
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Main \"Catalog\" page not displayed");
		report.log(Status.PASS, "Channel's page displayed");
		
		//********************************************************//
		//Tap any MOVIE's banner from current channel
		catalogPage.scrollToTop();
		catalogPage.tapOnMovieBanner();
		report.info("Tap any MOVIE's banner from any channel's carousel");
		
		//Title Card displayed; w/ "No space" button
		Assert.assertTrue(catalogPage.isTitleCardDisplayedWithNoSpace(),"Title Card displayed; w/o \"No space\" button");
		report.log(Status.PASS, "Title Card displayed; w/ \"No space\" button");
		
		//********************************************************//
		
		//Tap "No space" button 
		catalogPage.clickOnNoSpaceButton();
		report.info("Tap No space button");

		//Informational dialog displayed
		Assert.assertTrue(catalogPage.infomartionDialougeIsDisplayed(),"Informational dialog not displayed");
		report.log(Status.PASS, "Informational dialog displayed");

		//********************************************************//
		
		//Tap "CANCEL"
		catalogPage.clickOnCancelButton();
		report.info("Tap CANCEL");
		
		//Information Dialog hidden
		Assert.assertTrue(catalogPage.isTitleCardDisplayedWithNoSpace(),"Title Card displayed; w/o \"No space\" button");
		report.log(Status.PASS, "Information Dialog hidden");
		
		catalogPage.clickOnCloseXButton();
		report.info("Tap close (x) button on top right corner of Title Card");
		
		Assert.assertEquals(catalogPage.getCatalogTitle(), "Movies & TV Shows Catalog", "Title Card not hidden");
		report.log(Status.PASS, "Title Card hidden");
		
		//Tap "Downloads" tab
		downloadPage.clickOnDownloadTab();
		report.info("Tap \"Downloads\" tab");
		
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

	}
	@Test
	@Description(value = "Connecting to the Migo Hotspot using manual connection")
	@Author(name = "Sridatta")
	public void FU2_11() throws InterruptedException  {

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
		//Tap E35 SERIES' line item
		downloadPage.clickOnSeriesViewButton();
		report.info("Go back to Migo app");
		report.info("Tap  SERIES' line item");

		//ER1: Title in E35 starts downloading
		Assert.assertTrue(downloadPage.isStartsDownloading(), "Title in E35 not starts downloading");
		report.log(Status.PASS, "Title in E35 starts downloading");

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

		//Step-4: Tap E35 SERIES' line item
		downloadPage.clickOnSeriesViewButton();
		report.info("Tap E35 SERIES' line item");

		//ER1: E35 SERIES EPISODE displays "[x]%"
		String downloadingPercentage = downloadPage.getDownloadPercentage();
		report.log(Status.PASS, "E35 SERIES EPISODE displays ["+downloadingPercentage+"]%");

	}
	@Test
	@Description(value = "Using other apps while downloading")
	
	@Author(name = "Sridatta")
	public void FU2_12() throws InterruptedException  {

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

		//ER2: E35 SERIES EPISODE continues downloading
		Assert.assertTrue(downloadPage.isDownloadProgressBarInNotificationDrawer(), "E35 SERIES EPISODE continues not downloading");
		report.log(Status.PASS, "E35 SERIES EPISODE continues downloading");

		//ER3: Out-of-app progress notification displayed in Notification Drawer
		Assert.assertTrue(downloadPage.isDownloadProgressBar(), "Out-of-app progress notification is not displayed in Notification Drawer");
		report.log(Status.PASS, "Out-of-app progress notification displayed in Notification Drawer");
	}
	
	@Test
	@Description(value = "Using Migo while downloading")
	@Author(name = "Sridatta")
	public void FU2_13() throws InterruptedException  {

		DownloadPage downloadPage=new DownloadPage(driver);

		//Step-1: After [10 seconds], tap Migo download progress notification
		downloadPage.tapMigoDownloadProgressNotification();
		report.info("After [10 seconds], tap Migo download progress notification");

		//Tap E35 SERIES' line item
		downloadPage.clickOnSeriesViewButton();
		System.out.println("click on series line");

		//Step-2: Wait for E35 SERIES EPISODE to finish downloading
		//ER1: E35 SERIES EPISODE finishes downloading
		Assert.assertTrue(downloadPage.finishDownloading(), "Wait for E35 SERIES EPISODE to not finish downloading");
		report.log(Status.PASS, "Wait for E35 SERIES EPISODE to finish downloading");

		//Tap back (<-) button on top left corner of SERIES' page
		downloadPage.clickOnBackButton();
		report.info("Tap back (<-) button on top left corner of SERIES' page");

		//Tap on E65 series
		downloadPage.clickOnE65SeriesViewButton();
		report.info("Click on E65 series");

		//ER2: Episode 1 of E65 SERIES  starts downloading
		Assert.assertTrue(downloadPage.isStartsDownloading(), "Episode 1 of E65 SERIES  not starts downloading");
		report.log(Status.PASS, "Episode 1 of E65 SERIES  starts downloading");

		//Step-3: Tap back (<-) button on top left corner of SERIES' page
		downloadPage.clickOnBackButton();
		report.info("Tap back (<-) button on top left corner of SERIES' page");

		//ER1: "Downloads" page displayed
		Assert.assertTrue(downloadPage.getDownloadPageTitle(), "Downloads page is not displayed"); 
		report.log(Status.PASS, "Downloads page displayed");

		//Step-4: Tap on E65 SERIES' line item
		downloadPage.clickOnE65SeriesViewButton();
		report.info("Tap on E65 SERIES' line item");

		//ER1: Episode 1 of E65 SERIES displays "[x]%"
		String downloadingPercentageOfE65=downloadPage.getDownloadPercentage();
		report.log(Status.PASS, "Episode 1 of E65 SERIES displays ["+downloadingPercentageOfE65+"]%");

	}
	@Test
	@Description(value = "Device is locked while waiting")
	@Author(name = "Sridatta")
	public void FU2_14() throws InterruptedException  {

		DownloadPage downloadPage=new DownloadPage(driver);

		//Step-1: Lock device
		downloadPage.lockTheDevice();
		report.info("Lock device");
		



	}	

}
