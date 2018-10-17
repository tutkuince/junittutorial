package org.junit.junittutorial._03mid;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.junittutorial._02elementary._04example.model.*;
import org.junit.junittutorial._02elementary._04example.model.Course.CourseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class StudentTestWithDynamicTest {
	
	private Student student;
	
	@BeforeEach
	void setUp() {
		student = new Student("1", "Tutku", "Ince");
	}

	@TestFactory
	Stream<DynamicNode> addCourseToStudentWithCourseCodeAndCourseType() throws Exception {

		return Stream.of("101", "103", "105")
				.map(courseCode -> dynamicContainer("Add course<" + courseCode + "> to student",
						Stream.of(Course.CourseType.MANDATORY, Course.CourseType.ELECTIVE)
								.map(courseType -> dynamicTest("Add course<" + courseType + "> to student", () -> {

									final Course course = Course.newCourse().withCode(courseCode)
											.withCourseType(courseType).course();
									final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course,
											new Semester());
									student.addCourse(lecturerCourseRecord);
									assertTrue(student.isTakeCourse(course));
								}))));
	}

	@TestFactory
	Stream<DynamicTest> addCourseToStudentWithCourseCode() throws Exception {

		final Iterator<String> courseCodeGenerator = Stream.of("101", "103", "105").iterator();
		Function<String, String> displayNameGenerator = courseCode -> "Add course<" + courseCode + "> to student";
		ThrowingConsumer<String> testExecutor = courseCode -> {

			final Course course = Course.newCourse().withCode(courseCode).withCourseType(CourseType.MANDATORY).course();
			final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
			student.addCourse(lecturerCourseRecord);
			assertTrue(student.isTakeCourse(course));
		};
		
		return stream(courseCodeGenerator, displayNameGenerator, testExecutor);	
	}
}
