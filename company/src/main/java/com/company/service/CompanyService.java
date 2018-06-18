package com.company.service;

import java.util.List;

import com.company.dto.OwnersRequest;
import com.company.dto.UpdateCompanyRequest;
import com.company.exception.CompanyNotFoundException;
import com.company.model.Company;

public interface CompanyService {
	public String addcompany(Company company);
	public List<Company> getCompanies();
	public Company getCompanyDetails(String companyId) throws CompanyNotFoundException;
	public Company updateCompany(String companyId, UpdateCompanyRequest updateCompanyRequest) throws CompanyNotFoundException;
	public Company addOwners(String companyId, OwnersRequest owners) throws CompanyNotFoundException;
	public Company removeOwners(String companyId, OwnersRequest owners) throws CompanyNotFoundException;
}