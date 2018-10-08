package org.junit.junittutorial._02elementary._04example.service;

import java.util.Optional;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.Department;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public interface CourseService {
	Optional<Course> findCourse(Course course);

	Optional<Course> findCourse(Department department, String code, String name);
}
