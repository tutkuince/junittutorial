package org.junit.junittutorial._04advance;

import java.util.logging.Logger;

import org.junit.junittutorial._02elementary._04example.model.Student;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class DropCourseTestInstancePostProcessor implements TestInstancePostProcessor {

	private static Logger logger = Logger.getLogger(DropCourseTestInstancePostProcessor.class.getName());
	
	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
		
		final StudentTestWithTestInstancePostProcessor.DropCourseFromStudent dropCourseFromStudent = (StudentTestWithTestInstancePostProcessor.DropCourseFromStudent) testInstance;
		dropCourseFromStudent.setStudent(new Student("1", "Tutku", "Ince"));
		logger.info("Student " + dropCourseFromStudent.getStudent().getName() + " is provided!");
	}

}
