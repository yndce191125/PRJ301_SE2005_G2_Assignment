package com.royalcrown.model;

/**
 * Represents a user in the Royal Crown Hotel system.
 *
 * @author Nguyen Duong Y
 */
public class User {

    private int userId;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String role;
    private boolean status;

    /**
     * Creates an empty User object.
     */
    public User() {
    }

    /**
     * Creates a User object with all fields.
     *
     * @param userId the user's ID
     * @param fullName the user's full name
     * @param email the user's email
     * @param password the user's password
     * @param phone the user's phone number
     * @param role the user's role
     * @param status the user's account status
     */
    public User(int userId, String fullName, String email,
            String password, String phone, String role, boolean status) {

        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{"
                + "userId=" + userId
                + ", fullName='" + fullName + '\''
                + ", email='" + email + '\''
                + ", phone='" + phone + '\''
                + ", role='" + role + '\''
                + ", status=" + status
                + '}';
    }
}