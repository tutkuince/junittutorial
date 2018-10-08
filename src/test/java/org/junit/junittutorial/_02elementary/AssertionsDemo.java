package org.junit.junittutorial._02elementary;



import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.junittutorial._02elementary._01example.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class AssertionsDemo {
	
	private Student student;
	
	@BeforeEach
	void init() {
		student = new Student("1", "Tutku", "Ince");
	}
	
	@AfterEach
	void tearDown() {
		student = null;
	}
	
	@Test
	void standardAssertions() {
		assertEquals(2, 2);
		assertEquals(4, 4, "The optional assertion message is now the last parameter.");
		assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
				+ "to avoid constructing complex messages unnecessarily.");
	}

	@Test
	void groupedAssertions() {
		// In a grouped assertion all assertions are executed, and any
		// failures will be reported together.
		assertAll("person", 
				() -> assertEquals("Tutku", student.getName()),
				() -> assertEquals("Ince", student.getSurname()));
	}

	@Test
	void dependentAssertions() {
		// Within a code block, if an assertion fails the
		// subsequent code in the same block will be skipped.
		assertAll("properties", 
				() -> {
			String firstName = student.getName();
			assertNotNull(firstName);

			// Executed only if the previous assertion is valid.
			assertAll("first name", 
					() -> assertTrue(firstName.startsWith("T")),
					() -> assertTrue(firstName.endsWith("u")));
		}, () -> {
			// Grouped assertion, so processed independently
			// of results of first name assertions.
			String lastName = student.getSurname();
			assertNotNull(lastName);

			// Executed only if the previous assertion is valid.
			assertAll("last name", 
					() -> assertTrue(lastName.startsWith("I")),
					() -> assertTrue(lastName.endsWith("e")));
		});
	}

	@Test
	void exceptionTesting() {
		Throwable exception = assertThrows(IllegalArgumentException.class, 
				() -> { 
					throw new IllegalArgumentException("a message");
		});
		assertEquals("a message", exception.getMessage());
	}

	@Test
	void timeoutNotExceeded() {
		// The following assertion succeeds.
		assertTimeout(ofMinutes(2), () -> {
			// Perform task that takes less than 2 minutes.
		});
	}

	@Test
	void timeoutNotExceededWithResult() {
		// The following assertion succeeds, and returns the supplied object.
		String actualResult = assertTimeout(ofMinutes(2), () -> {
			return "a result";
		});
		assertEquals("a result", actualResult);
	}

	@Test
	void timeoutNotExceededWithMethod() {
		// The following assertion invokes a method reference and returns an object.
		String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemo::greeting);
		assertEquals("Hello, World!", actualGreeting);
	}

	@Test
	void timeoutExceeded() {
		// The following assertion fails with an error message similar to:
		// execution exceeded timeout of 10 ms by 91 ms
		assertTimeout(ofMillis(10), () -> {
			// Simulate task that takes more than 10 ms.
			Thread.sleep(100);
		});
	}

	@Test
	void timeoutExceededWithPreemptiveTermination() {
		// The following assertion fails with an error message similar to:
		// execution timed out after 10 ms
		assertTimeoutPreemptively(ofMillis(10), () -> {
			// Simulate task that takes more than 10 ms.
			Thread.sleep(100);
		});
	}

	private static String greeting() {
		return "Hello, World!";
	}

}
