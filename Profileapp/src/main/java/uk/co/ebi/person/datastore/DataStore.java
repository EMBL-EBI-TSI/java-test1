package uk.co.ebi.person.datastore;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import uk.co.ebi.person.domain.Person;

public class DataStore {
	
	List<Person> stockList = new CopyOnWriteArrayList<Person>();

	public List<Person> getPersonList() {
		return stockList;
	}

	public void setPersonList(List<Person> stockList) {
		this.stockList = stockList;
	}
}
