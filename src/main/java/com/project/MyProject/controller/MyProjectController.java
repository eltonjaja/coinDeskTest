package com.project.MyProject.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.MyProject.service.ReadJsonService;
import com.project.MyProject.service.CoinService;

@Controller
public class MyProjectController {

	@Autowired
	ReadJsonService readJsonService;
	
	@Autowired
	CoinService coinService;
	
	private static final Logger log = LogManager.getLogger(MyProjectController.class);

	@RequestMapping("/hello")
	@ResponseBody
	public String helloWorld() throws Exception {

		readJsonService.readApiToDb();

		return "Hello World ~";
	}

}
