package com.company.builder;

import com.company.dto.UpdateCompanyRequest;

public class UpdateCompanyRequestBuilder {
	private String name;
	private String address;
	private String country;
	private String city;
	private String email;
	private String phone;
	
	public UpdateCompanyRequestBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public UpdateCompanyRequestBuilder withAddress(String address) {
		this.address = address;
		return this;
	}
	
	public UpdateCompanyRequestBuilder withCountry(String country) {
		this.country = country;
		return this;
	}
	
	public UpdateCompanyRequestBuilder withCity(String city) {
		this.city = city;
		return this;
	}
	
	public UpdateCompanyRequestBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	
	public UpdateCompanyRequestBuilder withPhone(String phone) {
		this.phone = phone;
		return this;
	}
	
	public UpdateCompanyRequest build() {
		UpdateCompanyRequest ucr = new UpdateCompanyRequest();
		ucr.setName(name);
		ucr.setAddress(address);
		ucr.setCity(city);
		ucr.setCountry(country);
		ucr.setEmail(email);
		ucr.setPhone(phone);
		return ucr;
	}
}
