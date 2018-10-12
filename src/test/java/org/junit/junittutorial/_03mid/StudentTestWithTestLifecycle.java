package org.junit.junittutorial._03mid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author Tutku Ince
 *
 * @since Oct 12, 2018
 */
@TestInstance(Lifecycle.PER_CLASS)
public class StudentTestWithTestLifecycle {
	
	private Student stdTutku = new Student("1", "Tutku", "Ince");
	
	@BeforeAll
	void setUp() {
		
	}
	
	@Test
	void stateCannotChangeWhenLifecycleIsPerMethod() throws Exception {
		assertEquals("Tutku", stdTutku.getName());
		stdTutku = new Student("2", "Emin", "Koklu");
	}
	
	@Test
	void stateCannotChangeWhenLifecycleIsPerClass() throws Exception {
		assertEquals("Tutku", stdTutku.getName());
		stdTutku = new Student("2", "Emin", "Koklu");
	}
}
