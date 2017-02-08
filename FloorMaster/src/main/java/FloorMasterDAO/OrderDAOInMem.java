/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDAO;

import FloorMasterDTO.Order;
import java.util.HashMap;

/**
 *
 * @author apprentice
 */
public class OrderDAOInMem implements OrderDAOInterface {

    Order order = new Order();

    private HashMap<Integer, Order> orderCollection = new HashMap<>();

    @Override
    public void addOrder(Order currentOrder) {
        orderCollection.put(currentOrder.getOrderId(), currentOrder);
    }

    @Override
    public Order getOrderById(int orderId) {
        //HashMap<Integer, Order> orderMap = orderCollection;
        return orderCollection.get(orderId);
    }

    @Override
    public Order getOrderByDateAndId(String date, int orderId) {
        load(date);
        HashMap<Integer, Order> orderMap = orderCollection;
        Order currentOrder = orderMap.get(orderId);
        return currentOrder;
    }

    @Override
    public void updateOrder(Order orderToEdit) {
        orderCollection.put(orderToEdit.getOrderId(), orderToEdit);
    }

    @Override
    public void removeOrder(Order orderToRemove) {
        orderCollection.remove(orderToRemove.getOrderId());
    }

    @Override
    public void load(String date) {
        //Does nothing in InMem state
    }

    @Override
    public void save(String date) {
        //Does nothing in InMem state
    }

    @Override
    public HashMap<Integer, Order> getAllOrdersByDate(String date) {
        load(date);
        return orderCollection;
    }

    @Override
    public void clearCollection() {
        orderCollection.clear();
    }

}
