package io.github.acdcjunior.prp.extratos.aaasample.service;

import io.github.acdcjunior.prp.extratos.aaasample.domain.City;
import io.github.acdcjunior.prp.extratos.aaasample.domain.HotelSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component("cityService")
@Transactional
class CityServiceImpl implements CityService {

	private final CityRepository cityRepository;

	private final HotelRepository hotelRepository;

	public CityServiceImpl(CityRepository cityRepository,
			HotelRepository hotelRepository) {
		this.cityRepository = cityRepository;
		this.hotelRepository = hotelRepository;
	}

	@Override
	public Page<City> findCities(CitySearchCriteria criteria, Pageable pageable) {

		Assert.notNull(criteria, "Criteria must not be null");
		String name = criteria.getName();

		if (!StringUtils.hasLength(name)) {
			return this.cityRepository.findAll(null);
		}

		String country = "";
		int splitPos = name.lastIndexOf(",");

		if (splitPos >= 0) {
			country = name.substring(splitPos + 1);
			name = name.substring(0, splitPos);
		}

		return this.cityRepository
				.findByNameContainingAndCountryContainingAllIgnoringCase(name.trim(),
						country.trim(), pageable);
	}

	@Override
	public City getCity(String name, String country) {
		Assert.notNull(name, "Name must not be null");
		Assert.notNull(country, "Country must not be null");
		return this.cityRepository.findByNameAndCountryAllIgnoringCase(name, country);
	}

	@Override
	public Page<HotelSummary> getHotels(City city, Pageable pageable) {
		Assert.notNull(city, "City must not be null");
		return this.hotelRepository.findByCity(city, pageable);
	}
}
