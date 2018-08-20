package uk.co.ebi.person.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.ebi.person.datastore.DataStore;
import uk.co.ebi.person.domain.Person;
import uk.co.ebi.person.exception.DataStoreException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@Component
public class PersonRepositoryImpl implements PersonRepository {
	
	@Autowired
	private DataStore dataStore;

	@Override
	public void save(Person personC) throws DataStoreException {
		Person person = fetchByName(personC.getFirst_name()+" "+personC.getFirst_name());
		if(person != null)
			throw new DataStoreException("Already Person exists on system");
		List<Person> personList = getPersonList();
		personList.add(person);
		setPersonList(personList);
	}

	@Override
	public void update(Person personUpdate) throws DataStoreException {

		Person person = fetchByName(personUpdate.getFirst_name()+" "+personUpdate.getFirst_name());
		if(person == null)
			throw new DataStoreException("Person not found!!!");
		List<Person> personList = getPersonList();
		for (int i = 0; i < personList.size(); i++) {
			person = personList.get(i);
			if((personUpdate.getFirst_name()+" "+personUpdate.getLast_name()).equals(person.getFirst_name()+" "+person.getFirst_name())){
				person.setFavourite_colour(personUpdate.getFavourite_colour());
				person.setAge(personUpdate.getAge());
				personList.set(i, person);
				break;
			}
		}
		setPersonList(personList);
	}

	@Override
	public Person fetchByName(String name) {
		Person PersonRet = null;
		List<Person> stockList = getPersonList();
		for (Iterator<Person> iterator = stockList.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			if((person.getFirst_name()+" "+person.getLast_name()).equals(name)){
				PersonRet = person;
				break;
			}
		}
		return PersonRet;
	}

	@Override
	public void delete(String name) throws DataStoreException {

		Person person = fetchByName(name);
		if(person == null)
			throw new DataStoreException("Person not found!!!");
		List<Person> personList = getPersonList();
		for (int i = 0; i < personList.size(); i++) {
			person = personList.get(i);
			if(name.equals(person.getFirst_name()+" "+person.getFirst_name())){
				personList.remove(i);
				break;
			}
		}
		setPersonList(personList);

	}
	public List<Person> fetchAll() {
		List<Person> personList = getPersonList();
		Collections.sort(personList);
		return personList;
	}

	private List<Person> getPersonList(){
		return dataStore.getPersonList();
	}
	private void setPersonList(List<Person> personList){
		dataStore.setPersonList(personList);
	}

}
