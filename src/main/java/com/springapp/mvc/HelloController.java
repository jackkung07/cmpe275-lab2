package com.springapp.mvc;

import com.springapp.Entity.Profile;
import com.springapp.Service.ProfileService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

		String rpl = getProfileServices().testquery();
		model.addAttribute("message", rpl);
		return "hello";
	}

	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deletebyid(@PathVariable("id") String id ,ModelMap model) {

		getProfileServices().delete(id);
		model.addAttribute("message", "done");
		return "hello";
	}

	@RequestMapping(value="/querybyid/{id}", method = RequestMethod.GET)
	public String querybyid(@PathVariable("id") String id ,ModelMap model) {

		String linkrst ="";
		List<Profile> rstset = getProfileServices().getProfilebyid(id);
		for(int i=0; i<rstset.size(); i++)
		linkrst += "Record #: " + String.valueOf(i)+": "+ rstset.get(i).toString();
		model.addAttribute("message", linkrst);
		return "hello";
	}

	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String update(ModelMap model) {

		Profile test = new Profile();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		List<Profile> rstset = getProfileServices().getProfilebyid("0001");
		for(int i=0; i<rstset.size(); i++)
		{
			test = rstset.get(i);
			test.setOrganization(dateFormat.format(cal.getTime()));
			getProfileServices().update(test);
		}
		model.addAttribute("message", "done");
		return "hello";
	}


}