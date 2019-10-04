package com.migo.pages;

import java.time.Duration;
import java.util.logging.Logger;
import org.openqa.selenium.support.PageFactory;
import com.migo.appium.utils.CommonAppiumTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import com.migo.pageobjects.LoadPageObjects;

public class LoadPage extends CommonAppiumTest{
static Logger log = Logger.getLogger(CatalogPage.class.getName());
public LoadPageObjects LoadPageObjects = new LoadPageObjects();

public LoadPage(AppiumDriver<MobileElement>driver) {
super(driver);
PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(5)), LoadPageObjects);
}

public void clickOnLoadTab() {
clickOnElement(LoadPageObjects.getLoadstab());
}

public boolean isDifferentLoadVisible() {
waitForElementInSeconds(LoadPageObjects.getDifferentLoad(), 20);
return isElementVisible(LoadPageObjects.getDifferentLoad());
}

}


