package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;

import java.util.List;

/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
public interface ClassFlooringMasteryServiceLayer {
    List<Order> getAllOrders() throws Exception;

    void completeOrderFields(Order curOrder);
}
