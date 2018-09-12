package org.junit.junittutorial._01unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author tutku
 *
 * @since Sep 12, 2018
 */
class FizzBuzzTest {
	
	private FizzBuzz fizzBuzz;
	
	@BeforeEach
	void setUp() {
		fizzBuzz = new FizzBuzz();
	}

	@Test
	void returnFizzWhenTheNumberIsDividedByThree() {
		assertEquals("Fizz", fizzBuzz.stringFor(3));
	}

	@Test
	void returnBuzzWhenTheNumberIsDividedByFive() {

	}

}
