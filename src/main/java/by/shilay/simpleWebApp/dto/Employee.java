package by.shilay.simpleWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private Integer departmentId;
    private String jobTitle;
    private Gender gender;
    private LocalDate dateOfBirth;

}
