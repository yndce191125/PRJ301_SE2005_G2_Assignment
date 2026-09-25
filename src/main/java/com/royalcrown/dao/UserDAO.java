package com.royalcrown.dao;

import com.royalcrown.model.User;
import com.royalcrown.utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for the Users table.
 *
 * @author Nguyen Duong Y
 */
public class UserDAO {

    /**
     * Finds a user by email address.
     *
     * @param email the user's email address
     * @return the User object if found; otherwise null
     */
    public User findByEmail(String email) {

        String sql = "SELECT user_id, full_name, email, password, "
                + "phone, role, status "
                + "FROM Users "
                + "WHERE email = ?";

        try (Connection connection = DBContext.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Finds an active user by email address.
     *
     * @param email the user's email address
     * @return the active User object if found; otherwise null
     */
    public User findActiveUserByEmail(String email) {

        String sql = "SELECT user_id, full_name, email, password, "
                + "phone, role, status "
                + "FROM Users "
                + "WHERE email = ? AND status = 1";

        try (Connection connection = DBContext.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Checks whether an email already exists.
     *
     * @param email the email address to check
     * @return true if the email exists; otherwise false
     */
    public boolean existsByEmail(String email) {

        String sql = "SELECT 1 "
                + "FROM Users "
                + "WHERE email = ?";

        try (Connection connection = DBContext.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Inserts a new customer account.
     *
     * @param user the user to insert
     * @return true if the user was inserted successfully; otherwise false
     */
    public boolean insertCustomer(User user) {

        String sql = "INSERT INTO Users "
                + "(full_name, email, password, phone, role, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBContext.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhone());
            statement.setString(5, "CUSTOMER");
            statement.setBoolean(6, true);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Maps a ResultSet row to a User object.
     *
     * @param resultSet the ResultSet containing user data
     * @return a User object
     * @throws SQLException if a database access error occurs
     */
    private User mapUser(ResultSet resultSet) throws SQLException {

        User user = new User();

        user.setUserId(resultSet.getInt("user_id"));
        user.setFullName(resultSet.getString("full_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setPhone(resultSet.getString("phone"));
        user.setRole(resultSet.getString("role"));
        user.setStatus(resultSet.getBoolean("status"));

        return user;
    }
}