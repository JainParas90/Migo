package com.migo.appium.utils;

import java.io.IOException;
import java.time.Duration;

import org.apache.tools.ant.taskdefs.Get.DownloadProgress;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.LocksDevice;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CommonAppiumTest extends CommandPrompt{

	public AppiumDriver<MobileElement> driver;

	public CommonAppiumTest(AppiumDriver<MobileElement>driver) {
		this.driver = driver;
	}

	/**
	 * Click on element
	 * @param element
	 */
	public void clickOnElement(MobileElement element) {
		element.click();
	}

	
	/**
	 * Enter element in Box
	 * @param element
	 */
	public void enterElement(MobileElement element) {
		element.sendKeys();
	}
	/**
	 * Extract Text from Element
	 * @param element
	 * @return text
	 */
	public String getTexOfElement(MobileElement element) {
		return element.getText();
	}

	/**
	 * Check whether element is displayed
	 * @param element
	 * @return boolean
	 */
	public boolean isElementVisible(MobileElement element) {
		return element.isDisplayed();
	}

	/**
	 * 
	 * @param element
	 * @return Point(x and Y co-ordinate)
	 */
	public Point getLocationOfElement(MobileElement element) {
		return element.getLocation();
	}

	/**
	 * 
	 * @param element
	 * @return X co-ordinate
	 */
	public int getXCoordinateOfElement(MobileElement element) {
		return element.getLocation().getX();
	}

	/**
	 * 
	 * @param element
	 * @return Y co-ordinate
	 */
	public int getYCoordinateOfElement(MobileElement element) {
		return element.getLocation().getY();
	}

	/**
	 * Click On Home Button
	 */
	public void clickOnHomeButton() {
		((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.HOME);
	}

	/**
	 * Lock the device
	 */
	public void lockTheDevice() {
		((AndroidDriver) driver).lockDevice();
	}
	
	/**
	 * Check if device is locked
	 * @return 
	 */
	public boolean isDeviceLocked() {
		boolean isLocked = ((AndroidDriver) driver).isDeviceLocked();
		return isLocked;
	}
	
	/**
	 * Unlock the device
	 */
	
	public void unlockTheDevice() {
		((AndroidDriver) driver).unlockDevice();
	}

	/**
	 * Wait for Element
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementInSeconds(MobileElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Enable Wifi
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void enableWifi() throws InterruptedException, IOException {
		
		ConfigurationManager prop = ConfigurationManager.getInstance();
		String adbCommand = "adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable";
		if (prop.getProperty("Runner").equalsIgnoreCase("Local")) {
			System.out.println("In Adb local");
			runCommand(adbCommand);
		}
		else {
			driver.executeScript("pCloudy_enableWifi", true);
		}
	}

	/**
	 * Open Notification bar
	 */
	public void clickOnopenNotificationDrawer() {
		((AndroidDriver) driver).openNotifications();

	}
	
	public void backButton() {
		((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
	}
	
	/**
	 * disable Wifi
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void disableWifi() throws InterruptedException, IOException {
		ConfigurationManager prop = ConfigurationManager.getInstance();
		String adbCommand = "adb shell am broadcast -a io.appium.settings.wifi --es setstatus disable";
		if (prop.getProperty("Runner").equalsIgnoreCase("Local")) {
			System.out.println("In Adb local");
			runCommand(adbCommand);
		}
		else {
			driver.executeScript("pCloudy_enableWifi", false);

		}
	}
	
	public void resetApplication() {
		driver.resetApp();
	}
	
	/**
	 * Launch the App again.
	 */
	
	public void launchApp() {
		driver.launchApp();
	}
	
	/**
	 * Tap somewhare on the screen
	 */
	
	
	
	/**
	 * Swiping from top panel to open quick settings.
	 */
	
	public void scrollQuickSettings()
	{
		Dimension windowSize = driver.manage().window().getSize();
		int starty = (int) (windowSize.height * 0.10);
		int endy = (int) (windowSize.height * 0.90);
		int startx = windowSize.width / 2;
		new TouchAction(driver).press(PointOption.point(startx, endy)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(startx, starty)).release().perform();
	}
	
	/**
	 * Play background app again
	 */
	public void runInBackgroundApp() {
		driver.runAppInBackground(Duration.ofMillis(10));
	}
	
	/**
	 * Open Background app
	 */
	public void openbackGroundAPP()
	{
		((StartsActivity) driver).currentActivity();
	}
	
	
}
