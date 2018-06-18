package com.company.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.dto.OwnersRequest;
import com.company.dto.UpdateCompanyRequest;
import com.company.exception.CompanyNotFoundException;
import com.company.model.Company;
import com.company.repository.CompanyInMemoryRepo;

@Service
public class CompanyServiceImpl implements CompanyService {
	private CompanyInMemoryRepo companyRepo;

	public CompanyServiceImpl() {
		companyRepo = CompanyInMemoryRepo.getInstance();
	}
	
	@Override
	public String addcompany(Company company) {
		companyRepo.addCompany(company);
		return company.getCompanyId();
	}

	@Override
	public List<Company> getCompanies() {
		return companyRepo.getCompanies();
	}

	@Override
	public Company getCompanyDetails(String companyId) throws CompanyNotFoundException {
		Company company = companyRepo.getCompany(companyId);
		if(company == null)
			throw new CompanyNotFoundException("Company with id : " + companyId + " not found");
		return company;
	}

	@Override
	public Company updateCompany(String companyId, UpdateCompanyRequest ucr) throws CompanyNotFoundException {
		Company company = getCompanyDetails(companyId);
		company.setName(ucr.getName() != null ? ucr.getName() : company.getName());
		company.setAddress(ucr.getAddress() != null ? ucr.getAddress() : company.getAddress());
		company.setCity(ucr.getCity() != null ? ucr.getCity() : company.getCity());
		company.setCountry(ucr.getCountry() != null ? ucr.getCountry() : company.getCountry());
		company.setPhone(ucr.getPhone() != null ? ucr.getPhone() : company.getPhone());
		company.setEmail(ucr.getEmail() != null ? ucr.getEmail() : company.getEmail());
		return company;
	}

	@Override
	public Company addOwners(String companyId, OwnersRequest owners) throws CompanyNotFoundException {
		return companyRepo.addOwners(companyId, owners.getOwners());
	}

	@Override
	public Company removeOwners(String companyId, OwnersRequest owners) throws CompanyNotFoundException {
		return companyRepo.removeOwners(companyId, owners.getOwners());
	}

}