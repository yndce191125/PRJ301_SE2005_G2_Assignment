package com.royalcrown.controller;

import com.royalcrown.dao.UserDAO;
import com.royalcrown.model.User;
import com.royalcrown.utils.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Handles user registration.
 *
 * @author Nguyen Duong Y
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/auth/register.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword =
                request.getParameter("confirmPassword");
        String phone = request.getParameter("phone");


        /*
         * Trim unnecessary spaces.
         */

        if (fullName != null) {
            fullName = fullName.trim();
        }

        if (email != null) {
            email = email.trim();
        }

        if (phone != null) {
            phone = phone.trim();
        }


        /*
         * Validate required fields.
         */

        if (isBlank(fullName)
                || isBlank(email)
                || isBlank(password)
                || isBlank(confirmPassword)) {

            request.setAttribute(
                    "error",
                    "Please fill in all required fields."
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/register.jsp"
            ).forward(request, response);

            return;
        }


        /*
         * Validate full name.
         */

        if (fullName.length() < 2
                || fullName.length() > 100) {

            request.setAttribute(
                    "error",
                    "Full name must be between 2 and 100 characters."
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/register.jsp"
            ).forward(request, response);

            return;
        }


        /*
         * Validate email.
         */

        if (!isValidEmail(email)) {

            request.setAttribute(
                    "error",
                    "Invalid email format."
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/register.jsp"
            ).forward(request, response);

            return;
        }


        /*
         * Validate password length.
         */

        if (password.length() < 6) {

            request.setAttribute(
                    "error",
                    "Password must contain at least 6 characters."
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/register.jsp"
            ).forward(request, response);

            return;
        }


        /*
         * Confirm password.
         */

        if (!password.equals(confirmPassword)) {

            request.setAttribute(
                    "error",
                    "Passwords do not match."
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/register.jsp"
            ).forward(request, response);

            return;
        }


        /*
         * Check duplicate email.
         */

        if (userDAO.existsByEmail(email)) {

            request.setAttribute(
                    "error",
                    "This email is already registered."
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/register.jsp"
            ).forward(request, response);

            return;
        }


        /*
         * Create new customer.
         */

        User user = new User();

        user.setFullName(fullName);

        user.setEmail(email);

        user.setPassword(
                PasswordUtil.hashPassword(password)
        );

        user.setPhone(phone);


        /*
         * Insert customer into database.
         *
         * UserDAO.insertCustomer()
         * automatically sets role = CUSTOMER.
         */

        boolean success = userDAO.insertCustomer(user);


        if (!success) {

            request.setAttribute(
                    "error",
                    "Registration failed. Please try again."
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/register.jsp"
            ).forward(request, response);

            return;
        }


        /*
         * Registration successful.
         */

        response.sendRedirect(
                request.getContextPath()
                + "/LoginServlet?registered=true"
        );
    }


    private boolean isBlank(String value) {

        return value == null
                || value.trim().isEmpty();
    }


    private boolean isValidEmail(String email) {

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }
}