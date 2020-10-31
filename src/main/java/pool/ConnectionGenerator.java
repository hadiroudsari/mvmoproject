package pool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


/**
 * @author STS
 */
public class ConnectionGenerator {
    private final static String url = "jdbc:postgresql://pg:5432/postgres";
    private final static String user = "postgres";
    private final static String password = "1234";

    public synchronized static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException ex) {
            System.out.println("Connection problem");
            System.out.println(ex.toString());
        }

        return conn;
    }
}