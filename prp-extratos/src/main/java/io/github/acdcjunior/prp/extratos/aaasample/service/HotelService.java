package io.github.acdcjunior.prp.extratos.aaasample.service;

import io.github.acdcjunior.prp.extratos.aaasample.domain.City;
import io.github.acdcjunior.prp.extratos.aaasample.domain.Hotel;
import io.github.acdcjunior.prp.extratos.aaasample.domain.Review;
import io.github.acdcjunior.prp.extratos.aaasample.domain.ReviewDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {

	Hotel getHotel(City city, String name);

	Page<Review> getReviews(Hotel hotel, Pageable pageable);

	Review getReview(Hotel hotel, int index);

	Review addReview(Hotel hotel, ReviewDetails details);

	ReviewsSummary getReviewSummary(Hotel hotel);

}
