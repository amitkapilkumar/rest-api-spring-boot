package com.company.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.CompanyApplication;
import com.company.builder.CompanyBuilder;
import com.company.builder.OwnersRequestBuilder;
import com.company.builder.UpdateCompanyRequestBuilder;
import com.company.dto.OwnersRequest;
import com.company.dto.UpdateCompanyRequest;
import com.company.model.Company;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompanyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyControllerTest {
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void testCreateCompany() {
		List<String> owners = new ArrayList<>();
		owners.add("MavenInk");
		Company company = new CompanyBuilder().withName("ABC Corp").withAddress("171 Sifi Road").withCity("Newark")
				.withCountry("Malta").withCompanyId("CM1234").withOwners(owners).build();
		HttpEntity<Company> entity = new HttpEntity<Company>(company, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/company/add"),
				HttpMethod.PUT, entity, String.class);

		String expected = "Company Added successfully";
		assertTrue(response.getBody().contains(expected));
	}
	
	@Test
	public void testGetCompanyDetails() {
		List<String> owners = new ArrayList<>();
		owners.add("MavenInk");
		Company company = new CompanyBuilder().withName("ABC Corp").withAddress("171 Sifi Road").withCity("Newark")
				.withCountry("Malta").withCompanyId("CM1234").withOwners(owners).build();
		HttpEntity<Company> entity = new HttpEntity<Company>(company, headers);

		restTemplate.exchange(createURLWithPort("/company/add"), HttpMethod.PUT, entity, String.class);
		HttpEntity<String> getEntity = new HttpEntity<String>(null, headers);
		ResponseEntity<Company> response = restTemplate.exchange(createURLWithPort("/company/detail/CM1234"), 
				HttpMethod.GET, getEntity, Company.class);

		assertTrue(response.getBody().getCompanyId().equals("CM1234"));
		assertTrue(response.getBody().getName().equals("ABC Corp"));
		assertTrue(response.getBody().getAddress().equals("171 Sifi Road"));
		assertTrue(response.getBody().getCity().equals("Newark"));
		assertTrue(response.getBody().getCountry().equals("Malta"));
		assertTrue(response.getBody().getOwners().size() == 1);
		assertTrue(response.getBody().getOwners().get(0).equals("MavenInk"));
	}
	
	@Test
	public void testUpdateCompanyDetails() {
		List<String> owners = new ArrayList<>();
		owners.add("MavenInk");
		Company company = new CompanyBuilder().withName("ABC Corp").withAddress("171 Sifi Road").withCity("Newark")
				.withCountry("Malta").withCompanyId("CM1234").withOwners(owners).build();
		HttpEntity<Company> entity = new HttpEntity<Company>(company, headers);

		restTemplate.exchange(createURLWithPort("/company/add"), HttpMethod.PUT, entity, String.class);
		UpdateCompanyRequest ucr = new UpdateCompanyRequestBuilder().withCity("Naili").withEmail("abc@gmail.com").build();
		HttpEntity<UpdateCompanyRequest> updateEntity = new HttpEntity<UpdateCompanyRequest>(ucr, headers);
		
		ResponseEntity<Company> response = restTemplate.exchange(createURLWithPort("/company/update/CM1234"), 
				HttpMethod.POST, updateEntity, Company.class);

		assertTrue(response.getBody().getCompanyId().equals("CM1234"));
		assertTrue(response.getBody().getName().equals("ABC Corp"));
		assertTrue(response.getBody().getAddress().equals("171 Sifi Road"));
		assertTrue(response.getBody().getCity().equals("Naili"));
		assertTrue(response.getBody().getCountry().equals("Malta"));
		assertTrue(response.getBody().getEmail().equals("abc@gmail.com"));
		assertTrue(response.getBody().getOwners().size() == 1);
		assertTrue(response.getBody().getOwners().get(0).equals("MavenInk"));
	}
	
	@Test
	public void testUpdateCompanyDetailsWithCompanyNotFoundException() {
				
		UpdateCompanyRequest ucr = new UpdateCompanyRequestBuilder().withCity("Naili").withEmail("abc@gmail.com").build();
		HttpEntity<UpdateCompanyRequest> updateEntity = new HttpEntity<UpdateCompanyRequest>(ucr, headers);
		
		ResponseEntity<Company> response = restTemplate.exchange(createURLWithPort("/company/update/CM111"), 
				HttpMethod.POST, updateEntity, Company.class);
		
		assertTrue(response.getStatusCodeValue() == 500);
	}

	
	@Test
	public void testAddOwners() {
		List<String> owners = new ArrayList<String>() {{
			add("Liark");
		}};
		
		OwnersRequest ownerRequest = new OwnersRequestBuilder().withOwners(owners).build();
		HttpEntity<OwnersRequest> entity = new HttpEntity<OwnersRequest>(ownerRequest, headers);
		
		ResponseEntity<Company> response = restTemplate.exchange(createURLWithPort("/company/CM1234/addowners"), 
				HttpMethod.POST, entity, Company.class);
		
		assertTrue(response.getBody().getOwners().size() == 2);
	}
	
	@Test
	public void testRemoveOwners() {
		List<String> owners = new ArrayList<String>() {{
			add("Liark");
		}};
		
		OwnersRequest ownerRequest = new OwnersRequestBuilder().withOwners(owners).build();
		HttpEntity<OwnersRequest> entity = new HttpEntity<OwnersRequest>(ownerRequest, headers);
		
		ResponseEntity<Company> response = restTemplate.exchange(createURLWithPort("/company/CM1234/removeowners"), 
				HttpMethod.POST, entity, Company.class);
		
		assertTrue(response.getBody().getOwners().size() == 1);
	}
	
	@Test
	public void testGetCompanyList() {
		List<String> owners1 = new ArrayList<>();
		owners1.add("MavenInk");
		
		List<String> owners2 = new ArrayList<>();
		owners2.add("Liamiki");
		owners2.add("HansWay");
		
		Company company1 = new CompanyBuilder().withName("ABC Corp").withAddress("171 Sifi Road").withCity("Newark")
				.withCountry("Malta").withCompanyId("CM1234").withOwners(owners1).build();
		HttpEntity<Company> entity = new HttpEntity<Company>(company1, headers);
		restTemplate.exchange(createURLWithPort("/company/add"), HttpMethod.PUT, entity, String.class);
		
		Company company2 = new CompanyBuilder().withName("XYZ Corp").withAddress("256 Umi Estate").withCity("Miniti")
				.withCountry("Estonia").withCompanyId("CM1235").withOwners(owners2).build();
		entity = new HttpEntity<Company>(company2, headers);
		restTemplate.exchange(createURLWithPort("/company/add"), HttpMethod.PUT, entity, String.class);
		
		HttpEntity<String> getEntity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<List<Company>> response = restTemplate.exchange(createURLWithPort("/company/list"), 
				HttpMethod.GET, getEntity, new ParameterizedTypeReference<List<Company>>() {});
		
		assertTrue(response.getBody().size() == 2);
		for(Company company : response.getBody()) {
			if(company.getCompanyId().equals(company1.getCompanyId())) {
				assertTrue(company.getName().equals(company1.getName()));
				assertTrue(company.getAddress().equals(company1.getAddress()));
				assertTrue(company.getCity().equals(company1.getCity()));
				assertTrue(company.getCountry().equals(company1.getCountry()));
				assertTrue(company.getOwners().size() == 1);
				assertTrue(company.getOwners().get(0).equals("MavenInk"));
			}
			else {
				assertTrue(company.getName().equals(company2.getName()));
				assertTrue(company.getAddress().equals(company2.getAddress()));
				assertTrue(company.getCity().equals(company2.getCity()));
				assertTrue(company.getCountry().equals(company2.getCountry()));
				assertTrue(company.getOwners().size() == 2);
				assertTrue(company.getOwners().contains("Liamiki"));
				assertTrue(company.getOwners().contains("HansWay"));
			}
		}
	}
	
	@Test
	public void testCreateCompanyWithException() {
		List<String> owners = new ArrayList<>();
		owners.add("MavenInk");
		Company company = new CompanyBuilder().withAddress("171 Sifi Road").withCity("Newark")
				.withCountry("Malta").withCompanyId("CM1234").withOwners(owners).build();
		HttpEntity<Company> entity = new HttpEntity<Company>(company, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/company/add"),
				HttpMethod.PUT, entity, String.class);

		assertTrue(response.getStatusCodeValue() == 500);
		assertTrue(response.getBody().contains("Company : name is required"));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
