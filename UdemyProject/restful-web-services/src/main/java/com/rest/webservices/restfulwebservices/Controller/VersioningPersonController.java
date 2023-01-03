package com.rest.webservices.restfulwebservices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.restfulwebservices.versioning.Name;
import com.rest.webservices.restfulwebservices.versioning.PersonV1;
import com.rest.webservices.restfulwebservices.versioning.PersonV2;

@RestController
public class VersioningPersonController {


	@Autowired
// versioning useing url
	@GetMapping("/v1/person")
	public PersonV1 getVersionV1()
	{
		return new PersonV1 ("Bob tom");
	}

	@GetMapping("/v2/person")
	public PersonV2 getVersionV2()
	{
		return new PersonV2 ( new Name("Bob", "tom"));
	}
	
	
	//// versioning usinng 
	@GetMapping(path="/person",params = "version=1")
	public PersonV1 getVersionV2RequestPaer()
	{
		return new PersonV1 ("Bob tom v1");
	}
	
	@GetMapping(path="/v2/person",params = "version=2")
	public PersonV2 getVersionV2RequestPaerV2()
	{
		return new PersonV2 ( new Name("Bob", "tom"));
	}
	
	@GetMapping(path="/person",headers = "version=1" )
	public PersonV1 getVersionV2RequestHeader1()
	{
		return new PersonV1 ("Bob tom v1");
	}
	
	@GetMapping(path="/person",headers = "version=2")
	public PersonV2 getVersionV2RequestHeader2()
	{
		return new PersonV2 ( new Name("Bob", "tom"));
	}
}
