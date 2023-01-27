package com.air.skyroad.service;

import org.springframework.beans.factory.annotation.Value;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;

public enum FlightServices {
	INSTANCE;
	private Amadeus amadeus;
	
	@Value("${key}")
	private String key = "J2itoOuZCiKi7AdXb3p4VVmOtG8hRg6A";
	
	@Value("${secret}")
	private String secret = "BDzBLLaRydFbu8DQ";

	private FlightServices() {
		this.amadeus = Amadeus
						.builder(key, secret)
						.build();
	}
	
	public com.amadeus.resources.Location[] location(String keyword) throws ResponseException
	{
		return amadeus.referenceData.locations.get(
				Params
				.with("keyword", keyword)
				.and("subType", Locations.AIRPORT));
	}	
	
	public FlightOfferSearch[] flights(String origin, String destination, String departDate, String adults, String returnDate) throws ResponseException
	{
		return amadeus.shopping.flightOffersSearch.get(
				Params.with("originLocationCode", origin)
							.and("destinationLocationCode", destination)
							.and("departureDate", departDate)
							.and("returnDate", returnDate)
							.and("adults", adults)
							.and("max", 3));
	}
	
	public FlightPrice confirm(FlightOfferSearch offer) throws ResponseException
	{
		return amadeus.shopping.flightOffersSearch.pricing.post(offer);
	}
}
