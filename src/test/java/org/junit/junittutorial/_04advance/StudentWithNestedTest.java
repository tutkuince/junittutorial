package org.junit.junittutorial._04advance;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.junittutorial._02elementary._01example.Student;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Tutku Ince
 *
 * @since Sep 14, 2018
 */
// @Disabled("No more valid tests")
@ExtendWith(TestLoggerExtension.class)
@Tag("student")
public class StudentWithNestedTest {

	@Nested
	@DisplayName("Create Student")
	class CreateStudent {
		@Test
		@DisplayName("Test every student must have an id, name and surname")
		@Tag("createStudent")
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
		@Tag("createStudent")
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
		@DisplayName("Test student creation at different environments")
		@Tag("createStudent")
		void shouldCreateStudentWithNameAndSurnameWithSpecificEnvironment() {
			final Student stdTutku = new Student("1", "Tutku", "Ince");

			final String env = System.getenv("ENV");

			assumingThat(env != null && env.equals("dev"), () -> {
				stdTutku.addCourse(new Object());
				assertEquals(1, stdTutku.getStudentCourseRecords().size());
			});

			assertAll("Student Information", () -> assertEquals("Tutku", stdTutku.getName()),
					() -> assertEquals("Ince", stdTutku.getSurname()), () -> assertEquals("1", stdTutku.getId()));
		}

		@Test
		@Disabled("No mor valid scenario")
		@DisplayName("Test that student must have only number id")
		@Tag("createStudent")
		void shouldCreateStudentWithNumberId() {
			assertThrows(IllegalArgumentException.class, () -> new Student("id", "Tutku", "Ince"));
		}
	}

	@Nested
	@DisplayName("Add Course to Student")
	class AddCourseToStudent {
		/**
		 * timeoutNotExceeded timeoutNotExceededWithResult timeoutNotExceededWithMethod
		 * timeoutExceeded timeoutExceededWithPreemptiveTermination
		 */
		@Test
		@DisplayName("Add course to a student less than 10ms")
		@Tag("addCourse")
		void addCourseToStudentWithTimeConstraint() {
			assertTimeout(Duration.ofMillis(10), () -> {
				// nothing will be done and this code run under 10ms
			});

			final String result = assertTimeout(Duration.ofMillis(10), () -> {
				// return a string and this code run under 10ms
				return "some string result";
			});
			assertEquals("some string result", result);

			final Student student = new Student("1", "Tutku", "Ince");
			assertTimeout(Duration.ofMillis(6), () -> student.addCourse(new Object()));

			assertTimeoutPreemptively(Duration.ofMillis(6), () -> student.addCourse(new Object()));
		}

		@Nested
		@DisplayName("Add Course to Student(Exception)")
		class AddCourseToStudentExceptionScenario {
			@Test
			@DisplayName("Got an exception when add a null lecturer couse record to student")
			@Tags({ @Tag("addCourse"), @Tag("exceptional") })
			void throwsExceptionWhenAddToNullCourseToStudent() {
				final Student stdTutku = new Student("1", "Tutku", "Ince");

				assertThrows(IllegalArgumentException.class, () -> stdTutku.addCourse(null));
				assertThrows(IllegalArgumentException.class, () -> stdTutku.addCourse(null),
						"Throws IllegalArgumentExcepiton");
				final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
						() -> stdTutku.addCourse(null));
				assertEquals("Can't add course with null lecturer course record",
						illegalArgumentException.getMessage());
			}
		}
	}

}
