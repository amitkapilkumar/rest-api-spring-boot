package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.OwnersRequest;
import com.company.dto.Response;
import com.company.dto.UpdateCompanyRequest;
import com.company.exception.CompanyNotFoundException;
import com.company.exception.CompanyValidationException;
import com.company.model.Company;
import com.company.service.CompanyService;
import com.company.validator.CompanyValidator;

@RestController
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyValidator companyValidator;
	
	@RequestMapping(value = "/company/add", method = RequestMethod.PUT, consumes = "application/json")
	public Response addCompany(@RequestBody Company company) throws CompanyValidationException {
		companyValidator.validate(company);
		companyService.addcompany(company);
		return response("Company Added successfully");
	}
	
	@RequestMapping(value = "/company/list") 
	public List<Company> getCompanyList() {
		return companyService.getCompanies();
	}
	
	@RequestMapping(value = "/company/detail/{companyId}")
	public Company companyDetails(@PathVariable("companyId") String companyId) throws CompanyNotFoundException {
		return companyService.getCompanyDetails(companyId);
	}
	
	@RequestMapping(value = "/company/update/{companyId}", method = RequestMethod.POST, consumes = "application/json")
	public Company updateCompany(@PathVariable("companyId") String companyId, 
			@RequestBody UpdateCompanyRequest updateCompanyRequest) throws CompanyNotFoundException, CompanyValidationException {
		companyValidator.validate(updateCompanyRequest);
		return companyService.updateCompany(companyId, updateCompanyRequest);
	}
	
	@RequestMapping(value = "/company/{companyId}/addowners", method = RequestMethod.POST, consumes = "application/json")
	public Company addOwners(@PathVariable("companyId") String companyId,
			@RequestBody OwnersRequest owners) throws CompanyNotFoundException {
		return companyService.addOwners(companyId, owners);
	}
	
	@RequestMapping(value = "/company/{companyId}/removeowners", method = RequestMethod.POST, consumes = "application/json")
	public Company removeOwners(@PathVariable("companyId") String companyId,
			@RequestBody OwnersRequest owners) throws CompanyNotFoundException {
		return companyService.removeOwners(companyId, owners);
	}
	
	private Response response(String message) {
		Response acr = new Response();
		acr.setMessage(message);
		return acr;
	}
}
