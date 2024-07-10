package com.example.pizzaasmit1;

import com.example.pizzaasmit1.dao.OrderDAO;
import com.example.pizzaasmit1.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PizzaOrderingController implements Initializable {
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField mobileNumberField;
    @FXML
    private TextField toppingsField;
    @FXML
    private CheckBox xlCheckbox, lCheckbox, mCheckbox, sCheckbox;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, Integer> idColumn;
    @FXML
    private TableColumn<Order, String> customerNameColumn;
    @FXML
    private TableColumn<Order, String> mobileNumberColumn;
    @FXML
    private TableColumn<Order, String> pizzaSizeColumn;
    @FXML
    private TableColumn<Order, Integer> toppingsColumn;
    @FXML
    private TableColumn<Order, Double> totalBillColumn;

    private OrderDAO orderDAO;
    private ObservableList<Order> ordersList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderDAO = new OrderDAO();
        ordersList = FXCollections.observableArrayList(orderDAO.getAllOrders());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        pizzaSizeColumn.setCellValueFactory(new PropertyValueFactory<>("pizzaSize"));
        toppingsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfToppings"));
        totalBillColumn.setCellValueFactory(new PropertyValueFactory<>("totalBill"));

        ordersTable.setItems(ordersList);
    }

    @FXML
    protected void handleAddOrder(ActionEvent event) {
        try {
            String customerName = customerNameField.getText();
            String mobileNumber = mobileNumberField.getText();
            String pizzaSize = getPizzaSize();
            int numberOfToppings = Integer.parseInt(toppingsField.getText());
            double totalBill = BillCalculator.calculateTotalBill(pizzaSize, numberOfToppings);

            Order order = new Order(0, customerName, mobileNumber, pizzaSize, numberOfToppings, totalBill);
            orderDAO.addOrder(order);
            refreshOrdersTable();
        } catch (NumberFormatException e) {
            showAlert("Invalid input", "Please enter a valid number for toppings.");
        }
    }

    @FXML
    protected void handleUpdateOrder(ActionEvent event) {
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            try {
                selectedOrder.setCustomerName(customerNameField.getText());
                selectedOrder.setMobileNumber(mobileNumberField.getText());
                selectedOrder.setPizzaSize(getPizzaSize());
                selectedOrder.setNumberOfToppings(Integer.parseInt(toppingsField.getText()));
                selectedOrder.setTotalBill(BillCalculator.calculateTotalBill(selectedOrder.getPizzaSize(), selectedOrder.getNumberOfToppings()));
                orderDAO.updateOrder(selectedOrder);
                refreshOrdersTable();
            } catch (NumberFormatException e) {
                showAlert("Invalid input", "Please enter a valid number for toppings.");
            }
        }
    }

    @FXML
    protected void handleDeleteOrder(ActionEvent event) {
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            orderDAO.deleteOrder(selectedOrder.getId());
            refreshOrdersTable();
        }
    }

    @FXML
    protected void handleLoadOrder(ActionEvent event) {
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            customerNameField.setText(selectedOrder.getCustomerName());
            mobileNumberField.setText(selectedOrder.getMobileNumber());
            toppingsField.setText(String.valueOf(selectedOrder.getNumberOfToppings()));
            setPizzaSize(selectedOrder.getPizzaSize());
        }
    }

    @FXML
    protected void handleFetchOrders(ActionEvent event) {
        refreshOrdersTable();
    }

    private String getPizzaSize() {
        if (xlCheckbox.isSelected()) return "XL";
        if (lCheckbox.isSelected()) return "L";
        if (mCheckbox.isSelected()) return "M";
        if (sCheckbox.isSelected()) return "S";
        return "";
    }

    private void setPizzaSize(String size) {
        xlCheckbox.setSelected("XL".equals(size));
        lCheckbox.setSelected("L".equals(size));
        mCheckbox.setSelected("M".equals(size));
        sCheckbox.setSelected("S".equals(size));
    }

    private void refreshOrdersTable() {
        ordersList.setAll(orderDAO.getAllOrders());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
