package org.root.dto;

import org.root.enums.Status;

public record EmployeeDto(Long id, String fullName, String email, Status status, String phone) {
}
