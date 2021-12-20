package by.shilay.simpleWebApp.service.impl;

import by.shilay.simpleWebApp.dao.api.EmployeeDao;
import by.shilay.simpleWebApp.dto.Employee;
import by.shilay.simpleWebApp.dto.Gender;
import by.shilay.simpleWebApp.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void getAllEmployees() {
        when(employeeDao.getAll()).thenReturn((Arrays.asList(
                new Employee(1L, "Viktor", "Shilay", 1, "Java developer", Gender.MALE, LocalDate.parse("1996-01-22")),
                new Employee(2L, "Dmitry", "Soloviev", 2, "DevOps", Gender.MALE, LocalDate.parse("1996-05-12")),
                new Employee(3L, "Anastasiya", "Romanova", 3, "HR", Gender.FEMALE, LocalDate.parse("1997-03-02")))
        ));
        List<Employee> employees = employeeServiceImpl.getAll();
        assertEquals(new Employee(1L, "Viktor", "Shilay", 1, "Java developer", Gender.MALE, LocalDate.parse("1996-01-22")), employees.get(0));
        assertEquals(new Employee(2L, "Dmitry", "Soloviev", 2, "DevOps", Gender.MALE, LocalDate.parse("1996-05-12")), employees.get(1));
        assertEquals(new Employee(3L, "Anastasiya", "Romanova", 3, "HR", Gender.FEMALE, LocalDate.parse("1997-03-02")), employees.get(2));
    }

    @Test
    void getEmployeeById() {
        Employee returned = new Employee(1L, "Viktor", "Shilay", 1, "Java developer", Gender.MALE, LocalDate.parse("1996-01-22"));
        when(employeeDao.getById(1L)).thenReturn(returned);
        Employee result = employeeServiceImpl.getById(1L);
        assertEquals(returned, result);
    }

    @Test
    void getEmployeeByIdNotFound() {
        when(employeeDao.getById(1L)).thenThrow(new EmployeeNotFoundException());
        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.getById(1L));
    }

    @Test
    void saveEmployee() {
        Employee employee = new Employee(1L, "Viktor", "Shilay", 1, "Java developer", Gender.MALE, LocalDate.parse("1996-01-22"));
        doNothing().when(employeeDao).save(employee);
        employeeServiceImpl.save(employee);
    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee(1L, "Viktor", "Shilay", 1, "Java developer", Gender.MALE, LocalDate.parse("1996-01-22"));
        doNothing().when(employeeDao).update(1L, employee);
        employeeServiceImpl.update(1L, employee);
    }

    @Test
    void deleteEmployee() {
        doNothing().when(employeeDao).delete(1L);
        employeeServiceImpl.delete(1L);
    }
}
