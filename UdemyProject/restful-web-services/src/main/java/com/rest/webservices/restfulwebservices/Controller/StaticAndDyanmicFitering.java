package com.rest.webservices.restfulwebservices.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.webservices.restfulwebservices.user.SomeBean;

@RestController
public class StaticAndDyanmicFitering {

	@GetMapping("/filter")
	public SomeBean Filtering() {
		
		return new SomeBean("value1","value2","value3");
	}
	@GetMapping("/list")
	public List<SomeBean> FilteringList() {
		
		return Arrays.asList( new SomeBean( "value1","value2","value3"),
			                  new SomeBean( "value1","value2","value3"),
			                  new SomeBean( "value1","value2","value3")
				);
	}
	
	//dyanamic filtering 
	@GetMapping("/filtering")
	public MappingJacksonValue jacksonValue()
	{
	  SomeBean someBean	=new SomeBean("value1","value2","value3");
		MappingJacksonValue mappingjacksonValue = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter beanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		
		
		FilterProvider filterProvider= new SimpleFilterProvider().addFilter("SomeBeanFilter",beanPropertyFilter);
		
		mappingjacksonValue.setFilters(filterProvider);
		return mappingjacksonValue;
		
	}
	
	@GetMapping("/filteringList")
	public MappingJacksonValue jacksonValueList()
	{
	     List<SomeBean> someBean	= Arrays.asList( new SomeBean("value1","value2","value3"),
	    		 new SomeBean("value4","value5","value6"),
	    		 new SomeBean("value7","value8","value9") 	 );
	     
		MappingJacksonValue mappingjacksonValue = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter beanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filterProvider= new SimpleFilterProvider().addFilter("SomeBeanFilter",beanPropertyFilter);
		
		mappingjacksonValue.setFilters(filterProvider);
		return mappingjacksonValue;
		
	}
}
