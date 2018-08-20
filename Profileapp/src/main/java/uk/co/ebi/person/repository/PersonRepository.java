package uk.co.ebi.person.repository;

import org.springframework.stereotype.Service;
import uk.co.ebi.person.domain.Person;
import uk.co.ebi.person.exception.DataStoreException;
import java.util.List;

@Service
public interface PersonRepository {
	public void save(Person person) throws DataStoreException;
	public void update(Person person) throws DataStoreException;
	public Person fetchByName(String name);
	public void delete(String name) throws DataStoreException;
	public List<Person> fetchAll();
}
