package org.junit.junittutorial._02elementary._05example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tutku Ince
 *
 * @since Oct 12, 2018
 */
public class PrimeFactors {

	public List<Integer> generate(int number) {
		if (number < 2)
			throw new IllegalArgumentException();

		List<Integer> primeNumbers = new ArrayList<>();

		// Counter's initial value is 2, because Minimum prime number is 2. 
		int counter = 2;

		while (counter <= number) {
			if (number % counter == 0) {
				primeNumbers.add(counter);
				number /= counter;
			} else
				counter++;
		}
		return primeNumbers;
	}
}
