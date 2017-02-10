package ServletPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author apprentice
 */
public class LuckySevensServlet extends HttpServlet {

    Random die1 = new Random();
    Random die2 = new Random();
    int d1;
    int d2;
    int sum = 0;
    int rollNumber = 0;
    int maxRoll = 1;
    String printNumber = Integer.toString(rollNumber);
    String printRoll = Integer.toString(maxRoll);
    String printDollars = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int myBet = Integer.parseInt(request.getParameter("myBet"));

        int maxDollars = myBet;

//While loop to play game        
        while (myBet > 0) {
            if (sum != 7) {
                myBet = (myBet -= 1);
            } else {
                myBet = (myBet += 4);
            }
            d1 = die1.nextInt(5) + 1;
            d2 = die2.nextInt(5) + 1;
            sum = (d1 + d2);
            rollNumber++;
//Determines at which roll the player had the most dollars            
            if (myBet > maxDollars) {
                maxDollars = myBet;
                maxRoll++;
            }

            printNumber = Integer.toString(rollNumber);
            printRoll = Integer.toString(maxRoll);
            printDollars = Integer.toString(maxDollars);

        }
        String message = "You are broke after " + printNumber + " rolls. You "
                + "should have quit after " + printRoll + " rolls when you had "
                + "$" + printDollars + ".00.";

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("LuckySevensResult.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("LuckySevensEntry.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int myBet = Integer.parseInt(request.getParameter("myBet"));
        int maxDollars = myBet;

//While loop to play game        
        while (myBet > 0) {
            if (sum != 7) {
                myBet = (myBet -= 1);
            } else {
                myBet = (myBet += 4);
            }
            d1 = die1.nextInt(5) + 1;
            d2 = die2.nextInt(5) + 1;
            sum = (d1 + d2);
            rollNumber++;
//Determines at which roll the player had the most dollars            
            if (myBet > maxDollars) {
                maxDollars = myBet;
                maxRoll++;
            }

            printNumber = Integer.toString(rollNumber);
            printRoll = Integer.toString(maxRoll);
            printDollars = Integer.toString(maxDollars);

        }
        String message = "You are broke after " + printNumber + " rolls. You "
                + "should have quit after " + printRoll + " rolls when you had "
                + "$" + printDollars + ".00.";

        request.setAttribute("message", message);

        RequestDispatcher rd = request.getRequestDispatcher("LuckySevensResult.jsp");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
