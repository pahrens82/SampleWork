/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDAO;

import FloorMasterDTO.Tax;

/**
 *
 * @author apprentice
 */
public interface TaxDAOInterface {

    void loadAllTaxes();

    double getTaxByState(String state);
}
