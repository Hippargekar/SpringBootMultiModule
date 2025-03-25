package org.root.service;

import org.root.domain.Employee;
import org.root.dto.DepartmentDto;
import org.root.dto.EmployeeDto;
import org.root.mapper.EmployeeMapper;
import org.root.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::convertToDto)
                .collect(Collectors.toList());
    }


//    @Transactional
//    public Employee saveEmployee(EmployeeDto dto) {//catch (DataAccessException e) {}
//        DepartmentDto dept1 = new DepartmentDto(1L, "Human Resources");
//        Employee emp = EmployeeMapper.convertToEntity(dto);
//        emp = employeeRepository.save(emp);
//        logger.info("***** inserted empId: {}", emp.getId());
//        departmentService.saveDepartment(dept1);
//        return emp;
//    }

//    @Transactional
//    public Employee saveEmployee(EmployeeDto dto) {
//        DepartmentDto dept1 = new DepartmentDto(1L, "Human Resources");
//        Employee emp = EmployeeMapper.convertToEntity(dto);
//        emp = employeeRepository.save(emp);
//        logger.info("***** inserted empId: {}", emp.getId());
//        try{
//            departmentService.saveDepartment(dept1);
//        } catch(Exception e) {
//           // logger.error("exception while persisting dept:{}",e.getMessage(), e);
//        }
//        return emp;
//    }

    @Transactional
    public Employee saveEmployee(EmployeeDto dto) {
        DepartmentDto dept1 = new DepartmentDto(1L, "Human Resources");
        Employee emp = EmployeeMapper.convertToEntity(dto);
        emp = employeeRepository.save(emp);
        logger.info("***** inserted empId: {}", emp.getId());
        departmentService.saveDepartment(dept1);
        if(true){
            throw new RuntimeException();
        }
        return emp;
    }

}
