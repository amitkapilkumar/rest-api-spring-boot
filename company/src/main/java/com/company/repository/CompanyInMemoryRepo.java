package com.company.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.company.exception.CompanyNotFoundException;
import com.company.model.Company;

public class CompanyInMemoryRepo {
	private static CompanyInMemoryRepo companyInMemoryRepo;
	private Map<String, Company> companies;
	private CompanyInMemoryRepo() {
		companies = new ConcurrentHashMap<>();
	}
	
	public static CompanyInMemoryRepo getInstance() {
		if(companyInMemoryRepo == null) {
			synchronized (CompanyInMemoryRepo.class) {
				if(companyInMemoryRepo == null) {
					companyInMemoryRepo = new CompanyInMemoryRepo();
				}
			}
		}
		return companyInMemoryRepo;
	}
	
	public String addCompany(Company company) {
		companies.put(company.getCompanyId(), company);
		return company.getCompanyId();
	}
	
	public List<Company> getCompanies() {
		return companies.values().stream().collect(Collectors.toList());
	}
	
	public Company getCompany(String companyId) {
		return companies.get(companyId);
	}
	
	public Company addOwners(String companyId, List<String> owners) throws CompanyNotFoundException {
		Company company = companies.get(companyId);
		if(company == null)
			throw new CompanyNotFoundException("Company with id : " + companyId + " not found");
		if(owners == null || owners.isEmpty())
			return company;
		synchronized (company) {
			for(String owner : owners) {
				if(!company.getOwners().contains(owner)) {
					company.getOwners().add(owner);
				}
			}
			return company;
		}
	}

	public Company removeOwners(String companyId, List<String> owners) throws CompanyNotFoundException {
		Company company = companies.get(companyId);
		if(company == null)
			throw new CompanyNotFoundException("Company with id : " + companyId + " not found");
		if(owners == null || owners.isEmpty())
			return company;
		synchronized (company) {
			for(String owner : owners) {
				if(company.getOwners().contains(owner))
					company.getOwners().remove(owner);
			}
			return company;
		}
	}
	
}
