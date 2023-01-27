package com.air.skyroad.service;

import com.amadeus.resources.Traveler;
import com.amadeus.resources.Traveler.Contact;
import com.amadeus.resources.Traveler.Document;
import com.amadeus.resources.Traveler.Name;
import com.amadeus.resources.Traveler.Phone;
import com.google.gson.JsonObject;

public class UserInfo {
	public static Traveler traveler(JsonObject travelerInfo)
	{
		String fname = travelerInfo.get("fname").toString();
		String lname = travelerInfo.get("lname").toString();
		String dob = travelerInfo.get("dob").toString();
		
		Traveler traveler = new Traveler();
        Phone phone = traveler.new Phone();
        phone.setCountryCallingCode("91");
        phone.setNumber("8953049289");
        phone.setDeviceType("MOBILE");
        Contact contact = traveler.new Contact();
        Phone[] phones = {phone};
        contact.setPhones(phones);
        traveler.setContact(contact);
        Name name = traveler.new Name(fname, lname);
        traveler.setName(name);
        traveler.setDateOfBirth(dob);
        traveler.setId("1");
        Document document = traveler.new Document();
        document.setDocumentType("PASSPORT");
        document.setNumber("00000000");
        document.setExpiryDate("2027-04-14");
        document.setNationality("IND");
        document.setHolder(true);
        document.setIssuanceCountry("IND");
        Document[] documents = {document};
        traveler.setDocuments(documents);
        return traveler;
	}
}
