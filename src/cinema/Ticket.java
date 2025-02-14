package cinema;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Ticket {
    int row;
    int col;
    int price;
    @JsonIgnore
    boolean expired = false;

    public Ticket(int row, int col, int price) {
        this.row = row;
        this.col = col;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getPrice() {
        return price;
    }
}
