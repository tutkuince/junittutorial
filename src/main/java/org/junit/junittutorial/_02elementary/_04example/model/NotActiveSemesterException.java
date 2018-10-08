package org.junit.junittutorial._02elementary._04example.model;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class NotActiveSemesterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotActiveSemesterException(String message) {
		super(message);
	}
}
