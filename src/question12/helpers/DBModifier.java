package question12.helpers;

import question12.models.Order;
import question12.models.Product;
import question12.models.Item;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Vasil Talkachou
 */
public class DBModifier {
    
    public static final String COMMAND_ADD_ORDER
        = "INSERT INTO orders (order_date) VALUES (?)";

    public static final String COMMAND_ADD_PRODUCT
        = "INSERT INTO products (product_name, product_info, product_cost) VALUES (?,?,?)";

    public static final String COMMAND_ADD_ITEM
        = "INSERT INTO items (order_id, product_id, items_quantity) VALUES (?,?,?)";

    public static final String COMMAND_DEL_ITEMS
        = "DELETE FROM items WHERE items.order_id=? "; 
        
    public static final String COMMAND_DEL_ORDER
        = "DELETE FROM orders WHERE orders.order_id=? ";
    
    private Connection connection;
    private PreparedStatement preparedStatement;

    public DBModifier() {
        connection = DBConnector.getConnection();
    }

    public void insertProduct(Product prod) {
        try {
            preparedStatement = connection.prepareStatement(COMMAND_ADD_PRODUCT);
            preparedStatement.setString(1, prod.getName());
            preparedStatement.setString(2, prod.getInfo());
            preparedStatement.setInt(3, prod.getCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertItem(Item item) {
        try { 
            preparedStatement = connection.prepareStatement(COMMAND_ADD_ITEM);
            preparedStatement.setInt(1, item.getOrderId());
            preparedStatement.setInt(2, item.getProductId());
            preparedStatement.setInt(3, item.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertOrder(Order order) {
        try {
            preparedStatement = connection.prepareStatement(COMMAND_ADD_ORDER);
            preparedStatement.setTimestamp(1, order.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteOrder(int id) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(COMMAND_DEL_ITEMS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(COMMAND_DEL_ORDER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void closeStatement() {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
