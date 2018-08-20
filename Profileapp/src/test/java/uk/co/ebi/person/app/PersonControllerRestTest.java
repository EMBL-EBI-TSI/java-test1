package uk.co.ebi.person.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.ebi.person.controller.PersonController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerRestTest {
	
	
	@Autowired 
	private MockMvc mockMvc;
	
	private static final String PERSON_JSON1 =
		      "{"
		          + "\"first_name\":\"Johnny\","
					  + "\"last_name\":\"Felix\","
					  + "\"age\":30,"
		          + "\"favourite_colour\":\"blue\""
		+ "}";
	
	private static final String PERSON_JSON2 =
			"{"
					+ "\"first_name\":\"Amal\","
					+ "\"last_name\":\"Xavier\","
					+ "\"age\":25,"
					+ "\"favourite_colour\":\"red\""
					+ "}";
	
	private static final String PERSON_NAME_PARAM = "Johnny Felix";
	

	 @Test
	 public void shouldCreateStock1() throws Exception {
		 mockMvc.perform(post("/api/persons")
	                .content(PERSON_JSON1)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated())
	                .andReturn();
	 }
	 
	// @Test
	 public void shouldCreateStock2() throws Exception {
		 mockMvc.perform(post("/api/persons")
	                .content(PERSON_JSON2)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated())
	                .andReturn();
	 }
	 
	// @Test
	 public void shouldNotCreateStockDuplicate() throws Exception {
		 mockMvc.perform(post("/api/persons")
	                .content(PERSON_JSON1)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isExpectationFailed())
	                .andReturn();
	 }
	 
	 
	 @Test
	 public void retrieveAll() throws Exception {
	 
		 mockMvc.perform(get("/api/persons")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	 }
	

	
}
