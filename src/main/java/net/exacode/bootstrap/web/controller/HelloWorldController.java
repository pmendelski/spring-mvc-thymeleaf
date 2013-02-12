package net.exacode.bootstrap.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		logger.debug("Method hello");
		return "hello";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		logger.debug("Method index");
		return "redirect:/hello";
	}

}
