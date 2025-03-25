package org.root.service;

import org.root.domain.Department;
import org.root.dto.DepartmentDto;
import org.root.mapper.DepartmentMapper;
import org.root.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::convertToDto)
                .collect(Collectors.toList());
    }


//    @Transactional(propagation = Propagation.REQUIRED)
//    public Department saveDepartment(DepartmentDto dto ) {
//        Department dept = DepartmentMapper.convertToEntity(dto);
//        dept = departmentRepository.save(dept);
//        logger.info("***** inserted deptId: {}", dept.getId());
//        return dept;
//    }


//    @Transactional(propagation = Propagation.REQUIRED)
//    public Department saveDepartment(DepartmentDto dto ) {
//        Department dept = DepartmentMapper.convertToEntity(dto);
//        dept = departmentRepository.save(dept);
//        logger.info("***** inserted deptId: {}", dept.getId());
//        if(true){
//            throw new RuntimeException();
//        }
//        return dept;
//    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Department saveDepartment(DepartmentDto dto ) {
        Department dept = null;
        try {
            dept = DepartmentMapper.convertToEntity(dto);
            dept = departmentRepository.save(dept);
            logger.info("***** inserted deptId: {}", dept.getId());
            if(true){
                throw new RuntimeException();
            }
        } catch (RuntimeException ex) {
            // logger.error("exception while saveDepartment:{}", e.getMessage(), e);
        }
        return dept;
    }

}
