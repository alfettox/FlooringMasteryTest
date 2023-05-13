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

    public int getOrderNumber() {
        return io.readInt("Enter Order Number:");
    }

    public LocalDate getDate() {
        return io.readDate("Please enter a date with the following format MM/dd/yyyy");
    }

    public Order editAnOrder(Order order) {
        System.out.println(order.getDate());
        System.out.println(order.getOrderNumber());
        BigDecimal area100 = BigDecimal.valueOf(100);
        io.print(order.getCustomerName());
        String cName = io.readString("Enter Customer Name: ");

        order.setCustomerName(cName);
        io.print("State: " + order.getState());
        String state = io.readString("Enter State Name: ");
        if (state.length() == 0) ;// do nothing
        order.setState(state);

        io.print("Product Type: " + order.getProductType());
        String productType = io.readString("Enter Product Type: ");
        order.setProductType(productType);
        io.print("Area: " + order.getArea());
        String area = io.readString("Enter Area: ");
        BigDecimal pArea = BigDecimal.valueOf(Long.parseLong(area));
        order.setArea(pArea);
        return order;
    }

    private boolean isValidName(String s) {
        if (s.equals(" ") || s.length() == 0) return false;
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == ',' || c == '.') return true;
        }
        return false;
    }

    public Order removeAnOrder(List<Order> ordersList) {
        LocalDate userInpDate = io.readDate("Please enter the Order Date: (MM/dd/yyyy)");
        int orderNumber = io.readInt("Please enter the Order Number: ");
        for (Order curOrder : ordersList) {
            if (curOrder.getOrderNumber() == orderNumber && curOrder.getDate().equals(userInpDate)) {
                displaySingleOrder(curOrder);
                String inp = io.readString("Are you sure? (Y/N)");
                if (inp.equalsIgnoreCase("Y")) {
                    ordersList.remove(curOrder);
                    displaySingleOrder(curOrder);
//                    displayOrderRemovedBanner();
                    return curOrder;
                } else {
//                    displayOrderNotRemovedBanner();
                }
                break;
            }
        }
        return null;
    }
}


//   EDIT
//   CustomerName
//            State
//    ProductType
//            Area
