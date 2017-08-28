package com.tmo.gemfire.security.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gemstone.gemfire.cache.Region;

@RestController
public class HomeController {

	@Autowired
	Environment env;

	@Resource(name="customers")
	Region customerRegion;

	@RequestMapping("/")
	public String home() {


		System.out.println(env.getProperty("vcap.services.gemfire9.credentials.locators"));
		System.out.println(env.getProperty("vcap.services.gemfire9.credentials.users[0].username"));
		System.out.println(env.getProperty("vcap.services.gemfire9.credentials.users[0].password"));



		return "Customer Search Service -- Available APIs: <br/>"
		+ "<br/>"
		+ "GET /addcustomer?email={email}&value={name}  - insert a value <br/>"
		+ "GET /getcustomer?email={email}               - get specific value <br/>"
		+ "GET /deletecustomer?email={email}            - delete a value <br/>";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/addcustomer")
	@ResponseBody
	public String insertCustomerData(@RequestParam(value = "email", required = true)
	String email, @RequestParam(value = "value", required = true) String name)  {


		customerRegion.put(email, name);
		return "customer data successfully inserted into cache.";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getcustomer")
	@ResponseBody
	public String getCustomerData(@RequestParam(value = "email", required = true)
					String email)  {


		String name = (String) customerRegion.get(email);
		return "customer name:" + name;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/deletecustomer")
	@ResponseBody
	public String deleteCustomerData(@RequestParam(value = "email", required = true)
					String email)  {


		customerRegion.destroy(email);
		return "customer data successfully destroyed.";
	}

}
