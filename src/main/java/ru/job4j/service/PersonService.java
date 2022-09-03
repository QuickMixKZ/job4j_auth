package ru.job4j.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(int id) {
        return personRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Person not found."
        ));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public boolean update(Person person) {
        boolean result = personRepository.existsById(person.getId());
        if (result) {
            personRepository.save(person);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Person not found."
            );
        }
        return result;
    }

    public boolean deleteById(int id) {
        boolean result = personRepository.existsById(id);
        if (result) {
            personRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Person not found."
            );
        }
        return result;
    }

}
