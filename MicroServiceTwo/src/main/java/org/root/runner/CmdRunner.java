package org.root.runner;

import org.root.dto.EmployeeDto;
import org.root.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.root.enums.Status;

@Component
public class CmdRunner implements CommandLineRunner {

    private final EmployeeService employeeService;

    public CmdRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        EmployeeDto emp1 = new EmployeeDto(1L, "John Doe", "john.doe@example.com", Status.OPEN, "123-456-7890");
        employeeService.saveEmployee(emp1);
    }
}
