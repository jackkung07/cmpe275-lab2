package com.springapp.mvc;

import com.springapp.Entity.Profile;
import com.springapp.Service.ProfileService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HelloController {

	private ProfileService getProfileServices(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("application-context.xml");
		return (ProfileService) context.getBean("ProfileService");


	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {


		model.addAttribute("message", "Hello world!");
		return "hello";
	}

	@RequestMapping(value="/insert/{id}/{lastname}/{firstname}/{aboutmyself}/{address}/{email}/{organization}", method = RequestMethod.GET)
	public String addtest(@PathVariable("id") String id,@PathVariable("lastname") String lastname,@PathVariable("firstname") String firstname,
						  @PathVariable("aboutmyself") String aboutmyself,@PathVariable("address") String address,@PathVariable("email") String email,
						  @PathVariable("organization") String organization) {
		Profile newprofile = new Profile();
		newprofile.setSystem_id(null);
		newprofile.setId(id);
		newprofile.setLastname(lastname);
		newprofile.setFirstname(firstname);
		newprofile.setEmail(email);
		newprofile.setAboutMyself(aboutmyself);
		newprofile.setAddress(address);
		newprofile.setOrganization(organization);

		getProfileServices().insert(newprofile);
		return "hello";
	}

	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String retrieve(ModelMap model) {

		//List<Profile> stdlist = getProfileServices().queryall();
		String rpl = getProfileServices().testquery();
		model.addAttribute("message", rpl);
		return "hello";
	}
}