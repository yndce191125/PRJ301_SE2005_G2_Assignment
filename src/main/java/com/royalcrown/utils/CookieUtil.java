package com.royalcrown.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Utility class for creating, reading, and deleting cookies.
 *
 * @author Nguyen Duong Y - CE191125
 */
public final class CookieUtil {

    private CookieUtil() {
    }

    /**
     * Creates or updates a cookie.
     *
     * @param response HTTP response
     * @param name cookie name
     * @param value cookie value
     * @param maxAge cookie lifetime in seconds
     */
    public static void setCookie(HttpServletResponse response,
            String name,
            String value,
            int maxAge) {

        if (response == null || name == null || name.trim().isEmpty()) {
            return;
        }

        Cookie cookie = new Cookie(name, value == null ? "" : value);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    /**
     * Gets a cookie value by cookie name.
     *
     * @param request HTTP request
     * @param name cookie name
     * @return cookie value, or null if the cookie does not exist
     */
    public static String getCookie(HttpServletRequest request, String name) {

        if (request == null || name == null || name.trim().isEmpty()) {
            return null;
        }

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    /**
     * Deletes a cookie.
     *
     * @param response HTTP response
     * @param name cookie name
     */
    public static void deleteCookie(HttpServletResponse response,
            String name) {

        if (response == null || name == null || name.trim().isEmpty()) {
            return;
        }

        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}