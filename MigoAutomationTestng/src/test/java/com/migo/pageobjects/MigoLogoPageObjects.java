package com.migo.pageobjects;
import com.migo.annotation.values.ElementDescription;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class MigoLogoPageObjects {
	

	@ElementDescription(value ="Migo Logo")
	@AndroidFindBy(id="com.migo.androidplayer:id/migo_logo_iv")
	public static MobileElement migoLogo;
	
	@ElementDescription(value ="Service Page")
	@AndroidFindBy(xpath ="//android.widget.TextView[@text='DRM Status']")
	private MobileElement serviceRunning;
	
	@ElementDescription(value ="Active Storage")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txtStorageStatus'] ")
	private MobileElement activeStorage;
	
	@ElementDescription(value ="Internal Storage Initialize")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resouce-id='com.migo.androidplayer:id/txtInternalStorage']")
	private MobileElement internalStorage;

	@ElementDescription(value ="External Storage Initialize")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txtExternalStorage']")
	private MobileElement externalStorage;
	
	@ElementDescription(value ="Storage Type")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txtStorageType']")
	private MobileElement storageType;

	@ElementDescription(value ="Read only")
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txtReadonly']")
	private MobileElement readOnly;
		
	@ElementDescription(value = "Drm status ")
	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txtDrmStatusValue']")
	private MobileElement drmStatus;
	
	@ElementDescription(value = "Is DRM ready")
	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id='com.migo.androidplayer:id/txtDrmStatusIsReady']")
	private MobileElement drmReady;

	public MobileElement getDrmReady() {
		return drmReady;
	}

	public MobileElement getDrmStatus() {
		return drmStatus;
	}

	public MobileElement getReadOnly() {
		return readOnly;
	}
	
	public MobileElement getStorageType() {
		return storageType;
	}
	
	public MobileElement getInternalStorage() {
		return internalStorage;
	}

	public MobileElement getExternalStorage() {
		return externalStorage;
	}

	public MobileElement getActiveStorage() {
		return activeStorage;
	}

	public MobileElement getServiceRunning() {
		return serviceRunning;
	}

	public MobileElement getMigoLogo() {
		return migoLogo;
	}


}
