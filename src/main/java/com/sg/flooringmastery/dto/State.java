package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
public class State {
    private String stateAbbr;
    private String stateName;
    private BigDecimal taxRate;

    public State(String stateAbbr, String stateName, BigDecimal taxRate) {
        this.stateAbbr = stateAbbr;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    public String getStateName() {
        return stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
}
