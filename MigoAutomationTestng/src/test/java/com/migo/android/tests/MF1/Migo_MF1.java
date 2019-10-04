package com.migo.android.tests.MF1;

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
import com.migo.pages.MorePage;

public class Migo_MF1 extends UserBaseTest {
	

		Logger logger = Logger.getLogger(Migo_MF1.class.getName());
		double deductedMemory;
		double availableMemoryBeforeDownload;
		int xCorinate;

		@BeforeClass
		@Description(value = "MF")
		@Parameters({ "device" })
		@Author(name = "Paras jain")
		public void MFI_Precondition(String device) throws InterruptedException, IOException {
			CatalogPage catalogPage = new CatalogPage(driver);
			DownloadPage downloadPage = new DownloadPage(driver);
			
			//Turn Device WIFI Off
			downloadPage.disableWifi();
			//Reset App
			downloadPage.resetApplication();

			//Click on Allow button
			catalogPage.clickOnAllowButton();

			//Click on Allow button
			catalogPage.clickOnAllowButton();

			//Wait untill the video not completed
			Assert.assertTrue(downloadPage.isEmptylayoutpageDisplayed(), "video is not completed");
			report.log(Status.PASS, "video is completed");
		
		}
		
		@Test
		@Description(value = "MF1 Browsing more tab for the after launch")
		@Parameters({ "device" })
		@Author(name = "Paras Jain")
		public void MF1_001(String device) throws InterruptedException {

			MorePage morePage = new MorePage(driver);
			DownloadPage downloadPage = new DownloadPage(driver);
			// Step-1: Tap on More icon
			morePage.clickOnMoreTab();
			
			//ER 1: More Page Title is Displayed
		    Assert.assertTrue(morePage.get_title(),
		        	"More Page Title is Displayed");
			        report.log(Status.PASS, "More Page Title is Displayed");	
			morePage.get_title();
			
			  Assert.assertTrue(morePage.get_FirstcardTitle(),
						"First card Title'Saan may Migo Wifi Zone?' is Displayed");
						report.log(Status.PASS, "First card Title 'Saan may Migo Wifi Zone?'is Displayed");	
		        morePage.get_FirstcardTitle();
		        
		        Assert.assertTrue(morePage.get_FirstcardsubTitle(),
						"First card SubTitle 'Available ngayon sa Dasmariñas,Cavite' is Displayed");
						report.log(Status.PASS, "First card SubTitle 'Available ngayon sa Dasmariñas,Cavite' is Displayed");
		        morePage.get_FirstcardsubTitle();

			Assert.assertTrue(morePage.get_SecondcardTitle(),
					"Second card Title'Alamin kung paano gamitin ang Migo!' is Displayed");
					report.log(Status.PASS, "Second card Title 'Alamin kung paano gamitin ang Migo!'is Displayed");	
	        morePage.get_SecondcardTitle();
	        
	        Assert.assertTrue(morePage.get_SecondcardsubTitle(),
					"Second card SubTitle 'Paano nga ba gamitin ang Migo?' is Displayed");
					report.log(Status.PASS, "Second card SubTitle 'Paano nga ba gamitin ang Migo?' is Displayed");
	        morePage.get_SecondcardsubTitle();
	        
	      
			
			
			// Step-2: Tap First Card
			morePage.click_firstcard();
			report.info("Tap \"Firstcard\" Tab.");
			 
	       // Step-3: Tap back (<-) button on top left corner  
			morePage.click_Fcard_back();
			Assert.assertTrue(morePage.get_title(),
		        	"More Page Title is Displayed");
			        report.log(Status.PASS, "More Page Title is Displayed");

			// Step-3: Tap second Card
			morePage.click_secondcard();
			report.info("Tap \"Secondcard\" Tab.");
			
			morePage.click_onvideo();
			report.info("Tap any MOVIE's banner from any channel's carousel.");
			morePage.click_onvideo();
			report.info("Tap any MOVIE's banner from any channel's carousel.");
			morePage.click_onvideo();
			
			// Step-4:Tap Skip
			morePage.click_onskip();
			report.info("Tap Skip button on top right corner of Title Card.");
			Thread.sleep(2000);
			
			// Step-5:Scroll down 
			downloadPage.scrollElements();
			report.info("Scroll the more_card screen");
			
			// Step-6: Tap Third Card
			morePage.click_Thirdcard();
			report.info("Tap \"Thirdcard\" Tab.");
			
			/*
			// Step-7: Tap on Globe||TM icon
			morePage.click_Globe_TM();		
		    report.info("Tap on Globe||TM icon");
		    
		    // Step-8: Tap Back button
		    downloadPage.backButton();	    
		    Assert.assertTrue(morePage.getFREEMigoHotline_title(),
		        	"FREE Migo Hotline Page is Displayed");
			report.log(Status.PASS, "FREE Migo Hotline Page is Displayed");
			
			// Step-9: Tap on SMART||SUN||TNT icon
			morePage.click_Thirdcard_SMART_SUN_TNT();		
		    report.info("Tap on SMART||SUN||TNT icon");
		   
		    // Step-10: Tap Back button
		     downloadPage.backButton();  
		    Assert.assertTrue(morePage.getFREEMigoHotline_title(),
		        	"FREE Migo Hotline Page is Displayedd");
			report.log(Status.PASS, "FREE Migo Hotline Page is Displayed");
			
			*/
			
			 // Step-11: Tap Back button
			morePage.click_Fcard_back();
			report.info("Tap \"Back_icon\" Tab.");
			Assert.assertTrue(morePage.get_title(),
		        	"More Page Title is Displayed");
			report.log(Status.PASS, "More Page Title is Displayed");
			        
			morePage.click_Termscondition();		
			report.info("Tap on Terms and conditions icon");
			
			Assert.assertTrue(morePage.get_Tnc_title(),
		        	"Terms and conditions");
			report.log(Status.PASS, "Terms and conditions Page is Displayed");
			
			 
			
			
		}


	}

