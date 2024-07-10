package com.example.pizzaasmit1.model;

public class Order {
    private int id;
    private String customerName;
    private String mobileNumber;
    private String pizzaSize;
    private int numberOfToppings;
    private double totalBill;

    public Order(int id, String customerName, String mobileNumber, String pizzaSize, int numberOfToppings, double totalBill) {
        this.id = id;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.pizzaSize = pizzaSize;
        this.numberOfToppings = numberOfToppings;
        this.totalBill = totalBill;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public String getPizzaSize() { return pizzaSize; }
    public void setPizzaSize(String pizzaSize) { this.pizzaSize = pizzaSize; }
    public int getNumberOfToppings() { return numberOfToppings; }
    public void setNumberOfToppings(int numberOfToppings) { this.numberOfToppings = numberOfToppings; }
    public double getTotalBill() { return totalBill; }
    public void setTotalBill(double totalBill) { this.totalBill = totalBill; }
}
