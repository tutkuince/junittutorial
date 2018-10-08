package org.junit.junittutorial._02elementary._04example;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.Lecturer;
import org.junit.junittutorial._02elementary._04example.model.LecturerCourseRecord;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class LecturerTest {
	/**
	 * When a lecturer course record is added to lecturer then lecturer course
	 * record size increase Lecturer course record has lecturer when added to
	 * lecturer Throws illegal argument exception when a null course is add to
	 * lecturer Throws not active semester exception when a course is added for not
	 * active semester
	 * 
	 */
	private Lecturer lecturer;

	@BeforeEach
	void setUp() {
		lecturer = new Lecturer();
	}

	private LecturerCourseRecord lecturerCourseRecord() {
		return new LecturerCourseRecord(new Course(), new Semester());
	}

	@Test
	@DisplayName("When a lecturer course record is added to lecturer then lecturer course record size increase")
	void whenACourseIsAddedToLecturerThenLecturerCourseSizeIncrease() throws Exception {

		assertEquals(0, lecturer.getLecturerCourseRecords().size());
		lecturer.addLecturerCourseRecord(lecturerCourseRecord());
		assertEquals(1, lecturer.getLecturerCourseRecords().size());
	}

	@Test
	@DisplayName("Record size increase Lecturer course record has lecturer when added to")
	void lecturerCourseRecordHasLecturerInfoWhenAddedToALecturer() throws Exception {
		final LecturerCourseRecord lecturerCourseRecord = lecturerCourseRecord();
		lecturer.addLecturerCourseRecord(lecturerCourseRecord);

		assertEquals(lecturer, lecturerCourseRecord.getLecturer());
	}

	@Test
	@DisplayName("Lecturer Throws illegal argument exception when a null course is add to")
	void throwsIllegalArgumentExceptionWhenANullCourseIsAddedToLecturer() throws Exception {
		final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(null, new Semester());
		final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
				() -> lecturer.addLecturerCourseRecord(lecturerCourseRecord));
		assertEquals("Can't add a null course to lecturer", illegalArgumentException.getMessage());
	}
}
