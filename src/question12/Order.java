package question12;

import java.sql.Timestamp;

/**
 *
 * @author Vasil Talkachou
 */
public class Order extends Entity {

    private Timestamp date;

    public Order() {
        this.date = new Timestamp(System.currentTimeMillis());
        
    }
    
    public Order(Timestamp date) {
        this.date = date;

    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", date=" + date + "]";
    }

    /**
     * @return the date
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

   
}
