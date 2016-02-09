
package question12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Vasil Talkachou
 */
public class DBConnector {

    public static Connection getConnection() {
        Connection dbConnection = null;
        ResourceBundle resource = ResourceBundle.getBundle("resources/database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        try {
            dbConnection = DriverManager.getConnection(url, user, pass);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
