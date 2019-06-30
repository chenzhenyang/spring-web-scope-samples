package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	@Qualifier("test3")
	@Lazy
	private IAddress address3; 
	
	@RequestMapping("test")
	public Map<String, String> test() throws Exception {
		String test3 = address3.toString() + address3.hashCode() + address3.getTs();
		IAddress address2 = address3.getPrevious();
		String test2 = address2.toString() + address2.hashCode() + address2.getTs();
		IAddress address1 = address2.getPrevious();
		String test1 = address1.toString() + address1.hashCode() + address3.getTs();
		
		Map<String,String> map = new HashMap<>();
		map.put("test1", test1);
		map.put("test2", test2);
		map.put("test3", test3);
		map.put("test1Origin", AopTargetUtils.getTarget(address1).toString());
		return  map;
	}

}
