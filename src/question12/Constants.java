package question12;

/**
 * @author Vasil Talkachou
 */
public class Constants {
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
        + "order_id INT NOT NULL, "
        + "item_id INT NOT NULL, "
        + "items_quantity INT NOT NULL, "
        + "FOREIGN KEY (order_id) REFERENCES orders (order_id),"
        + "FOREIGN KEY (item_id) REFERENCES products (product_id)"
        + ")";

    public static final String COMMAND_ADD_PRODUCT = "INSERT INTO products "
        + "(product_name, product_info, product_cost) VALUES (?, ?);";

    public static final String[] PRODUCTS_NAME_INFO = {
//       product_name, product_info 
        "phone, Android GSM phone Huawei, 150",
        "laptop, laptop Lenovo, 250",
        "TV, TV \"Horizont\", 120",
        "player, MP3 player Sony, 50"
        };
    
    public static final int[] PRODUCTS_COST = {
        150,
        250,
        120,
        50
    };
    
}
