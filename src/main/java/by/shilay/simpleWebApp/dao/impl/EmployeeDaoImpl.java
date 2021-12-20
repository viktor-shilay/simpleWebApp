package by.shilay.simpleWebApp.dao.impl;

import by.shilay.simpleWebApp.dao.api.EmployeeDao;
import by.shilay.simpleWebApp.dto.Employee;
import by.shilay.simpleWebApp.exception.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private final String SQL_SELECT_ALL = "SELECT * FROM employee";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM employee WHERE employee_id = ?";
    private final String SQL_INSERT_EMPLOYEE = "INSERT INTO employee(first_name, last_name, department_id, job_title, gender, date_of_birth)" +
            " VALUES (:firstName, :lastName, :departmentId, :jobTitle, :gender, :dateOfBirth)";
    private final String SQL_UPDATE_EMPLOYEE = "UPDATE employee " +
            "SET first_name = :firstName, last_name = :lastName, department_id = :departmentId, job_title = :jobTitle, gender = :gender, date_of_birth = :dateOfBirth " +
            "WHERE employee_id = :employeeId";
    private final String SQL_DELETE_EMPLOYEE = "DELETE FROM employee WHERE employee_id = ?";

    @Override
    public List<Employee> getAll() {
        return jdbcTemplate.query(
                SQL_SELECT_ALL,
                new BeanPropertyRowMapper<>(Employee.class)
        );
    }

    @Override
    public Employee getById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    SQL_SELECT_BY_ID,
                    new BeanPropertyRowMapper<>(Employee.class),
                    id
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new EmployeeNotFoundException("Employee with id " + id + " was not found!");
        }
    }

    @Override
    public void save(Employee employee) {
        Map<String, Object> params = paramsMap(employee);
        namedJdbcTemplate.update(
                SQL_INSERT_EMPLOYEE,
                params
        );
    }

    @Override
    public void update(Long id, Employee employee) {
        Map<String, Object> params = paramsMap(employee);
        params.put("employeeId", id);
        namedJdbcTemplate.update(
                SQL_UPDATE_EMPLOYEE,
                params
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_EMPLOYEE, id);
    }

    private Map<String, Object> paramsMap(Employee employee) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstName", employee.getFirstName());
        params.put("lastName", employee.getLastName());
        params.put("departmentId", employee.getDepartmentId());
        params.put("jobTitle", employee.getJobTitle());
        params.put("gender", employee.getGender().toString());
        params.put("dateOfBirth", employee.getDateOfBirth());
        return params;
    }
}
