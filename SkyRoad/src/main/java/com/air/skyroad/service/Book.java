package com.air.skyroad.service;

import org.springframework.beans.factory.annotation.Value;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOrder;
import com.google.gson.JsonObject;

public enum Book {
	INSTANCE;
	private Amadeus amadeus;
	
	@Value("${key}")
	private String key = "J2itoOuZCiKi7AdXb3p4VVmOtG8hRg6A";
	
	@Value("${secret}")
	private String secret = "BDzBLLaRydFbu8DQ";

	private Book() {
		this.amadeus = Amadeus
						.builder(key, secret)
						.build();
	}
	
	public FlightOrder order(JsonObject order) throws ResponseException
	{
		return amadeus.booking.flightOrders.post(order);
	}
}
