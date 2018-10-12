package org.junit.junittutorial._03mid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.stream.Stream;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.LecturerCourseRecord;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.model.Student;
import static org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
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
		
		@ParameterizedTest
		@EnumSource(value = Course.CourseType.class, names = "MANDATORY")
		void addMandatoryCourseToStudent(Course.CourseType courseType) {
			final Course course = Course.newCourse().withCode(String.valueOf(new Random().nextInt(200)))
					.withCourseType(courseType).course();

			final LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
			student.addCourse(courseRecord);
			assertFalse(student.getStudentCourseRecords().isEmpty());
			assertTrue(student.isTakeCourse(course));

		}
		
		@ParameterizedTest
		@EnumSource(value = Course.CourseType.class, mode = EnumSource.Mode.EXCLUDE, names = "MANDATORY")
		void addElectiveCourseToStudent(Course.CourseType courseType) {
			final Course course = Course.newCourse().withCode(String.valueOf(new Random().nextInt(200)))
					.withCourseType(courseType).course();

			final LecturerCourseRecord courseRecord = new LecturerCourseRecord(course, new Semester());
			student.addCourse(courseRecord);
			assertFalse(student.getStudentCourseRecords().isEmpty());
			assertTrue(student.isTakeCourse(course));

		}
	}
	
	@TestInstance(Lifecycle.PER_CLASS)
	@Nested
	class MethodSourceDemo {

		private int studentCourseSize = 0;

		@BeforeAll
		void setUp() {
			student = new Student("1", "Tutku", "Ince");
		}

		@ParameterizedTest
		@MethodSource
		void addCourseToStudent(String courseCode) {

			final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode),
					new Semester());
			student.addCourse(lecturerCourseRecord);
			studentCourseSize++;
			assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
			assertTrue(student.isTakeCourse(new Course(courseCode)));
		}

		// Factory Method
		Stream<String> addCourseToStudent() {
			return Stream.of("101", "103", "105");
		}
		
		@ParameterizedTest
		@MethodSource("courseWithCodeAndType")
		void addCourseToStudent(String courseCode, Course.CourseType courseType) {

			final Course course = new Course(courseCode);
			course.setCourseType(courseType);
			
			final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode),
					new Semester());
			student.addCourse(lecturerCourseRecord);
			studentCourseSize++;
			assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
			assertTrue(student.isTakeCourse(new Course(courseCode)));
			assumingThat(courseCode.equals("101"),
					() -> assertEquals(Course.CourseType.MANDATORY, courseType));
			assumingThat(courseCode.equals("103"),
					() -> assertEquals(Course.CourseType.ELECTIVE, courseType));
			assumingThat(courseCode.equals("105"),
					() -> assertEquals(Course.CourseType.MANDATORY, courseType));
		}
		
		// Factory Method
		Stream<Arguments> courseWithCodeAndType(){
			return Stream
					.of(
						Arguments.of("101", Course.CourseType.MANDATORY),
						Arguments.of("103", Course.CourseType.ELECTIVE),
						Arguments.of("105", Course.CourseType.MANDATORY));
		}
	}

	@TestInstance(Lifecycle.PER_CLASS)
	@Nested
	class CsvSourceDemo {
		
		private int studentCourseSize = 0;

		@BeforeAll
		void setUp() {
			student = new Student("1", "Tutku", "Ince");
		}

		@ParameterizedTest
		@CsvSource({"101,MANDATORY", "103,ELECTIVE", "105,MANDATORY"})
		void addCourseToStudent(String courseCode, Course.CourseType courseType) {

			final Course course = new Course(courseCode);
			course.setCourseType(courseType);
			
			final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode),
					new Semester());
			student.addCourse(lecturerCourseRecord);
			studentCourseSize++;
			assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
			assertTrue(student.isTakeCourse(new Course(courseCode)));
			assumingThat(courseCode.equals("101"),
					() -> assertEquals(Course.CourseType.MANDATORY, courseType));
			assumingThat(courseCode.equals("103"),
					() -> assertEquals(Course.CourseType.ELECTIVE, courseType));
			assumingThat(courseCode.equals("105"),
					() -> assertEquals(Course.CourseType.MANDATORY, courseType));
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/courseCodeAndTypes.csv", numLinesToSkip = 1)
		void addCourseToStudentWithCsvFile(String courseCode, Course.CourseType courseType) {

			final Course course = new Course(courseCode);
			course.setCourseType(courseType);
			
			final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode),
					new Semester());
			student.addCourse(lecturerCourseRecord);
			studentCourseSize++;
			assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
			assertTrue(student.isTakeCourse(new Course(courseCode)));
			assumingThat(courseCode.equals("101"),
					() -> assertEquals(Course.CourseType.MANDATORY, courseType));
			assumingThat(courseCode.equals("103"),
					() -> assertEquals(Course.CourseType.ELECTIVE, courseType));
			assumingThat(courseCode.equals("105"),
					() -> assertEquals(Course.CourseType.MANDATORY, courseType));
		}
	}

	@TestInstance(Lifecycle.PER_CLASS)
	@Nested
	class ArgumentsSourceDemo {
		private int studentCourseSize = 0;

		@BeforeAll
		void setUp() {
			student = new Student("1", "Tutku", "Ince");
		}

		@ParameterizedTest
		@ArgumentsSource(CourseCodeAndTypeProvider.class)
		void addCourseToStudent(String courseCode, Course.CourseType courseType) {

			final Course course = new Course(courseCode);
			course.setCourseType(courseType);
			
			final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode),
					new Semester());
			student.addCourse(lecturerCourseRecord);
			studentCourseSize++;
			assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
			assertTrue(student.isTakeCourse(new Course(courseCode)));
			assumingThat(courseCode.equals("101"),
					() -> assertEquals(Course.CourseType.MANDATORY, courseType));
			assumingThat(courseCode.equals("103"),
					() -> assertEquals(Course.CourseType.ELECTIVE, courseType));
			assumingThat(courseCode.equals("105"),
					() -> assertEquals(Course.CourseType.MANDATORY, courseType));
		}
	}
	
	static class CourseCodeAndTypeProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
			return Stream.of(
					Arguments.of("101", Course.CourseType.MANDATORY),
					Arguments.of("103", Course.CourseType.ELECTIVE),
					Arguments.of("105", Course.CourseType.MANDATORY)
					);
		}
		
	}
}
