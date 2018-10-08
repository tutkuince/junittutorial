package org.junit.junittutorial._02elementary._04example.model;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class CourseBuilder {
	private final Course course;

	public CourseBuilder(Course course) {
		this.course = course;
	}

	public CourseBuilder withCode(String code) {
		this.course.setCode(code);
		return this;
	}

	public CourseBuilder withName(String name) {
		this.course.setName(name);
		return this;
	}

	public CourseBuilder withDescription(String description) {
		this.course.setDescription(description);
		return this;
	}

	public CourseBuilder withCourseType(Course.CourseType courseType) {
		this.course.setCourseType(courseType);
		return this;
	}

	public CourseBuilder withCredit(int credit) {
		this.course.setCredit(credit);
		return this;
	}

	public CourseBuilder withDepartment(Department department) {
		this.course.setDepartment(department);
		return this;
	}

	public Course course() {
		return this.course;
	}
}
