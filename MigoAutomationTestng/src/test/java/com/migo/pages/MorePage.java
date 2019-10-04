package com.migo.pages;

import java.time.Duration;
import java.util.logging.Logger;
import com.migo.pageobjects.MorePageObjects;
import org.openqa.selenium.support.PageFactory;

import com.migo.appium.utils.CommonAppiumTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MorePage extends CommonAppiumTest{
	static Logger log = Logger.getLogger(CatalogPage.class.getName());
	public MorePageObjects MorePageObjects = new MorePageObjects();

	public MorePage(AppiumDriver<MobileElement>driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(5)), MorePageObjects);
	}

	public void clickOnMoreTab() {
		clickOnElement(MorePageObjects.getMoreTab());
	}

	public boolean isDifferentAssetsVisible() {
		waitForElementInSeconds(MorePageObjects.getDifferentAsset(), 20);
		return isElementVisible(MorePageObjects.getDifferentAsset());
	}

	public boolean get_title() {
		waitForElementInSeconds(MorePageObjects.getmoretitle(), 50);
		return isElementVisible(MorePageObjects.getmoretitle());
		
		
	}
	public void click_firstcard() {
		clickOnElement(MorePageObjects.getFirstcard());
	}
	public boolean get_FirstcardTitle() {
		waitForElementInSeconds(MorePageObjects.getFirstcardTitle(), 50);
		return isElementVisible(MorePageObjects.getFirstcardTitle());
		}
	
	public boolean get_FirstcardsubTitle() {
		waitForElementInSeconds(MorePageObjects.getFirstcardsubTitle(), 50);
		return isElementVisible(MorePageObjects.getFirstcardsubTitle());
		

	}
	
	public boolean get_SecondcardTitle() {
		waitForElementInSeconds(MorePageObjects.getSecondcardTitle(), 50);
		return isElementVisible(MorePageObjects.getSecondcardTitle());
		}
	
	public boolean get_SecondcardsubTitle() {
		waitForElementInSeconds(MorePageObjects.getSecondcardsubTitle(), 50);
		return isElementVisible(MorePageObjects.getSecondcardsubTitle());
		

	}
	
	public void click_secondcard() {
		clickOnElement(MorePageObjects.getSecondcard());
	}
	public void click_Fcard_back() {
		clickOnElement(MorePageObjects.Backicon_Fcard());
	}

	public void click_onvideo() {
		clickOnElement(MorePageObjects.videoclick_Scard());
	}
	public void click_onskip() {
		clickOnElement(MorePageObjects.videoskip_Scard());
	}
	public void click_Thirdcard() {
		clickOnElement(MorePageObjects.getThirdcard());
	}
	public void click_Termscondition() {
		clickOnElement(MorePageObjects.More_Termscondition());
	}
	
	public void click_Globe_TM() {
		clickOnElement(MorePageObjects.Thirdcard_Globe_TM());
	}

	public void click_Thirdcard_SMART_SUN_TNT() {
		clickOnElement(MorePageObjects.Thirdcard_SMART_SUN_TNT());
	}

	public boolean get_Tnc_title() {
		waitForElementInSeconds(MorePageObjects.getTermsandconditionstitle(), 50);
		return isElementVisible(MorePageObjects.getTermsandconditionstitle());

	}
	
	public boolean getFREEMigoHotline_title() {
		waitForElementInSeconds(MorePageObjects.getFREEMigoHotline(), 50);
		return isElementVisible(MorePageObjects.getFREEMigoHotline());

	}
	
}

