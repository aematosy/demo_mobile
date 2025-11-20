package com.store.ui;

import net.serenitybdd.screenplay.targets.Target;

public class YouTubeResultsPage {

    public static final Target SHOWING_RESULTS_FOR = Target.the("search results text")
            .locatedBy("//android.widget.TextView[@resource-id='com.google.android.youtube:id/showing_results_for']");

    // Localizador correcto: ViewGroup con content-desc (títulos de videos)
    public static final Target VIDEO_TITLES = Target.the("video search results")
            .locatedBy("//android.view.ViewGroup[@content-desc and @content-desc!='']");
}