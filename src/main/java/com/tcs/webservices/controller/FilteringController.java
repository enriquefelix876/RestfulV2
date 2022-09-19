package com.tcs.webservices.controller;

import java.time.LocalDate;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.tcs.webservices.model.User;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		
		User user = new User(2L, "Enrique Felix", LocalDate.now());
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
		SimpleBeanPropertyFilter  filter = 
				SimpleBeanPropertyFilter.filterOutAllExcept("id","name");
		
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("UserFilter", filter);
		
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
}
