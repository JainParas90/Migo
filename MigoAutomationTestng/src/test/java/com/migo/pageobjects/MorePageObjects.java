package com.migo.pageobjects;

import com.migo.annotation.values.ElementDescription;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * 
 * @author Shibu Prasad Panda
 * 
 * This class is used to store the objects of More Page.
 *
 */

public class MorePageObjects {



	@ElementDescription(value ="More Tab")
	@AndroidFindBy(id="com.migo.androidplayer:id/relativeLayout_tab_more")
	private MobileElement moreTab;

	@ElementDescription(value ="Different asset in more tab")
	@AndroidFindBy(id="com.migo.androidplayer:id/imageView")
	private MobileElement differentAsset;

	@ElementDescription(value ="More Title")
	@AndroidFindBy(id="com.migo.androidplayer:id/txtToolBarTitle")
	private MobileElement more_title;

	@ElementDescription(value ="First card")
	@AndroidFindBy(xpath="(//*[@class='android.widget.ImageView'])[1]")
	private MobileElement First_card;

	@ElementDescription(value ="Second card")
	@AndroidFindBy(xpath="(//*[@class='android.widget.ImageView'])[3]")
	private MobileElement Second_card;
	

	
	@ElementDescription(value ="Second card")
	@AndroidFindBy(xpath="(//*[@class='android.widget.ImageView'])[6]")
	private MobileElement Third_card;

	@ElementDescription(value ="First card title")
	@AndroidFindBy(xpath="(//*[@class='android.widget.TextView'])[1]")
	private MobileElement First_card_title;
	
	@ElementDescription(value ="First card subtitle")
	@AndroidFindBy(xpath="(//*[@class='android.widget.TextView'])[2]")
	private MobileElement First_card_subtitle;
	
	@ElementDescription(value ="Second card title")
	@AndroidFindBy(xpath="(//*[@class='android.widget.TextView'])[3]")
	private MobileElement Second_card_title;
	
	@ElementDescription(value ="Second card subtitle")
	@AndroidFindBy(xpath="(//*[@class='android.widget.TextView'])[4]")
	private MobileElement Second_card_subtitle;

	@ElementDescription(value ="First card backicon" )
	@AndroidFindBy(id="com.migo.androidplayer:id/toolbar_ic")
	private MobileElement First_card_back;

	@ElementDescription(value ="Second card video skip" )
	@AndroidFindBy(id="com.migo.androidplayer:id/video_tutorial")
	private MobileElement videoSkip;

	@ElementDescription(value ="Second card video click" )
	@AndroidFindBy(id="com.migo.androidplayer:id/video_tutorial")
	private MobileElement Svideoclick;

	@ElementDescription(value ="Second card video skip" )
	@AndroidFindBy(id="com.migo.androidplayer:id/text_skip")
	private MobileElement Svideoskip;
	
	@ElementDescription(value ="Terms and conditions" )
	@AndroidFindBy(xpath="(//*[@class='android.widget.TextView'])[5]")
	private MobileElement Termscondition;
	//com.migo.androidplayer:id/footerLl
	
	@ElementDescription(value ="GloBE||TM" )
	@AndroidFindBy(xpath="(//*[@class='android.widget.TextView'])[5]")
	private MobileElement Globe_TM;
	
	@ElementDescription(value ="SMART||SUN||TNT" )
	@AndroidFindBy(xpath="(//*[@class='android.widget.TextView'])[5]")
	private MobileElement SMART_SUN_TNT;
	
	@ElementDescription(value ="Terms and conditions page title" )
	@AndroidFindBy(xpath="(//*[@class='android.widget.TextView'])[1]")
	private MobileElement Termsandconditions_title;
	
	@ElementDescription(value ="Third card title ,FREE Migo Hotline" )
	@AndroidFindBy(id="com.migo.androidplayer:id/txtToolBarTitle")
	private MobileElement FREEMigoHotlinetitle;
	
	
	public MobileElement getDifferentAsset() {
		return differentAsset;
	}

	public MobileElement getMoreTab() {
		return moreTab;
	}

	public MobileElement getmoretitle() {
		return more_title;
	}

	public MobileElement getFirstcard() {
		return First_card;
	}
	public MobileElement getSecondcard() {
		return Second_card;
	}
	public MobileElement getThirdcard() {
		return Third_card;
	}
	
	
	public MobileElement getFirstcardTitle() {
		return First_card_title;
	}
	public MobileElement getFirstcardsubTitle() {
		return First_card_subtitle;
	}
	
	public MobileElement getSecondcardTitle() {
		return Second_card_title;
	}
	public MobileElement getSecondcardsubTitle() {
		return Second_card_subtitle;
	}
	public MobileElement Backicon_Fcard() {
		return First_card_back;
	}
	
	public MobileElement videoclick_Scard() {
		return Svideoclick;
	}

	public MobileElement videoskip_Scard() {
		return Svideoskip;
	}
	public MobileElement More_Termscondition() {
		return Termscondition;
	}
	public MobileElement Thirdcard_Globe_TM() {
		return Globe_TM;
	}
	public MobileElement Thirdcard_SMART_SUN_TNT() {
		return SMART_SUN_TNT;
	}
	public MobileElement getTermsandconditionstitle() {
		return Termsandconditions_title;
	}
	public MobileElement getFREEMigoHotline() {
		return FREEMigoHotlinetitle;
	}
	
}


