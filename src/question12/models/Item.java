
package question12.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vasil Talkachou
 */
public class Item extends Entity {

    private int orderId;
    private int productId;
    private int quantity;

    public Item() {
    }
    
    public Item(ResultSet rs) {
        try {
            this.orderId = rs.getInt(1);
            this.productId = rs.getInt(2);
            this.quantity = rs.getInt(3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Item(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item [orderId=" + orderId
             + ", productId=" + productId + ", quantity=" + quantity + "]";
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
