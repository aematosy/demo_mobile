package com.store.ui;

import net.serenitybdd.screenplay.targets.Target;
import io.appium.java_client.AppiumBy;

public class YouTubeSearchPage {

    public static final Target SEARCH_INPUT = Target.the("input search box")
            .located(AppiumBy.id("com.google.android.youtube:id/search_edit_text"));
}