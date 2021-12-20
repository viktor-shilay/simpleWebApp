package by.shilay.simpleWebApp.service.api;

import by.shilay.simpleWebApp.dto.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(Long id);

    void save(Employee employee);

    void update(Long id, Employee employee);

    void delete(Long id);
}
