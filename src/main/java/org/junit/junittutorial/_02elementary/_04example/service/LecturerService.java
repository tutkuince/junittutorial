package org.junit.junittutorial._02elementary._04example.service;

import java.util.Optional;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.Lecturer;
import org.junit.junittutorial._02elementary._04example.model.Semester;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public interface LecturerService {
	Optional<Lecturer> findLecturer(Course course, Semester semester);
}
