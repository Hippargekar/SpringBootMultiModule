package org.root.resource;

import jakarta.servlet.http.HttpServletRequest;
import org.root.dto.AuthRequest;
import org.root.dto.AuthResponse;
import org.root.service.EmployeeService;
import org.root.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/employee/{version}")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(@PathVariable("version") String version, HttpServletRequest request) {
        logger.info("Requested URI: {}", request.getRequestURI());
        if (!ResponseUtils.VERSIONS.contains(version)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid version: " + version);
        }
        if ("v1".equals(version)) {
            // Logic for version 1
        } else if ("v2".equals(version)) {
            // Logic for version 2
        }
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest login, @PathVariable("version") String version) {
        logger.info("Request body: {} - {}", login.getVersion(), version);
        if (!ResponseUtils.VERSIONS.contains(version)) {
            logger.info("Invalid version: {}", version);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid version: " + version);
        }
        if ("v1".equals(version)) {
            // Logic for version 1
        } else if ("v2".equals(version)) {
            // Logic for version 2
        }
        return new ResponseEntity<>(new AuthResponse("idToken"), HttpStatus.OK);
    }
}
