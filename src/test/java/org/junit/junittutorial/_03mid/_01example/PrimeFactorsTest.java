package org.junit.junittutorial._03mid._01example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.junittutorial._03mid.PrimeFactors;
import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class PrimeFactorsTest {

	@Test
	void generateWithStandartTest() throws Exception {
		assertEquals(Collections.emptyList(), PrimeFactors.generate(1));
		assertEquals(Arrays.asList(2), PrimeFactors.generate(2));
		assertEquals(List.of(3), PrimeFactors.generate(3));
		assertEquals(List.of(2, 2), PrimeFactors.generate(4));
		assertEquals(List.of(5), PrimeFactors.generate(5));
		assertEquals(List.of(2, 3), PrimeFactors.generate(6));
		assertEquals(List.of(7), PrimeFactors.generate(7));
		assertEquals(List.of(2, 2, 2), PrimeFactors.generate(8));
		assertEquals(List.of(3, 3), PrimeFactors.generate(9));
	}
}
