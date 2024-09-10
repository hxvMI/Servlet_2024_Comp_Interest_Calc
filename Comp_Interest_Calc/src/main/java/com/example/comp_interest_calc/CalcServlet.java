package com.example.comp_interest_calc;
import java.io.*;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.junit.platform.commons.logging.LoggerFactory;

//NOTE: if MySQL No Connection Established refer to

/**
 * USER FLOW 1:
 * index.jsp                         (http://localhost:8080/)
        V
 * submit POST to /Calculate         (http://localhost:8080/Calculate)
        V
 * doPost() does stuff and displays
   HTML from index.jsp               (http://localhost:8080/Calculate)
        V
 * END
 */


/**
 * USER FLOW 2:
 * starts at /Calculate                   (http://localhost:8080/Calculate)
        V
 * doGet() is called
   displays HTML from index.jsp            (http://localhost:8080/Calculate)
        V
 * doPost() does stuff and displays
   HTML from index.jsp                    (http://localhost:8080/Calculate)
        V
 * END
 */

@WebServlet(name = "CalcServlet", urlPatterns = "/Calculate")
public class CalcServlet extends HttpServlet {

    public void init() {
    }

    /**
     * By default, USER starts at index.jsp
     * doGet sends RequestDispatcher to GET the HTML from index.jsp
     * IF someone Directly goes to /Calculate
     * like if they were to type in the exact URL
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("GET REQUEST GOT");

        getServletContext()
                .getRequestDispatcher("/index.jsp")
                .forward(request,response);
    }

    /**
     * Is called when user Submits the FORM
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("POST REQUEST GOT");

        //Get User Input
        String principle = request.getParameter("principle");
        String interest = request.getParameter("interest");
        String years = request.getParameter("years");
        String perYear = request.getParameter("perYear");
        String history = request.getParameter("history");
        String delete = request.getParameter("delete");

        //Checking Input
        if (Utils.checkInput(principle, interest, years, perYear)) {
            double compoundInterest = Utils.calculateInterest(principle, interest, years, perYear); //Calculate Interest
            request.setAttribute("compoundInterest", "Result: " + compoundInterest);    //Set result in request
            retainInput(request,principle,interest,years,perYear); //retain previous User Input

            showHistory(request,history);
            deleteHistory(delete);
        }
        else {
            request.setAttribute("incomplete","Incomplete Form");
            retainInput(request,principle,interest,years,perYear);
        }

        //Forward CompoundInterest Attribute
        getServletContext()
                .getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }

    /**
     *  @param request,principle,interest,years,perYear
     *  Stores old INPUT in request
     *  so values remain on Page when "Calculate" is clicked
     */
    protected void retainInput(HttpServletRequest request, String principle, String interest, String years, String perYear) throws ServletException, IOException{
        request.setAttribute("rPrinciple",principle);
        request.setAttribute("rInterest",interest);
        request.setAttribute("rYears",years);
        request.setAttribute("rPerYear",perYear);
    }

    /**
     *  @param request,history
     *  Converts request Parameter to Boolean
     *  if true Calls HistoryItem.getItems(); to retrieve Database items
     *  and sets request Attribute "showHistory" used in index.jsp to determine if HistoryItems should be shown or not
     */
    protected void showHistory(HttpServletRequest request, String history) throws ServletException, IOException {
        // Shows Calculation History if the checkbox is checked
        if (history != null && history.equals("on")) {
            request.setAttribute("showHistory", true);  // Set an attribute to indicate history should be shown
        } else {
            request.setAttribute("showHistory", false); // Ensure history is not shown if checkbox is unchecked
        }
    }


    /**
     *  @param delete
     *  Converts request Parameter to Boolean
     *  if true Calls HistoryItem.clearHistory();
     */
    protected void deleteHistory(String delete) throws ServletException, IOException {
        //Clears Calculation History if true
        if (delete != null) {
            HistoryItemRepo.clearHistory();
        }
    }

    /**
     * Automatically CLEARS Calculation history when Server Ends
     */
    public void destroy() {
        HistoryItemRepo.clearHistory();
    }
}







