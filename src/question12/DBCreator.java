
package question12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Vasil Talkachou
 */
public class DBCreator {
    
    private static void sendPreparedSQL(String text, String s1, String s2, int i1) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection = Connector.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(text);
            preparedStatement.setString(1, s2);
            preparedStatement.setString(1, s2);
            preparedStatement.setInt(3, i1);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static void sendSQL(String text) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        try {
            dbConnection = Connector.getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(text);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
    
    public static void createAllTables() {
        try {
            sendSQL(Constants.CREATE_TABLE_ORDERS);
            sendSQL(Constants.CREATE_TABLE_PRODUCTS);
            sendSQL(Constants.CREATE_TABLE_ITEMS_IN_ORDER);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void addAllProducts() {
        try {
            sendSQL("INSERT INTO products (product_name, product_info,product_cost) VALUES ('valera', 'valera@mail.ru', '2222');");
               
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
