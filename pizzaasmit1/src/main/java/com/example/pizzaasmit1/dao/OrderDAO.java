package com.example.pizzaasmit1.dao;

import com.example.pizzaasmit1.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pizzaorderingdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders")) {

            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("mobile_number"),
                        resultSet.getString("pizza_size"),
                        resultSet.getInt("number_of_toppings"),
                        resultSet.getDouble("total_bill")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void addOrder(Order order) {
        String sql = "INSERT INTO orders (customer_name, mobile_number, pizza_size, number_of_toppings, total_bill) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, order.getCustomerName());
            statement.setString(2, order.getMobileNumber());
            statement.setString(3, order.getPizzaSize());
            statement.setInt(4, order.getNumberOfToppings());
            statement.setDouble(5, order.getTotalBill());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order) {
        String sql = "UPDATE orders SET customer_name = ?, mobile_number = ?, pizza_size = ?, number_of_toppings = ?, total_bill = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, order.getCustomerName());
            statement.setString(2, order.getMobileNumber());
            statement.setString(3, order.getPizzaSize());
            statement.setInt(4, order.getNumberOfToppings());
            statement.setDouble(5, order.getTotalBill());
            statement.setInt(6, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
