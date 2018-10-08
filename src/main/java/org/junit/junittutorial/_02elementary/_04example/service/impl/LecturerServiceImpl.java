package org.junit.junittutorial._02elementary._04example.service.impl;

import java.util.Optional;

import org.junit.junittutorial._02elementary._04example.model.Course;
import org.junit.junittutorial._02elementary._04example.model.Lecturer;
import org.junit.junittutorial._02elementary._04example.model.LecturerRepository;
import org.junit.junittutorial._02elementary._04example.model.Semester;
import org.junit.junittutorial._02elementary._04example.service.LecturerService;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class LecturerServiceImpl implements LecturerService {

	private final LecturerRepository lecturerRepository;

	public LecturerServiceImpl(LecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
	}

	@Override
	public Optional<Lecturer> findLecturer(Course course, Semester semester) {
		if (course == null || semester == null) {
			throw new IllegalArgumentException("Can't find lecturer without course and semester");
		}
		return lecturerRepository.findByCourseAndSemester(course, semester);
	}
}
