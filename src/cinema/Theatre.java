package cinema;

public class Theatre {
    public final int rows = 9;
    public final int columns = 9;

    private Seat[]seats = new Seat[rows * columns];

    public Theatre() {
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                seats[i * columns + j] = new Seat(i + 1, j + 1);
            }
        }
    }

    public boolean seatExists(int row, int column){
        return row <= rows && column <= columns;
    }

    public Seat getSeat(int row, int column){
        return seats[(row - 1) * columns + column - 1];
    }

    public Seat purchaseSeat(int row, int column) {

        seats[(row - 1) * columns + column - 1].purchase();
        return seats[(row - 1) * columns + column - 1];
    }

    public Seat[] getSeats(){
        return seats;
    }

}
