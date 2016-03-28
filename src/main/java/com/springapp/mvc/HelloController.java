package com.springapp.mvc;

import com.springapp.Entity.Profile;
import com.springapp.Service.ProfileService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HelloController {

	private ProfileService crtstudsrv(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("application-context.xml");
		return (ProfileService) context.getBean("ProfileService");


	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {


		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String retrieve(ModelMap model) {

		List<Profile> stdlist = crtstudsrv().queryall();
		String rpl = crtstudsrv().testquery();
		model.addAttribute("message", rpl);
		return "hello";
	}
}