package org.junit.junittutorial._02elementary._01example;

import java.util.List;

/**
 * @author Tutku Ince
 *
 * @since Sep 14, 2018
 */
public class Student {
	private final String id;
	private final String name;
	private final String surname;

	public Student(String id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void addCourse(Object lecturerCourseRecord) {
		if (lecturerCourseRecord == null)
			throw new IllegalArgumentException("Can't add course with null lecturer course record");
	}

	public List<Object> getStudentCourseRecords() {
		return null;
	}

}
