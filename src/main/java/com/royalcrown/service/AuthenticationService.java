package com.royalcrown.service;

import com.royalcrown.dao.UserDAO;
import com.royalcrown.model.User;
import com.royalcrown.utils.PasswordUtil;

/**
 * Handles user authentication for the Royal Crown Hotel system.
 *
 * @author Nguyen Duong Y
 */
public class AuthenticationService {

    private final UserDAO userDAO;

    /**
     * Creates an AuthenticationService.
     */
    public AuthenticationService() {
        userDAO = new UserDAO();
    }

    /**
     * Authenticates a user using email and password.
     *
     * @param email the user's email
     * @param password the plain-text password entered by the user
     * @return the authenticated User if login is successful;
     *         otherwise null
     */
    public User authenticate(String email, String password) {

        // Validate input.
        if (email == null || email.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        // Find user by email.
        User user = userDAO.findByEmail(email.trim());

        // Email does not exist.
        if (user == null) {
            return null;
        }

        // Account is disabled.
        if (!user.isStatus()) {
            return null;
        }

        // Verify password.
        if (!PasswordUtil.verifyPassword(
                password,
                user.getPassword())) {
            return null;
        }

        // Authentication successful.
        return user;
    }
}