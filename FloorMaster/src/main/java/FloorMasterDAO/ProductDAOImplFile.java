/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDAO;

import FloorMasterDTO.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ProductDAOImplFile implements ProductDAOInterface {

    private HashMap<String, Product> productCollection = new HashMap<>();
    private final String PRODUCT_FILE = "Data/productfile.txt";
    public static final String DELIMITER = "::";

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

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));

            String currentLine;
            String[] currentTokens;
            do {
                currentLine = sc.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                Product currentProduct = new Product();
                currentProduct.setMaterialName(currentTokens[0]);
                currentProduct.setMaterialCostPerSqFt(Double.parseDouble(currentTokens[1]));
                currentProduct.setLaborCostPerSqFt(Double.parseDouble(currentTokens[2]));
                productCollection.put(currentProduct.getMaterialName(), currentProduct);

            } while (sc.hasNextLine());

            sc.close();

        } catch (FileNotFoundException fnf) {
            System.out.println("Problem reading from file: " + PRODUCT_FILE);
            System.out.println(fnf.getMessage());
        }
    }
}
