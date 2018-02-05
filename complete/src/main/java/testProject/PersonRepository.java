package testProject;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/*
 *  Interface that extends Spring Boot JPA repository
 *  It uses Person entity and predefined access methods to add, update, query and delete Person records in
 *  the embedded in-memory HSQL database. 
 */

public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findByLastName(@Param("name") String lastName);

}