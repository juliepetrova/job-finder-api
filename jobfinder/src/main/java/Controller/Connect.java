package Controller;

//import com.mysql.jdbc.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public Connect() {
        Connection conn = null;

        try {

            String dbName = "dbi425782";
            String dbUserName = "dbi425782";
            String dbPassword = "yuliaadvanced";
            String connectionString = "jdbc:mysql://studmysql01.fhict.local/" + dbName + "?user=" + dbUserName +
                    "&password=" + dbPassword + "&useUnicode=true&characterEncoding=UTF-8";

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection) DriverManager.getConnection(connectionString);
            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Database Connection Terminated");
                } catch (Exception e) {}
            }
        }
    }
}
