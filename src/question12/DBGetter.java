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

    public static final String COMMAND_GET_PRODUCTS_NUMBER
        = "SELECT count(product_id) FROM products";

    public static final String COMMAND_GET_ORDERS
        = "SELECT * FROM orders";

    public static final String COMMAND_GET_ORDER
        = "SELECT * FROM orders WHERE order_id=?";

    public static final String COMMAND_GET_ITEMS_IN_ORDER
        = "SELECT * FROM items WHERE order_id=?";

    public static final String COMMAND_GET_PRODUCT
        = "SELECT * FROM products WHERE product_id=?";

    public static final String COMMAND_GET_ORDER_INFO
        = "SELECT product_name, "
        + "product_info,"
        + "product_cost, "
        + "items_quantity "
        + "FROM items INNER JOIN products "
        + "ON items.product_id=products.product_id "
        + "AND order_id = ?";

    public static final String COMMAND_GET_ORDERS_WITH_ITEM
        = "SELECT order_id "
        + "FROM items INNER JOIN products "
        + "ON items.product_id = products.product_id "
        + "AND items.product_id = ?";

    public static final String COMMAND_GET_ORDERS_TODAY
        = "SELECT order_id "
        + "FROM orders "
        + "WHERE TO_DAYS(NOW()) - TO_DAYS(order_date) < 1";

    public static final String COMMAND_GET_ORDERS_TOTAL_AND_QTY
        = "SELECT COUNT(items.product_id), "
        + "SUM(items.items_quantity*products.product_cost) "
        + "FROM items INNER JOIN products "
        + "ON items.product_id = products.product_id "
        + "WHERE items.order_id = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public DBGetter() {
        connection = DBConnector.getConnection();
    }

    public int getInt(String request) {
        int num = 0;
        try {
            preparedStatement = connection.prepareStatement(request);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return num;
    }

    public ArrayList<Integer> getIntList(String request, int num) {
        ArrayList list = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(request);
            if (num >= 0) {
                preparedStatement.setInt(1, num);
            }
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Order getOrder(int num) {
        Order order = null;
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ORDER);
            preparedStatement.setInt(1, num);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                order = new Order(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public ArrayList<Integer> getOrdersWithItem(int itemId) {
//        ArrayList list = new ArrayList();
//        try {
//            preparedStatement = connection.prepareStatement(COMMAND_GET_ORDERS_WITH_ITEM);
//            preparedStatement.setInt(1, itemId);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                list.add(rs.getInt(1));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return list;
        return getIntList(COMMAND_GET_ORDERS_WITH_ITEM, itemId);
    }

    public ArrayList<Integer> getOrdersExcludeItemToday(int itemId) {
        ArrayList list = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ORDERS_TODAY);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int order = rs.getInt(1);
                for (Item i : getItemsInOrder(order)) {
                    if (i.getProductId() == itemId) {
                        order = 0;
                    }
                }
                if (order > 0) {
                    list.add(order);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<Order> getOrders() {
        ArrayList list = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ORDERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<Item> getItemsInOrder(int num) {
        ArrayList list = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ITEMS_IN_ORDER);
            preparedStatement.setInt(1, num);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new Item(rs));
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
                int quantity = rs.getInt(4);
                sum += quantity * cost;
                String res = name + " (" + info + ") cost:" + cost + " qty:" + quantity;
                list.add(res);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        list.add("Total:" + sum);
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

    public ArrayList<Integer> getOrdersTotalQty(int number, int total, int qty) {
        ArrayList list = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(COMMAND_GET_ORDERS_TOTAL_AND_QTY);
            for (int i = 1; i < number; i++) {
                preparedStatement.setInt(1, i);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    int q = rs.getInt(1);
                    int t = rs.getInt(2);
                    if (q == qty && t <= total) {
                        list.add(i);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
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

//SELECT 
//order_id, 
//product_name, 
//product_cost,
//items_quantity,
//product_cost*items_quantity AS full
//FROM items INNER JOIN products
//ON items.product_id=products.product_id
//-- AND order_id=1
//ORDER BY items.order_id
