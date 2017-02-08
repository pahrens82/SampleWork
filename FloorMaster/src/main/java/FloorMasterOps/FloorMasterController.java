/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterOps;

import FloorMasterDAO.OrderDAOInterface;
import FloorMasterDAO.ProductDAOImplFile;
import FloorMasterDAO.ProductDAOInterface;
import FloorMasterDAO.TaxDAOImplFile;
import FloorMasterDAO.TaxDAOInterface;
import FloorMasterDTO.Order;
import FloorMasterUI.FloorMasterConsole;
import FloorMasterUI.FloorMasterUI;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class FloorMasterController {

    private FloorMasterConsole con = new FloorMasterConsole();
    private FloorMasterBusinessLogic logic = new FloorMasterBusinessLogic();
    private FloorMasterUI ui = new FloorMasterUI();
    private OrderDAOInterface oDAO;
    private TaxDAOInterface tDAO = new TaxDAOImplFile();
    private ProductDAOInterface pDAO = new ProductDAOImplFile();
    Date currentDate = new Date();
    SimpleDateFormat formatMonthDayYear = new SimpleDateFormat("MMddyyyy");
    String monthDayYear = formatMonthDayYear.format(currentDate);
    int menuChoice = 0;
    private String date;
    boolean keepRunning = true;

    public FloorMasterController(OrderDAOInterface oDAO, ProductDAOInterface pDAO, TaxDAOInterface tDAO) {
        this.oDAO = oDAO;
        this.pDAO = pDAO;
        this.tDAO = tDAO;
    }

    public void run() {
        tDAO.loadAllTaxes();
        pDAO.loadAllProducts();
        do {
            ui.printMenu();
            menuChoice = con.promptForIntInRange("Please select an option from the menu: ", 1, 7);
            switch (menuChoice) {
                case 1:
                    displaySingleOrder();
                    break;
                case 2:
                    displayOrderList();
                    break;
                case 3:
                    createOrder();
                    break;
                case 4:
                    editOrder();
                    break;
                case 5:
                    removeOrder();
                    break;
                case 6:
                    saveOrders(date);
                    break;
                case 7:
                    quitProgram(date);
                    break;
                default:
                    ui.selectValidOption();
            }

        } while (keepRunning);
    }

    private String displaySingleOrder() {
        date = con.promptForString("Please enter the date of your order (mmddyyyy format please) ");
        boolean fileNameOK = fileNameCheck(date);
        if (fileNameOK == true) {
            int orderNumber = con.promptForIntInRange("Please enter your order number ", 1, 10000);
            Order currentOrder = oDAO.getOrderByDateAndId(date, orderNumber);
            if (currentOrder == null) {
                ui.printNoFile(date);
            } else {
                ui.printOrder(currentOrder);
            }
        } else if (fileNameOK == false) {
            ui.printNoFile(date);
        }
        return date;
    }

    private String displayOrderList() {
        date = con.promptForString("Please enter the date of your order (mmddyyyy format please) ");
        boolean fileNameOK = fileNameCheck(date);
        if (fileNameOK == true) {
            HashMap<Integer, Order> orders = (HashMap<Integer, Order>) oDAO.getAllOrdersByDate(date);
            if (orders.size() < 1) {
                ui.printNoFile(date);
            }
            orders.values().stream().forEach((o) -> {
                ui.printAbbreviatedOrder(o);
            });
        } else if (fileNameOK == false) {
            ui.printNoFile(date);
        }
        return date;
    }

    private String createOrder() {
        oDAO.clearCollection();
        boolean createMoreOrders = true;
        while (createMoreOrders == true) {
            File file = new File("OrderFiles/" + monthDayYear);
            boolean fileCreated = false;
            try {
                fileCreated = file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FloorMasterController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (fileCreated == true) {
                con.print(file.getName() + " was created.");
            } else if (fileCreated == false) {
                con.print("This order will be added to file " + monthDayYear);
                oDAO.load(monthDayYear);
            }

            Order newOrder = new Order();
            int orderId = 1;
            while (oDAO.getOrderById(orderId) != null) {
                orderId++;
            }
            newOrder.setOrderId(orderId);
            newOrder.setOrderName(con.promptForString("Please enter the customer name: "));
            String stateSelection = ui.stateSelection();
            newOrder.setState(stateSelection);
            newOrder.setTaxRate(tDAO.getTaxByState(stateSelection));
            newOrder.setOrderArea(con.promptForDoubleRange("Please enter the area you are planning to cover: ", 1, 2000));
            String materialSelection = ui.materialSelecetion();
            newOrder.setMaterialType(materialSelection);
            newOrder.setMaterialUnitCost(pDAO.getMaterialUnitCost(materialSelection));
            newOrder.setLaborUnitCost(pDAO.getLaborUnitCost(materialSelection));
            newOrder.setTotalMaterialCost(logic.calcMaterialCost(newOrder.getMaterialUnitCost(), newOrder.getOrderArea()));
            newOrder.setTotalLaborCost(logic.calcLaborCost(pDAO.getLaborUnitCost(materialSelection), newOrder.getOrderArea()));
            newOrder.setSubTotal(logic.calcSubTotal(newOrder.getTotalMaterialCost(), newOrder.getTotalLaborCost()));
            newOrder.setTaxTotal(logic.calcTax(newOrder.getTaxRate(), newOrder.getSubTotal()));
            newOrder.setOrderTotal(logic.calcTotal(newOrder.getTaxTotal(), newOrder.getSubTotal()));

            con.print("Here are the details of the order you have entered: ");
            ui.printOrder(newOrder);
            String userResponse = con.promptForYesOrNo("Please enter Yes to keep this order or No to discard it. ");
            if (userResponse.toLowerCase().contains("y")) {
                oDAO.addOrder(newOrder);
                con.print("This order has been stored for the session. Please do not forget to save it. ");
            } else if (userResponse.toLowerCase().contains("n")) {
                con.print("This order has been discarded.");
            }

            String createMoreOrdersQuery = con.promptForYesOrNo("Would you like to add another order? ");
            if (createMoreOrdersQuery.toLowerCase().contains("y")) {
                createMoreOrders = true;
            } else if (createMoreOrdersQuery.toLowerCase().contains("n")) {
                createMoreOrders = false;
            }
            date = monthDayYear;
        }
        return date;
    }

    private String editOrder() {
        date = con.promptForString("Please enter the date of your order (mmddyy format please) ");
        boolean fileNameOK = fileNameCheck(date);
        if (fileNameOK == true) {
            int orderId = con.promptForIntInRange("Please enter the ID of the order you would like to edit. ", 1, 10000);
            Order orderToEdit = oDAO.getOrderByDateAndId(date, orderId);
            oDAO.load(date);
            if (orderToEdit == null) {
                con.print("That order does not exist.");
            } else {
                con.print("Enter your new information. ");
                String orderName = con.promptForString("Order name (" + orderToEdit.getOrderName() + "): ");
                String stateSelection = ui.stateSelection();
                String orderArea = con.promptForString("Area ( " + orderToEdit.getOrderArea() + "): ");
                String materialSelection = ui.materialSelecetion();

                if (!orderName.isEmpty()) {
                    orderToEdit.setOrderName(orderName);
                }

                if (!stateSelection.isEmpty()) {
                    orderToEdit.setState(stateSelection);
                    orderToEdit.setTaxRate(tDAO.getTaxByState(stateSelection));
                }

                if (!orderArea.isEmpty()) {
                    double area = 0;
                    try {
                        area = Double.parseDouble(orderArea);
                    } catch (NumberFormatException nfe) {
                        area = con.promptForDoubleRange("Please enter an area for your order. ", 1, 2000);
                    }
                    orderToEdit.setOrderArea(area);
                }

                if (!materialSelection.isEmpty()) {
                    orderToEdit.setMaterialType(materialSelection);
                    orderToEdit.setMaterialUnitCost(pDAO.getMaterialUnitCost(materialSelection));
                    orderToEdit.setLaborUnitCost(pDAO.getLaborUnitCost(materialSelection));
                    orderToEdit.setTotalMaterialCost(logic.calcMaterialCost(orderToEdit.getMaterialUnitCost(), orderToEdit.getOrderArea()));
                    orderToEdit.setTotalLaborCost(logic.calcLaborCost(pDAO.getLaborUnitCost(materialSelection), orderToEdit.getOrderArea()));
                    orderToEdit.setSubTotal(logic.calcSubTotal(orderToEdit.getTotalMaterialCost(), orderToEdit.getTotalLaborCost()));
                    orderToEdit.setTaxTotal(logic.calcTax(orderToEdit.getTaxRate(), orderToEdit.getSubTotal()));
                    orderToEdit.setOrderTotal(logic.calcTotal(orderToEdit.getTaxTotal(), orderToEdit.getSubTotal()));
                }

                con.print("Here are the details of the order you have entered: ");
                ui.printOrder(orderToEdit);
                String userResponse = con.promptForYesOrNo("Please enter Yes to keep this order or No to discard it. ");
                if (userResponse.toLowerCase().contains("y")) {
                    oDAO.addOrder(orderToEdit);
                    con.print("This order has been stored for the session. The changes will not be reflected until the file has been saved.");
                } else if (userResponse.toLowerCase().contains("n")) {
                    con.print("This order has been discarded.");
                }
                oDAO.updateOrder(orderToEdit);
            }
        } else if (fileNameOK == false) {
            ui.printNoFile(date);
        }
        return date;
    }

    private String removeOrder() {
        date = con.promptForString("Please enter the date of your order (mmddyyyy format please) ");
        boolean fileNameOK = fileNameCheck(date);
        if (fileNameOK == true) {
            int orderId = con.promptForIntInRange("Please enter the ID of the order you would like to remove ", 1, 10000);
            oDAO.load(date);
            Order orderToRemove = oDAO.getOrderByDateAndId(date, orderId);
            if (orderToRemove == null) {
                con.print("That order does not exist.");
            } else {
                String userResponse = con.promptForYesOrNo("Please enter Yes to remove this order, or No to retain it. ");
                if (userResponse.toLowerCase().contains("y")) {
                    oDAO.removeOrder(orderToRemove);
                    con.print("This order has removed; this change will be reflected after the file has been saved.");
                } else if (userResponse.toLowerCase().contains("n")) {
                    con.print("This order has been retained.");
                }
            }
        } else {
            ui.printNoFile(date);
        }
        return date;
    }

    private void saveOrders(String date) {
        String dateToSave = date;
        oDAO.save(dateToSave);
        con.print("Your orders have been saved to file.");
    }

    private void quitProgram(String date) {
        ui.printThankYou();
        keepRunning = false;
    }

    private boolean fileNameCheck(String date) {
        File f = new File(date);
        boolean fileNameOK = false;
        boolean fileNameExtant = false;
        if (fileNameExtant == f.exists()) {
            fileNameOK = true;
        } else if (fileNameExtant != f.exists()) {
            fileNameOK = false;
        }
        return fileNameOK;
    }
}
