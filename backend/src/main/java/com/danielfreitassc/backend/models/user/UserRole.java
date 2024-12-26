package com.danielfreitassc.backend.models.user;

public enum UserRole {
    ADMIN("Admin"),
    USER("User");
    private String role;

    UserRole(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
