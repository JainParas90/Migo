package com.migo.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.migo.appium.utils.CommonAppiumTest;
import com.migo.pageobjects.DownloadPageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DownloadPage extends CommonAppiumTest{
	static Logger log = Logger.getLogger(CatalogPage.class.getName());
	public DownloadPageObjects DownloadPageObjects = new DownloadPageObjects();

	public DownloadPage(AppiumDriver<MobileElement>driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(5)), DownloadPageObjects);

	}
	public void clickOnDownloadTab() {
		clickOnElement(DownloadPageObjects.getDownloadsTab());
	}

	public void clickOnYesCancelButton() {
		clickOnElement(DownloadPageObjects.getYesCancelButton());
	}

	public boolean getDownloadPageTitle() {
		waitForElementInSeconds(DownloadPageObjects.getDownloadPageTitle(), 250);
		return isElementVisible(DownloadPageObjects.getDownloadPageTitle());
	}

	public void clickOnSeriesLineButton() {
		clickOnElement(DownloadPageObjects.getSeriesLineButton().get(0));
	}

	public void clickOnE70SeriesLineButton() {
		clickOnElement(DownloadPageObjects.getSeriesLineButton().get(1));
	}

	public String getDowdloadingNextLable() {
		return getTexOfElement(DownloadPageObjects.getDownloadingNextlable());
	}

	public String getMoiveTag() {
		return getTexOfElement(DownloadPageObjects.getMoiveSection());
	}

	public String getAvailableMemory() {
		waitForElementInSeconds(DownloadPageObjects.getMemorySection(), 30);
		return getTexOfElement(DownloadPageObjects.getMemorySection());
	}

	public void clickOnCancelXButton() {
		clickOnElement(DownloadPageObjects.getCancelXButton());
	}

	public String getConforamtionDialouge() {
		return getTexOfElement(DownloadPageObjects.getConfromationTab());
	}

	public String getEmptyPageLayout() {
		return getTexOfElement(DownloadPageObjects.getEmptyPageLayout());
	}

	public boolean isEmptylayoutpageDisplayed() {
		try {
			waitForElementInSeconds(DownloadPageObjects.getEmptyPageLayout(), 250);
			isElementVisible(DownloadPageObjects.getEmptyPageLayout());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getTvSeriesTag() {
		return getTexOfElement(DownloadPageObjects.getTvSeriesTag());
	}

	public void clickOnSeriesCancelButton() {
		clickOnElement(DownloadPageObjects.getSeriesCancelButton());
	}

	public void clickOnSeriesViewButton() {
		waitForElementInSeconds(DownloadPageObjects.getSeriesViewButton(), 10);
		clickOnElement(DownloadPageObjects.getSeriesViewButton());
	}

	public void clickOnFileManagerButton() {
		waitForElementInSeconds(DownloadPageObjects.getFileManagerButton(), 10);
		clickOnElement(DownloadPageObjects.getFileManagerButton());
	}

	public String getManageMigoFilesTitle() {
		return getTexOfElement(DownloadPageObjects.getManageMigoFilesTitle());
	}

	public void clickOnSeriesLineItem() {
		clickOnElement(DownloadPageObjects.getSeriesLineButton().get(0));
	}

	public void clickOnBackButton() {
		waitForElementInSeconds(DownloadPageObjects.getBackButton(), 20);
		clickOnElement(DownloadPageObjects.getBackButton());
	}

	//	public void clickOnEpisode1CancelButton() {
	//		waitForElementInSeconds(DownloadPageObjects.getCancelSeriesEpisode().get(0), 20);
	//		clickOnElement(DownloadPageObjects.getCancelSeriesEpisode().get(0));
	//	}

	public void clickOnEpisode1CancelButton() {
		waitForElementInSeconds(DownloadPageObjects.getCancelXSeriesEpisode1(), 20);
		clickOnElement(DownloadPageObjects.getCancelXSeriesEpisode1());
	}

	public void clickOnEpisode2CancelButton() {
		clickOnElement(DownloadPageObjects.getCancelXSeriesEpisode2());
	}

	public boolean isStartsDownloading() {
		return isElementVisible(DownloadPageObjects.getStartsDownloading());

	}

	public String getConnectivityIndicatorStatus() {
		String DownloadingStaus=  getTexOfElement(DownloadPageObjects.getConnectivityIndicator());
		DownloadingStaus = DownloadingStaus.replace("\n", "").replace("\r", "").trim();
		return DownloadingStaus;
	}

	public String downloadingXepisode() {
		return getTexOfElement(DownloadPageObjects.getDowndloadingXepisode().get(0));
	}

	public String findXepisode() {
		String getEpisode = downloadingXepisode();
		getEpisode = getEpisode.replaceAll("[^0-9 ]", "").trim();
		return getEpisode;
	}

	public String getDownloadPercentage() {
		String downloadPercent = getTexOfElement(DownloadPageObjects.getDownloadingPercentage());
		downloadPercent = downloadPercent.replaceAll("[^0-9 ]", "").trim();
		return downloadPercent;

	}

	public void tapMigoDownloadProgressNotification() {
		clickOnElement(DownloadPageObjects.getDownloadProgressBar());
	}

	public boolean finishDownloading() throws InterruptedException {

		while (true) {
			try {
				boolean displayed = DownloadPageObjects.getFinishDownloadButton().isDisplayed();
				if (displayed)
					break;
			} catch (Exception e) {

			}
			Thread.sleep(3000);
		}
		return true;
	}

	public void clickOnE69SeriesViewButton() {
		waitForElementInSeconds(DownloadPageObjects.getSeriesLineButton().get(1), 10);
		clickOnElement(DownloadPageObjects.getSeriesLineButton().get(1));
	}

	public void clickOnE65SeriesViewButton() {
		waitForElementInSeconds(DownloadPageObjects.getSeriesLineButton().get(1), 10);
		clickOnElement(DownloadPageObjects.getSeriesLineButton().get(1));
	}

	public boolean isDownloadProgressBar() {
		return isElementVisible(DownloadPageObjects.getDownloadProgressBar());

	}

	public boolean isDownloadProgressBarInNotificationDrawer() {
		return isElementVisible(DownloadPageObjects.getNotificationDownloadProgressBar());
	}

	public void clickOnCloseXButtonOfFileManager() {
		clickOnElement(DownloadPageObjects.getCloseXButton());
	}
	public boolean getSeriesLayout() {
		return isElementVisible(DownloadPageObjects.getMoivesLayout());
	}

	public boolean isYesCancelDisplayed() {
		try {
			isElementVisible(DownloadPageObjects.getYesCancelButton());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isEmptyPageLayoutDisplayed() {
		try {
			isElementVisible(DownloadPageObjects.getEmptyPageLayout());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getTextFromSeries() {
		return getTexOfElement(DownloadPageObjects.getConnectToDownload());
	}

	public boolean isSeriesDisplayed() {
		try {
			isElementVisible(DownloadPageObjects.getSeriesList().get(0));
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public List<String> getMovieNameList() {

		waitForElementInSeconds(DownloadPageObjects.getMovieTitle().get(0), 30);
		List<String> movieName= new ArrayList<String>(); 
		List<MobileElement> list = DownloadPageObjects.getMovieTitle();
		for(MobileElement element: list) {
			movieName.add(element.getText());
		}

		return movieName;
	}

	public List<String> getSeriesNameList() {

		waitForElementInSeconds(DownloadPageObjects.getSeriesTitle().get(0), 30);
		List<String> seriesName= new ArrayList<String>(); 
		List<MobileElement> list = DownloadPageObjects.getSeriesTitle();
		for(MobileElement element: list) {
			seriesName.add(element.getText());
		}

		return seriesName;
	}

	public int getYCordinateOfSeekBar()
	{
		return getYCoordinateOfElement(DownloadPageObjects.getMoiveTime());
	}
	public int getXCordinateOfSeekBar()
	{
		return getXCoordinateOfElement(DownloadPageObjects.getSeekBarButton());
	}

	public int getYCordinateOfEpisode1() {
		return getYCoordinateOfElement(DownloadPageObjects.getEpisodeListInsideSeries().get(0));
	}

	public int getYCordinateOfEpisode2() {
		return getYCoordinateOfElement(DownloadPageObjects.getEpisodeListInsideSeries().get(1));
	}

	public String getTextOfEpisode1() {
		return getTexOfElement(DownloadPageObjects.getEpisodeListInsideSeries().get(0));
	}

	public String getTextOfSecondTvShows() {
		return getTexOfElement(DownloadPageObjects.getTvShows().get(1));
	}

	public String getTextOfEpisode2() {
		return getTexOfElement(DownloadPageObjects.getEpisodeListInsideSeries().get(1));
	}

	public boolean isEpisode1Removed() {
		if(getTextOfEpisode1().contains("EP1"))
			return false;
		else
			return true;
	}

	public boolean isEpisode2Displayed() {
		if(getTextOfEpisode1().contains("EP2"))
			return true;
		else
			return false;
	}

	public boolean isTitlePageDisplayedAsSecond(String episodeName) {
		System.out.println(DownloadPageObjects.getTvShows().size());
		waitForElementInSeconds(DownloadPageObjects.getTvShows().get(1), 10);
		System.out.println(getTextOfSecondTvShows());
		System.out.println(episodeName);
		if(getTextOfSecondTvShows().trim().contentEquals(episodeName)) {
			return true;
		}else {
			return false;
		}
	}

	public boolean isEpisode1FollowedByEpisode2() {
		if(getYCordinateOfEpisode1() < getYCordinateOfEpisode2()) 
			return true;
		else
			return false;

	}

	public boolean isSeriesPageDisplayed() {
		try {
			isElementVisible(DownloadPageObjects.getSeriesPage());
			return true;
		}catch (NoSuchElementException e) {
			try {
				isElementVisible(driver.findElement(By.xpath("//android.view.View[@resource-id='com.migo.androidplayer:id/htab_maincontent']")));
				return true;
			}catch (NoSuchElementException ex) {
				return false;
			}
		}
	}

	public boolean isMoviesLayoutDisplayed() {

		try {
			isElementVisible(DownloadPageObjects.getMoivesLayout());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public double getAvailableStorageSpaceBeforeDownload() {
		String text = getAvailableMemory();
		return getValueAsMB(text);

	}

	public double getAvailableStorageSpaceAfterDownload() {
		String text = getAvailableMemory();
		return getValueAsMB(text);
	}

	public double getValueAsMB(String text) {
		String freeSpace = text.substring(0, text.indexOf(" "));
		double value = Double.parseDouble(freeSpace);

		if (text.contains("MB")) {
			return value;
		}

		// else GB
		return (value * 1024);
	}

	public boolean isConnectLabelDisplayed() {
		try {
			isElementVisible(DownloadPageObjects.getConnectLabel());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}

	}

	public void scrollElements()
	{
		Dimension windowSize = driver.manage().window().getSize();
		int startx = (int) (windowSize.width * 0.10);
		int endx = (int) (windowSize.width * 0.90);
		int starty = windowSize.height / 2;
		new TouchAction(driver).press(PointOption.point(starty, endx)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(starty, startx)).release().perform();
	}

	public void scrollUp() {
		try {
			Dimension windowSize = driver.manage().window().getSize();
			int startx = (int) (windowSize.height);
			int endx = (int) (windowSize.height * 0.001);
			int starty = windowSize.width / 2;
			new TouchAction(driver).press(PointOption.point(starty, startx)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(starty, endx)).release().perform();

		} catch(NoSuchElementException e) {
			Dimension windowSize = driver.manage().window().getSize();
			int startx = (int) (windowSize.width);
			int endx = (int) (windowSize.width * 0.001);
			int starty = windowSize.height / 2;
			new TouchAction(driver).press(PointOption.point(starty, startx)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(starty, endx)).release().perform();
		}
	}

	public void scrollForToolbar () {
		try {
			Dimension windowSize = driver.manage().window().getSize();
			int starty = (int) (windowSize.height * 0.01);
			int endy = (int) (windowSize.height * 0.90);
			int startx = windowSize.width / 2;
			new TouchAction(driver).press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(startx, endy)).release().perform();
			new TouchAction(driver).press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(startx, endy)).release().perform();
		} catch(NoSuchElementException e) {
			Dimension windowSize = driver.manage().window().getSize();
			int starty = (int) (windowSize.width * 0.01);
			int endy = (int) (windowSize.width * 0.90);
			int startx = windowSize.height / 2;
			new TouchAction(driver).press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(startx, endy)).release().perform();
			new TouchAction(driver).press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(startx, endy)).release().perform();
		}
	}

	public boolean isLastEpisodeDisplayed() {	
		try {
			isElementVisible(DownloadPageObjects.getSelectMore());
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}

	public Set<Integer> scrollToLastEpisode()
	{
		Set<Integer> set = new HashSet<Integer>();
		do{  
			scrollElements();
			List<MobileElement>episodes = DownloadPageObjects.getEpisodeListInsideSeries();
			for(MobileElement episode: episodes) {
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(episode.getText());
				while(m.find()) {
					set.add(Integer.parseInt(m.group()));
				}
			}
		}while(isLastEpisodeDisplayed()==false);  
		return set;
	}

	public boolean isStartDownloading() {
		return isElementVisible(DownloadPageObjects.getTitleStartDownloading());
	}

	public boolean isDownloadingBar() {
		return isElementVisible(DownloadPageObjects.getDownloadingBar());
	}

	public void clickOnOkButton() {
		clickOnElement(DownloadPageObjects.getOkButton());
	}

	public void clickOnPlayButton() {
		clickOnElement(DownloadPageObjects.getPlayButton());
	}

	public void clickOnPlayIcon() throws InterruptedException {
		
		clickOnElement(DownloadPageObjects.getPlayIcon());
		tapOnScreen();
	}

	public boolean isDownoadTabVisible() {
		return isElementVisible(DownloadPageObjects.getDownloadTabTitle());
	}

	public boolean isMtcRatingdisplayed() {
		waitForElementInSeconds(DownloadPageObjects.getMtcRating(), 5);
		return isElementVisible(DownloadPageObjects.getMtcRating());
	}

	public boolean isSeekBarDispalyed() throws InterruptedException {
		tapOnScreen();
		return isElementVisible(DownloadPageObjects.getSeekBarButton());
	}

	public double getTextOfMoiveTime() {
		try {
			waitForElementInSeconds(DownloadPageObjects.getMoiveTime(), 20);
			String moiveTimeInString=getTexOfElement(DownloadPageObjects.getMoiveTime());
			String replaceString=moiveTimeInString.replace(':','.');
			double moiveTime = Double.parseDouble(replaceString);
			return moiveTime;
		}catch (NoSuchElementException e) {
			return -0.00;
		}

	}

	public boolean isTitleStratsPLaying() throws InterruptedException
	{

		tapOnScreen();
		double startTime=getTextOfMoiveTime();
		Thread.sleep(5000);
		tapOnScreen();
		double endTime=getTextOfMoiveTime();
		if(endTime>startTime)
		{
			return true;
		}
		return false;
	}

	public boolean isTitlePlayedStopped() throws InterruptedException
	{
		double startTime=getTextOfMoiveTime();
		Thread.sleep(5000);
		tapOnScreen();
		double endTime=getTextOfMoiveTime();
		if(endTime==startTime)
		{
			return true;
		}
		return false;
	}

	public boolean isPlayBackControlsDisplayed() throws InterruptedException
	{
		if(isPausePlayVisible()&&isSeekBarDispalyed())
		{
			return true;
		}
		return false;
	}

	public void clickOnVideoScreen() throws InterruptedException {
		System.out.println(isVideoBackArrowButtonDisplayed());
		if(isVideoBackArrowButtonDisplayed())
		{
			System.out.println(isVideoBackArrowButtonDisplayed());
		}
		else 
			clickOnElement(DownloadPageObjects.getVideoScreen());

	}

	public boolean isPlayBackDisplayed() throws InterruptedException {
	//	tapOnScreen();
		return isElementVisible(DownloadPageObjects.getMoiveTime());
	}

	public boolean isTrashCanVisible() {
		waitForElementInSeconds(DownloadPageObjects.getTrashCan(), 20);
		return isElementVisible(DownloadPageObjects.getTrashCan());
	}

	public boolean isTitleProgrssBarVisible() {
		return isElementVisible(DownloadPageObjects.getTitleProgressBar());
	}

	public void clickOnVideoBackArrowButton() throws InterruptedException {
		tapOnScreen();
		clickOnElement(DownloadPageObjects.getVideoBackArrowButton());
	}

	public boolean isVideoBackArrowButtonDisplayed() throws InterruptedException {
		try {
			isElementVisible(DownloadPageObjects.getVideoBackArrowButton());
			return true;

		}
		catch(Exception e)
		{
			return false;
		}
	}

	public boolean isAutoPlayDisplayed() {
		try {
			isElementVisible(DownloadPageObjects.getAutoPlay());
			return true;

		}
		catch(NoSuchElementException e)
		{
			return false;
		}

	}

	public boolean isNotificationDrawerVisible() {
		return isElementVisible(DownloadPageObjects.getNotificationDrawer());
	}

	public boolean isPausePlayVisible() throws InterruptedException {
		tapOnScreen();
		return isElementVisible(DownloadPageObjects.getPlayIcon());
	}

	public boolean isplayingagainatlastmark() throws InterruptedException
	{
		if(isTitleStratsPLaying())
		{
			return true;
		}
		return false;
	}

	public boolean isAndroidToolbarDisplayed() {
		try {
			return isElementVisible(DownloadPageObjects.getVideoScreen());
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public int move180Sec() throws InterruptedException
	{

		int x=20,xCorinate;
		Double moivetime=getTextOfMoiveTime();
		moivetime=moivetime+3.00;
		while(true)
		{

			if(getTextOfMoiveTime()<=moivetime)
			{
				System.out.println("inside method");
				tapOnScreen1(getXCordinateOfSeekBar()+x,getYCordinateOfSeekBar());
				x=x+10;
			}
			else
			{
				xCorinate=getXCordinateOfSeekBar()+x;
				break;
			}
		}
		return xCorinate;
	}
	public void tapOnScreen1(int xcorinate,int ycordinate) throws InterruptedException {

		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(xcorinate,ycordinate)).perform();
	}
	public boolean isAutoPlayPageDisplayed() {
		try {
			isElementVisible(DownloadPageObjects.getAutoPlayPage());
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}

	}

	public void scrollToLast(int xCorinate,int yCordinate) throws InterruptedException
	{
		//Scroll till last
		Double moivetime;
		while(true)
		{
			System.out.println("Starting of moive ");
			tapOnScreen();
			moivetime=getTextOfMoiveTime();
			System.out.println(moivetime);
			if(moivetime<-0.00)
			{
				xCorinate=xCorinate+40;
				tapOnScreen1(xCorinate,yCordinate);
				System.out.println(isAutoPlayDisplayed());
				if(isAutoPlayDisplayed()==true)
				{
					System.out.println(isAutoPlayDisplayed());
					break;
				}
			}
			System.out.println("ending of moive ");
			/*else
			{
				break;
			}*/
		}
	}
	public void tapOnScreen() throws InterruptedException {

		clickOnVideoScreen();
	}


	public void clickOnDeny() {
		waitForElementInSeconds(DownloadPageObjects.getDeny(), 40);
		clickOnElement(DownloadPageObjects.getDeny());
	}

	public void clickOnAllow() {
		waitForElementInSeconds(DownloadPageObjects.getAllow(), 40);
		clickOnElement(DownloadPageObjects.getAllow());
	}

	public void clickOnClosePopup() {
		waitForElementInSeconds(DownloadPageObjects.getClosePopup(), 10);
		clickOnElement(DownloadPageObjects.getClosePopup());
	}

	public void clickOnMigoLogo() {
		clickOnElement(DownloadPageObjects.getMigoLogo());
	}

	public boolean isServiceRunningVisible() {
		return isElementVisible(DownloadPageObjects.getServiceRunning());
	}

	public boolean isSplashLoadingVisible() {
		try {
			isElementVisible(DownloadPageObjects.getSplashLoading());
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}

	public boolean isPermissionGuideVisible() {
		waitForElementInSeconds(DownloadPageObjects.getPermissionGuide(), 10);
		return isElementVisible(DownloadPageObjects.getPermissionGuide());
	}

	public boolean isPermissionPromptVisible() {
		waitForElementInSeconds(DownloadPageObjects.getPermissionPrompt(), 30);
		return isElementVisible(DownloadPageObjects.getPermissionPrompt());
	}

	public boolean isSettingPromptVisible() {
		waitForElementInSeconds(DownloadPageObjects.getSettingPrompt(), 10);
		return isElementVisible(DownloadPageObjects.getSettingPrompt());
	}

	public boolean isTutoralVideoDisplayed() {
		waitForElementInSeconds(DownloadPageObjects.getTutorialVideo(), 50);
		return isElementVisible(DownloadPageObjects.getTutorialVideo());
	}

	public boolean isConnectivityIndicatorStatusVisible() {
		waitForElementInSeconds(DownloadPageObjects.getConnectivityIndicator(), 140);
		return isElementVisible(DownloadPageObjects.getConnectivityIndicator());
	}

}

