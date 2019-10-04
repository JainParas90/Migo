package com.migo.pages;
import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.support.PageFactory;

import com.migo.appium.utils.CommonAppiumTest;
import com.migo.pageobjects.MigoLogoPageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MigoLogoPage extends CommonAppiumTest{
	static Logger log = Logger.getLogger(CatalogPage.class.getName());
	public MigoLogoPageObjects MigoLogoPageObjects = new MigoLogoPageObjects();
	public MigoLogoPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(5)), MigoLogoPageObjects);
	}

	public void clickOnMigoLogo() {
		clickOnElement(MigoLogoPageObjects.getMigoLogo());
	}

	public boolean isServiceRunningVisible() {
		return isElementVisible(MigoLogoPageObjects.getServiceRunning());
	}

	public boolean checkActiveStorage() {
		String activeStorage = MigoLogoPageObjects.getActiveStorage().getText();
		if(activeStorage.equals("Internal")) {
			return true;
		} else if(activeStorage.equals("External")) {
			return true;
		} else return false;
	}

	public boolean checkReadOnly() {
		String readOnly = MigoLogoPageObjects.getReadOnly().getText();
		if(readOnly.equals("No")) {
			return true;
		} else return false;
	}

	public boolean checkDrmStatus() {
		String drmStatus = MigoLogoPageObjects.getDrmStatus().getText();
		if(drmStatus.equals("2")) {
			return true;
		} else return false;
	}

	public boolean checkStorageType() {
		String storageType = MigoLogoPageObjects.getStorageType().getText();
		if(storageType.equals("Internal")) {
			return true;
		} else if(storageType.equals("External")) {
			return true;
		} else return false;
	}

	public boolean checkdrm() {
		String drm = MigoLogoPageObjects.getDrmReady().getText();
		if(drm.equals("true")) {
			return true;
		} else return false;
	}

}
