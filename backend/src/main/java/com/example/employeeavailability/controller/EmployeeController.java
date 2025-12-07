package com.example.employeeavailability.controller;

import com.example.employeeavailability.model.AvailabilityStatus;
import com.example.employeeavailability.model.Employee;
import com.example.employeeavailability.repository.EmployeeRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(@AuthenticationPrincipal Employee employee) {
        if (employee == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        return ResponseEntity.ok(Map.of(
                "id", employee.getId(),
                "name", employee.getName(),
                "email", employee.getEmail(),
                "availabilityStatus", employee.getAvailabilityStatus().name()
        ));
    }

    public static class AvailabilityUpdateRequest {
        @NotBlank
        private String availabilityStatus;

        public AvailabilityUpdateRequest() {}

        public String getAvailabilityStatus() {
            return availabilityStatus;
        }

        public void setAvailabilityStatus(String availabilityStatus) {
            this.availabilityStatus = availabilityStatus;
        }
    }

    @PutMapping("/me/status")
    public ResponseEntity<?> updateStatus(
            @AuthenticationPrincipal Employee employee,
            @Valid @RequestBody AvailabilityUpdateRequest request
    ) {
        if (employee == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        AvailabilityStatus status;
        try {
            status = AvailabilityStatus.valueOf(request.getAvailabilityStatus());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Invalid availability status");
        }

        employee.setAvailabilityStatus(status);
        employeeRepository.save(employee);

        return ResponseEntity.ok(Map.of(
                "id", employee.getId(),
                "name", employee.getName(),
                "email", employee.getEmail(),
                "availabilityStatus", employee.getAvailabilityStatus().name()
        ));
    }
}
