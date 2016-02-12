package question12.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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

    public Order(ResultSet rs) {
        try {
            id = rs.getInt(1);
            date = rs.getTimestamp(2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
