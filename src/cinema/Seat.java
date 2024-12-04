package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seat {
    private int row;
    private int column;
    private int price;
    private boolean purchased = false;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        if (row <= 4) {
            price = 10;
        } else {
            price = 8;
        }
    }

    public void purchase() {
        purchased = true;
    }

    //Getters
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

    @JsonIgnore
    public int getPrice() {
        return price;
    }

    @JsonIgnore
    public boolean isPurchased() {
        return purchased;
    }
}
