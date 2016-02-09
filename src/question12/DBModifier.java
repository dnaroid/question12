package question12;

import java.sql.*;

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

    
    private Connection connection;
    private PreparedStatement preparedStatement;

    public DBModifier() {
        connection = DBConnector.getConnection();
    }

    public boolean insertProduct(Product prod) {
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(COMMAND_ADD_PRODUCT);
            preparedStatement.setString(1, prod.getName());
            preparedStatement.setString(2, prod.getInfo());
            preparedStatement.setInt(3, prod.getCost());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    public boolean insertItem(Item item) {
        boolean flag = false;
        try { 
            preparedStatement = connection.prepareStatement(COMMAND_ADD_ITEM);
            preparedStatement.setInt(1, item.getOrderId());
            preparedStatement.setInt(2, item.getProductId());
            preparedStatement.setInt(3, item.getQuantity());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean insertOrder(Order order) {
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(COMMAND_ADD_ORDER);
            preparedStatement.setTimestamp(1, order.getDate());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
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
