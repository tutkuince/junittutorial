package org.junit.junittutorial._03mid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Oct 12, 2018
 */
public class StudentTestWithDefaultMethods implements CreateDomain<Student> {

	@Override
	public Student createDomain() {
		return new Student("1", "Tutku", "Ince");
	}
	
	@Test
	void createStudent() throws Exception {
		final Student student = createDomain();
		
		assertAll("Student", 
				() -> assertEquals("1", student.getId()),
				() -> assertEquals("Tutku", student.getName()),
				() -> assertEquals("Ince", student.getSurname())
				);
	}

}
