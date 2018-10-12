package org.junit.junittutorial._03mid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * @author Tutku Ince
 *
 * @since Oct 12, 2018
 */
@DisplayName("Student Test with TestInfo and TestReporter Parameters")
public class JUnitParameterizedStudentTest {
	
	private Student student;
	
	// for class
	public JUnitParameterizedStudentTest(TestInfo testInfo) {
		assertEquals("Student Test with TestInfo and TestReporter Parameters", testInfo.getDisplayName());
	}

	@BeforeEach
	void setStudent(TestInfo testInfo) {
		
		if(testInfo.getTags().contains("create"))
			student = new Student("1", "Tutku", "Ince");
		else
			student = new Student("2", "Ugur", "Batikan");

	}
	
	@Test
	@DisplayName("Create Student")
	@Tag("create")
	void createStudent() throws Exception {
		assertEquals("Tutku", student.getName());
	}
	
	@Test
	@DisplayName("Add Course to Student")
	@Tag("addCourse")
	void addCourseToStudent() throws Exception {
		assertEquals("Ugur", student.getName());
	}
}
