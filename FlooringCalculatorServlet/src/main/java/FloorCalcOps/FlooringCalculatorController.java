/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorCalcOps;

import FloorCalcDTO.Floor;

/**
 *
 * @author apprentice
 */
public class FlooringCalculatorController {

    public Floor calculateAreaAndCost(int length, int width, double price) {
        int area = length * width;
        double cost = area * price;
        Floor newFloor = new Floor(area, cost);
        return newFloor;
    }

    public double calculateLaborCost(Floor newFloor) {
        //$86 per hour
        //20 sqft per hour
        //5 sqft per increment
        //15 min increments
        //$21.5 per increment
        //Increment for this method refers to 1 15 minute block of time

        int numberOfIncrements = newFloor.getArea() / 5;
        double laborCost = numberOfIncrements * 21.5;
        return laborCost;
    }

}
