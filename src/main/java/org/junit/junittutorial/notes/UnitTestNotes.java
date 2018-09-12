package org.junit.junittutorial.notes;

/**
 * @author tutku
 *
 * @since Sep 12, 2018
 */
public class UnitTestNotes {
	/**
	 * 
	 * given - an object under test initialization + stubs/mocks creation, stubbing and injection 
	 * when - an operation to test in a given test 
	 * then - received result assertion + mocks verification (if needed)
	 * clear - 
	 * 
	 * 
	 * A test is not a unit test if:
	 * 	1) It talks to the database
	 * 	2) It communicates across the network
	 * 	3) It touches the file system
	 * 	4) It can't run correctly at the same time as any of your other unit tests
	 * 	5) You have to do special things to your environment (such as editing config files) to run it.
	 * 
	 * Tests that do things things aren't bad. Often they are worth writing,
	 * and they can be written in a unit test harness. However, it is
	 * important to be able to separate them from true unit tests so that we
	 * can keep a set of tests that we can run fast whenever we make our changes.
	 * 
	 *  Michael Feathers
	 */
}
