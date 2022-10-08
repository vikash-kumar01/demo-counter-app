package com.example.springboot.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {

	private transient Integer val = 0;
	
	@GetMapping(value = "/")
	public String getValue() {

//		LocalDateTime date = LocalDateTime.now();
//
//		Integer year = date.getYear();
//		Integer month = date.getMonthValue();
//		Integer day = date.getDayOfMonth();
//		Integer hour = date.getHour();
//		Integer min = date.getMinute();
//		Integer sec = date.getSecond();
		val++;
//		Integer count = year + month + day + hour + min + sec + 1;
		String result = "Hello-World-" + val;
		return result;
	}
}
