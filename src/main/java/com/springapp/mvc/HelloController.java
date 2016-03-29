package com.springapp.mvc;

import com.springapp.Entity.Profile;
import com.springapp.Service.ProfileService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

	//welcome page
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "CMPE 277 Lab 2");
		return "hello";
	}

	//custom 404 pages
	//getmessage returns profile id
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handleCustomException(ResourceNotFoundException ex) {
		ModelAndView model = new ModelAndView("notfound");
		model.addObject("id", ex.getMessage());
		return model;

	}

	//Get a profile as HTML
	//first requirement
	@RequestMapping(value="/profile/{userId}", method = RequestMethod.GET)
	public String getProfile(ModelMap model, @PathVariable("userId") String id) {

		List<Profile> list = getProfileServices().getProfilebyid(id);
		if(list.size()>0) {
			Profile profile = list.get(0);
			model.put("id", profile.getId());
			model.put("firstname", profile.getFirstname());
			model.put("lastname", profile.getLastname());
			model.put("email", profile.getEmail());
			model.put("address", profile.getAddress());
			model.put("organization", profile.getOrganization());
			model.put("aboutmyself", profile.getAboutMyself());
		}else{
			//return 404
			throw new ResourceNotFoundException(id);
		}

		return "createprofile";
	}

	//get a profile as plain text
	//second requirement
	@RequestMapping(value="/profile/{userId}", method = RequestMethod.GET, params = "brief")
	public String getProfile(ModelMap model, @PathVariable("userId") String id, @RequestParam("brief") boolean brief) {

		List<Profile> list = getProfileServices().getProfilebyid(id);
		if(list.size()>0) {
			Profile profile = list.get(0);
			model.put("id", profile.getId());
			model.put("firstname", profile.getFirstname());
			model.put("lastname", profile.getLastname());
			model.put("email", profile.getEmail());
			model.put("address", profile.getAddress());
			model.put("organization", profile.getOrganization());
			model.put("aboutmyself", profile.getAboutMyself());
		}else{
			//return 404
			throw new ResourceNotFoundException(id);
		}

		if(brief){
			return "brief";
		}
		return "createprofile";
	}

	//get the profile creation html
	//third requirement
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String getProfile(ModelMap model) {
		return "createprofile";
	}

	//create or update profile
	//4th requirement
	@RequestMapping(value="/profile", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	public String postProfile(ModelMap model, @ModelAttribute("profile") Profile profile) {

		//if it is in database
		List<Profile> list = getProfileServices().getProfilebyid(profile.getId());
		if(list.size() > 0){
			profile.setSystem_id(list.get(0).getSystem_id());
			getProfileServices().update(profile);
		}else {
			//not in database
			getProfileServices().insert(profile);
		}

		model.put("id", profile.getId());
		model.put("firstname", profile.getFirstname());
		model.put("lastname", profile.getLastname());
		model.put("email", profile.getEmail());
		model.put("address", profile.getAddress());
		model.put("organization", profile.getOrganization());
		model.put("aboutmyself", profile.getAboutMyself());
		return "brief";
	}

	//delete a profile
	//5th requirement
	@RequestMapping(value="/profile/{userId}", method = RequestMethod.DELETE)
	public String deleteProfile(ModelMap model, @PathVariable("userId") String id){
		List<Profile> list = getProfileServices().getProfilebyid(id);
		if(list.size()>0) {
			getProfileServices().delete(id);
		}else{
			throw new ResourceNotFoundException(id);
		}
		return "createprofile";
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