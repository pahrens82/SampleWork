/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterOps;

/**
 *
 * @author apprentice
 */
public class FloorMasterBusinessLogic {

    public double calcMaterialCost(double area, double materialCostPerSqFt) {
        double materialCost = area * materialCostPerSqFt;
        return materialCost;
    }

    public double calcLaborCost(double area, double laborCostPerSqFt) {
        double laborCost = area * laborCostPerSqFt;
        return laborCost;
    }

    public double calcTax(double taxRate, double totalCost) {
        double tax = (taxRate / 100) * totalCost;
        return tax;
    }

    public double calcTotal(double tax, double subTotal) {
        double total = tax + subTotal;
        return total;
    }

    public double calcSubTotal(double totalMaterialCost, double totalLaborCost) {
        double subTotal = totalMaterialCost + totalLaborCost;
        return subTotal;
    }
}
