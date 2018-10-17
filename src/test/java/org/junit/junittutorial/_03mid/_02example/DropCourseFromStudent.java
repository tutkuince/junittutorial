package org.junit.junittutorial._03mid._02example;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.LecturerCourseRecord;
import org.junit.junittutorial._02elementary._04example.model.NotActiveSemesterException;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.junittutorial._02elementary._04example.model.StudentCourseRecord;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class DropCourseFromStudent {

	// throws illegal argument exception for null lecturer course record
	// throws illegal argument exception if the student did't register course before
	// throws not active semester exception if the semester is not active
	// throws not active semester exception if the add drop period is closed for the
	// semester
	// drop course from student

	@TestFactory
	Stream<DynamicTest> dropCourseFromStudentFactory() throws Exception {

		final Student student = new Student("1", "Tutku", "Ince");

		return Stream.of(
			dynamicTest("throws illegal argument exception for null lecturer course record", () -> {
				assertThrows(IllegalArgumentException.class, () -> student.dropCourse(null));
		}), dynamicTest("throws illegal argument exception if the student did't register course before", () -> {
				final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), new Semester());
				assertThrows(IllegalArgumentException.class, () -> student.dropCourse(lecturerCourseRecord));
		}), dynamicTest("throws not active semester exception if the semester is not active", () -> {
				final Semester notActiveSemester = notActiveSemester();
				Assumptions.assumeTrue(!notActiveSemester().isActive());
				final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), notActiveSemester);
				Student stdUgur = new Student("1", "Ugur", "Batikan", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
				assertThrows(NotActiveSemesterException.class, () -> stdUgur.dropCourse(lecturerCourseRecord));
		}), dynamicTest("throws not active semester exception if the add drop period is closed for the", () -> {
				final Semester addDropPeriodClosedSemester = addDropPeriodCloseSemester();
				Assumptions.assumeTrue(!addDropPeriodClosedSemester.isAddDropAllowed());
				final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodClosedSemester);
				Student stdUgur = new Student("1", "Ugur", "Batikan", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
				assertThrows(NotActiveSemesterException.class, () -> stdUgur.dropCourse(lecturerCourseRecord));
		})
				
				);
	}

	private Semester addDropPeriodOpenSemester() {
		final Semester activeSemester = new Semester();
		final LocalDate semesterDate = LocalDate.of(activeSemester.getYear(), activeSemester.getTerm().getStartMonth(),
				1);
		final LocalDate now = LocalDate.now();
		activeSemester.setAddDropPeriodInWeek(Long.valueOf(semesterDate.until(now, ChronoUnit.WEEKS)).intValue());
		return activeSemester;
	}

	private Semester addDropPeriodCloseSemester() {
		final Semester activeSemester = new Semester();
		activeSemester.setAddDropPeriodInWeek(0);
		if (LocalDate.now().getMonthValue() == 1)
			activeSemester.setAddDropPeriodInWeek(-1);
		return activeSemester;
	}

	private Semester notActiveSemester() {
		final Semester activeSemester = new Semester();
		return new Semester(LocalDate.of(activeSemester.getYear() - 1, 1, 1));
	}
}
