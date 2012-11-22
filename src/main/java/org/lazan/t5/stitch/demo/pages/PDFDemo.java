package org.lazan.t5.stitch.demo.pages;

import org.apache.tapestry5.annotations.Property;

public class PDFDemo {
	@Property
	private Person person;
	
	public String getName() {
		return "World";
	}
	
	public Person[] getPeople() {
		return new Person[] {
			new Person("Lance", "Java", 30),
			new Person("Angelina", "Jolie", 35),
			new Person("Brad", "Pitt", 36)
		};
	}
	
	public static class Person {
		public String firstName;
		public String surname;
		public int age;
		public Person(String firstName, String surname, int age) {
			super();
			this.firstName = firstName;
			this.surname = surname;
			this.age = age;
		}
	}
}
