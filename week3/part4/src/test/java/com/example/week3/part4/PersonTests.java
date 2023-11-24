package com.example.week3.part4;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Negative;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.api.constraints.WithNull;

import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PersonTests {

	@Property
	void should_create_person(
			@ForAll @Positive long creationTimestamp,
			@ForAll @StringLength(min=2) String name,
			@ForAll @Positive int itemCount,
			@ForAll Occupation occupation
	) {
		Person person = new Person(creationTimestamp, name, itemCount, occupation);

		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(person.getCreationTimestamp()).isEqualTo(creationTimestamp);
		softly.assertThat(person.getName()).isEqualTo(name);
		softly.assertThat(person.getNumberOfBoughtGoods()).isEqualTo(itemCount);
		softly.assertThat(person.getOccupation()).isEqualTo(occupation);
		softly.assertAll();
	}

	@Property
	void should_fail_to_create_person_because_of_invalid_name(
			@ForAll @StringLength(max=1) @WithNull(value=0.1) String name
	) {
		assertThatThrownBy(() -> new Person(1, name, 42, Occupation.EMPLOYED))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Name can't be empty");
	}

	@Property
	void should_fail_to_create_person_because_of_invalid_number_of_goods(
			@ForAll @Negative int itemCount
	) {
		assertThatThrownBy(() -> new Person(1, "name", itemCount, Occupation.EMPLOYED))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Number of goods can't be negative");

	}

	@Property
	void should_fail_to_create_person_because_of_invalid_timestamp(
			@ForAll @Negative long creationTimestamp
	) {
		assertThatThrownBy(() -> new Person(creationTimestamp, "name", 1, Occupation.EMPLOYED))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Timestamp can't be negative");
	}

	@Example
	void should_fail_to_create_person_because_of_invalid_occupation() {
		assertThatThrownBy(() -> new Person(1, "name", 1, null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Object can't be empty");

	}
}
