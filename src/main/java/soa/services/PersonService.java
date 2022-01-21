package soa.services;

import soa.entity.Person;
import org.springframework.stereotype.Service;
import soa.repository.PersonRepository;
import soa.validation.EntityValidator;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final EntityValidator entityValidator;

    public PersonService(PersonRepository personRepository, EntityValidator entityValidator) {
        this.personRepository = personRepository;
        this.entityValidator = entityValidator;
    }

    public Person getPersonById(Integer id) {
        return personRepository.findById(id).orElseThrow(NoResultException::new);
    }

    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    @Transactional
    public void updatePerson(Person personToUpdate) {
        personRepository.findById(personToUpdate.getId())
                .orElseThrow(() -> new NoResultException(" Person with id " + personToUpdate.getId() + " does not exist"));
        entityValidator.validatePerson(personToUpdate);
        personRepository.save(personToUpdate);
    }

    @Transactional
    public void createPerson(Person personToPersist) {
        entityValidator.validatePerson(personToPersist);
        personRepository.save(personToPersist);
    }
}
