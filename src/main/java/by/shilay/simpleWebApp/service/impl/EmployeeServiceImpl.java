package by.shilay.simpleWebApp.service.impl;

import by.shilay.simpleWebApp.dao.api.EmployeeDao;
import by.shilay.simpleWebApp.dto.Employee;
import by.shilay.simpleWebApp.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        log.info("Fetching all employees...");
        return employeeDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getById(Long id) {
        log.info("Fetching employee by id: {} ", id);
        return employeeDao.getById(id);
    }

    @Override
    public void save(Employee employee) {
        log.info("Saving new employee: {} ", employee.getFirstName() + " " + employee.getLastName());
        employeeDao.save(employee);
    }

    @Override
    public void update(Long id, Employee employee) {
        log.info("Updating employee with id: {} ", id);
        employeeDao.update(id, employee);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting employee with id: {} ", id);
        employeeDao.delete(id);
    }
}
