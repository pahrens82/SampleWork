/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDAO;

import FloorMasterDTO.Tax;
import java.util.HashMap;

/**
 *
 * @author apprentice
 */
public class TaxDAOInMem implements TaxDAOInterface {

    private HashMap<String, Tax> taxCollection = new HashMap<>();

    @Override
    public void loadAllTaxes() {
        Tax ohio = new Tax("OH", 6.25);
        Tax pennsylvania = new Tax("PA", 6.75);
        Tax michigan = new Tax("MI", 5.75);
        Tax indiana = new Tax("IN", 6.00);
        taxCollection.put("OH", ohio);
        taxCollection.put("PA", pennsylvania);
        taxCollection.put("MI", michigan);
        taxCollection.put("IN", indiana);
    }

    @Override
    public double getTaxByState(String state) {
        double taxRate = taxCollection.get(state).getTaxRate();
        return taxRate;
    }

}
