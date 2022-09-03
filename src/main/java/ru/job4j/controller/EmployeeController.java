package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final RestTemplate rest;
    private final EmployeeService employeeService;

    private static final String API = "http://localhost:8080/person/";

    private static final String API_ID = "http://localhost:8080/person/{id}";

    public EmployeeController(RestTemplate rest, EmployeeService employeeService) {
        this.rest = rest;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(
                employeeService.save(employee),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @PostMapping("/{id}/account")
    public ResponseEntity<Employee> addAccount(@PathVariable("id") int id,
                                               @RequestBody Person person) {
        Employee employee = employeeService.findById(id);
        person.setEmployeeId(id);
        Person newPerson = rest.postForObject(API, person, Person.class);
        employee.addAccount(newPerson);
        employeeService.save(employee);
        return new ResponseEntity<>(
                employee,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/account")
    public ResponseEntity<Employee> updateAccount(@PathVariable("id") int id,
                                               @RequestBody Person person) {
        Employee employee = employeeService.findById(id);
        person.setEmployeeId(id);
        rest.put(API, person);
        return new ResponseEntity<>(
                employee,
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping("/{id}/account")
    public ResponseEntity<Employee> deleteAccount(@PathVariable("id") int id,
                                                  @RequestBody Person person) {
        Employee employee = employeeService.findById(id);
        rest.delete(API_ID, person.getId());
        return new ResponseEntity<>(
                employee,
                HttpStatus.ACCEPTED
        );
    }

}
