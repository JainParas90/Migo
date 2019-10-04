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
 * This class is used to store the objects of Download Page.
 *
 */

public class DownloadPageObjects {

	@ElementDescription(value = "Download Tab")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/imageView_tab_cw']")
	private MobileElement downloadsTab;

	@ElementDescription(value = "Cancel Button")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/iv_remove']")
	private MobileElement cancelButton;

	@ElementDescription(value = "Yes, Cancel Button")
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/btn_delete']")
	private MobileElement yesCancelButton;

	@ElementDescription(value = "Download Page Title")
	@AndroidFindBy(id="com.migo.androidplayer:id/ll_downloads_parent")
	private MobileElement downloadPageTitle;

	@ElementDescription(value = "Movie Section")
	@AndroidFindBy(id="com.migo.androidplayer:id/tv_content_type_tag")
	private MobileElement moiveSection;

	@ElementDescription(value = "Free Sace Counter")
	@AndroidFindBy(id="com.migo.androidplayer:id/storage_capacity")
	private MobileElement freeSpaceCounter;

	@ElementDescription(value = "Cacel (x) Button")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/iv_remove']")
	private MobileElement cancelXButton;

	@ElementDescription(value = "Movies Layout")
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.migo.androidplayer:id/movie_layout']")
	private MobileElement moivesLayout;

	@ElementDescription(value = "Confirmation Tab")
	@AndroidFindBy(id="com.migo.androidplayer:id/txt_dialog_title_message")
	private MobileElement confromationTab;

	@ElementDescription(value = "Empty Page Layout")
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/empty_download_layout']")
	private MobileElement emptyPageLayout;

	@ElementDescription(value = "")
	@AndroidFindBy(id="com.migo.androidplayer:id/tv_content_type_tag")
	private MobileElement tvSeriesTag;

	@ElementDescription(value = "Series Cancel Button")
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.migo.androidplayer:id/dismiss_content_view']")
	private MobileElement seriesCancelButton;

	@ElementDescription(value = "Series View Button")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/iv_series_view']")
	private MobileElement seriesViewButton;

	@ElementDescription(value = "Back (<-) Button")
	@AndroidFindBy(id="com.migo.androidplayer:id/toolbar_ic")
	private MobileElement backButton;

	@ElementDescription(value = "Close (x) Button")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/toolbar_ic']")
	private MobileElement closeXButton;

	@ElementDescription(value = "File Manager Button")
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/ll_file_manage']")
	private MobileElement fileManagerButton;

	@ElementDescription(value = "File Manager Page Title")
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txtToolBarTitle']")
	private MobileElement manageMigoFilesTitle;

	@ElementDescription(value = "Series Line Button")
	@AndroidFindBys(value = { @AndroidBy(xpath="//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/iv_series_view']")})
	private List<MobileElement> seriesLineButton;

	@ElementDescription(value = "Cancel (x) Button for Series episode")
	@AndroidFindBys(value = { @AndroidBy(xpath ="//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/queued_rl_remove']")})
	private List<MobileElement> cancelXSeriesEpisode;
	
	@ElementDescription(value = "Cancel (x) Button for Series episode1")
	@AndroidFindBy(xpath ="//android.widget.TextView[contains(@text, 'EP1')]//preceding::android.widget.ImageView['com.migo.androidplayer:id/series_iv_remove'][1]")
	private MobileElement cancelXSeriesEpisode1;
	
	@ElementDescription(value = "Cancel (x) Button for Series episode2")
	@AndroidFindBy(xpath ="//android.widget.TextView[contains(@text, 'EP2')]//preceding::android.widget.ImageView['com.migo.androidplayer:id/series_iv_remove'][1]")
	private MobileElement cancelXSeriesEpisode2;

	@ElementDescription(value = "Connect to Download Text")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_download_series_disconnected']")
	private MobileElement connectToDownload;

	@ElementDescription(value = "Series List In Queue")
	@AndroidFindBys(value = { @AndroidBy(xpath = "//android.widget.FrameLayout[@resource-id='com.migo.androidplayer:id/fl_queued']")})
	private List<MobileElement> seriesList;

	@AndroidFindBys(value = { @AndroidBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/series_tv_title']")})
	private List<MobileElement> tvShows;
	
	@ElementDescription(value = "Episode list Inside Series")
	@AndroidFindBys(value = { @AndroidBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/queued_tv_title']")})
	private List<MobileElement> episodeListInsideSeries;
	
	@ElementDescription(value = "Episode list Inside Series")
	@AndroidFindBys(value = { @AndroidBy(xpath = "//android.widget.TextView[@text='MOVIES']//following::android.widget.TextView[@resource-id='com.migo.androidplayer:id/movie_tv_title']")})
	private List<MobileElement> movieTitle;
	
	@ElementDescription(value = "Episode list Inside Tv Shows")
	@AndroidFindBys(value = { @AndroidBy(xpath = "//android.widget.TextView[@text='TV SHOWS']//following::android.widget.TextView[@resource-id='com.migo.androidplayer:id/series_tv_title']")})
	private List<MobileElement> seriesTitle;

	@ElementDescription(value = "Series Page")
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.migo.androidplayer:id/htab_maincontent']")
	private MobileElement seriesPage;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_selectMore_episode']")
	private MobileElement selectMore;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_queued_show_state']")
	private MobileElement connectLabel;
	
	@ElementDescription(value = "starts downloading")
	@AndroidFindBy(xpath="//android.widget.ProgressBar[@resource-id='com.migo.androidplayer:id/progress_connected']")
	private MobileElement startsDownloading;
	
	@ElementDescription(value = "Connectivity indicator")
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_connection_ssid']")
	private MobileElement connectivityIndicator;
	
	@ElementDescription(value = "SERIES line items")
	@AndroidFindBys(value = { @AndroidBy(xpath="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_download_series_state']")})
	private List<MobileElement>  downdloadingXepisode;
	
	@ElementDescription(value = "E38 SERIES EPISODE displays \"[x]%\"")
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_queued_show_state']")
	private MobileElement downloadingPercentage;
	
	@ElementDescription(value = "Download progress notification bar")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_header']")
	private MobileElement downloadProgressBar;
	
	@ElementDescription(value = "EPISODE to finish downloading")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/iv_play']")
	private MobileElement finishDownloadButton;
	
	@ElementDescription(value = "E38 SERIES EPISODE continues downloading")
	@AndroidFindBy(xpath = "//android.widget.ProgressBar[@resource-id='com.migo.androidplayer:id/progress_notif']")
	private MobileElement notificationDownloadProgressBar;
	
	@AndroidFindBy(xpath = "//android.widget.ProgressBar[@resource-id='com.migo.androidplayer:id/progress_connected']")
	private MobileElement titleStartDownloading ;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/tv_download_movie_state']")
	private MobileElement downloadingNextlable;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/btn_ok']")
	private MobileElement okButton;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.migo.androidplayer:id/ll_episode_queued']")
	private MobileElement downloadingBar;
	
	@ElementDescription(value = "Play button on the download page.")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/iv_play']")
	private MobileElement playButton;
	
	@ElementDescription(value = "Download Tab location")
	@AndroidFindBy(xpath="//com.migo.androidplayer:id/txtToolBarTitle[@resource-id='com.migo.androidplayer:id/txtToolBarTitle']")
	private MobileElement downloadTabTitle;
	
	@ElementDescription(value = "MTC Rating Page.")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/iv_mtc_rating']")
	private MobileElement mtcRating;
	
	@ElementDescription(value = "Movie Time.")
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/vcv_txt_total']")
	private MobileElement moiveTime;

	@ElementDescription(value = "Check Video Screen.")
	@AndroidFindBy(xpath="//android.widget.VideoView[@resource-id='com.migo.androidplayer:id/video']")
	private MobileElement videoScreen;	
	
	@ElementDescription(value = "Play Icon from the media player")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/play_pause_image_view']")
	private MobileElement playIcon;
	
	@ElementDescription(value = "Trash Can(Delete) button on left side of the title")
	@AndroidFindBy(id="com.migo.androidplayer:id/iv_remove")
	private MobileElement trashCan;
	
	@ElementDescription(value = "Title progress bar under the title image in download page")
	@AndroidFindBy(xpath="//android.widget.ProgressBar[@resource-id='com.migo.androidplayer:id/progress_watched']")
	private MobileElement titleProgressBar;
	
	@ElementDescription(value = "Back button of Media player")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.migo.androidplayer:id/imgBack']")
	private MobileElement videoBackArrowButton;
	
	@ElementDescription(value = "Auto Play Page location.")
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.migo.androidplayer:id/auto_play_list']")
	private MobileElement autoPlay;
	
	@ElementDescription(value = "Notification Drower location.")
	@AndroidFindBy(id="com.android.systemui:id/quick_settings_container")
	private MobileElement notificationDrawer;
	
	@ElementDescription(value = "Seekbar location of the Player")
	@AndroidFindBy(xpath="//android.widget.SeekBar[@resource-id='com.migo.androidplayer:id/vcv_seekbar']")
	private MobileElement seekBarButton;
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.migo.androidplayer:id/auto_play_list']")
	private MobileElement autoPlayPage ;
	

	@ElementDescription(value = "Deny button locator")
	@AndroidFindBy(id="com.android.packageinstaller:id/permission_deny_button")
	private MobileElement deny;

	@ElementDescription(value = "Close button after Deny")
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='android:id/button2']")
	private MobileElement closePopup;	

	@ElementDescription(value ="Allow button")
	@AndroidFindBy(id ="com.android.packageinstaller:id/permission_allow_button")
	private MobileElement allow;

	@ElementDescription(value ="Migo Logo")
	@AndroidFindBy(id="com.migo.androidplayer:id/migo_logo_iv")
	private MobileElement migoLogo;

	@ElementDescription(value ="Service Page")
	@AndroidFindBy(xpath ="//android.widget.TextView[@text='DRM Status']")
	private MobileElement serviceRunning;

	@ElementDescription(value ="Splash Loading")
	@AndroidFindBy(xpath ="//android.widget.ProgressBar[@resource-id='com.migo.androidplayer:id/ProgressBar']")
	private MobileElement splashLoading;

	@ElementDescription(value ="Permission Guide Page")
	@AndroidFindBy(xpath ="//android.widget.FrameLayout[@resource-id='android:id/content']")
	private MobileElement permissionGuide;

	@ElementDescription(value ="Permission Prompt")
	@AndroidFindBy(id ="com.android.packageinstaller:id/perm_desc_root")
	private MobileElement permissionPrompt;

	@ElementDescription(value ="Setting prompt")
	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id='android:id/message']")
	private MobileElement settingPrompt;

	@ElementDescription(value ="Introduction Video")
	@AndroidFindBy(id ="com.migo.androidplayer:id/text_skip")
	private MobileElement tutorialVideo;

	public MobileElement getTutorialVideo() {
	return tutorialVideo;
	}

	public MobileElement getSettingPrompt() {
	return settingPrompt;
	}

	public MobileElement getPermissionPrompt() {
	return permissionPrompt;
	}

	public MobileElement getPermissionGuide() {
	return permissionGuide;
	}

	public MobileElement getSplashLoading() {
	return splashLoading;
	}

	public MobileElement getServiceRunning() {
	return serviceRunning;
	}

	public MobileElement getMigoLogo() {
	return migoLogo;
	}

	public MobileElement getAllow() {
	return allow;
	}	

	public MobileElement getClosePopup() {
	return closePopup;
	}

	public MobileElement getDeny() {
	return deny;
	}
	
	public MobileElement getAutoPlayPage() {
		return autoPlayPage;
	}
	
	public MobileElement getSeekBarButton() {
		return seekBarButton;
	}

	public MobileElement getAutoPlay() {
		return autoPlay;
	}

	public MobileElement getVideoBackArrowButton() {
		return videoBackArrowButton;
	}

	public MobileElement getTitleProgressBar() {
		return titleProgressBar;
	}

	public MobileElement getTrashCan() {
		return trashCan;
	}

	public MobileElement getPlayIcon() {
		return playIcon;
	}

	public MobileElement getVideoScreen() {
		return videoScreen;
	}

	public MobileElement getMoiveTime() {
		return moiveTime;
	}

	public MobileElement getMtcRating() {
		return mtcRating;
	}	

	public MobileElement getDownloadTabTitle() {
		return downloadTabTitle;
	}

	public MobileElement getPlayButton() {
		return playButton;
	}
	
	
	public MobileElement getDownloadingBar() {
		return downloadingBar;
	}

	public MobileElement getOkButton() {
		return okButton;
	}
	
	
	public MobileElement getDownloadingNextlable() {
		return downloadingNextlable;
	}

	public MobileElement getTitleStartDownloading() {
		return titleStartDownloading;
	}
	
	public MobileElement getNotificationDownloadProgressBar() {
		return notificationDownloadProgressBar;
	}

	public MobileElement getFinishDownloadButton() {
		return finishDownloadButton;
	}

	public MobileElement getDownloadProgressBar() {
		return downloadProgressBar;
	}

	public MobileElement getDownloadingPercentage() {
		return downloadingPercentage;
	}

	public List<MobileElement> getDowndloadingXepisode() {
		return downdloadingXepisode;
	}

	public MobileElement getConnectivityIndicator() {
		return connectivityIndicator;
	}

	public MobileElement getStartsDownloading() {
		return startsDownloading;
	}

	public MobileElement getSeriesPage() {
		return seriesPage;
	}

	public List<MobileElement> getEpisodeListInsideSeries() {
		return episodeListInsideSeries;
	}
	
	public List<MobileElement> getTvShows() {
		return tvShows;
	}

	public List<MobileElement> getSeriesList() {
		return seriesList;
	}

	public MobileElement getConnectToDownload() {
		return connectToDownload;
	}

	public MobileElement getMoivesLayout() {
		return moivesLayout;
	}

	public MobileElement getManageMigoFilesTitle() {
		return manageMigoFilesTitle;
	}

	public List<MobileElement> getSeriesLineButton() {
		return seriesLineButton;
	}

	public List<MobileElement> getCancelSeriesEpisode() {
		return cancelXSeriesEpisode;
	}

	public MobileElement getFileManagerButton() {
		return fileManagerButton;
	}

	public MobileElement getBackButton() {
		return backButton;
	}

	public MobileElement getSeriesViewButton() {
		return seriesViewButton;
	}

	public MobileElement getSeriesCancelButton() {
		return seriesCancelButton;
	}

	public MobileElement getTvSeriesTag() {
		return tvSeriesTag;
	}

	public MobileElement getEmptyPageLayout() {
		return emptyPageLayout;
	}

	public MobileElement getConfromationTab() {
		return confromationTab;
	}
	
	public MobileElement getConnectLabel() {
		return connectLabel;
	}

	public MobileElement getCancelXButton() {
		return cancelXButton;
	}

	public MobileElement getMoiveSection() {
		return moiveSection;
	}

	public MobileElement getCloseXButton() {
		return closeXButton;
	}

	public MobileElement getDownloadPageTitle() {
		return downloadPageTitle;
	}
	public MobileElement getMemorySection() {
		return freeSpaceCounter;
	}

	public MobileElement getDownloadsTab() {
		return downloadsTab;
	}

	public MobileElement getCancelButton() {
		return cancelButton;
	}

	public MobileElement getYesCancelButton() {
		return yesCancelButton;
	}
	
	public MobileElement getSelectMore() {
		return selectMore;
	}
	
	public List<MobileElement> getMovieTitle() {
		return movieTitle;
	}
	
	public List<MobileElement> getSeriesTitle() {
		return seriesTitle;
	}
	
	public MobileElement getCancelXSeriesEpisode1() {
		return cancelXSeriesEpisode1;
	}
	
	public MobileElement getCancelXSeriesEpisode2() {
		return cancelXSeriesEpisode2;
	}

	public MobileElement getNotificationDrawer() {
		return notificationDrawer;
	}

}
