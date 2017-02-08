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
public class Product {

    private String materialName;
    private double materialCostPerSqFt;
    private double laborCostPerSqFt;

    public Product() {
       
    }
    
    public Product(String materialName, double materialCostPerSqFt, double laborCostPerSqFt) {
        this.materialName = materialName;
        this.materialCostPerSqFt = materialCostPerSqFt;
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getMaterialCostPerSqFt() {
        return materialCostPerSqFt;
    }

    public void setMaterialCostPerSqFt(double materialCostPerSqFt) {
        this.materialCostPerSqFt = materialCostPerSqFt;
    }

    public double getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(double laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

}
