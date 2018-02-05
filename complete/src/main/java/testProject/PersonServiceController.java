package testProject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/*
 * The main controller for the repository access with base uri /persons
 */
@RestController
@RequestMapping("/persons")
public class PersonServiceController {     

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	@Autowired
	private PersonRepository personRepo;
	

	/*
	 * This method allows querying the repository by person's last name
	 * persons/<name>
	 */
	@RequestMapping(method=RequestMethod.GET, value="{name}")
	public List<Person> getByName(@PathVariable String name) { 
		List<Person> p =personRepo.findByLastName(name);
		return p;
	}


	/*
	 * This method allows querying all the data rows in the repository
	 */
	@RequestMapping(method=RequestMethod.GET)
	public List<Person> getAllPeople() {
		return personRepo.findAll();
	}


	/*
	 * This method allows inserting a new person data in the repository. It checks if the person already exists in the db and returns appropriate message
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String create(@RequestBody Person person) {
		// check if this person exists already in db
		List <Person> pList= personRepo.findByLastName(person.getLastName());
		if(pList!=null) {
			for(Person p : pList) {
				if(p.getLastName().equals(person.getLastName()) && p.getFirstName().equals(person.getFirstName())) {
					log.info("Duplicate person name cannot be added: "+person.getFirstName()+" "+person.getLastName());
					return "Person's name already exists in repository. Not added";
				}			
			}
		}
		// this person does not exist in db
		personRepo.save(person);
		return "Success!";
	}

	/*
	 * This method deletes a single person record in the repository by using the Id supplied in the URL
	 * It checks if Id is valid and return an appropriate message
	 * persons/<id>
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public String delete(@PathVariable Long id) {
		Person p = personRepo.findOne(id);
		if(p!=null) {
			personRepo.delete(id);
			return "Success";
		}
		return "Id "+id+" not found. Person not deleted";

	}

	/*
	 * This method updates a person's data in the repository using the Id supplied in the URL as well as the new person's data
	 * It checks if Id is valid and returns an appropriate message
	 * persons/<id>
	 */
	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public String update(@PathVariable Long id, @RequestBody Person person) {
		Person newP = personRepo.findOne(id);
		if(newP!=null) {
			newP.setFirstName(person.getFirstName());
			newP.setLastName(person.getLastName());
			personRepo.save(newP);
			return "Success";
		}
		return "Id "+id+" not found. Person not updated";
	}

}
