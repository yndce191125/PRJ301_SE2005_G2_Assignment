package com.royalcrown.utils;

import com.royalcrown.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Utility class for managing user sessions.
 *
 * @author Nguyen Duong Y
 */
public final class SessionUtil {

    /**
     * Private constructor to prevent object creation.
     */
    private SessionUtil() {
    }

    /**
     * Stores the logged-in user information in the session.
     *
     * @param request HTTP request
     * @param user logged-in user
     */
    public static void setUser(
            HttpServletRequest request,
            User user) {

        if (request == null || user == null) {
            return;
        }

        HttpSession session = request.getSession(true);

        session.setAttribute("user", user);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("fullName", user.getFullName());
        session.setAttribute("role", user.getRole());
    }

    /**
     * Gets the currently logged-in user.
     *
     * @param request HTTP request
     * @return logged-in user, or null if the user is not logged in
     */
    public static User getUser(
            HttpServletRequest request) {

        if (request == null) {
            return null;
        }

        HttpSession session =
                request.getSession(false);

        if (session == null) {
            return null;
        }

        Object object =
                session.getAttribute("user");

        if (object instanceof User) {
            return (User) object;
        }

        return null;
    }

    /**
     * Checks whether a user is currently logged in.
     *
     * @param request HTTP request
     * @return true if logged in, otherwise false
     */
    public static boolean isLoggedIn(
            HttpServletRequest request) {

        return getUser(request) != null;
    }

    /**
     * Gets the role of the currently logged-in user.
     *
     * @param request HTTP request
     * @return user role, or null if not logged in
     */
    public static String getRole(
            HttpServletRequest request) {

        User user = getUser(request);

        if (user == null) {
            return null;
        }

        return user.getRole();
    }

    /**
     * Gets the logged-in user's ID.
     *
     * @param request HTTP request
     * @return user ID, or null if not logged in
     */
    public static Integer getUserId(
            HttpServletRequest request) {

        User user = getUser(request);

        if (user == null) {
            return null;
        }

        return user.getUserId();
    }

    /**
     * Invalidates the current session.
     *
     * @param request HTTP request
     */
    public static void invalidate(
            HttpServletRequest request) {

        if (request == null) {
            return;
        }

        HttpSession session =
                request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }
}