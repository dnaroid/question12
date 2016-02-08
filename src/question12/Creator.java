
package question12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Vasil Talkachou
 */
public class Creator {
    
    private static void sendPreparedSQL(String text, String s1, int i2) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection = Connector.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(text);
            preparedStatement.setString(1, s1);
            preparedStatement.setInt(2, i2);
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
    
    public static void createTables() {
        try {
            sendSQL(Constants.COMMAND_DROP_ALL_TABLES);
            sendSQL(Constants.COMMAND_CREATE_TABLE_ORDERS);
            sendSQL(Constants.COMMAND_CREATE_TABLE_PRODUCTS);
            sendSQL(Constants.COMMAND_CREATE_TABLE_ITEMS);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void addProducts() {
        try {
            for (String product : Constants.PRODUCTS) {
                sendSQL("INSERT INTO products " +
                        "(product_name, product_info, product_cost) VALUES " +
                        product);
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void addOrders() {
        try {
            for (String order : Constants.ORDERS) {
                sendSQL("INSERT INTO orders "
                    + "(order_id, order_date) VALUES "
                    + order);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void addItems() {
        try {
            for (String item : Constants.ITEMS) {
                sendSQL("INSERT INTO items "
                    + "(order_id, item_id, items_quantity) VALUES "
                    + item);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
