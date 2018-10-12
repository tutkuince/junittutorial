package org.junit.junittutorial._03mid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.LecturerCourseRecord;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
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

		@ParameterizedTest	// Parameterized tests make it possible to run a test multiple times with different arguments.
		//They are declared just like regular @Test methods but use the @ParameterizedTest annotation instead.
		@ValueSource(strings = { "101", "103", "105" })	// 3 times (101, 103, 105) 
		void addCourseToStudent(String courseCode) {
			
			final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode), new Semester());
			student.addCourse(lecturerCourseRecord);
			studentCourseSize++;
			assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
			assertTrue(student.isTakeCourse(new Course(courseCode)));
		}
	}
	
	@TestInstance(Lifecycle.PER_CLASS)
	@Nested
	class EnumSourceDemo {
		
		@BeforeAll
		void setUp() {
			student = new Student("1", "Tutku", "Ince");
		}

		@ParameterizedTest
		@EnumSource(Course.CourseType.class)
		void addCourseToStudent(Course.CourseType courseType) {
			final Course course = Course.newCourse().withCode(String.valueOf(new Random().nextInt(200)))
					.withCourseType(courseType).course();

			final LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
			student.addCourse(courseRecord);
			assertFalse(student.getStudentCourseRecords().isEmpty());
			assertTrue(student.isTakeCourse(course));

		}
	}
	
}
