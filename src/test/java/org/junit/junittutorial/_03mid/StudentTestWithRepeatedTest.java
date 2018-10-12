package org.junit.junittutorial._03mid;

import static org.junit.Assert.*;
import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.LecturerCourseRecord;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author Tutku Ince
 *
 * @since Oct 12, 2018
 */
@TestInstance(Lifecycle.PER_CLASS)
public class StudentTestWithRepeatedTest implements TestLifecycleReporter {
	private Student student;

	@BeforeAll
	void setUp() {
		student = new Student("1", "Tutku", "Ince");
	}

	@DisplayName("Add Course to Student ")
	@RepeatedTest(value = 5, name = "{displayName} => Add one course to student and student has {currentRepetition} courses")
	void addCourseToStudent(RepetitionInfo repetitionInfo) {
		final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(
				new Course(String.valueOf(repetitionInfo.getCurrentRepetition())), new Semester());
		student.addCourse(lecturerCourseRecord);

		assertEquals(repetitionInfo.getCurrentRepetition(), student.getStudentCourseRecords().size());
	}
}
