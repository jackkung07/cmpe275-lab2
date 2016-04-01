package com.springapp.mvc;

import com.springapp.Entity.Profile;
import com.springapp.Repository.AbsProfileDaoImp;
import com.springapp.Service.ProfileService;
import com.springapp.XDao.ProfileDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class HelloControllerTest {

    @Mock
    private ProfileService profileService;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    @InjectMocks
    private HelloController helloController;

    private MockMvc mockMvc;
    private Profile profile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
        profile = new Profile();
        profile.setId("0100");
        profile.setAboutMyself("student");
        profile.setAddress("123 A St., San Jose CA 94321");
        profile.setEmail("abc@gmail.com");
        profile.setFirstname("Alan");
        profile.setLastname("Chen");
        profile.setOrganization("SJSU");
        profileService.insert(profile);
    }

    @Test
    public void testHello() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"));
    }


    @Test
    public void testPostProfile() throws Exception {
        mockMvc.perform(post("/profile")
                .param("id", "0100")
                .param("firstname", "Alan")
                .param("lastname", "Chen")
                .param("email", "abc@abc.com")
                .param("address", "1 A St., San Jose CA 94123")
                .param("organization", "SJSU")
                .param("aboutMyself", "Student"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("profile"))
                .andExpect(view().name("editprofile"));
    }

    @Test
    public void testGetProfile() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("createprofile"));
    }

    @Test
    public void testGetProfileWithIncorrectID() throws Exception {
        mockMvc.perform(get("/profile/2"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("notfound"));
    }

    @Test
    public void testDatabase() {
        List<Profile> list = new ArrayList<Profile>();
        when(profileService.getProfilebyid("0100")).thenReturn(list);
        Assert.assertNotNull(profileService.getProfilebyid("0100"));
    }
}
