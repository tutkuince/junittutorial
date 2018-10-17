package org.junit.junittutorial._04advance;

import java.util.logging.Logger;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class TestLoggerExtension implements BeforeAllCallback, AfterAllCallback {

	private static Logger log = Logger.getLogger(TestLoggerExtension.class.getName());
	
	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		log.info(String.format("Test is ended . . .%s", context.getDisplayName()));		
	}

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		log.info(String.format("Test is started . . .%s", context.getDisplayName()));
	}

}
