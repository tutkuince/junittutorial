package org.junit.junittutorial._02elementary._04example.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class Lecturer {
	private String id;
	private String name;
	private String surname;
	private String title;
	private Set<LecturerCourseRecord> lecturerCourseRecords = new HashSet<>();
	private Department department;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<LecturerCourseRecord> getLecturerCourseRecords() {
		return Collections.unmodifiableSet(lecturerCourseRecords);
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void addLecturerCourseRecord(LecturerCourseRecord lecturerCourseRecord) {

		lecturerCourseRecord.setLecturer(this);
	}

	public LecturerCourseRecord lecturerCourseRecord(Course course, Semester semester) {
		return lecturerCourseRecords.stream()
				.filter(lecturerCourseRecord -> lecturerCourseRecord.getCourse().equals(course)
						&& lecturerCourseRecord.getSemester().equals(semester))
				.findAny().orElseThrow(() -> new IllegalArgumentException(String.format(
						"Can't find lecturer course record for course<%s> and semester<%s>", course, semester)));
	}
}
