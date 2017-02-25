package io.github.acdcjunior.prp.extratos.aaasample.service;

import io.github.acdcjunior.prp.extratos.aaasample.domain.Rating;

public interface ReviewsSummary {

	long getNumberOfReviewsWithRating(Rating rating);

}
