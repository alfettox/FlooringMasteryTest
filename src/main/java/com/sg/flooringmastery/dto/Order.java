package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
public class Order {
    private int orderNumber;
    private static int globalOrderNumber;
    private LocalDate date;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;

    public Order(int orderNumber, LocalDate date, String customerName, String state, BigDecimal taxRate, String productType, BigDecimal area, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal total) {
        if (globalOrderNumber >= orderNumber) {
            orderNumber = globalOrderNumber++;
            globalOrderNumber = orderNumber;
        }
        this.orderNumber = orderNumber;
        this.date = date;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.total = total;
    }

    public Order(String customerName, String state, String productType, BigDecimal area, LocalDate date) {
        this.orderNumber = globalOrderNumber++;
        globalOrderNumber++;
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.area = area;
        this.date = date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getState() {
        return state;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }


    public String toString() {
        return "Order { " + "Order number: " + this.orderNumber +
                ", customer name: " + this.customerName +
                ", state:" + this.state +
                ", tax rate: " + this.taxRate +
                ", product type: " + this.productType +
                ", area: " + this.area +
                ", cost per square foot: " + this.costPerSquareFoot +
                ", labor cost per square foot: " + this.laborCostPerSquareFoot +
                ", material cost: " + this.materialCost +
                ", labor cost: " + this.laborCost +
                ", tax: " + this.tax +
                ", total: " + this.total +
                " }";
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public static int getGlobalOrderNumber() {
        return globalOrderNumber;
    }

    public static void setGlobalOrderNumber(int globalOrderNumber) {
        Order.globalOrderNumber = globalOrderNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    //    MaterialCost = (Area * CostPerSquareFoot)
//
//    LaborCost = (Area * LaborCostPerSquareFoot)
//
//    Tax = (MaterialCost + LaborCost) * (TaxRate/100)
//
//    Tax rates are stored as whole numbers
//            Total = (MaterialCost + LaborCost + Tax)


}
