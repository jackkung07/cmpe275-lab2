package com.springapp.mvc;

import com.springapp.Entity.Profile;
import com.springapp.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ProfileService profileService;

	//welcome page
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Group 7: CMPE 275 Lab 2");
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

		List<Profile> list = profileService.getProfilebyid(id);
		if(list.size()>0) {
			Profile profile = list.get(0);
			model.put("profile", profile);
		}else{
			//return 404
			throw new ResourceNotFoundException(id);
		}

		return "editprofile";
	}

	//get a profile as plain text
	//second requirement
	@RequestMapping(value="/profile/{userId}", method = RequestMethod.GET, params = "brief")
	public String getProfile(ModelMap model, @PathVariable("userId") String id, @RequestParam("brief") boolean brief) {
		List<Profile> list = profileService.getProfilebyid(id);
		if(list.size()>0) {
			Profile profile = list.get(0);
			model.put("profile", profile);
		}else{
			//return 404
			throw new ResourceNotFoundException(id);
		}

		if(brief){
			return "brief";
		}
		return "editprofile";
	}

	//get the profile creation html
	//third requirement
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String getProfile(ModelMap model) {
		return "createprofile";
	}

	//create or update profile
	//4th requirement
	@RequestMapping(value="/profile", method = RequestMethod.POST)
	public String postProfile(ModelMap model, @ModelAttribute("profile") Profile profile) {

		//if it is in database
		List<Profile> list = profileService.getProfilebyid((profile.getId()));
		if(list.size() > 0){
			profile.setSystem_id(list.get(0).getSystem_id());
			profileService.update(profile);
		}else {
			//not in database
			profileService.insert(profile);
		}

		model.put("profile", profile);
		return "editprofile";
	}

	//delete a profile
	//5th requirement
	@RequestMapping(value="/profile/{userId}", method = RequestMethod.DELETE)
	public String deleteProfile(ModelMap model, @PathVariable("userId") String id){
		System.out.println("-------------------");
		List<Profile> list = profileService.getProfilebyid(id);
		if(list.size()>0) {
			profileService.delete(id);
		}else{
			throw new ResourceNotFoundException(id);
		}
		return "redirect:/profile";
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

		profileService.insert((newprofile));
		return "hello";
	}

	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String retrieve(ModelMap model) {

		String rpl = profileService.testquery();
				//getProfileServices().testquery();
		model.addAttribute("message", rpl);
		return "hello";
	}

	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deletebyid(@PathVariable("id") String id ,ModelMap model) {
		System.out.println("ok");
		profileService.delete(id);
		model.addAttribute("message", "done");
		return "hello";
	}

	@RequestMapping(value="/querybyid/{id}", method = RequestMethod.GET)
	public String querybyid(@PathVariable("id") String id ,ModelMap model) {
		String linkrst ="";
		List<Profile> rstset = profileService.getProfilebyid(id);
				//getProfileServices().getProfilebyid(id);
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
		List<Profile> rstset = profileService.getProfilebyid("0002");
		for(int i=0; i<rstset.size(); i++)
		{
			test = rstset.get(i);
			test.setOrganization(dateFormat.format(cal.getTime()));
			profileService.update(test);
		}
		model.addAttribute("message", "done");
		return "hello";
	}

}