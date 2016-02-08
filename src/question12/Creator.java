
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
    
    public static void createAllTables() {
        try {
            sendSQL(Constants.COMMAND_CREATE_TABLE_ORDERS);
            sendSQL(Constants.COMMAND_CREATE_TABLE_PRODUCTS);
            sendSQL(Constants.COMMAND_CREATE_TABLE_ITEMS);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void addAllProducts() {
        try {
            for(int i = 0; i < Constants.PRODUCTS_COST.length; i++) {
                sendPreparedSQL(Constants.COMMAND_ADD_PRODUCT,
                                Constants.PRODUCTS_NAME_INFO[i],
                                Constants.PRODUCTS_COST[i]);
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
