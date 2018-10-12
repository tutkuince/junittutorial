package org.junit.junittutorial._03mid;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * @author Tutku Ince
 *
 * @since Oct 12, 2018
 */
public interface CreateDomain<T> {
	
	T createDomain();
	
	@Test
	default void createDomainShouldBeImplemented() {
		assertNotNull(createDomain());
	}
}
