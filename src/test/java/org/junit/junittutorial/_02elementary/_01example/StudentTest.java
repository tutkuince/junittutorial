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

	/**
	 * grouped assertions failed grouped assertions dependent assertions
	 */
	@Test
	@DisplayName("Test every student must have an id, name and surname with grouped assertions")
	void shoudCreateStudentWithIdNameAndSurnameWithGroupedAssertions() {

		// In a grouped assertions all assertions are executed,
		Student stdTutku = new Student("1", "Tutku", "Ince");

		assertAll("Student's name check", () -> assertEquals("Tutku", stdTutku.getName()),
				() -> assertEquals("Tutku", stdTutku.getName(), "Student's name"),
				() -> assertNotEquals("Tutku", stdTutku.getName(), "Student's name"));

		// and any failures will be reported together,
		assertAll("Student's name character check", () -> assertTrue(stdTutku.getName().startsWith("T")),
				() -> assertTrue(stdTutku.getName().startsWith("T"), () -> "Student's name starts with T"),
				() -> assertFalse(() -> {
					Student stdEmin = new Student("id1", "Emin", "Koklu");
					return stdEmin.getName().endsWith("U");
				}, () -> "Student's name ends with U"));

		// dependent assertions
		assertAll(() -> {
			final Student stdUgur = new Student("2", "Ugur", "Batikan");

			assertArrayEquals(new String[] { "Tutku", "Ugur" },
					Stream.of(stdTutku, stdUgur).map(Student::getName).toArray());

		}, () -> {
			Student student = stdTutku;
			final Student stdUgur = new Student("2", "Ugur", "Batikan");

			assertSame(stdTutku, student);
			assertNotSame(student, stdUgur);
		});
	}

	@Test
	@DisplayName("Got an exception when add a null lecturer couse record to student")
	void throwsExceptionWhenAddToNullCourseToStudent() {
		final Student stdTutku = new Student("1", "Tutku", "Ince");

		assertThrows(IllegalArgumentException.class, () -> stdTutku.addCourse(null));
		assertThrows(IllegalArgumentException.class, () -> stdTutku.addCourse(null), "Throws IllegalArgumentExcepiton");
		final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
				() -> stdTutku.addCourse(null));
		assertEquals("Can't add course with null lecturer course record", illegalArgumentException.getMessage());
	}
}
