
package question12.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vasil Talkachou
 */
public class Product extends Entity {

    private String name;
    private String info;
    private int cost;

    public Product() {
    }
    
    public Product(ResultSet rs) {
        try {
            super.id = rs.getInt(1);
            this.name = rs.getString(2);
            this.info = rs.getString(3);
            this.cost = rs.getInt(4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Product(String name, String info, int cost) {
        this.name = name;
        this.info = info;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product [id=" +super.getId() + ", name=" + name
            + ", info=" + info + ", cost=" + cost + "]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
}
