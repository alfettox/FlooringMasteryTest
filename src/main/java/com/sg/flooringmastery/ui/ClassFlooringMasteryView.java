package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
@Component
@Profile("Application")
public class ClassFlooringMasteryView {
    private UserIO io;

    public ClassFlooringMasteryView(UserIO io) {
        this.io = io;
    }

    //Controller messages
    public void displayErrorMessage(String message) {
        io.print("ERROR");
        io.print(message);
    }

    public void displayExitBanner() {
        io.print("Good bye!!!");
    }

    public int getMenuSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * *");
        io.print("* <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Export All Data");
        io.print("* 6. Quit");
        io.print("* ");
        io.print("* * * * * * * * * * * * * * * * * * * * *");
        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayAllOrders(List<Order> ordersList) {
        for (Order curOrder : ordersList) {
            displaySingleOrder(curOrder);
        }
        io.readString("Please hit enter to continue.");

    }

    public void displaySingleOrder(Order order) {
        io.print(order.toString());
    }

    public Order addOrder() {
        String customerName = io.readString("Enter Customer Name: ");
        String state = io.readString("Enter state: ");
        String productType = io.readString("Enter product type: ");
        BigDecimal area = io.readBigDecimal("Enter Area: ");
        LocalDate date = io.readDate("Enter the order date: ");

        Order curOrder = new Order(customerName, state, productType, area, date);


        return curOrder;
    }


//   EDIT
//   CustomerName
//            State
//    ProductType
//            Area
}
