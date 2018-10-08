package org.junit.junittutorial._02elementary._04example.model;

import java.util.Optional;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public interface StudentRepository {

	Optional<Student> findById(String id);

	Student save(Student student);

	void delete(Student student);

}
