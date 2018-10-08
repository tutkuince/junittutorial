package org.junit.junittutorial._02elementary._04example.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.Lecturer;
import org.junit.junittutorial._02elementary._04example.model.LecturerCourseRecord;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.junittutorial._02elementary._04example.model.StudentCourseRecord;
import org.junit.junittutorial._02elementary._04example.model.StudentRepository;
import org.junit.junittutorial._02elementary._04example.service.CourseService;
import org.junit.junittutorial._02elementary._04example.service.LecturerService;
import org.junit.junittutorial._02elementary._04example.service.StudentService;
import org.junit.junittutorial._02elementary._04example.service.TranscriptItem;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class StudentServiceImpl implements StudentService {

	private final CourseService courseService;
	private final LecturerService lecturerService;
	private final StudentRepository studentRepository;

	public StudentServiceImpl(CourseService courseService, LecturerService lecturerService,
			StudentRepository studentRepository) {
		this.courseService = courseService;
		this.lecturerService = lecturerService;
		this.studentRepository = studentRepository;
	}

	@Override
	public void addCourse(String studentId, Course course) {
		addCourse(studentId, course, new Semester());
	}

	@Override
	public void addCourse(String studentId, Course course, Semester semester) {

		final Student student = student(studentId);
		student.addCourse(lecturerCourseRecord(course, semester));
		studentRepository.save(student);
	}

	private LecturerCourseRecord lecturerCourseRecord(Course course, Semester semester) {
		final Course courseFromRepo = course(course);
		final Lecturer lecturer = lecturer(courseFromRepo, semester);
		return lecturer.lecturerCourseRecord(courseFromRepo, semester);
	}

	private Lecturer lecturer(Course course, Semester semester) {
		return lecturerService.findLecturer(course, semester).orElseThrow(
				() -> new IllegalArgumentException(String.format("Can't find a lecturer with info<%s>", course)));
	}

	private Course course(Course course) {
		return courseService.findCourse(course).orElseThrow(
				() -> new IllegalArgumentException(String.format("Can't find a course with info<%s>", course)));
	}

	private Student student(String studentId) {
		return studentRepository.findById(studentId).orElseThrow(
				() -> new IllegalArgumentException(String.format("Can't find a student with id<%s>", studentId)));
	}

	@Override
	public void dropCourse(String studentId, Course course) {

		final Student student = student(studentId);
		student.dropCourse(lecturerCourseRecord(course, new Semester()));
		studentRepository.save(student);
	}

	@Override
	public void addGrade(String studentId, Course course, StudentCourseRecord.Grade grade) {
		final Student student = student(studentId);
		student.addGrade(lecturerCourseRecord(course, new Semester()), grade);
		studentRepository.save(student);
	}

	@Override
	public boolean isTakeCourse(String studentId, Course course) {
		return false;
	}

	@Override
	public BigDecimal gpa(String studentId) {
		return student(studentId).gpa();
	}

	@Override
	public List<TranscriptItem> transcript(String studentId) {
		final Student student = student(studentId);
		return student.getStudentCourseRecords().stream()
				.map(studentCourseRecord -> TranscriptItem.newTranscriptItem()
						.withCourse(studentCourseRecord.getLecturerCourseRecord().getCourse())
						.withCredit(studentCourseRecord.getLecturerCourseRecord().getCredit())
						.withSemester(studentCourseRecord.getLecturerCourseRecord().getSemester())
						.withGrade(studentCourseRecord.getGrade()).transcriptItem())
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Student> findStudent(String studentId) {
		return studentRepository.findById(studentId);
	}

	@Override
	public void deleteStudent(String studentId) {
		final Student student = student(studentId);
		studentRepository.delete(student);
	}

}
