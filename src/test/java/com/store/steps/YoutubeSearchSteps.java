package com.store.steps;

import com.store.hooks.AppiumHooks;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.greaterThan;

import com.store.questions.SearchResultsDisplayed;
import com.store.tasks.SearchForVideo;

public class YoutubeSearchSteps {

    private Actor actor;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("the user has the YouTube application opened")
    public void theUserHasTheYouTubeApplicationOpened() {
        AppiumDriver driver = AppiumHooks.getDriver();

        if (driver == null) {
            throw new IllegalStateException("Driver no inicializado");
        }

        System.out.println("✅ YouTube Session: " + driver.getSessionId());

        actor = OnStage.theActorCalled("Mobile user");
        actor.can(BrowseTheWeb.with(driver));

        System.out.println("✅ YouTube app opened on " + AppiumHooks.getCurrentPlatform());
    }

    @When("the user searches for the video {string}")
    public void theUserSearchesForTheVideo(String videoTitle) {
        theActorInTheSpotlight().attemptsTo(
                SearchForVideo.called(videoTitle)
        );
    }

    @Then("the user should see more than one result in the results list")
    public void theUserShouldSeeMoreThanOneResultInTheResultsList() {
        theActorInTheSpotlight().should(
                seeThat("number of results",
                        SearchResultsDisplayed.count(),
                        greaterThan(1)
                )
        );
    }
}