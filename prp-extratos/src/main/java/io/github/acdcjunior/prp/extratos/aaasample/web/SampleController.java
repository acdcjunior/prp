package io.github.acdcjunior.prp.extratos.aaasample.web;

import io.github.acdcjunior.prp.extratos.aaasample.domain.Hotel;
import io.github.acdcjunior.prp.extratos.aaasample.service.CityService;
import io.github.acdcjunior.prp.extratos.aaasample.service.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SampleController {

	@Autowired
	private CityService cityService;

	@Autowired
	private HotelRepository hotelRepository;

	@GetMapping("/")
	@ResponseBody
	@Transactional(readOnly = true)
	public String helloWorld() {
		return this.cityService.getCity("Bath", "UK").getName();
	}

	@GetMapping("/hotels")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Hotel> hotels() {
		return this.hotelRepository.findAll();
	}

}
