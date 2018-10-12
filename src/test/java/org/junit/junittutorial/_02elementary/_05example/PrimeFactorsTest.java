package org.junit.junittutorial._02elementary._05example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Oct 12, 2018
 */
public class PrimeFactorsTest {
	
	@Test
	@DisplayName("Find prime factors of number.")
	void findPrimeFactors() throws Exception {
		PrimeFactors primeFactors = new PrimeFactors();
		
		assertThrows(IllegalArgumentException.class, () -> primeFactors.generate(1), () -> "Number must be greater than 2");
		
		assertAll("Prime Numbers", 
				() -> assertEquals(Arrays.asList(2), primeFactors.generate(2)),
				() -> assertEquals(Arrays.asList(3), primeFactors.generate(3)),
				() -> assertEquals(Arrays.asList(2, 2, 3), primeFactors.generate(12)),
				() -> assertEquals(Arrays.asList(5), primeFactors.generate(5)),
				() -> assertEquals(Arrays.asList(2, 2, 5), primeFactors.generate(20))
				);
	}
}
