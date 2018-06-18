package com.company.validator;

import com.company.dto.UpdateCompanyRequest;
import com.company.exception.CompanyValidationException;
import com.company.model.Company;

public interface CompanyValidator {
	public void validate(Company company) throws CompanyValidationException;
	public void validate(UpdateCompanyRequest updateCompanyRequest) throws CompanyValidationException;
}