package uk.co.ebi.person.domain;

public class Person implements Comparable<Person> {

	private String first_name;
	private String last_name;
	private Integer age;
	private String favourite_colour;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFavourite_colour() {
		return favourite_colour;
	}

	public void setFavourite_colour(String favourite_colour) {
		this.favourite_colour = favourite_colour;
	}

	@Override
	public int hashCode() {
		return first_name.hashCode()+age;
	}

	@Override
	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append(";name:").append(first_name+" "+last_name).append(age);
		build.append(";favourite_colour:").append(favourite_colour);
		return build.toString();
	}
	public int compareTo(Person person) {
		if(person.age > this.age)
			return 1;
		else if(person.age < this.age)
			return -1;
		else return 0;
	}
	
	
}
