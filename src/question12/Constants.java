package question12;

/**
 * @author Vasil Talkachou
 */
public class Constants {
    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE orders("
        + "order_id INT AUTO_INCREMENT, "
        + "order_date DATE NOT NULL, "
        + "PRIMARY KEY (order_id) "
        + ")";

    public static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE products("
        + "product_id INT AUTO_INCREMENT, "
        + "product_name VARCHAR(20) NOT NULL, "
        + "product_info VARCHAR(50) NOT NULL, "
        + "product_cost INT NOT NULL, "
        + "PRIMARY KEY (product_id) "
        + ")";

    public static final String CREATE_TABLE_ITEMS_IN_ORDER = "CREATE TABLE items("
        + "order_id INT NOT NULL, "
        + "item_id INT NOT NULL, "
        + "items_quantity INT NOT NULL, "
        + "FOREIGN KEY (order_id) REFERENCES orders (order_id),"
        + "FOREIGN KEY (item_id) REFERENCES products (product_id)"
        + ")";

    public static final String ADD_PRODUCT = "INSERT INTO products"
        + "(product_name, product_info, product_cost) VALUES(?,?,?)";

    public static final String[] PRODUCTS = {
//       product_name, product_info, product_cost 
        "телефон, мобильный телефон Huawei, 150",
        "ноутбук, ноутбук Lenovo, 250",
        "телевизор, телевизор \"Горизонт\", 120",
        "плейер, MP3 плейер Sony, 50",
        "пылесос, пылесос Samsung, 90"
        };
}
