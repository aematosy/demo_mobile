package com.store.ui;

import net.serenitybdd.screenplay.targets.Target;
import io.appium.java_client.AppiumBy;

public class YouTubeHomePage {

    // Usando Accessibility ID (más confiable)
    public static final Target SEARCH_BOX = Target.the("search box")
            .located(AppiumBy.accessibilityId("Search YouTube"));
}