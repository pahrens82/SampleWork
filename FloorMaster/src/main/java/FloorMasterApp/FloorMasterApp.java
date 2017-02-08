/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterApp;

import FloorMasterOps.FloorMasterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FloorMasterApp {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FloorMasterController controller = (FloorMasterController) ctx.getBean("floorMasterControllerFileIO");
        //FloorMasterController controller = (FloorMasterController) ctx.getBean("floorMasterControllerInMem");
        controller.run();

    }
}
