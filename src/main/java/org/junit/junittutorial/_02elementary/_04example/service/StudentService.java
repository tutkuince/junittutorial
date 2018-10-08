package org.junit.junittutorial._02elementary._04example.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.junittutorial._02elementary._04example.model.StudentCourseRecord;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public interface StudentService {
	void addCourse(String studentId, Course course);

	void addCourse(String studentId, Course course, Semester semester);

	void dropCourse(String studentId, Course course);

	void addGrade(String studentId, Course course, StudentCourseRecord.Grade grade);

	boolean isTakeCourse(String studentId, Course course);

	BigDecimal gpa(String studentId);

	List<TranscriptItem> transcript(String studentId);

	Optional<Student> findStudent(String studentId);

	void deleteStudent(String studentId);
}
