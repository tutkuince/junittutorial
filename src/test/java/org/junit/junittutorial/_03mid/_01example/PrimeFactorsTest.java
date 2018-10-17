package org.junit.junittutorial._03mid._01example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.junittutorial._03mid.PrimeFactors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
@TestInstance(Lifecycle.PER_CLASS)
public class PrimeFactorsTest {

	private Map<Integer, List<Integer>> primeFactorExpectations = new HashMap<>();

	@BeforeAll
	void setUp() {
		primeFactorExpectations.put(1, Collections.emptyList());
		primeFactorExpectations.put(2, List.of(2));
		primeFactorExpectations.put(3, List.of(3));
		primeFactorExpectations.put(4, List.of(2, 2));
		primeFactorExpectations.put(5, List.of(5));
		primeFactorExpectations.put(6, List.of(2, 3));
		primeFactorExpectations.put(7, List.of(7));
		primeFactorExpectations.put(8, List.of(2, 2, 2));
		primeFactorExpectations.put(9, List.of(3, 3));
	}

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

	@RepeatedTest(9)
	void generateWithRepeatedTest(RepetitionInfo repetitionInfo) throws Exception {
		assertEquals(primeFactorExpectations.get(repetitionInfo.getCurrentRepetition()),
				PrimeFactors.generate(repetitionInfo.getCurrentRepetition()));
	}

	@ParameterizedTest(name = "Generate Prime Factor for {arguments}")
	@ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8, 9 })
	void generateWithParameterizedTest(Integer number) throws Exception {
		assertEquals(primeFactorExpectations.get(number), PrimeFactors.generate(number));
	}
}
