package org.root.mapper;

import org.root.domain.Employee;
import org.root.dto.EmployeeDto;

public class EmployeeMapper {

    public static EmployeeDto convertToDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getFullName(), employee.getEmail(), employee.getStatus(), employee.getPhone());
    }

    public static Employee convertToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFullName(employeeDto.fullName());
        employee.setEmail(employeeDto.email());
        employee.setStatus(employeeDto.status());
        employee.setPhone(employeeDto.phone());
        return employee;
    }
}
