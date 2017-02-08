/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDAO;

import FloorMasterDTO.Product;
import java.util.HashMap;

/**
 *
 * @author apprentice
 */
public class ProductDAOInMem implements ProductDAOInterface {

    private HashMap<String, Product> productCollection = new HashMap<>();

    @Override
    public double getMaterialUnitCost(String materialSelection) {
        double unitMaterialPrice = productCollection.get(materialSelection).getMaterialCostPerSqFt();
        return unitMaterialPrice;
    }

    @Override
    public double getLaborUnitCost(String materialSelection) {
        double unitLaborPrice = productCollection.get(materialSelection).getLaborCostPerSqFt();
        return unitLaborPrice;
    }

    @Override
    public void loadAllProducts() {
        Product carpet = new Product("Carpet", 2.25, 2.10);
        Product laminate = new Product("Laminate", 1.75, 2.10);
        Product tile = new Product("Tile", 3.50, 4.15);
        Product wood = new Product("Wood", 5.15, 4.74);
        productCollection.put("Carpet", carpet);
        productCollection.put("Laminate", laminate);
        productCollection.put("Tile", tile);
        productCollection.put("Wood", wood);
    }

}
