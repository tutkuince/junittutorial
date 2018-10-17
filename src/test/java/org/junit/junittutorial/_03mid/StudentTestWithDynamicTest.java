package org.junit.junittutorial._03mid;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.stream.Stream;

import org.junit.junittutorial._02elementary._04example.model.*;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class StudentTestWithDynamicTest {

	@TestFactory
	Stream<DynamicNode> addCourseToStudentWithCourseCodeAndCourseType() throws Exception {

		final Student student = new Student("1", "Tutku", "Ince");

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
}
