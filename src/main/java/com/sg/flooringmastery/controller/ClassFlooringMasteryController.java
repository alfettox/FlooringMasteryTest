package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.ClassFlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.ClassFlooringMasteryServiceLayer;
import com.sg.flooringmastery.ui.ClassFlooringMasteryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
@Controller
@Profile("Application")
public class ClassFlooringMasteryController {

    @Autowired
    private ClassFlooringMasteryView view;
    @Autowired
    private ClassFlooringMasteryServiceLayer service;

    public ClassFlooringMasteryController(ClassFlooringMasteryServiceLayer classFlooringMasteryServiceLayerImpl,
                                          ClassFlooringMasteryView classFlooringMasteryView) {
        this.service = service;
        this.view = view;
    }


    public void run() throws Exception {
        boolean keepGoing = true;
        int menuSelection = 99;
        service.loadOrders();
//        try {
        do {
            menuSelection = getMenuSelection();
            switch (menuSelection) {
                case 1:
                    getAllOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    EditOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportAllData();
                    break;
                case 6:
                    exitMessage();
                    break;
                default:
                    unknownCommand();
            }
        }
        while (keepGoing);
        exitMessage();

//        } catch (Exception e) {
//            displayError(e.getMessage());
//        }
    }

    private void getAllOrders() throws Exception {
        List<Order> ordersList = service.getAllOrders();
        view.displayAllOrders(ordersList);
    }

    private void addOrder() {
        Order curOrder = view.addOrder();
        service.completeOrderFields(curOrder);


    }

    private void EditOrder() throws Exception {
        int orderNumber = view.getOrderNumber();
        LocalDate date = view.getDate();
        Order order = service.validateOrderInput(date, orderNumber);
        Order editedOrder = view.editAnOrder(order);
        service.editOrderConfirmed(editedOrder);
    }

    private void removeOrder() throws Exception {
        List<Order> ordersList = service.getAllOrders();
        Order order = view.removeAnOrder(ordersList);
        service.removeOrderConfirmed(order);
        view.displayAllOrders(service.getAllOrders());
    }

    private void exportAllData() {
            service.exportAllData();
        }

    private int getMenuSelection() {
        return view.getMenuSelection();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
        System.exit(0);
    }

    private void displayError(String message) {
        view.displayErrorMessage(message);
    }


}



