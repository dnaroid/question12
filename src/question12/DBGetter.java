package question12;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Vasil Talkachou
 */
public class DBGetter {
    
    public static final String COMMAND_GET_ORDERS_NUMBER
        = "SELECT count(order_id) FROM orders";
    
    public static final String COMMAND_GET_ORDERS
        = "SELECT * FROM orders";
    
    public static final String COMMAND_GET_ORDER
        = "SELECT * FROM orders WHERE order_id=?";
    
    public static final String COMMAND_GET_ITEMS
        = "SELECT * FROM items WHERE order_id=?";
    
    public static final String COMMAND_GET_PRODUCT
        = "SELECT * FROM products WHERE product_id=?";
    
    public static final String COMMAND_GET_ORDER_INFO
        = "SELECT products.product_name, "
        + "products.product_info, products.product_cost, "
        + "items.items_quantity "
        + "FROM products, items "
        + "WHERE items.product_id = products.product_id "
        + "AND items.order_id=?";
    
    private Connection connection;
    private PreparedStatement preparedStatement;

    public DBGetter() {
        connection = DBConnector.getConnection();
    }
    
    public int getOrdersNumber() {
        int num = 0;
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ORDERS_NUMBER);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                num = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return num;
    }
    
    public Order getOrder(int num) {
        Order order = null;
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ORDER);
            preparedStatement.setInt(1, num);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt(1);
                Timestamp date = rs.getTimestamp(2);
                order = new Order(date);
                order.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }
    
    
    public ArrayList<Order> getOrders() {
        ArrayList list = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ORDERS);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                Timestamp date = rs.getTimestamp(2);
                Order order = new Order(date);
                order.setId(id);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public ArrayList<Item> getItems(int num) {
        ArrayList list = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ITEMS);
            preparedStatement.setInt(1, num);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt(1);
                int item_id = rs.getInt(2);
                int quantity = rs.getInt(3);
                Item item = new Item(order_id, item_id, quantity);
                list.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public ArrayList<String> getOrderInfo(int num) {
        ArrayList list = new ArrayList();
        int sum = 0;
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ORDER_INFO);
            preparedStatement.setInt(1, num);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                String info = rs.getString(2);
                int cost = rs.getInt(3);
                sum += cost;
                int quantity = rs.getInt(4);
                String res = name + " (" + info + ") cost:" + cost + " qty:" + quantity;
                list.add(res);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        list.add("Full cost:" + sum);
        return list;
    }
    
    public Product getProduct(int num) {
        Product prod = null;
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_PRODUCT);
            preparedStatement.setInt(1, num);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String info = rs.getString(3);
                int cost = rs.getInt(4);
                prod = new Product(name, info, cost);
                prod.setId(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return prod;
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
