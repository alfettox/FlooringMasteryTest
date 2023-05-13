package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;

import java.util.List;
import java.util.Map;


/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
public interface ClassFlooringMasteryDao {

    Map<String, State> getStatesMap();

    Map<String, Product> getProductsMap();

    Order getOrder(int orderNumber);

    Order removeOrder(int orderNumber) throws Exception;

    List<Order> getAllOrders() throws Exception;

    Order addOrder();

    void loadOrders() throws Exception;

    void saveOrder(Order curOrder);

    Order editOrder(Order order) throws Exception;

    void writeOrder() throws Exception;
}
