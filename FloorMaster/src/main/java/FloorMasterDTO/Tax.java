/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDTO;

/**
 *
 * @author apprentice
 */
public class Tax {

    private String state;
    private double taxRate;

    public Tax() {

    }

    public Tax(String state, double taxRate) {
        this.state = state;
        this.taxRate = taxRate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
