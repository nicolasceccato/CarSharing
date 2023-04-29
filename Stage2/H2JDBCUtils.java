package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2JDBCUtils {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing;";

    /*//Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    */

    public static void createDB() {
        //STEP 3: Execute a query
        String sql = "CREATE TABLE COMPANY " +
                "(ID  INT          AUTO_INCREMENT PRIMARY KEY, " +
                "NAME VARCHAR(255) UNIQUE         NOT NULL)";

        String sql2 = "DROP TABLE IF EXISTS company";

        try (Connection conn = H2JDBCUtils.getConnection();) {
            Statement statement;
            statement = conn.createStatement();
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return connection;
    }
}

