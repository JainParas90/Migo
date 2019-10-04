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
 * This class is used to store the objects of Load Page.
 *
 */

public class LoadPageObjects {

@ElementDescription(value = "Load Tab")
@AndroidFindBy(id = "com.migo.androidplayer:id/relativeLayout_tab_fill")
private MobileElement loadstab;

@ElementDescription(value = "Different Loads in load tab")
@AndroidFindBy(xpath ="//android.widget.RelativeLayout[@resource-id='com.migo.androidplayer:id/price_item']")
private MobileElement differentLoad;

public MobileElement getDifferentLoad() {
return differentLoad;
}

public MobileElement getLoadstab() {
return loadstab;
}

}

