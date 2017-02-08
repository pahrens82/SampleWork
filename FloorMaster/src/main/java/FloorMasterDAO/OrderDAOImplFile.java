/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDAO;

import FloorMasterDTO.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class OrderDAOImplFile implements OrderDAOInterface {

    Order order = new Order();

    private HashMap<Integer, Order> orderCollection = new HashMap<>();
    private HashMap<Integer, Order> removedOrders = new HashMap<>();

    Date currentDate = new Date();
    SimpleDateFormat format = new SimpleDateFormat("MMddyyyy");
    String dateToString = format.format(currentDate);

    public static final String DELIMITER = "::";

    @Override
    public void load(String date) {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("OrderFiles/" + date)));

            String currentLine;
            String[] currentTokens;
            do {
                currentLine = sc.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                Order currentOrder = new Order();
                currentOrder.setOrderId(Integer.parseInt(currentTokens[0]));
                currentOrder.setOrderName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                currentOrder.setTaxRate(Double.parseDouble(currentTokens[3]));
                currentOrder.setOrderArea(Double.parseDouble(currentTokens[4]));
                currentOrder.setMaterialType(currentTokens[5]);
                currentOrder.setMaterialUnitCost(Double.parseDouble(currentTokens[6]));
                currentOrder.setLaborUnitCost(Double.parseDouble(currentTokens[7]));
                currentOrder.setTotalMaterialCost(Double.parseDouble(currentTokens[8]));
                currentOrder.setTotalLaborCost(Double.parseDouble(currentTokens[9]));
                currentOrder.setSubTotal(Double.parseDouble(currentTokens[10]));
                currentOrder.setTaxTotal(Double.parseDouble(currentTokens[11]));
                currentOrder.setOrderTotal(Double.parseDouble(currentTokens[12]));
                orderCollection.put(currentOrder.getOrderId(), currentOrder);

            } while (sc.hasNextLine());

            sc.close();

        } catch (FileNotFoundException fnf) {
            System.out.println("File " + date + " does not exist.");
        } catch (NoSuchElementException nee) {
            System.out.println("There no entries to read from this file.");
        }
    }

    @Override
    public void save(String date) {
        String fileName = date;
        try (PrintWriter outPrinter = new PrintWriter(new FileOutputStream(new File("OrderFiles/" + fileName), false))) {
            orderCollection.values().stream().forEach((orderToSave) -> {
                outPrinter.println(orderToSave.getOrderId() + DELIMITER
                        + orderToSave.getOrderName() + DELIMITER
                        + orderToSave.getState() + DELIMITER
                        + orderToSave.getTaxRate() + DELIMITER
                        + orderToSave.getOrderArea() + DELIMITER
                        + orderToSave.getMaterialType() + DELIMITER
                        + orderToSave.getMaterialUnitCost() + DELIMITER
                        + orderToSave.getLaborUnitCost() + DELIMITER
                        + orderToSave.getTotalMaterialCost() + DELIMITER
                        + orderToSave.getTotalLaborCost() + DELIMITER
                        + orderToSave.getSubTotal() + DELIMITER
                        + orderToSave.getTaxTotal() + DELIMITER
                        + orderToSave.getOrderTotal());
            });
            outPrinter.flush();
        } catch (FileNotFoundException fnf) {
            System.out.println("We cannot find the file: " + date);
            System.out.println(fnf.getMessage());
        }
    }

    @Override
    public void addOrder(Order currentOrder) {
        orderCollection.put(currentOrder.getOrderId(), currentOrder);
    }

    @Override
    public void removeOrder(Order orderToRemove) {
        orderCollection.remove(orderToRemove.getOrderId());
        removedOrders.put(orderToRemove.getOrderId(), orderToRemove);
    }

    @Override
    public void updateOrder(Order orderToEdit) {
        orderCollection.put(orderToEdit.getOrderId(), orderToEdit);
    }

    @Override
    public Order getOrderByDateAndId(String date, int orderId) {
        clearCollection();
        load(date);
        if (removedOrders.size() > 0) {
            orderCollection.keySet().removeAll(removedOrders.keySet());
        }
        HashMap<Integer, Order> orderMap = orderCollection;
        Order currentOrder = orderMap.get(orderId);
        return currentOrder;
    }

    @Override
    public Object getOrderById(int orderId) {
        HashMap<Integer, Order> orderMap = orderCollection;
        return orderMap.get(orderId);
    }

    @Override
    public HashMap<Integer, Order> getAllOrdersByDate(String date) {
        clearCollection();
        load(date);
        if (removedOrders.size() > 0) {
            orderCollection.keySet().removeAll(removedOrders.keySet());
        }
        return orderCollection;
    }

    @Override
    public void clearCollection() {
        orderCollection.clear();
    }
}
