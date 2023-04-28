package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing;";

    /*//Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    */
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;


        try {
            //STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a Connection
            conn = DriverManager.getConnection(DB_URL); //it could also have user and pass

            //STEP 3: Execute a query
            statement = conn.createStatement();
            String sql = "CREATE TABLE COMPANY " +
                    "(ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "NAME VARCHAR(255))";
            String sql2 = "DROP TABLE IF EXISTS company";
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql);

            //STEP 4: Clean-up environment
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

}