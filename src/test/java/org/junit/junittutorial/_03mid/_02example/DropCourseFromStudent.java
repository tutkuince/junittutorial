package org.junit.junittutorial._03mid._02example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.junittutorial._02elementary._04example.model.Semester;

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
