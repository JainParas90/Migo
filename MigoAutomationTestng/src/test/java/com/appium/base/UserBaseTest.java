package com.appium.base;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.migo.annotation.values.Author;
import com.migo.annotation.values.Description;
import com.migo.appium.utils.ConfigurationManager;
import com.migo.report.factory.ExtentManager;
import com.migo.report.factory.ExtentTestManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * 
 * @author Shibu Prasad Panda
 *
 */

public class UserBaseTest extends TestListenerAdapter implements ITestListener {

	public AppiumDriver<MobileElement> driver = null;
	public ConfigurationManager prop;
	protected boolean dontStopAppOnReset = false;
	public String device_udid;
	public ExtentTest report;
	public ExtentTest parentReport;

	Logger logger = Logger.getLogger(UserBaseTest.class);

	public UserBaseTest() {
		try {
			prop = ConfigurationManager.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Shibu Prasad Panda
	 * @param name- device name/udid
	 * @throws Exception
	 */

	@BeforeClass(alwaysRun = true)
	@Parameters({ "device", "version" })
	public void startApp(String device, String version) throws Exception {
		parentReport = ExtentTestManager
				.createTest(getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1), device);
		report = parentReport.createNode(Thread.currentThread().getStackTrace()[1].getMethodName(), "LaunchApp");
		DesiredCapabilities androidCaps = androidNative(device, version);
		try {
			this.driver = startingServerInstance(androidCaps);
			report.log(Status.PASS, "Application Launched Successfully");
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			report.fail("Driver not created");
			report.log(Status.ERROR, "<pre>" + sw.toString() + "</pre>");

			throw e;
		}
	}

	@Parameters({ "device" })
	@AfterClass(alwaysRun = true)
	public void stopApp(String device) {
		report = parentReport.createNode(Thread.currentThread().getStackTrace()[1].getMethodName(), "CloseApp");
		if (driver != null)
			this.driver.quit();
		report.log(Status.PASS, "Application Closed Successfully");
	}

	@Parameters({ "device" })
	@BeforeMethod(alwaysRun = true)
	public void reportStartUp(Method name, String device) {
		report = parentReport.createNode(name.getName(), name.getAnnotation(Description.class).value())
				.assignAuthor(name.getAnnotation(Author.class).name());
		/*
		 * report. assignCategory(device).
		 * assignAuthor(name.getAnnotation(Author.class).name());
		 */

	}

	@Parameters({ "device" })
	@AfterMethod(alwaysRun = true)
	public void reportTearDown(Method name, String device, ITestResult result) throws Exception {
		/*
		 * Success Block
		 */
		if (result.getStatus() == ITestResult.SUCCESS) {
			report.log(Status.PASS, name.getName() + " passed");
		}

		/*
		 * Failure Block
		 */
		if (result.getStatus() == ITestResult.FAILURE) {
			report.log(Status.FAIL, name.getName() + "Failed\n " + result.getThrowable().getMessage());
			// String imgPath = captureScreenShot(name.getName());
			// report.addScreenCaptureFromPath(imgPath);
			report.addScreenCaptureFromBase64String(base64conversion(), name.getName());
		}

		/*
		 * Skip Block
		 */
		if (result.getStatus() == ITestResult.SKIP) {
			report.log(Status.SKIP, "Test Skipped");
		}
	}

	@AfterSuite
	public void flushReport() {
		if (ExtentManager.getExtent() != null)
			ExtentManager.getExtent().flush();
	}

	/**
	 * 
	 * @param device Id
	 * @return capabilities instance
	 */
	public synchronized DesiredCapabilities androidNative(String device_udid, String version) {
		if (prop.getProperty("Runner").equalsIgnoreCase("Local")) {
			DesiredCapabilities androidCapabilities = new DesiredCapabilities();
			androidCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("PLATFORM"));
			androidCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("VERSION"));
			androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
					prop.getProperty("APP_ACTIVITY"));
			androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, prop.getProperty("APP_PACKAGE"));
			androidCapabilities.setCapability(MobileCapabilityType.APP,prop.getProperty("ANDROID_APP_PATH"));
			androidCapabilities.setCapability(MobileCapabilityType.UDID, device_udid);
			if (shouldSetAutomationName(version)) {
				androidCapabilities.setCapability("automationName", "UiAutomator2");
			}
			if (dontStopAppOnReset == true) {
				androidCapabilities.setCapability(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, true);
			} else {
				androidCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			}
			androidCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 360);
			return androidCapabilities;
		} else {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability("pCloudy_Username", prop.getProperty("pCloudy_Username"));
			capabilities.setCapability("pCloudy_ApiKey", prop.getProperty("pCloudy_ApiKey"));
			capabilities.setCapability("pCloudy_ApplicationName", prop.getProperty("pCloudy_ApplicationName"));
			capabilities.setCapability("pCloudy_DurationInMinutes", prop.getProperty("pCloudy_DurationInMinutes"));
			capabilities.setCapability("pCloudy_DeviceFullName", device_udid);
			capabilities.setCapability("platformVersion", version);
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("appPackage", prop.getProperty("APP_PACKAGE"));
			capabilities.setCapability("appActivity", prop.getProperty("APP_ACTIVITY"));
			capabilities.setCapability("noReset", true);
			if (shouldSetAutomationName(version)) {
				capabilities.setCapability("automationName", "UiAutomator2");
				capabilities.setCapability("noSign", true);
			}
			return capabilities;
		}
	}

	/**
	 * 
	 * @param Capabilities
	 * @return Driver Instance
	 * @throws Exception
	 */
	public AppiumDriver<MobileElement> startingServerInstance(DesiredCapabilities androidCaps) throws Exception {

		/*
		 * if (androidCaps == null) { androidCaps = androidNative(device_udid); }
		 */
		if (prop.getProperty("Runner").equalsIgnoreCase("Local")) {
			driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), androidCaps);
		} else {
			driver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("pCloudy_Endpoint")+"/appiumcloud/wd/hub"),
					androidCaps);
		}
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		return driver;

	}

	/**
	 * 
	 * @param methodName
	 * @return Image Path
	 * @throws IOException
	 */
	private String captureScreenShot(String methodName) throws IOException {

		String folder_name = "FailedTestsScreenshots";
		String screenShotNameWithTimeStamp = currentDateAndTime();
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String failedScreen = System.getProperty("user.dir") + "/" + folder_name + "/" + screenShotNameWithTimeStamp
				+ "_" + methodName + "_failed" + ".png";
		FileUtils.copyFile(srcFile, new File(failedScreen));
		String filePath = failedScreen.toString();
		return filePath;

	}

	/**
	 * 
	 * @return Base64
	 * @throws Exception
	 */
	public String base64conversion() throws Exception {
		TakesScreenshot newScreen = (TakesScreenshot) driver;
		String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
		return "data:image/jpg;base64, " + scnShot;

	}

	/**
	 * 
	 * @return CurentDate and Time
	 */
	private String currentDateAndTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		return now.format(dtf);

	}

	public boolean isMemoryDeducted(double availableMemoryBeforeDownload, double availableMemoryAfterDownload) {

		if (availableMemoryBeforeDownload > availableMemoryAfterDownload)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param availableMemoryBeforeCancel -Memory Space At start
	 * @param availableMemoryAfterCancel  -Memory Space After Cancellation
	 * @return-return the boolean value
	 */
	public boolean isMemoryReverted(double availableMemoryAfterCancel, double availableMemoryBeforeCancel) {

		if (availableMemoryAfterCancel > availableMemoryBeforeCancel)
			return true;
		else
			return false;
	}

	public boolean isEpisodeFitOnDeviceQueue(int startEpisode, int lastEpisode, Set<Integer> episodeList) {
		for (Integer episode : episodeList) {
			if (episode >= startEpisode && episode <= lastEpisode) {
				return true;
			} else {
				break;
			}
		}
		return false;
	}

	private boolean shouldSetAutomationName(String deviceVersion) {
		try {
			String str = deviceVersion.substring(0,1);
			int version = Integer.parseInt(str);
			if (version >= 6) {
				return true;
			}

			return false;
		} catch (Exception e) {
			System.out.println("version not given. Required to set automationName:UiAutomator2");
		}

		return false;
	}

}
