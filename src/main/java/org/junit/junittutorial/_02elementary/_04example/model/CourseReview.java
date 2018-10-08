package org.junit.junittutorial._02elementary._04example.model;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
@SuppressWarnings("unused")
public class CourseReview {

	private CourseRate courseRate;
	private String comments;
	private StudentCourseRecord studentCourseRecord;

	public enum CourseRate {
		ONE, TWO, THREE, FOUR, FIVE
	}
}
