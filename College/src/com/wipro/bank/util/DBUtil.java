package com.wipro.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection getDBConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url  = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "system";
            String pass = "swathi418";

            Connection con =
                    DriverManager.getConnection(url, user, pass);

            return con;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
