package org.junit.junittutorial._04advance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.LecturerCourseRecord;
import org.junit.junittutorial._02elementary._04example.model.NotActiveSemesterException;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.junittutorial._02elementary._04example.model.StudentCourseRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class StudentTestWithTestInstancePostProcessor {
	
	@ExtendWith(DropCourseTestInstancePostProcessor.class)
	@Nested
	@DisplayName("Drop Course from Student")
	@Tag("dropCourse")
	class DropCourseFromStudent {
		
		// throws illegal argument exception for null lecturer course record
		// throws illegal argument exception if the student did't register course before
		// throws not active semester exception if the semester is not active
		// throws not active semester exception if the add drop period is closed for the semester
		// drop course from student
		
		private Student student;
		private Semester addDropPeriodOpenSemester;
		private Semester addDropPeriodCloseSemester;
		private Semester notActiveSemester;
		
		public Semester getAddDropPeriodOpenSemester() {
			return addDropPeriodOpenSemester;
		}

		public void setAddDropPeriodOpenSemester(Semester addDropPeriodOpenSemester) {
			this.addDropPeriodOpenSemester = addDropPeriodOpenSemester;
		}

		public Semester getAddDropPeriodCloseSemester() {
			return addDropPeriodCloseSemester;
		}

		public void setAddDropPeriodCloseSemester(Semester addDropPeriodCloseSemester) {
			this.addDropPeriodCloseSemester = addDropPeriodCloseSemester;
		}

		public Semester getNotActiveSemester() {
			return notActiveSemester;
		}

		public void setNotActiveSemester(Semester notActiveSemester) {
			this.notActiveSemester = notActiveSemester;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		@TestFactory
		Stream<DynamicTest> dropCourseFromStudentFactory() throws Exception {

			

			return Stream.of(
				dynamicTest("throws illegal argument exception for null lecturer course record", () -> {
					assertThrows(IllegalArgumentException.class, () -> student.dropCourse(null));
			}), dynamicTest("throws illegal argument exception if the student did't register course before", () -> {
					final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), new Semester());
					assertThrows(IllegalArgumentException.class, () -> student.dropCourse(lecturerCourseRecord));
			}), dynamicTest("throws not active semester exception if the semester is not active", () -> {
					final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), notActiveSemester);
					Student stdUgur = new Student("1", "Ugur", "Batikan", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
					assertThrows(NotActiveSemesterException.class, () -> stdUgur.dropCourse(lecturerCourseRecord));
			}), dynamicTest("throws not active semester exception if the add drop period is closed for the", () -> {
					final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodCloseSemester);
					Student stdUgur = new Student("1", "Ugur", "Batikan", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
					assertThrows(NotActiveSemesterException.class, () -> stdUgur.dropCourse(lecturerCourseRecord));
			}), dynamicTest("drop course from student", () -> {
					final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodOpenSemester);
					Student stdUgur = new Student("1", "Ugur", "Batikan", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
					assertEquals(1, stdUgur.getStudentCourseRecords().size());
					stdUgur.dropCourse(lecturerCourseRecord);
					assertTrue(stdUgur.getStudentCourseRecords().isEmpty());
			})
					
					);
		}
	}
}
