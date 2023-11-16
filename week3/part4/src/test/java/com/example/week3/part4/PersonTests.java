package com.example.week3.part4;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.StringLength;

public class PersonTests {

	@Property
	void should_create_person(
			@ForAll @StringLength(min=1) String name

	) {
		int itemCount = 5;
		Occupation occupation = Occupation.EMPLOYED;
		new Person(name, itemCount, occupation);
	}
}
