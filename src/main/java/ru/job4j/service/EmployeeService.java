package ru.job4j.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.Employee;
import ru.job4j.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Employee not found."
            );
        });
    }

    public boolean deleteById(int id) {
        boolean result = employeeRepository.existsById(id);
        if (result) {
            employeeRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST, "Employee not found!"
            );
        }
        return result;
    }
}
