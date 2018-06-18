package com.company.validator;

import static com.company.util.Constants.ADDRESS;
import static com.company.util.Constants.CITY;
import static com.company.util.Constants.COMPANYID;
import static com.company.util.Constants.COUNTRY;
import static com.company.util.Constants.NAME;

import java.util.List;

import org.springframework.stereotype.Component;

import com.company.dto.UpdateCompanyRequest;
import com.company.exception.CompanyValidationException;
import com.company.model.Company;

@Component
public class CompanyValidatorImpl implements CompanyValidator {

	@Override
	public void validate(Company company) throws CompanyValidationException {
		verify(company.getCompanyId(), COMPANYID);
		verify(company.getName(), NAME);
		verify(company.getAddress(), ADDRESS);
		verify(company.getCity(), CITY);
		verify(company.getCountry(), COUNTRY);
		verify(company.getOwners(), "owners");
		
	}
	
	private void verify(String attribute, String fieldName) throws CompanyValidationException {
		if(attribute == null || attribute.trim().isEmpty()) {
			throw new CompanyValidationException("Company : " + fieldName + " is required");
		}
	}
	
	private void verify(List<String> list, String fieldName) throws CompanyValidationException {
		if(list == null || list.isEmpty()) {
			throw new CompanyValidationException("Company : " + fieldName + " is required");
		}
	}

	@Override
	public void validate(UpdateCompanyRequest updateCompanyRequest) throws CompanyValidationException {
		if(updateCompanyRequest.getName() != null && updateCompanyRequest.getName().trim().isEmpty()) {
			throw new CompanyValidationException("Company : name is required");
		}
		
		if(updateCompanyRequest.getAddress() != null && updateCompanyRequest.getAddress().trim().isEmpty()) {
			throw new CompanyValidationException("Company : address is required");
		}
		
		if(updateCompanyRequest.getCity() != null && updateCompanyRequest.getCity().trim().isEmpty()) {
			throw new CompanyValidationException("Company : city is required");
		}
		
		if(updateCompanyRequest.getCountry() != null && updateCompanyRequest.getCountry().trim().isEmpty()) {
			throw new CompanyValidationException("Company : country is required");
		}	
	}
}
