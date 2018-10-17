package org.junit.junittutorial._04advance;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class DropCourseTestInstancePostProcessor implements TestInstancePostProcessor {

	private static Logger logger = Logger.getLogger(DropCourseTestInstancePostProcessor.class.getName());
	
	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
		
		final StudentTestWithTestInstancePostProcessor.DropCourseFromStudent dropCourseFromStudent = (StudentTestWithTestInstancePostProcessor.DropCourseFromStudent) testInstance;
		dropCourseFromStudent.setStudent(new Student("1", "Tutku", "Ince"));
		logger.info("Student " + dropCourseFromStudent.getStudent().getName() + " is provided!");
		dropCourseFromStudent.setAddDropPeriodCloseSemester(addDropPeriodCloseSemester());
		dropCourseFromStudent.setAddDropPeriodOpenSemester(addDropPeriodOpenSemester());
		dropCourseFromStudent.setNotActiveSemester(notActiveSemester());
	}
	
	private Semester addDropPeriodOpenSemester() {
		final Semester activeSemester = new Semester();
		final LocalDate semesterDate = LocalDate.of(activeSemester.getYear(), activeSemester.getTerm().getStartMonth(),
				1);
		final LocalDate now = LocalDate.now();
		activeSemester.setAddDropPeriodInWeek(Long.valueOf(semesterDate.until(now, ChronoUnit.WEEKS) + 1).intValue());
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
