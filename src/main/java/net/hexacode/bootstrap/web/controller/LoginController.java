package net.hexacode.bootstrap.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/login")
	public String login(LoginViewModel formModel, ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginForm(@Valid LoginViewModel formModel,
			final BindingResult errors, ModelMap model) {
		if (errors.hasErrors()) {
			return "login";
		}
		if (formModel.getEmail().equals("john@gmail.com")) {
			List<String> msgs = new ArrayList<String>();
			msgs.add("login.email.illegal");
			model.addAttribute("businessErrors", msgs);
			return "login";
		}
		// securityService
		// .logUserIn(formModel.getEmail(), formModel.getPassword());
		return "redirect:login?email=ok";
	}
}
