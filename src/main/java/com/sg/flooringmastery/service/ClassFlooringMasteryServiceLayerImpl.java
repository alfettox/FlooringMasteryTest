package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.ClassFlooringMasteryAuditDao;
import com.sg.flooringmastery.dto.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.sg.flooringmastery.dao.ClassFlooringMasteryDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
@Service
@Profile("Application")
public class ClassFlooringMasteryServiceLayerImpl implements ClassFlooringMasteryServiceLayer {

    private final ClassFlooringMasteryAuditDao auditDao;
    ClassFlooringMasteryDao dao;


    public ClassFlooringMasteryServiceLayerImpl(ClassFlooringMasteryDao dao, ClassFlooringMasteryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }


    @Override
    public List<Order> getAllOrders() throws Exception {
        return dao.getAllOrders();
    }

    public void completeOrderFields(Order curOrder) {
        String pType = curOrder.getProductType();
        System.out.println(dao.getStatesMap().get(curOrder.getState()).getTaxRate());
        curOrder.setTaxRate(dao.getStatesMap().get(curOrder.getState()).getTaxRate());
        curOrder.setCostPerSquareFoot((dao.getProductsMap().get(pType).getLaborCostPerSquareFoot()
                .add(dao.getProductsMap().get(pType).getMaterialCostPerSquareFoot())));
        curOrder.setLaborCostPerSquareFoot(dao.getProductsMap().get(pType).getLaborCostPerSquareFoot());
        curOrder.setMaterialCost((dao.getProductsMap().get(pType).getMaterialCostPerSquareFoot()).multiply(curOrder.getArea()));
        curOrder.setLaborCost(curOrder.getLaborCostPerSquareFoot().multiply(curOrder.getArea()));
        curOrder.setTax(curOrder.getTaxRate().multiply(curOrder.getMaterialCost().add(curOrder.getLaborCost())));
        curOrder.setTotal(curOrder.getLaborCost().add(curOrder.getMaterialCost()).add(curOrder.getTax()));
        dao.saveOrder(curOrder);
    }

    @Override
    public void loadOrders() throws Exception {
        dao.loadOrders();
    }
}
