package com.example.comp_interest_calc;

import java.sql.*;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class HistoryItemRepo {

    //Is used by index.jsp to determine if history should be SHOWN or NOT
    public static boolean showHistory = false;

    /**
     * inserts Calculation items submitted by USER into Database
     * using JDBC MySQL Query
     * @param item
     */
    protected static void insertItem(HistoryItem item){
        Connection connection = CalcDAO.connection;//calling the global variable
        String insertQuery = "insert into PrevResults values(?,?,?,?,?,?)";
        int rowsAffected;                  //^^ Table Name
        int randomNum;


        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, item.getId());
            preparedStatement.setDouble(2, item.getPrincipleD());
            preparedStatement.setDouble(3, item.getInterestPercentD());
            preparedStatement.setInt(4, item.getYearsInt());
            preparedStatement.setInt(5, item.getPerYearInt());
            preparedStatement.setDouble(6, item.getResult());

            //For FeedBack
            rowsAffected = preparedStatement.executeUpdate();
            System.out.printf("Row/s affected : %d\n", rowsAffected);

        }
        catch (SQLException e){
            System.out.println("insertItem ERROR");
            System.out.println(e.getErrorCode());
        }
    }

    /**
     * @return HashMap<Integer,HistoryItem>
     * gets all Calculation items from database using JDBC MySQL Query
     * inside a HashMap
     */
    public static HashMap<Integer,HistoryItem> getItems(){
        Connection connection = CalcDAO.connection;
        String getQuery = "select * from PrevResults";
        HashMap<Integer, HistoryItem> historyItems = new HashMap<>();
        int count = 0;
        showHistory = true;

        try {
            //create statement and store Result in resultSet
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getQuery);

            //We get the values of each row
            //then store them in a "Historyitem" instance within
            //a Hashmap
            while (resultSet.next()) {
                double principleD = resultSet.getDouble("principle");
                double interestPercentD = resultSet.getDouble("interest");
                int yearsInt = resultSet.getInt("years");
                int perYearInt = resultSet.getInt("perYear");
                double result = resultSet.getDouble("result");

                HistoryItem item = new HistoryItem(principleD, interestPercentD, yearsInt, perYearInt, result);
                historyItems.put(count, item);
                count++;

            }
            return historyItems;
        }
        catch (SQLException e){
            System.out.println("getItems ERROR");
            System.out.println(e.getErrorCode());
        }
        return null;
    }

    /**
     * @param statement
     * @return int
     * Uses JDBC MySQL query to count # of items in Database
     */
    //is static because getItems() is static
    protected static int countRows(Statement statement) throws SQLException {
        String countQuery = "Select count(*) from PrevResults";
        ResultSet resultSet = statement.executeQuery(countQuery);

        //Starts at Header so need to move up 1
        resultSet.next();

        return resultSet.getInt("count(*)");
    }

    /**
     * Clears Database by dropping the Table and making a replacement
     * using JDBC MySQL Query
     */
    protected static void clearHistory(){
        Connection connection = CalcDAO.connection;
        String dropQuery = "drop table PrevResults";
        String createQuery = "create table PrevResults (\n" +
                "id int,\n" +
                "principle double, \n" +
                "interest double, \n" +
                "years int, \n" +
                "perYear int, \n" +
                "result double\n" +
                ")";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropQuery);
            System.out.println("Table deleted...");

            statement.executeUpdate(createQuery);
            System.out.println("Table created...");
        }
        catch (SQLException e) {
            System.out.println("clearHistory ERROR");
            System.out.println(e.getErrorCode());
        }
    }
}
