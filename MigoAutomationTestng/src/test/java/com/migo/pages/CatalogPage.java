package com.migo.pages;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;

import com.migo.appium.utils.CommonAppiumTest;
import com.migo.pageobjects.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CatalogPage extends CommonAppiumTest{

	static Logger log = Logger.getLogger(CatalogPage.class.getName());
	public CatalogPageObjects CatalogPageObjects = new CatalogPageObjects();

	public CatalogPage(AppiumDriver<MobileElement>driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(5)), CatalogPageObjects);

	}
	
	public void clickOnAllowButton() {
		waitForElementInSeconds(CatalogPageObjects.getAllowButton(), 20);
		clickOnElement(CatalogPageObjects.getAllowButton());
	}
	
	public void clickOnCatalogTab() {
		waitForElementInSeconds(CatalogPageObjects.getCatalogButton(), 60);
		clickOnElement(CatalogPageObjects.getCatalogButton());
	}

	public void tapOnMovieBanner() throws InterruptedException {
		clickOnElement(CatalogPageObjects.getMovieBanner());
	}

	public void clickOnDownlaodButtonForEpisode1() {
		clickOnElement(CatalogPageObjects.getEp1DownloadButton());
	}

	public void clickOnDownlaodButtonForEpisode2() {
		clickOnElement(CatalogPageObjects.getEp2DownloadButton());
	}

	public void clickOnDownlaodButton() {
		clickOnElement(CatalogPageObjects.getDownloadButton());
	}

	public void clickOnCancelDownlaodButton() {
		clickOnElement(CatalogPageObjects.getDownloadButton());
	}

	public void clickOnCancelDownlaodButtonForEP1() {
		clickOnElement(CatalogPageObjects.getEp1DownloadButton());
	}

	public void clickOnCancelDownlaodButtonForEP2() {
		clickOnElement(CatalogPageObjects.getEp2DownloadButton());
	}



	public void clickOnNoSpaceButton() {
		clickOnElement(CatalogPageObjects.getDownloadButton());
	}

	public void clickOnCloseXButton() {
		waitForElementInSeconds(CatalogPageObjects.getCloseXButton(), 30);
		clickOnElement(CatalogPageObjects.getCloseXButton());
	}

	public void clickOnCancelButton() {
		clickOnElement(CatalogPageObjects.getCANCELButton());
	}

	public String getCatalogTitle() {
		waitForElementInSeconds(CatalogPageObjects.getCatalogTitle(), 30);
		return getTexOfElement(CatalogPageObjects.getCatalogTitle());
	}

	public boolean isTitleCardDisplayed() throws InterruptedException {
		try {
			Thread.sleep(2000);
			isElementVisible(CatalogPageObjects.getTitleCard());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getBlueSnackbarTitle() {
		return getTexOfElement(CatalogPageObjects.getBlueSnackbar());
	}

	public void ClickOnSeriesBannerTitleCard() {
		scrollElementsTopToDown(CatalogPageObjects.getSeriesBanner());
		waitForElementInSeconds(CatalogPageObjects.getSeriesBanner(), 10);
		clickOnElement(CatalogPageObjects.getSeriesBanner());
	}

	public void ClickOnSeriesCloseButton() {
		clickOnElement(CatalogPageObjects.getCloseXButton());
	}

	/*public void tapOnBlueSnackBar() {
		waitForElementInSeconds(CatalogPageObjects.getViewButton(), 10);
		clickOnElement(CatalogPageObjects.getViewButton());
	}*/

	public void tapOnBlueSnackBar() {
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		clickOnElement(CatalogPageObjects.getBlueSnackbar());
	}

	public void clickOnViewAllMoiveButton() throws InterruptedException {
		scrollToTop();
		waitForElementInSeconds(CatalogPageObjects.getViewAllButton(), 20);
		clickOnElement(CatalogPageObjects.getViewAllButton());
	}

	public String getChannelPageTitle() {
		
		waitForElementInSeconds(CatalogPageObjects.getChannelPage().get(0), 20);
		return getTexOfElement(CatalogPageObjects.getChannelPage().get(1));
	}

	public void clickOnQuickDownloadButton() {
		clickOnElement(CatalogPageObjects.getQuickDownloadButton());
	}

	public void clickOnViewAllButtonSeries() {
		scrollElementsTopToDown(CatalogPageObjects.getViewAllButtonSeries());
		waitForElementInSeconds(CatalogPageObjects.getViewAllButtonSeries(),20);
		clickOnElement(CatalogPageObjects.getViewAllButtonSeries());
	}

	public void clickOnQuickCancellButton() {
		waitForElementInSeconds(CatalogPageObjects.getQuickCancelButton(), 30);
		clickOnElement(CatalogPageObjects.getQuickCancelButton());
	}

	public void clickOnBackArrowButtonSeriesPage() throws InterruptedException {
		scrollToTop();
		//waitForElementInSeconds(CatalogPageObjects.getBackArrowButtonSeriesPage(), 20);
		clickOnElement(CatalogPageObjects.getBackArrowButtonSeriesPage());
		
	}

	public String getSeriesPageTitle() {
		waitForElementInSeconds(CatalogPageObjects.getSeriesTitle(), 10);
		return getTexOfElement(CatalogPageObjects.getSeriesTitle());
	}

	public String getTextOfMovieTitle() {
		return getTexOfElement(CatalogPageObjects.getMovieTitleName());

	}

	public String getTextOfSeriesTitle() {
		return getTexOfElement(CatalogPageObjects.getSeriesTitleName());

	}

	public void clickOnQucikDownloadButtonE70() {
		clickOnElement(CatalogPageObjects.getQuickDownloadButtonForE70());
	}

	public boolean infomartionDialougeIsDisplayed() {
		try {
			isElementVisible(CatalogPageObjects.getInfomartionDialouge());;
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}

	}

	public String getChannelRangeInXYButton() {
		return getTexOfElement(CatalogPageObjects.getXYdownloadbutton());
	}

	public void tapOnHomeButton() {
		clickOnHomeButton();
	}

	public void clickOnQuickCancelButtonForE70() {
		clickOnElement(CatalogPageObjects.getQuickCancelButtonForE70());
	}

	public void clickOnXYDownloadButton() {
		clickOnElement(CatalogPageObjects.getXYdownloadbutton());
	}

	public boolean isCatalogTabDisplayed() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isElementVisible(CatalogPageObjects.getCatalogButton());
	}

	public boolean isAndroidHomeScreenDisplayed() {
		try {
			isCatalogTabDisplayed();
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isCancelDownloadButtonDisplayed(String text) {
		return getTexOfElement(CatalogPageObjects.getCancelDownloadButton()).contains(text);
	}

	public boolean isEpisode1CancelDownloadButtonDisplayed(String text) {
		waitForElementInSeconds(CatalogPageObjects.getEpisodeCancelDownloadButton().get(0), 30);
		return getTexOfElement(CatalogPageObjects.getEpisodeCancelDownloadButton().get(0)).toLowerCase().contains(text.toLowerCase());
	}


	public boolean isEpisode2CancelDownloadButtonDisplayed(String text) { 
		return getTexOfElement(CatalogPageObjects.getEpisodeCancelDownloadButton().get(1)).contains(text); 
	}

	public boolean isBlueSnackBarDisplayed() {
		try {
		//	waitForElementInSeconds(CatalogPageObjects.getBlueSnackbar(), 3);
			isElementVisible(CatalogPageObjects.getBlueSnackbar());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isQuickDownloadButtonDisplayed() {
		try {
			isElementVisible(CatalogPageObjects.getQuickDownloadButton());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isQuickCancelButtonDisplayed() {
		try {
			isElementVisible(CatalogPageObjects.getQuickCancelButton());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isConfirmationDialogDisplayed() {
	//	waitForElementInSeconds(CatalogPageObjects.getConformationDialouge(), 30);
		try {
			isElementVisible(CatalogPageObjects.getConformationDialouge());
			return true;
		}catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getRangeinInt()
	{
		String channelRange=getChannelRangeInXYButton();
		int index = channelRange.indexOf("EP"); 
		return channelRange.substring(index+2);
	}

	public Integer startEpisode() {
		return Integer.parseInt(getRangeinInt().substring(0,getRangeinInt().indexOf("-")));
	}

	public Integer lastEpisode() {
		return Integer.parseInt(getRangeinInt().substring(getRangeinInt().indexOf("-")+1));
	}

	public String getTextOfEpisodeName() {
		String episodeName = getTexOfElement(CatalogPageObjects.getSeriesEpisodeName());
		return episodeName.substring(episodeName.indexOf("of ")+2, episodeName.length()-1).trim();
	}

	public String getTextOfNoSpaceButton() {
		return getTexOfElement(CatalogPageObjects.getNoSpaceButton());
	}

	public void scrollElementsTopToDown(MobileElement element)
	{
		while(true) {
			try {
				if(element.isDisplayed()) {
					break;
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			Dimension windowSize = driver.manage().window().getSize();
			int startx = (int) (windowSize.width * 0.10);
			int endx = (int) (windowSize.width * 0.90);
			int starty = windowSize.height / 2;
			new TouchAction(driver).press(PointOption.point(starty, endx)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(starty, startx)).release().perform();
		}
	}

	public void scrollElementsBottomToTop(MobileElement element) {
		int counter =0;
		while(counter<=10) {
			try {
				if(element.isDisplayed()) {
					break;
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			Dimension windowSize = driver.manage().window().getSize();
			int starty = (int) (windowSize.width * 0.20);
			int endy = (int) (windowSize.height * 0.80);
			int startx = windowSize.width / 2;
			counter++;
		}
	}

	public void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {


		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.width * anchorPercentage);
		int startPoint = (int) (size.height * startPercentage);
		int endPoint = (int) (size.height * endPercentage);

		new TouchAction(driver)
		.press(PointOption.point(anchor, startPoint))
		.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		.moveTo(PointOption.point(anchor, endPoint))
		.release().perform();
	}

	public void verticalSwipeByPercentages(MobileElement element) {
		int counter = 0;
		while(counter<=10) {
			try {
				if(element.isDisplayed()) {
					break;
				}
			}catch (Exception e) {
				// TODO: handle exception
				verticalSwipeByPercentages(0.1, 0.6, 0.1);
			}
			counter++;
		}
	}

	public void scrollForEpisodeTopToDown()
	{
		Dimension windowSize = driver.manage().window().getSize();
		int startx = (int) (windowSize.width * 0.10);
		int endx = (int) (windowSize.width * 0.80);
		int starty = windowSize.height / 2;
		new TouchAction(driver).press(PointOption.point(starty, endx)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(starty, startx)).release().perform();
	}

	public boolean isTitleCardDisplayedWithNoSpace() {
		if(getTextOfNoSpaceButton().contains("No Space")) {
			return true;
		}else {
			return false;
		}
	}

	public boolean isProgressBarIsDisplayed() {
		return isElementVisible(CatalogPageObjects.getProgressBar());
	}
	
	public void resetApp() {
		resetApplication();
	}
	
	public String getDrm() {
		waitForElementInSeconds(CatalogPageObjects.getCatalogTitle(), 30);
		return getTexOfElement(CatalogPageObjects.getCatalogTitle());
	}
	
	public void clickOnDownloadOtherMoive()
	{
		waitForElementInSeconds(CatalogPageObjects.getDownloadOtherMoive(), 190);
		clickOnElement(CatalogPageObjects.getDownloadOtherMoive());
	}	
	public void getCloseXButtonForAnotherMoive()
	{
		clickOnElement(CatalogPageObjects.getCloseXButtonForAnotherMoive());
	}

   public void scrollToTop() throws InterruptedException {
		
		int counter = 5;
		for(int i=0;i<counter;i++)
		{
			verticalSwipeByPercentages(0.2, 0.6, 0.3);
		}
		
   }
   public void scrollToTop(MobileElement element) {
		int counter =0;
		while(counter<=10) {
			try {
				if(element.isDisplayed()) {
					break;
				}
			}catch (Exception e) {
				// TODO: handle exception
				verticalSwipeByPercentages(0.2, 0.6, 0.3);
				counter++;
				
			}
		}
	}
	   		
	public void clickBackArrowButton() throws InterruptedException
	{	
		clickOnElement(CatalogPageObjects.getBackButton());
	}
	
	public void clickOnMigoLogo() throws InterruptedException
	{	
		clickOnElement(CatalogPageObjects.getMigoLogo());
	}
	public void clickOnUserType() throws InterruptedException
	{	
		clickOnElement(CatalogPageObjects.getUserType());
	}
	
	public boolean isCatalogEmpty() {
		waitForElementInSeconds(CatalogPageObjects.getEmptyCatalogPage(), 10);
		return isElementVisible(CatalogPageObjects.getEmptyCatalogPage());
		}

		public boolean isChannelsVisible() {
		waitForElementInSeconds(CatalogPageObjects.getMovieBanner(), 190);
		return isElementVisible(CatalogPageObjects.getMovieBanner());
		}
		

public boolean isMovieBannerDisplayed() {
return isElementVisible(CatalogPageObjects.getMovieBanner());
}
}

