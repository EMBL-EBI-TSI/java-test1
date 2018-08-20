package uk.co.ebi.person.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.ebi.person.app.ProfileServiceConstants;
import uk.co.ebi.person.domain.Person;
import uk.co.ebi.person.exception.PersonException;
import uk.co.ebi.person.repository.PersonRepository;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	@Autowired
	PersonRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@RequestMapping(value = "/persons", method= RequestMethod.GET ,produces = "application/json")
    public ResponseEntity<List<Person>> getPersonList() {
		try{
			List<Person> personList = repository.fetchAll();
			return new ResponseEntity<List<Person>>(personList,HttpStatus.OK);
		}catch(Exception e){
			logger.error("Error while getting all Persons : "+e.getMessage());
			return new ResponseEntity<List<Person>>(handleException(e));
		}
    }
	
	@RequestMapping(value = "/persons/name/{name}", method= RequestMethod.GET ,produces = "application/json")
    public ResponseEntity<Person> getPersonByName(@PathVariable("name") String name)
    {
		try{
			Person Person = repository.fetchByName(name);
			return new ResponseEntity<Person>(Person,HttpStatus.OK);
		}catch(Exception e){
			logger.error("Error while getting Person by Name["+name+"] ; "+e.getMessage());
			return new ResponseEntity<Person>(handleException(e));
		}
    }

	
	@RequestMapping(value = "/persons", method= RequestMethod.POST ,consumes = "application/json")
    public ResponseEntity<Void> savePerson(@RequestBody Person person)
    {
		try{
			if(validatePerson(person)){
				repository.save(person);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
			else
				return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}catch(Exception e){
			logger.error("Error while saving Person ["+person+"] ; "+e.getMessage());
			return new ResponseEntity<Void>(handleException(e));
		}
    }
	
	
	
	@RequestMapping(value = "/persons", method= RequestMethod.PUT ,consumes = "application/json")
    public ResponseEntity<Void> updatePerson(@RequestBody Person bean)
    {
		try{
			if(bean.getFirst_name() != null && !bean.equals("") && bean.getLast_name() != null && !bean.getLast_name().equals("") ){
				repository.update(bean);
				return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
			}
			else
				return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}catch(Exception e){
			logger.error("Error while updating Person ["+bean+"] ; "+e.getMessage());
			return new ResponseEntity<Void>(handleException(e));
		}
    }

	@RequestMapping(value = "/persons", method= RequestMethod.DELETE ,consumes = "application/json")
	public ResponseEntity<Void> deletePerson(@PathVariable("name") String name)
	{
		try{
			if(name != null && !name.equals("")){
				repository.delete(name);
				return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
			}
			else
				return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}catch(Exception e){
			logger.error("Error while Delete person ["+name+"] ; "+e.getMessage());
			return new ResponseEntity<Void>(handleException(e));
		}
	}
	
	private Boolean validatePerson(Person bean){
		Boolean validate = false;
		if(bean.getFirst_name() != null && !bean.equals("") && bean.getLast_name() != null && !bean.getLast_name().equals("") ){
			validate = true; 
		}
		return validate;
	}

	private HttpStatus handleException(Exception e){
		
		HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
		if(e instanceof PersonException){
			PersonException personE = (PersonException) e;
			if(personE.getErrorCode() == ProfileServiceConstants.ALREADY_EXIST)
				statusCode = HttpStatus.CONFLICT;
			if(personE.getErrorCode() == ProfileServiceConstants.NOT_FOUND)
				statusCode = HttpStatus.BAD_REQUEST;
			if(personE.getErrorCode() == ProfileServiceConstants.DB_ERROR)
				statusCode = HttpStatus.EXPECTATION_FAILED;
		}
		if(e instanceof ParseException){
				statusCode = HttpStatus.BAD_REQUEST;
		}
		return statusCode;
	}
}
