package com.air.skyroad.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.air.skyroad.service.Book;
import com.air.skyroad.service.FlightServices;
import com.air.skyroad.service.UserInfo;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Traveler;
import com.google.gson.JsonObject;



@RestController
@RequestMapping("/air")
public class API {
	
	@RequestMapping("/health")
	public String healthCheck()
	{
		return "OK";
	}
	
	@GetMapping(value = "/locations", produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public com.amadeus.resources.Location[] locations(@RequestParam(required = true) String keyword) throws ResponseException
	{
		return FlightServices.INSTANCE.location(keyword);
	}
	
	@GetMapping(value = "/flights", produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public FlightOfferSearch[] flights(@RequestParam(required = true) String origin,
										@RequestParam(required = true) String destination,
										@RequestParam(required = true) String departDate,
										@RequestParam(required = true) String adults,
										@RequestParam(required = false) String returnDate) 
																	throws ResponseException
	{
		return FlightServices.INSTANCE.flights(origin, destination, departDate, adults, returnDate);
	}
	
	@PostMapping(value = "/confirm", produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public FlightPrice confirm(@RequestBody(required = true) FlightOfferSearch search) throws ResponseException
	{
		return FlightServices.INSTANCE.confirm(search);
	}
	
	@PostMapping(value = "/traveler", produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public Traveler traveler(@RequestBody(required = true) JsonObject user) throws ResponseException
	{
		return UserInfo.traveler(user.get("data").getAsJsonObject());
	}
	
	@PostMapping(value = "/order", produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public FlightOrder order(@RequestBody(required = true) JsonObject order) throws ResponseException
	{
		return Book.INSTANCE.order(order);
	}
}
