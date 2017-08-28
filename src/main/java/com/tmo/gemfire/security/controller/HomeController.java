package com.tmo.gemfire.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Autowired
	Environment env;

	@RequestMapping("/")
	public String home() {


		System.out.println(env.getProperty("vcap.services.gemfire9.credentials.locators"));
		System.out.println(env.getProperty("vcap.services.gemfire9.credentials.users[0].username"));
		System.out.println(env.getProperty("vcap.services.gemfire9.credentials.users[0].password"));

		return "Hello";
	}

}
