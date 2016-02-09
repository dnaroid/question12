
package question12;

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
