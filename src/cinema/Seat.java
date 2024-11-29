package cinema;

public class Seat {
    private int row;
    private int column;
    private int price;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        if (row <= 4) {
            price = 10;
        } else {
            price = 8;
        }
    }

    //Getters
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
}
