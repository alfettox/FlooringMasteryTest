package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.sg.flooringmastery.dto.Order.setGlobalOrderNumber;

/**
 * Giovanni De Franceschi
 * Wiley Edge
 **/
@Repository
@Profile("Application")
public class ClassFlooringMasteryDaoFileImpl implements ClassFlooringMasteryDao {
    private final String ORDERS_FILE;
    private final String TAXES_FILE;
    private final String PRODUCTS_FILE;
    public static final String DELIMITER = ",";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    Scanner scanner;

    private static Map<Integer, Order> ordersMap = new HashMap<Integer, Order>();
    private static Map<String, State> statesMap = new HashMap<String, State>();

    public static void setOrdersMap(Map<Integer, Order> ordersMap) {
        ClassFlooringMasteryDaoFileImpl.ordersMap.clear();
        ClassFlooringMasteryDaoFileImpl.ordersMap = ordersMap;
    }

    public Map<String, State> getStatesMap() {
        return statesMap;
    }

    public Map<String, Product> getProductsMap() {
        return productsMap;
    }

    @Override
    public Order getOrder(int orderNumber) {
        return ordersMap.get(orderNumber);
    }

    private static Map<String, Product> productsMap = new HashMap<String, Product>();

    public ClassFlooringMasteryDaoFileImpl(String orders_file, String taxes_file, String products_file) {
        ORDERS_FILE = orders_file;
        TAXES_FILE = taxes_file;
        PRODUCTS_FILE = products_file;
    }

    public ClassFlooringMasteryDaoFileImpl() {
        ORDERS_FILE = "orders.txt";
        TAXES_FILE = "taxes.txt";
        PRODUCTS_FILE = "products.txt";
    }

    @Override
    public Order removeOrder(int orderNumber) throws Exception {
        ordersMap.remove(orderNumber);
        for(Order x : getAllOrders()){
            System.out.println(x.getCustomerName());
        }
        return getOrder(orderNumber);
    }

    @Override
    public List<Order> getAllOrders() throws Exception {

        return new ArrayList<>(ordersMap.values());
    }

    @Override
    public Order addOrder() {
        return null;
    }

    public void loadOrders() throws Exception {
        try {
            loadTaxes();
            loadProducts();


            scanner = new Scanner(new BufferedReader(new FileReader(ORDERS_FILE)));

            String curLine;
            Order curOrder;
            if (scanner.hasNextLine()) { // Skip 1st line
                scanner.nextLine();
            }
            setGlobalOrderNumber(0);
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                curOrder = unmarshallOrder(curLine);
                setTaxes(curOrder);
                ordersMap.put(curOrder.getOrderNumber(), curOrder);
            setGlobalOrderNumber(curOrder.getOrderNumber());
            }
            scanner.close();
        } catch (Exception e){
//                io.print("-_- Could not load roster data into memory.", e.getMessage());
    }

}

    @Override
    public void saveOrder(Order curOrder) {
        ordersMap.put(curOrder.getOrderNumber(),curOrder);
    }

    @Override
    public Order editOrder(Order order) throws Exception {
        List<Order> listOrders = new ArrayList<>(ordersMap.values());
        Order filteredOrder = listOrders.stream()
                .filter(o -> o.getOrderNumber() == (order.getOrderNumber()))
                .findFirst()
                .orElse(null);
        Order newOrder = ordersMap.replace(order.getOrderNumber(), filteredOrder);
        saveOrder(newOrder);
        return newOrder;
    }

    private Order unmarshallOrder(String curLine) {
        String[] orderTokens = curLine.split(DELIMITER);

        String orderNumberasString = orderTokens[0];
        int orderNumber = Integer.parseInt(orderNumberasString);

        String customerName = orderTokens[1];

        String state = orderTokens[2];

        String taxRateAsString = orderTokens[3];
        BigDecimal taxRate = new BigDecimal(taxRateAsString);

        String productType = orderTokens[4];

        String areaAsString = orderTokens[5];
        BigDecimal area = new BigDecimal(areaAsString);

        String costPerSquareFootAsString = orderTokens[6];
        BigDecimal costPerSquareFoot = new BigDecimal(costPerSquareFootAsString);

        String laborCostPerSquareFootAsString = orderTokens[7];
        BigDecimal laborCostPerSquareFoot = new BigDecimal(laborCostPerSquareFootAsString);

        String materialCostAsString = orderTokens[8];
        BigDecimal materialCost = new BigDecimal(materialCostAsString);

        String laborCostAsString = orderTokens[9];
        BigDecimal laborCost = new BigDecimal(laborCostAsString);

        String taxAsString = orderTokens[10];
        BigDecimal tax = new BigDecimal(taxAsString);

        String totalAsString = orderTokens[11];
        BigDecimal total = new BigDecimal(totalAsString);

        String dateStr = orderTokens[12];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        return new Order(orderNumber,
                date,
                customerName,
                state,
                taxRate,
                productType,
                area,
                costPerSquareFoot,
                laborCostPerSquareFoot,
                materialCost,
                laborCost,
                tax,
                total
        );
    }

    private void loadProducts() throws Exception {
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
            String curLine;
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                curLine = scanner.nextLine();
                Product curProduct = unmarshallProducts(curLine);
                productsMap.put(curProduct.getProductType(), curProduct);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new Exception("-_- Could not load product data into memory.", e);
        }
        scanner.close();
    }

    private Product unmarshallProducts(String curLine) {
        String[] productsToken = curLine.split(DELIMITER);
        String productStr = productsToken[0];
        String costPerSquareFootStr = productsToken[1];
        String laborCostPerSquareFootStr = productsToken[2];
        BigDecimal costPerSquareFoot = new BigDecimal(costPerSquareFootStr);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(laborCostPerSquareFootStr);
        return new Product(productStr, costPerSquareFoot, laborCostPerSquareFoot);
    }

    public void loadTaxes() throws Exception {
        try (
                Scanner scanner = new Scanner(new File(TAXES_FILE))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                String stateAbbr = tokens[0];
                String stateName = tokens[1];
                BigDecimal taxRate = new BigDecimal(tokens[2]);
                statesMap.put(stateName, new State(stateAbbr, stateName, taxRate));
            }
        } catch (Exception e) {
            throw new Exception("-_- Could not load product data into memory.", e);
        }
    }

    private void setTaxes(Order curOrder) throws FileNotFoundException {
        switch (curOrder.getState()) {
            case "Texas":
                curOrder.setTaxRate(statesMap.get("Texas").getTaxRate());
                break;
            case "Washington":
                curOrder.setTaxRate(statesMap.get("Washington").getTaxRate());
                break;
            case "Kentucky":
                curOrder.setTaxRate(statesMap.get("Kentucky").getTaxRate());
                break;
            case "California":
                curOrder.setTaxRate(statesMap.get("California").getTaxRate());
                break;
            default:
        }

    }

    public void writeOrder() throws Exception {
        List<Order> orderList = this.getAllOrders();
        DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate dateStr;
        for (Order curOrder : orderList) {
            dateStr = curOrder.getDate();
            String fileName = "Orders_" + dateStr.format(formatter) + ".txt";
            File orderFile = new File("Orders", fileName);
            if (!orderFile.getParentFile().exists()) {
                if (!orderFile.getParentFile().mkdirs()) {
                    throw new ClassFlooringMasteryPersistenceException(
                            "Can't make the directory structure.");
                }
            }

            try (FileWriter out = new FileWriter(orderFile, true)) {
                String orderAsText = marshallOrder(curOrder);
                out.write(orderAsText);
                out.write(curOrder.getDate().format(formatterOut));
                out.write("\n");
                out.flush();
            } catch (IOException e) {
                throw new ClassFlooringMasteryPersistenceException("Error: can't write to file.");
            }
        }
    }

    private String marshallOrder(Order order) {
        String orderAsText;

        //Index 0 - int orderNumber
        orderAsText = order.getOrderNumber() + DELIMITER;

        // Index 1 - String customerName
        orderAsText += order.getCustomerName() + DELIMITER;

        // Index 2 - String State
        orderAsText += order.getState() + DELIMITER;

        //Index 3 - BigDecimal taxRATE
        orderAsText += order.getTaxRate() + DELIMITER;

        //Index 4 - String productType
        orderAsText += order.getProductType() + DELIMITER;

        //Index 5 - BigDecimal area
        orderAsText += order.getArea() + DELIMITER;

        //Index 6 - BigDecimal costPerSquareFoot
        orderAsText += order.getCostPerSquareFoot() + DELIMITER;

        //Index 7 - BigDecimal laborCostPerSquareFoot
        orderAsText += order.getLaborCostPerSquareFoot() + DELIMITER;

        //Index 8 - BigDecimal materialCost
        orderAsText += order.getMaterialCost() + DELIMITER;

        //Index 9 - BigDecimal laborCost
        orderAsText += order.getLaborCost() + DELIMITER;

        //Index 10 - BigDecimal tax
        orderAsText += order.getTax() + DELIMITER;

        //Index 11 - BigDecimal total
        orderAsText += order.getTotal() + DELIMITER;

        return orderAsText;
    }
}


