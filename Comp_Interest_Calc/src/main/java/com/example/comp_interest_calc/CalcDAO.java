package com.example.comp_interest_calc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class CalcDAO {
                                                            //  VV DataBase Name
    private static String url = "jdbc:mysql://localhost:3306/calc_history";
    private static String userName = "root";
    private static String passWord = "root";
    protected static Connection connection;

    /**
     *  Load MySQL Driver only ONCE
     */
    static{
        try {Class.forName("com.mysql.cj.jdbc.Driver");}
        catch (ClassNotFoundException e) {System.out.println("Couldn't load Driver");}
    }

    /**
     *  @param newURL,newUserName,newPassword
     *  updates MySQL login credentials
     */
    protected static void ChangeCredentials(String newURL, String newUserName, String newPassword){
        url = newURL;
        userName = newUserName;
        passWord = newPassword;
    }

    /**
     *  So we Don't have to input url,userName,passWord outside the CLASS
     *  every time we want to use connection
     */
    protected static void connect(){
        try{
            connection = DriverManager.getConnection(url,userName,passWord);
        }
        catch (SQLException e) {
            System.out.println("Invalid Connection - Wrong URl, Username, or Password");
            System.out.println(e.getErrorCode());
        }
    }

    protected static String getUrl() {
        return url;
    }

    protected static String getUserName() {
        return userName;
    }

    protected static String getPassWord() {
        return passWord;
    }

}
