package com.ecse428.project.fitboi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import com.ecse428.project.fitboi.model.UserProfile;
import com.ecse428.project.repository.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {
	private static final Logger LOGGER = Logger.getLogger( UserControllerTests.class.getName() );

	@Autowired 
	private UserRepository userRepository;

	@Autowired
    private MockMvc mockMvc;

	private	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	public void setup(){
		userRepository.deleteAll();
	}

	@AfterEach
	public void teardown(){
		userRepository.deleteAll();
	}

	@Test
	public void testPostUserControllerSuccess() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());
	}

	@Test
	public void testPostUserControllerInvalidUserInfo() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
			.andExpect(status().isCreated());
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isBadRequest());	
	}

	@Test
	public void testPostUserControllerDuplicate() throws Exception{
		String aEmail = "";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = -1;
		int aHeight = -1;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isNotAcceptable());
	}


	@Test
	public void testPostUserControllerNull() throws Exception{
		UserProfile testUser = null;

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isBadRequest());
	}

	@Test
	public void testPutUserControllerSuccess() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());

        testUser.setName("updatedBoi");
        mockMvc.perform(put("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isOk());
	}

	@Test
	public void testGetUserControllerSuccess() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());

        mockMvc.perform(get("/users/" + aEmail)).andExpect(status().isOk());
	}

	@Test
	public void testGetUserControllerFail() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());

        mockMvc.perform(get("/users/" + "test")).andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteUserControllerSuccess() throws Exception{
		String aEmail = "testUser1@mail.mcgill.ca";
		String aName = "testboi";
		String aUserName = "testBoi";
		String aPassword = "password";
		int aAge = 15;
		int aHeight = 193;
		boolean aBiologicalSex = true;
		UserProfile testUser = new UserProfile(aEmail, aName, aUserName, aPassword, aAge, aHeight, aBiologicalSex);

		LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser));
		mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON)
        	.content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testUser)))
        	.andExpect(status().isCreated());

        mockMvc.perform(delete("/users/" + aEmail)).andExpect(status().isOk());
	}

	
}