package com.store.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import com.store.ui.YouTubeResultsPage;

public class SearchResultsDisplayed implements Question<Integer> {

    public static SearchResultsDisplayed count() {
        return new SearchResultsDisplayed();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        // Cuenta cuántos títulos de video aparecen en los resultados
        return YouTubeResultsPage.VIDEO_TITLES
                .resolveAllFor(actor)
                .size();
    }
}
