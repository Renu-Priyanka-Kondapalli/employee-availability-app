package com.example.employeeavailability.dto;

import com.example.employeeavailability.model.AvailabilityStatus;

public class AuthResponse {

    private String token;
    private String name;
    private String email;
    private AvailabilityStatus availabilityStatus;

    public AuthResponse() {}

    public AuthResponse(String token, String name, String email, AvailabilityStatus availabilityStatus) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.availabilityStatus = availabilityStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
