package com.company.builder;

import java.util.List;

import com.company.model.Company;

public class CompanyBuilder {
	private String companyId;
	private String name;
	private String address;
	private String city;
	private String country;
	private String email;	
	private String phone;
	private List<String> owners;
	
	public CompanyBuilder withCompanyId(String companyId) {
		this.companyId = companyId;
		return this;
	}
	
	public CompanyBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public CompanyBuilder withAddress(String address) {
		this.address = address;
		return this;
	}
	
	public CompanyBuilder withCity(String city) {
		this.city = city;
		return this;
	}
	
	public CompanyBuilder withCountry(String country) {
		this.country = country;
		return this;
	}
	
	public CompanyBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	
	public CompanyBuilder withPhone(String phone) {
		this.phone = phone;
		return this;
	}
	
	public CompanyBuilder withOwners(List<String> owners) {
		this.owners = owners;
		return this;
	}
	
	public Company build() {
		Company company = new Company();
		company.setName(name);
		company.setAddress(address);
		company.setCity(city);
		company.setCountry(country);
		company.setCompanyId(companyId);
		company.setPhone(phone);
		company.setEmail(email);
		company.setOwners(owners);
		return company;
	}
}
