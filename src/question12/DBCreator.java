package question12;

import question12.helpers.DBModifier;
import question12.helpers.DBConnector;
import question12.models.Order;
import question12.models.Product;
import question12.models.Item;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Vasil Talkachou
 */
public class DBCreator {
    
    public static final String COMMAND_DROP_ALL_TABLES
        = "DROP TABLE items, orders, products;";

    public static final String COMMAND_CREATE_TABLE_ORDERS
        = "CREATE TABLE orders("
        + "order_id INT AUTO_INCREMENT, "
        + "order_date TIMESTAMP NOT NULL, "
        + "PRIMARY KEY (order_id) "
        + ")";

    public static final String COMMAND_CREATE_TABLE_PRODUCTS
        = "CREATE TABLE products("
        + "product_id INT AUTO_INCREMENT, "
        + "product_name VARCHAR(20) NOT NULL, "
        + "product_info VARCHAR(50) NOT NULL, "
        + "product_cost INT NOT NULL, "
        + "PRIMARY KEY (product_id) "
        + ")";

    public static final String COMMAND_CREATE_TABLE_ITEMS
        = "CREATE TABLE items("
        + "order_id INT NOT NULL, "
        + "product_id INT NOT NULL, "
        + "items_quantity INT NOT NULL, "
        + "FOREIGN KEY (order_id) REFERENCES orders (order_id),"
        + "FOREIGN KEY (product_id) REFERENCES products (product_id)"
        + ")";


    public static void sendSQL(String text) {
        Connection dbConnection = null;
        Statement statement = null;

        try {
            dbConnection = DBConnector.getConnection();
            statement = dbConnection.createStatement();
            statement.execute(text);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public static void dropTables() {
        sendSQL(COMMAND_DROP_ALL_TABLES);
    }
    
    public static void createTables() {
        sendSQL(COMMAND_CREATE_TABLE_ORDERS);
        sendSQL(COMMAND_CREATE_TABLE_PRODUCTS);
        sendSQL(COMMAND_CREATE_TABLE_ITEMS);
    }

    public static void addProducts() {
        DBModifier mod = new DBModifier();
        ArrayList <Product> products = new ArrayList <Product>() {
            {
                add(new Product("mouse", "optical USB mouse Genius", 5));
                add(new Product("phone", "Android GSM phone Huawei", 150));
                add(new Product("laptop", "laptop Lenovo Core i3", 250));
                add(new Product("TV set", "TV set \"Horizont\" 27\"", 120));
                add(new Product("player", "MP3 player Sony 2Gb", 25));
                add(new Product("e-book", "E-Ink book reader Kindle", 75));
            }
        };
        for(Product pr : products)
            mod.insertProduct(pr);
        mod.closeStatement();
        mod.closeConnection();
    }

    public static void addOrders() {
        DBModifier mod = new DBModifier();
        ArrayList<Order> orders = new ArrayList() {
            {
                add(new Order(new Timestamp(System.currentTimeMillis() - 1040_000_000)));
                add(new Order(new Timestamp(System.currentTimeMillis() - 1030_000_000)));
                add(new Order(new Timestamp(System.currentTimeMillis() - 1020_000_000)));
                add(new Order(new Timestamp(System.currentTimeMillis() - 1010_000_000)));
                add(new Order(new Timestamp(System.currentTimeMillis() - 1000_000_000)));
                add(new Order(new Timestamp(System.currentTimeMillis() - 900_000_000)));
                add(new Order(new Timestamp(System.currentTimeMillis() - 800_000_000)));
                add(new Order(new Timestamp(System.currentTimeMillis() - 700_000_000)));
                add(new Order());
                add(new Order());
                add(new Order());
                add(new Order());
            }
        };
        for (Order order : orders) {
            mod.insertOrder(order);
        }
        mod.closeStatement();
        mod.closeConnection();
    }

    public static void addItems() {
        DBModifier mod = new DBModifier();
        ArrayList<Item> items = new ArrayList() {
            {   //order_id, product_id, items_quantity
                add(new Item(1,1,1));
                add(new Item(1,2,2));
                add(new Item(2,4,2));
                add(new Item(2,1,1));
                add(new Item(3,3,2));
                add(new Item(4,2,3));
                add(new Item(5,1,1));
                add(new Item(6,2,2));
                add(new Item(6,4,3));
                add(new Item(7,3,4));
                add(new Item(8,5,2));
                add(new Item(9,5,1));
                add(new Item(10,5,2));
                add(new Item(10,3,1));
                add(new Item(11,1,1));
                add(new Item(12,6,2));
            }
        };
        for (Item item : items) {
            mod.insertItem(item);
        }
        mod.closeStatement();
        mod.closeConnection();
    }

}
