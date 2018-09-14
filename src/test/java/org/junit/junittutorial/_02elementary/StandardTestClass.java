package org.junit.junittutorial._02elementary;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Sep 14, 2018
 */
public class StandardTestClass {

	private static String oneInstancePerClass;
	private Integer oneInstancePreMethod;

	@BeforeAll
	static void initAll() {
		oneInstancePerClass = String.valueOf(new Random().nextInt());
		System.out.println("init Before All Test Method");
	}

	@AfterAll
	static void tearDownAll() {
		oneInstancePerClass = null;
		System.out.println("Tear Down After All Test Method");
	}

	@BeforeEach
	void init() {
		oneInstancePreMethod = new Random().nextInt();
		System.out.println("Init Before Each Test Method");
	}

	@AfterEach
	void tearDown() {
		oneInstancePreMethod = null;
		System.out.println("Tear Dorwn After Each Test Method");
	}

	@Test
	void testSomething1() {
		System.out.println("Test: testSomething1:" + oneInstancePreMethod + ":" + oneInstancePerClass);
	}

	@Test
	void testSomething2() {
		System.out.println("Test: testSomething2:" + oneInstancePreMethod + ":" + oneInstancePerClass);
	}

	@Test
	@Disabled("This is not in scope for now")
	void testSomething3() {
		System.out.println("Test: testSomething3");
	}

	@Test
	void testSomething4() {
		Assertions.fail("A falling test. . . ");
	}
}
