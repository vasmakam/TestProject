package testProject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonServiceTest {


	@Autowired
	private PersonRepository personRepository;



	private static final Logger log = LoggerFactory.getLogger(Application.class);

	// This will only test the data persistence layer and not the controller layer
	
	@Test
	public void findByNameTest() {
		// given
		Person alex = new Person("Alex", "Jones");
		Person rita = new Person("Rita", "Miller");
		Person shiv = new Person("Shiv", "Shankar");

		personRepository.save(alex);
		personRepository.save(rita);
		personRepository.save(shiv);

		// when
		List<Person> found = personRepository.findByLastName(alex.getLastName());
		log.info("Number of records found: "+found.size());
		assertThat(found.size()==1);     
		// then
		for(Person p : found) {
			assertThat(p.getFirstName())
			.isEqualTo(alex.getFirstName());
		}
	}

	
	@Test
	public void findAllPeople() {

		Person alex = new Person("Alex", "Jones");
		Person rita = new Person("Rita", "Miller");
		Person shiv = new Person("Shiv", "Shankar");

		personRepository.save(alex);
		personRepository.save(rita);
		personRepository.save(shiv);


		
		List<Person> found = personRepository.findAll();
		log.info("Number of records found: "+found.size());
		assertEquals(found.size(),3);
		
		personRepository.delete(alex);
		found = personRepository.findAll();
		log.info("Number of records found after delete: "+found.size());

		
		for(Person p : found) {
			log.info("Id: "+p.getId()+" Name: "+p.getFirstName()+" "+p.getLastName());    
		}
		assertEquals(found.size(),2);
	}
	
	/*
	 * This will test the controller layer for create, query by name, and delete operations
	 */
	@Test
	public void testDeletePerson() {
		PersonServiceController psc = new PersonServiceController();
		Person shiv = new Person("Shiv", "Shankar");
		psc.create(shiv);
		
		List<Person> found = psc.getByName(shiv.getLastName());
		log.info("Number of records found: "+found.size());
		assertEquals(found.size(),1);
		
		for(Person p : found) {
			if((shiv.getFirstName().equals(p.getFirstName()) && (shiv.getLastName().equals(p.getLastName())))) {
				String result = psc.delete(p.getId());
				assertEquals(result,"Success");
			}
		}
	}
	
	/*
	 * This will test the controller layer for update operation
	 */
	@Test
	public void testUpdatePerson() {
		PersonServiceController psc = new PersonServiceController();
		Person shiv = new Person("Shiv", "Shankar");
		Person rita = new Person("Rita", "Miller");
		psc.create(shiv);
		psc.create(rita);
		List<Person> found = psc.getByName(shiv.getLastName());
		Long id=0L;
		for(Person p : found) {
			if((shiv.getLastName().equals(p.getLastName())) && (shiv.getFirstName().equals(p.getFirstName()))) {
				id = p.getId();
			}
		}
		assertFalse(id==0L);
		
		// now update the record
		shiv.setLastName("Shaker");
		String result = psc.update(id, shiv);
		assertEquals(result,"Success");

	}
	
	

}


