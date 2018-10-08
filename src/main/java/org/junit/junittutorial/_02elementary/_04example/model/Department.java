package org.junit.junittutorial._02elementary._04example.model;

import java.util.Set;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
@SuppressWarnings("unused")
public class Department {
	private String code;
	private String name;

	private Set<Lecturer> lecturers;
	private Set<Course> courses;
	private Set<Student> students;
	private Faculty faculty;

	@Override
	public String toString() {
		return code + ":" + name;
	}
}
