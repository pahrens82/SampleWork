/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterUI;

import FloorMasterDTO.Order;
import java.text.DecimalFormat;

/**
 *
 * @author apprentice
 */
public class FloorMasterUI {

    FloorMasterConsole con = new FloorMasterConsole();
    DecimalFormat df = new DecimalFormat("#####.00");

    public void printMenu() {
        con.print("============Floor Master============");
        con.print("1. Display Order Details");
        con.print("2. Display Order List By Date");
        con.print("3. Add an Order");
        con.print("4. Edit an Order");
        con.print("5. Remove an Order");
        con.print("6. Save Orders");
        con.print("7. Quit Floor Master");
    }

    public String stateSelection() {
        int stateChoice = con.promptForIntInRange("We currently offer service to"
                + " the following states:\n1. Ohio\n2. Pennsylvania\n3. Michigan\n4. Indiana.\nWhich state do you live in? \n", 1, 4);
        String state = "";
        switch (stateChoice) {
            case 1:
                state = "OH";
                break;
            case 2:
                state = "PA";
                break;
            case 3:
                state = "MI";
                break;
            case 4:
                state = "IN";
                break;
            default:
                selectValidOption();
        }
        return state;
    }

    public void printThankYou() {
        con.print("Thank you for your business!");
    }

    public void selectValidOption() {
        con.print("Please select a valid option.");
    }

    public void printOrder(Order currentOrder) {
        con.print("Order ID:                  " + currentOrder.getOrderId());
        con.print("Purchaser Name:            " + currentOrder.getOrderName());
        con.print("Purchaser state:           " + currentOrder.getState());
        con.print("Purchaser state tax rate:  " + currentOrder.getTaxRate() + "%");
        con.print("Area to be covered:        " + currentOrder.getOrderArea());
        con.print("Material ordered:          " + currentOrder.getMaterialType());
        con.print("Material unit cost:        $" + df.format(currentOrder.getMaterialUnitCost()));
        con.print("Labor unit cost:           $" + df.format(currentOrder.getLaborUnitCost()));
        con.print("Total material cost:       $" + df.format(currentOrder.getTotalMaterialCost()));
        con.print("Total labor cost:          $" + df.format(currentOrder.getTotalLaborCost()));
        con.print("Subtotal                   $" + df.format(currentOrder.getSubTotal()));
        con.print("Tax on order:              $" + df.format(currentOrder.getTaxTotal()));
        con.print("Total bill for order:      $" + df.format(currentOrder.getOrderTotal()));
        con.print("=======================================");

    }

    public String materialSelecetion() {
        int materialSelection = con.promptForIntInRange("We currently offer four flooring products: \n"
                + "Please choose from the following menu:\n"
                + "1. Carpet:   $2.25 per square foot, $2.10 per hour labor cost.\n"
                + "2. Laminate: $1.75 per square foot, $2.10 per hour labor cost.\n"
                + "3. Tile:     $3.50 per square foot, $4.15 per hour labor cost.\n"
                + "4. Wood:     $5.15 per square foot, $4.15 per hour labor cost.\n", 1, 4);
        String material = "";
        switch (materialSelection) {
            case 1:
                material = "Carpet";
                break;
            case 2:
                material = "Laminate";
                break;
            case 3:
                material = "Tile";
                break;
            case 4:
                material = "Wood";
            default:
                selectValidOption();
        }
        return material;
    }

    public void printNoFile(String date) {
        con.print("No corresponding file found for date: " + date + ".");
    }

    public void printAbbreviatedOrder(Order o) {
        con.print("Order ID:                  " + o.getOrderId());
        con.print("Purchaser Name:            " + o.getOrderName());
        con.print("Material ordered:          " + o.getMaterialType());
        con.print("Total bill for order:      $" + df.format(o.getOrderTotal()));
        con.print("=======================================");
    }

}
