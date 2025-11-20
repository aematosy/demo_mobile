package com.store.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import com.store.ui.YouTubeHomePage;
import com.store.ui.YouTubeSearchPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class SearchForVideo implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchForVideo.class);
    private final String text;

    public SearchForVideo(String text) {
        this.text = text;
    }

    public static SearchForVideo called(String text) {
        return instrumented(SearchForVideo.class, text);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        AndroidDriver driver = (AndroidDriver) BrowseTheWeb.as(actor).getDriver();

        LOGGER.info("Esperando boton de busqueda");
        actor.attemptsTo(
                WaitUntil.the(YouTubeHomePage.SEARCH_BOX, isVisible()).forNoMoreThan(10).seconds()
        );

        RemoteWebElement searchBox = (RemoteWebElement) YouTubeHomePage.SEARCH_BOX
                .resolveFor(actor).getWrappedElement();

        LOGGER.info("Haciendo click en busqueda");
        ((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
                Map.of("elementId", searchBox.getId()));

        LOGGER.info("Esperando campo de entrada");
        actor.attemptsTo(
                WaitUntil.the(YouTubeSearchPage.SEARCH_INPUT, isVisible()).forNoMoreThan(10).seconds()
        );

        LOGGER.info("Escribiendo texto: {}", text);
        YouTubeSearchPage.SEARCH_INPUT.resolveFor(actor).sendKeys(text);

        LOGGER.info("Presionando ENTER");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        LOGGER.info("Esperando resultados de busqueda");
        actor.attemptsTo(
                WaitUntil.the(YouTubeSearchPage.SEARCH_INPUT, isNotVisible()).forNoMoreThan(10).seconds()
        );

        LOGGER.info("Busqueda completada");
    }
}