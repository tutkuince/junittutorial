package org.junit.junittutorial._03mid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.LecturerCourseRecord;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Tutku Ince
 *
 * @since Oct 12, 2018
 */
public class StudentTestWithParameterizedMethods {

	private Student student;

	@TestInstance(Lifecycle.PER_CLASS)
	@Nested
	class ValueSourceDemo {
		
		private int studentCourseSize = 0;

		@BeforeAll
		void setUp() {
			student = new Student("1", "Tutku", "Ince");
		}

		@ParameterizedTest
		@ValueSource(strings = { "101", "103", "105" })	// run more than 1 times. There are 3 parameters in @ValueSource so it runs 3 times.
		void addCourseToStudent(String courseCode) {
			
			final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode), new Semester());
			student.addCourse(lecturerCourseRecord);
			studentCourseSize++;
			assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
			assertTrue(student.isTakeCourse(new Course(courseCode)));
		}

	}
}
