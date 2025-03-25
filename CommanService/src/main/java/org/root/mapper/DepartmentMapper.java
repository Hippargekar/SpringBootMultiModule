package org.root.mapper;

import org.root.domain.Department;
import org.root.dto.DepartmentDto;

public class DepartmentMapper {

    public static DepartmentDto convertToDto(Department employee) {
        return new DepartmentDto(employee.getId(), employee.getDeptName());
    }

    public static Department convertToEntity(DepartmentDto employeeDto) {
        Department employee = new Department();
        employee.setDeptName(employeeDto.deptName());
        return employee;
    }
}
