package com.company.builder;

import java.util.List;

import com.company.dto.OwnersRequest;

public class OwnersRequestBuilder {
	private List<String> owners;
	
	public OwnersRequestBuilder withOwners(List<String> owners) {
		this.owners = owners;
		return this;
	}

	public OwnersRequest build() {
		OwnersRequest or = new OwnersRequest();
		or.setOwners(owners);
		return or;
	}
}