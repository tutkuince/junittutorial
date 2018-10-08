package org.junit.junittutorial._02elementary;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
// @Disabled -> Entire test classes or individual test methods may be disabled via the @Disabled annotation on class name
public class DisabledTestsDemo {

	@Disabled	// test class that contains a @Disabled test method.
	@Test
	void testWillBeSkipped() {
	}

	@Test
	void testWillBeExecuted() {
	}
}
