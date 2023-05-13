package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
public interface ClassFlooringMasteryServiceLayer {
    List<Order> getAllOrders() throws Exception;

    void completeOrderFields(Order curOrder);

    void loadOrders() throws Exception;

    Order validateOrderInput(LocalDate date, int orderNumber) throws Exception;

    void editOrderConfirmed(Order order) throws Exception;

    void exportAllData();

    Order removeOrderConfirmed(Order order) throws Exception;
}
