package org.junit.junittutorial._01unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author tutku
 *
 * @since Sep 12, 2018
 */
class FizzBuzzTest {

	@Test
	void returnFizzWhenTheNumberIsDividedByThree() {
		FizzBuzz fizzBuzz = new FizzBuzz();
		assertEquals("Fizz", fizzBuzz.stringFor(3));
	}

}
