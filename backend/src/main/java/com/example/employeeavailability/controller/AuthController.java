package com.example.employeeavailability.controller;

import com.example.employeeavailability.config.JwtService;
import com.example.employeeavailability.dto.AuthResponse;
import com.example.employeeavailability.dto.LoginRequest;
import com.example.employeeavailability.dto.RegisterRequest;
import com.example.employeeavailability.model.Employee;
import com.example.employeeavailability.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(EmployeeRepository employeeRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (employeeRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        }

        Employee employee = new Employee(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        Employee saved = employeeRepository.save(employee);
        String token = jwtService.generateToken(saved);
        AuthResponse response = new AuthResponse(
                token,
                saved.getName(),
                saved.getEmail(),
                saved.getAvailabilityStatus()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Employee employee = employeeRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (employee == null || !passwordEncoder.matches(request.getPassword(), employee.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }

        String token = jwtService.generateToken(employee);
        AuthResponse response = new AuthResponse(
                token,
                employee.getName(),
                employee.getEmail(),
                employee.getAvailabilityStatus()
        );
        return ResponseEntity.ok(response);
    }
}
