package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
public class Product {
    private String productType;
    private BigDecimal materialCostPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    public Product(String productType, BigDecimal materialCostPerSquareFoot, BigDecimal laborCostPerSquareFoot) {
        this.productType = productType;
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getMaterialCostPerSquareFoot() {
        return materialCostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }
}
