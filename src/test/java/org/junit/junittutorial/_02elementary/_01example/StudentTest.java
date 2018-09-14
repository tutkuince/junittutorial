package org.junit.junittutorial._02elementary._01example;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Sep 14, 2018
 */
public class StudentTest {

	@Test
	@DisplayName("Test every student must have an id, name and surname")
	void shouldCreateStudentWithIdNameAndSurname() {

		Student stdTutku = new Student("1", "Tutku", "Ince");

		assertEquals("Tutku", stdTutku.getName()); // "Tutku".equals(stdTutku.getName())
		assertEquals("Tutku", stdTutku.getName(), "Student's name");
		assertNotEquals("Tutku", stdTutku.getName(), "Student's name");

		assertTrue(stdTutku.getName().startsWith("T"));
		assertTrue(stdTutku.getName().startsWith("T"), () -> "Student's name " + "starts with T");
		assertFalse(() -> {
			Student stdEmin = new Student("id1", "Emin", "Koklu");
			return stdEmin.getName().endsWith("U");
		}, () -> "Student's name ends with U");

		final Student stdUgur = new Student("2", "Ugur", "Batikan");

		assertArrayEquals(new String[] { "Tutku", "Ugur" },
				Stream.of(stdTutku, stdUgur).map(Student::getName).toArray());

		Student student = stdTutku;

		assertSame(stdTutku, student); // stdTutku == student
		assertNotSame(stdUgur, student); // stdUgur != student

	}
}
