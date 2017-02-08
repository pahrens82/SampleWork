package FloorMasterUnitTests;

import FloorMasterDAO.OrderDAOInMem;
import FloorMasterDTO.Order;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author apprentice
 */
public class FloorMasterTests {
    // Methods to test:
    // addOrder
    // getOrderById
    // getOrderByDateAndId
    // updateOrder
    // removeOrder
    // getAllOrdersByDate

    OrderDAOInMem oDAO;

    @Before
    public void setup() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        oDAO = (OrderDAOInMem) ctx.getBean("OrderDAOInMem");
    }


    @Test
    public void addOrderAndRetrieveOrderTest() {
        //Arrange
        Order testOrder = new Order(1, "Ahrens", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77);
        //Act
        oDAO.addOrder(testOrder);
        Order returnOrder = oDAO.getOrderByDateAndId(null, 1);
        //Assert
        assertEquals(testOrder, returnOrder);

    }

    @Test
    public void addOneOrderHashMapSize() {
        //Arrange
        Order testOrder = new Order(1, "Ahrens", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77);
        int expectedMapSize = 1;
        //Act
        oDAO.addOrder(testOrder);
        //Assert
        assertEquals(expectedMapSize, oDAO.getAllOrdersByDate(null).size());
    }

    @Test
    public void addMultipleOrdersHashMapSize() {
        //Arrange
        Order[] ordersForTesting = {
            new Order(1, "Test1", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77),
            new Order(2, "Test2", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77),
            new Order(3, "Test3", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77),
            new Order(4, "Test4", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77),};
        int expectedMapSize = 4;
        //Act
        for (Order orders : ordersForTesting) {
            oDAO.addOrder(orders);
        }
        //Assert
        assertEquals(expectedMapSize, oDAO.getAllOrdersByDate(null).size());
    }

    @Test
    public void addAndRemoveOneOrder() {
        //Arrange
        Order testOrder = new Order(1, "Ahrens", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77);
        int expectedSizeBeforeRemoval = 1;
        int expectedSizeAfterRemoval = 0;
        //Act
        oDAO.addOrder(testOrder);
        assertEquals(expectedSizeBeforeRemoval, oDAO.getAllOrdersByDate(null).size());
        oDAO.removeOrder(testOrder);
        //Assert
        assertEquals(expectedSizeAfterRemoval, oDAO.getAllOrdersByDate(null).size());
        assertNotNull(oDAO.getAllOrdersByDate(null));
    }

    @Test
    public void addAndRemoveMultipleOrders() {
        Order[] ordersForTesting = {
            new Order(1, "Test1", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77),
            new Order(2, "Test2", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77),
            new Order(3, "Test3", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77),
            new Order(4, "Test4", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77),};
        int expectedMapSize3 = 3;
        int expectedMapSize2 = 2;
        int expectedMapSize1 = 1;
        int expectedMapSize0 = 0;

        //Act
        for (Order orders : ordersForTesting) {
            oDAO.addOrder(orders);
        }

        //Assert
        oDAO.removeOrder(oDAO.getOrderById(1));
        assertEquals(expectedMapSize3, oDAO.getAllOrdersByDate(null).size());
        oDAO.removeOrder(oDAO.getOrderById(2));
        assertEquals(expectedMapSize2, oDAO.getAllOrdersByDate(null).size());
        oDAO.removeOrder(oDAO.getOrderById(3));
        assertEquals(expectedMapSize1, oDAO.getAllOrdersByDate(null).size());
        oDAO.removeOrder(oDAO.getOrderById(4));
        assertEquals(expectedMapSize0, oDAO.getAllOrdersByDate(null).size());
        assertNotNull(oDAO.getAllOrdersByDate(null).size());

    }

    @Test
    public void getOrderByIdTest() {
        //Arrange
        Order testOrder = new Order(1, "Ahrens", "MI", 5.75, 40.0, "Wood", 5.15, 4.75, 206.0, 190.0, 396.0, 22.77, 418.77);
        //Act
        oDAO.addOrder(testOrder);
        Order returnOrder = oDAO.getOrderByDateAndId(null, 1);
        //Assert
        assertEquals(testOrder.getOrderId(), returnOrder.getOrderId());
    }
}
