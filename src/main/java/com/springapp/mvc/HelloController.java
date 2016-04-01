package com.springapp.mvc;

import com.springapp.Entity.Profile;
import com.springapp.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * This is the controller class
 * The controller class is responsible to display model to view
 * and handle event from view and changes model accordingly
 *
 * @author Che-Yi Kung, MingLu Liu, Yuebiao Ma
 * @version 1.0
 * @since 2016-03-30
 */

@Controller
public class HelloController {

    @Autowired
    private ProfileService profileService;

    /**
     * This shows welcome page
     *
     * @param model for modeling jsp parameters
     * @return String for send model to a specific view, hello.jsp in this case
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Group 7: CMPE 275 Lab 2");
        return "hello";
    }

    /**
     * This is for output a custom 404 not found http error page.
     *
     * @param ex takes ResourceNotFoundException obj
     * @return model returns notfound view, notfound.jsp in this case
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(final ResourceNotFoundException ex, final HttpServletRequest request, final HttpServletResponse response) {
        response.setStatus(404);
        request.setAttribute("id", ex.getMessage());
        return "notfound";
    }

    /**
     * This returns an HTML that renders the given user’s profile. The profile fields are part of an HTML form.
     * If profile were not in the database record, a exception is thrown.
     *
     * @param model takes model from modelandview
     * @param id    takes profile id
     * @return all model parameter to editprofile.jsp
     */
    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
    public String getProfile(ModelMap model, @PathVariable("userId") String id) {

        List<Profile> list = profileService.getProfilebyid(id);
        if (list.size() > 0) {
            Profile profile = list.get(0);
            model.put("profile", profile);
        } else {
            //return 404
            model.addAttribute("id", id);
            throw new ResourceNotFoundException(id);
        }

        return "editprofile";
    }

    /**
     * This returns an HTML that encapsulates the given user’s full profile in plain text format with the HTML.
     *
     * @param model takes model from modelandview
     * @param id    takes profile id
     * @param brief if true then plain text format is generated
     * @return all model parameter to brief.jsp or editprofile.jsp
     */
    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET, params = "brief")
    public String getProfile(ModelMap model, @PathVariable("userId") String id, @RequestParam("brief") boolean brief) {
        List<Profile> list = profileService.getProfilebyid(id);
        if (list.size() > 0) {
            Profile profile = list.get(0);
            model.put("profile", profile);
        } else {
            //return 404
            throw new ResourceNotFoundException(id);
        }

        if (brief) {
            return "brief";
        }
        return "editprofile";
    }

    /**
     * This get the profile creation HTML
     *
     * @param model takes model from modelandview
     * @return createprofile.jsp view
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(ModelMap model) {
        return "createprofile";
    }

    /**
     * This is for profile creation or update
     *
     * @param model   takes model from modelandview
     * @param profile Profile dao
     * @return editprofile.jsp
     */
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String postProfile(ModelMap model, @ModelAttribute("profile") Profile profile) {

        //if it is in database
        List<Profile> list = profileService.getProfilebyid((profile.getId()));
        if (list.size() > 0) {
            profile.setSystem_id(list.get(0).getSystem_id());
            profileService.update(profile);
        } else {
            //not in database
            profileService.insert(profile);
        }

        model.put("profile", profile);
        return "editprofile";
    }

    /**
     * This is for profile deletion
     *
     * @param model takes model from modelandview
     * @param id    takes id of which profile to delete
     * @return routes back to createProfile.jsp
     */
    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.DELETE)
    public String deleteProfile(ModelMap model, @PathVariable("userId") String id) {
        System.out.println("-------------------");
        List<Profile> list = profileService.getProfilebyid(id);
        if (list.size() > 0) {
            profileService.delete(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
        return "redirect:/profile";
    }

    /**
     * this is to delete the object by input id
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletebyid(@PathVariable("id") String id, ModelMap model) {
        System.out.println("ok");
        profileService.delete(id);
        model.addAttribute("message", "done");
        return "hello";
    }

    /**
     * this is for querying usage(test the query by id module)
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/querybyid/{id}", method = RequestMethod.GET)
    public String querybyid(@PathVariable("id") String id, ModelMap model) {
        String linkrst = "";
        List<Profile> rstset = profileService.getProfilebyid(id);
        //getProfileServices().getProfilebyid(id);
        for (int i = 0; i < rstset.size(); i++)
            linkrst += "Record #: " + String.valueOf(i) + ": " + rstset.get(i).toString();
        model.addAttribute("message", linkrst);
        return "hello";
    }

    /**
     * this is for testing usage(test the update module)
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(ModelMap model) {

        Profile test = new Profile();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        List<Profile> rstset = profileService.getProfilebyid("0002");
        for (int i = 0; i < rstset.size(); i++) {
            test = rstset.get(i);
            test.setOrganization(dateFormat.format(cal.getTime()));
            profileService.update(test);
        }
        model.addAttribute("message", "done");
        return "hello";
    }

    /**
     * this is to test the insert module
     *
     * @param id
     * @param lastname
     * @param firstname
     * @param aboutmyself
     * @param address
     * @param email
     * @param organization
     * @return
     */
    @RequestMapping(value = "/insert/{id}/{lastname}/{firstname}/{aboutmyself}/{address}/{email}/{organization}", method = RequestMethod.GET)
    public String addtest(@PathVariable("id") String id, @PathVariable("lastname") String lastname, @PathVariable("firstname") String firstname,
                          @PathVariable("aboutmyself") String aboutmyself, @PathVariable("address") String address, @PathVariable("email") String email,
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

    /**
     * this is to test the query module
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String retrieve(ModelMap model) {

        String rpl = profileService.testquery();
        //getProfileServices().testquery();
        model.addAttribute("message", rpl);
        return "hello";
    }

}