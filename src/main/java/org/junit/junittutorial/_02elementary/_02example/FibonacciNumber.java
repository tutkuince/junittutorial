package org.junit.junittutorial._02elementary._02example;

/**
 * @author Tutku Ince
 *
 * @since Sep 26, 2018
 */
public class FibonacciNumber {

	public int find(int order) {
		if(order <= 0)
			throw new IllegalArgumentException();
		else if (order == 1 || order == 2)
			return 1;
		return find(order - 2) + find(order - 1);
	}

}
