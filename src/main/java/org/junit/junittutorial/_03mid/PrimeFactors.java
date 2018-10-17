package org.junit.junittutorial._03mid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class PrimeFactors {

	public static List<Integer> generate(int i) {
		if (i <= 1)
			return Collections.emptyList();

		List<Integer> primeFactors = new ArrayList<>();
		for (int candidate = 2; i > 1; candidate++) {
			for (; i % candidate == 0; i /= candidate)
				primeFactors.add(candidate);
		}

		return primeFactors;
	}
}
