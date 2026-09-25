package com.royalcrown.controller;

import com.royalcrown.model.User;
import com.royalcrown.service.AuthenticationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Handles user login requests.
 *
 * @author Nguyen Duong Y
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private AuthenticationService authenticationService;

    /**
     * Initializes the LoginServlet.
     *
     * @throws ServletException if the servlet cannot be initialized
     */
    @Override
    public void init() throws ServletException {
        authenticationService = new AuthenticationService();
    }

    /**
     * Handles GET requests by displaying the login page.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/auth/login.jsp"
        ).forward(request, response);
    }

    /**
     * Handles POST requests for user authentication.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = authenticationService.authenticate(
                email,
                password
        );

        if (user == null) {

            request.setAttribute(
                    "error",
                    "Invalid email or password."
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/auth/login.jsp"
            ).forward(request, response);

            return;
        }

        /*
         * Remove old session to prevent session fixation.
         */
        HttpSession oldSession = request.getSession(false);

        if (oldSession != null) {
            oldSession.invalidate();
        }

        /*
         * Create a new session after successful login.
         */
        HttpSession session = request.getSession(true);

        session.setAttribute("user", user);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("fullName", user.getFullName());
        session.setAttribute("role", user.getRole());

        /*
         * Redirect to HomeServlet after successful login.
         */
        response.sendRedirect(
                request.getContextPath()
                + "/HomeServlet"
        );
    }
}