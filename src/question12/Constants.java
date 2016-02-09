package question12;

/**
 * @author Vasil Talkachou
 */
public class Constants {
    
    public static final String[] PRODUCTS = {
        //  product_name, product_info, product_cost
        "('phone', 'Android GSM phone Huawei', 150);",
        "('laptop', 'laptop Lenovo', 250);",
        "('TV', 'TV \"Horizont\"', 120)",
        "('player', 'MP3 player Sony', 50);"
    };

    public static final String[] ORDERS = {
        //  order_id, order_date
        "(1, '2016-02-08');",
        "(2, '2016-03-14');",
        "(3, '2016-04-05')",
        "(4, '2016-05-11');"
    };

    public static final String[] ITEMS = {
        //  order_id, item_id, items_quantity
        "(1, 1, 3);",
        "(1, 3, 1);",
        "(2, 2, 2)",
        "(2, 3, 1);",
        "(3, 1, 1);",
        "(4, 3, 2);"
    };

    public static final String COMMAND_DROP_ALL_TABLES = "DROP TABLE items, orders, products;";

    public static final String COMMAND_CREATE_TABLE_ORDERS = "CREATE TABLE orders("
        + "order_id INT AUTO_INCREMENT, "
        + "order_date DATE NOT NULL, "
        + "PRIMARY KEY (order_id) "
        + ")";

    public static final String COMMAND_CREATE_TABLE_PRODUCTS = "CREATE TABLE products("
        + "product_id INT AUTO_INCREMENT, "
        + "product_name VARCHAR(20) NOT NULL, "
        + "product_info VARCHAR(50) NOT NULL, "
        + "product_cost INT NOT NULL, "
        + "PRIMARY KEY (product_id) "
        + ")";

    public static final String COMMAND_CREATE_TABLE_ITEMS = "CREATE TABLE items("
        + "order_id AUTO_INCREMENT, "
        + "item_id INT NOT NULL, "
        + "items_quantity INT NOT NULL, "
        + "FOREIGN KEY (order_id) REFERENCES orders (order_id),"
        + "FOREIGN KEY (item_id) REFERENCES products (product_id)"
        + ")";
    
    public static final String COMMAND_GET_ORDERS_NUM
        = "SELECT count(order_id) FROM orders;";
    
    

}
