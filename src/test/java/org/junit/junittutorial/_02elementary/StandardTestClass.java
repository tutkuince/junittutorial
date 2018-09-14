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
 * 
 * This methods must be;
 * 	+ void
 * 	+ package accessible
 * 	+ if method is private, JUnit ignore this methods
 * 
 * 
 */
public class StandardTestClass {

	private static String oneInstancePerClass;
	private Integer oneInstancePreMethod;

	@BeforeAll	// is static as a default. Execute just once
	static void initAll() {
		oneInstancePerClass = String.valueOf(new Random().nextInt());
		System.out.println("init Before All Test Method");
	}

	@AfterAll	// is static as a default. Execute just once
	static void tearDownAll() {
		oneInstancePerClass = null;
		System.out.println("Tear Down After All Test Method");
	}

	@BeforeEach	// new instance
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
	@Disabled("This is not in scope for now")	// ignore this case
	void testSomething3() {
		System.out.println("Test: testSomething3");
	}

	@Test
	void testSomething4() {
		Assertions.fail("A falling test. . . ");	// mark as a fail
	}
}
