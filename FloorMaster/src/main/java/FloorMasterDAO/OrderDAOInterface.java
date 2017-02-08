/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDAO;

import FloorMasterDTO.Order;

/**
 *
 * @author apprentice
 */
public interface OrderDAOInterface {
    //C
    public void addOrder(Order currentOrder);
    //R
    public Object getOrderById(int orderId);
    public Order getOrderByDateAndId(String date, int orderId);
    public Object getAllOrdersByDate(String date);
    //U
    public void updateOrder(Order orderToEdit);
    //D
    public void removeOrder(Order order);
    
    public void load(String date);
    
    public void save(String date);

    public void clearCollection();
}
