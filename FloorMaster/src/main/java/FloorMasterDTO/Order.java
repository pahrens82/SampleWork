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
public class Order {

    Tax tax = new Tax();
    Product product = new Product();
    private int orderId;
    private String orderName;
    private String state;
    private Double taxRate;
    private double orderArea;
    private String materialType;
    private double materialUnitCost;
    private double laborUnitCost;
    private double totalMaterialCost;
    private double totalLaborCost;
    private double subTotal;
    private double taxTotal;
    private double orderTotal;

    public Order() {

    }

    public Order(int orderId, String orderName, String state, double taxRate,
            double orderArea, String materialType, double materialUnitCost,
            double laborUnitCost, double totalMaterialCost,
            double totalLaborCost, double subTotal, double taxTotal,
            double orderTotal) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.state = state;
        this.taxRate = taxRate;
        this.orderArea = orderArea;
        this.materialType = materialType;
        this.materialUnitCost = materialUnitCost;
        this.laborUnitCost = laborUnitCost;
        this.totalMaterialCost = totalMaterialCost;
        this.totalLaborCost = totalLaborCost;
        this.subTotal = subTotal;
        this.taxTotal = taxTotal;
        this.orderTotal = orderTotal;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String name) {
        this.orderName = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public double getOrderArea() {
        return orderArea;
    }

    public void setOrderArea(double area) {
        this.orderArea = area;
    }

    public double getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(double costMaterialTotal) {
        this.totalMaterialCost = costMaterialTotal;
    }

    public double getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(double costLaborTotal) {
        this.totalLaborCost = costLaborTotal;
    }

    public double getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(double taxTotal) {
        this.taxTotal = taxTotal;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public double getMaterialUnitCost() {
        return materialUnitCost;
    }

    public void setMaterialUnitCost(Double materialUnitCost) {
        this.materialUnitCost = materialUnitCost;
    }

    public double getLaborUnitCost() {
        return laborUnitCost;
    }

    public void setLaborUnitCost(Double laborUnitCost) {
        this.laborUnitCost = laborUnitCost;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

}
