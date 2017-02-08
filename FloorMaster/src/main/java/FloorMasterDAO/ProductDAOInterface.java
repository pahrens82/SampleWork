/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterDAO;

/**
 *
 * @author apprentice
 */
public interface ProductDAOInterface {

    public double getMaterialUnitCost(String materialSelection);

    public double getLaborUnitCost(String materialSelection);

    void loadAllProducts();

}
