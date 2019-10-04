package com.migo.pageobjects;

import java.util.List;

import com.migo.annotation.values.ElementDescription;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;

/**
 * 
 * @author Shibu Prasad Panda
 * 
 * This class is used to store the objects of Catalog Page.
 *
 */

public class CatalogPageObjects {


	@ElementDescription(value = "Allow Button")
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	private MobileElement allowButton;
	
	@ElementDescription(value = "Catalog tab")
	@AndroidFindBy(id = "com.migo.androidplayer:id/relativeLayout_tab_explore")
	private MobileElement catalogTab;
	

	@ElementDescription(value = " MOVIE's banner")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Action']//following::android.widget.ImageView[@resource-id='com.migo.androidplayer:id/title_image_item'][1] | //android.widget.TextView[@text='ACTION']//following::android.widget.ImageView[@resource-id='com.migo.androidplayer:id/title_image_item'][1]")
	private MobileElement movieBanner;

	@ElementDescription(value = " MOVIE's Title")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txt_title_name']")
	private MobileElement movieTitleName;
	
	@ElementDescription(value = " Series's Title")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txt_title_name']")
	private MobileElement seriesTitleName;

	@ElementDescription(value = "Download button")
	@AndroidFindBys(value = { @AndroidBy(xpath = "//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/add_remove_icon']")})
	private List<MobileElement> downloadButton;
	
	@ElementDescription(value = "EP1 Download Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'EP1')]//following::android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/catalog_button_container'][1]")
	private MobileElement ep1DownloadButton;
	
	@ElementDescription(value = "EP2 Download Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'EP2')]//following::android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/catalog_button_container'][1]")
	private MobileElement ep2DownloadButton;

	@ElementDescription(value = "Catalog Page Title")
	@AndroidFindBy(id = "com.migo.androidplayer:id/txtToolBarTitle")
	private MobileElement catalogPageTitle;

	@ElementDescription(value = "Title Card")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/iv_title_banner']")
	private MobileElement titleCard;

	@ElementDescription(value = "Cancel Button")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_download_episode']")
	private MobileElement cancelButton;

	@ElementDescription(value = "Blue Snackbar")
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='VIEW']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_message']")
	private MobileElement blueSnackbar;

	@ElementDescription(value = "Series Banner")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Kdrama']//following::android.widget.ImageView[@resource-id='com.migo.androidplayer:id/title_image_item'][1] | //android.widget.TextView[@text='KDRAMA']//following::android.widget.ImageView[@resource-id='com.migo.androidplayer:id/title_image_item'][1]")
	private MobileElement seriesBanner;

	@ElementDescription(value = "Close (x) Button")
	@AndroidFindBys(value = { @AndroidBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.migo.androidplayer:id/dismiss_content_view']")})
	private List<MobileElement> closeXButton;

	@ElementDescription(value = "Blue Snackbar View Button")
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='VIEW']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_message']")
	private MobileElement viewButton;

	@ElementDescription(value = "View All Button for Action")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Action']/../..//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/layout_view_all']//android.widget.TextView[@text='View All'] | //android.widget.TextView[@text='ACTION']/../..//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/layout_view_all']//android.widget.TextView[@text='VIEW ALL']")
	private MobileElement viewAllButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_quick_download_button']")
	private MobileElement XYdownloadbutton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_quick_download']")
	private MobileElement seriesEpisodeName;

	@ElementDescription(value = "View All Button for Kdrama")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Kdrama']/../..//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/layout_view_all']//android.widget.TextView[@text='View All'] | //android.widget.TextView[@text='KDRAMA']/../..//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/layout_view_all']//android.widget.TextView[@text='VIEW ALL']")
	private MobileElement viewAllButtonSeries;

	@ElementDescription(value = "ChannelPageTitles")
	@AndroidFindBys(value = { @AndroidBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txtToolBarTitle']")})
	private List<MobileElement> channelPageTitle;

	@ElementDescription(value = "Quick Download Button")
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[@resource-id='com.migo.androidplayer:id/activity_auto_play']/android.widget.ImageView")
	private MobileElement quickDownloadButton;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[2]/android.widget.RelativeLayout[@resource-id='com.migo.androidplayer:id/activity_auto_play']/android.widget.ImageView")
	private MobileElement quickDownloadButtonForE70;

	@ElementDescription(value = "Quick Cancel Button")
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[@resource-id='com.migo.androidplayer:id/activity_auto_play']/android.widget.ImageView")
	private MobileElement quickCancelButton;

	@ElementDescription(value = "Quick Cancel Button")
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[2]/android.widget.RelativeLayout[@resource-id='com.migo.androidplayer:id/activity_auto_play']/android.widget.ImageView")
	private MobileElement quickCancelButtonForE70;

	@ElementDescription(value = "")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/ll_channel_title_header']")
	private MobileElement quickDownloadCannelSeries;

	@ElementDescription(value = "Series Title")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txtToolBarTitle']")
	private MobileElement seriesTitle;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/migo_logo_iv']")
	private MobileElement backArrowButtonSeriesPage;

	@ElementDescription(value = "Confirmation Dialouge")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_quick_download_button']")
	private MobileElement conformationDialouge;

	@ElementDescription(value = "Infomartion Dialouge")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Migo File Manager']")
	private MobileElement infomartionDialouge;

	@ElementDescription(value = "Movie Cancel Download Text")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_download']")
	private MobileElement cancelDownloadButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_download']")
	private MobileElement noSpaceButton;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.migo.androidplayer:id/rl_cancel']")
	private MobileElement CANCELButton;

	@ElementDescription(value = "Episode Cancel Download Button")
	@AndroidFindBys(value = { @AndroidBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_download_episode']")})
	private List<MobileElement> episodeCancelDownloadButton;

	@ElementDescription(value = "ProgressBar for the player page.")
	@AndroidFindBy(xpath = "//android.widget.ProgressBar[@resource-id='com.migo.androidplayer:id/pb_downloads']")
	private MobileElement ProgressBar ;
	
	@ElementDescription(value = "Clicking on another movie.")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Action']//following::android.widget.ImageView[@resource-id='com.migo.androidplayer:id/title_image_item'][2] | //android.widget.TextView[@text='ACTION']//following::android.widget.ImageView[@resource-id='com.migo.androidplayer:id/title_image_item'][2]")
	private MobileElement downloadOtherMoive ;
	
	@ElementDescription(value = "Back arrow button for moive")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/back_button']")
	private MobileElement backButton ;
	
	@ElementDescription(value = "Migo logo in catalog page")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/migo_logo_iv']")
	private MobileElement migoLogo ;
	
	@ElementDescription(value = "User type in catalog page")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/user_type']")
	private MobileElement userType ;
	
	@ElementDescription(value = "Catalog Page is empty")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/ll_network_error']")
	private MobileElement emptyCatalogPage;


	public MobileElement getEmptyCatalogPage() {
	return emptyCatalogPage;
	}
	
	
	public MobileElement getUserType() {
		return userType;
	}

	public MobileElement getMigoLogo() {
		return migoLogo;
	}

	public MobileElement getBackButton() {
		return backButton;
	}

	public MobileElement getDownloadOtherMoive() {
	return downloadOtherMoive;
	}
	
	public MobileElement getProgressBar() {
		return ProgressBar;
	}

	public List<MobileElement> getEpisodeCancelDownloadButton() {
		return episodeCancelDownloadButton;
	}

	public MobileElement getCANCELButton() {
		return CANCELButton;
	}

	public MobileElement getCancelDownloadButton() {
		return cancelDownloadButton;
	}

	public MobileElement getNoSpaceButton() {
		return noSpaceButton;
	}

	public MobileElement getInfomartionDialouge() {
		return infomartionDialouge;
	}

	public MobileElement getConformationDialouge() {
		return conformationDialouge;
	}

	public MobileElement getSeriesTitle() {
		return seriesTitle;
	}

	public MobileElement getQuickCancelButton() {
		return quickCancelButton;
	}

	public MobileElement getQuickDownloadButton() {
		return quickDownloadButton;
	}

	public List<MobileElement> getChannelPage() {
		return channelPageTitle;
	}

	public MobileElement getViewAllButton() {
		return viewAllButton;
	}

	public MobileElement getViewButton() {
		return viewButton;
	}

	public MobileElement getSeriesBanner() {
		return seriesBanner;
	}

	public MobileElement getBlueSnackbar() {
		return blueSnackbar;
	}

	public MobileElement getCancelButton() {
		return cancelButton;
	}

	public MobileElement getTitleCard() {
		return titleCard;
	}

	public MobileElement getCatalogTitle() {
		return catalogPageTitle;
	}

	public MobileElement getCatalogButton() {
		return catalogTab;
	}

	public MobileElement getXYdownloadbutton() {
		return XYdownloadbutton;
	}

	public MobileElement getMovieBanner() {
		return movieBanner;
	}

	public MobileElement getDownloadButtonForEpisode1() {
		return downloadButton.get(0);
	}

	public MobileElement getDownloadButtonForEpisode2() {
		return downloadButton.get(1);
	}

	public MobileElement getDownloadButton() {
		return downloadButton.get(0);
	}


	public MobileElement getCloseXButton() {
		return closeXButton.get(0);
	}

	public MobileElement getViewAllButtonSeries() {
		return viewAllButtonSeries;
	}

	public MobileElement getQuickDownloadButtonForE70() {
		return quickDownloadButtonForE70;
	}

	public MobileElement getQuickCancelButtonForE70() {
		return quickCancelButtonForE70;
	}

	public MobileElement getSeriesEpisodeName() {
		return seriesEpisodeName;
	}

	public MobileElement getBackArrowButtonSeriesPage() {
		return backArrowButtonSeriesPage;
	}

	public MobileElement getMovieTitleName() {
		return movieTitleName;
	}
	
	public MobileElement getSeriesTitleName() {
		return seriesTitleName;
	}
	
	public MobileElement getEp1DownloadButton() {
		return ep1DownloadButton;
	}
	
	public MobileElement getEp2DownloadButton() {
		return ep2DownloadButton;
	}
	
	public MobileElement getCloseXButtonForAnotherMoive() {
		return closeXButton.get(1);
	}
	public MobileElement getAllowButton() {
		return allowButton;
	}

}
