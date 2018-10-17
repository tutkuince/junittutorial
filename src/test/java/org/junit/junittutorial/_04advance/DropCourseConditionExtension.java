package org.junit.junittutorial._04advance;

import java.util.List;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author Tutku Ince
 *
 * @since Oct 17, 2018
 */
public class DropCourseConditionExtension implements ExecutionCondition {

	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
		if (List.of("student").containsAll(context.getTags())
				|| List.of("student", "addCourse").containsAll(context.getTags()))
			return ConditionEvaluationResult.enabled("Add course is enabled!");
		return ConditionEvaluationResult.disabled("Only add course allowed to run");
	}

}
