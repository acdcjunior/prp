package io.github.acdcjunior.prp.extratos.aaasample.service;

import io.github.acdcjunior.prp.extratos.aaasample.domain.Hotel;
import io.github.acdcjunior.prp.extratos.aaasample.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

interface ReviewRepository extends Repository<Review, Long> {

	Page<Review> findByHotel(Hotel hotel, Pageable pageable);

	Review findByHotelAndIndex(Hotel hotel, int index);

	Review save(Review review);

}
