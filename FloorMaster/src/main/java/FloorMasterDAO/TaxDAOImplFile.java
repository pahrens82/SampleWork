/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDAO;

import FloorMasterDTO.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class TaxDAOImplFile implements TaxDAOInterface {

    private HashMap<String, Tax> taxCollection = new HashMap<>();
    private final String TAX_FILE = "Data/taxfile.txt";
    public static final String DELIMITER = "::";

    @Override
    public void loadAllTaxes() {

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));

            String currentLine;
            String[] currentTokens;
            do {
                currentLine = sc.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                Tax currentTax = new Tax();
                currentTax.setState(currentTokens[0]);
                currentTax.setTaxRate(Double.parseDouble(currentTokens[1]));
                taxCollection.put(currentTax.getState(), currentTax);

            } while (sc.hasNextLine());

            sc.close();

        } catch (FileNotFoundException fnf) {
            System.out.println("Problem reading from file: " + TAX_FILE);
            System.out.println(fnf.getMessage());
        }
    }

    @Override
    public double getTaxByState(String state) {
        double taxRate = taxCollection.get(state).getTaxRate();
        return taxRate;
    }

}
