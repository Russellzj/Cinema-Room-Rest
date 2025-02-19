package cinema;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Theatre {
    public final int rows = 9;
    public final int columns = 9;
    private Seat[]seats = new Seat[rows * columns];
    private HashMap<UUID, Ticket> tickets = new HashMap<>();

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

    public Object purchaseSeat(int row, int column) {
        int seatLocation = (row - 1) * columns + column - 1;
        seats[seatLocation].purchase();

        //Create Ticket
        UUID ticketId = UUID.randomUUID();
        Ticket ticket = new Ticket(row, column, seats[seatLocation].getPrice());
        tickets.put(ticketId, ticket);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("token", ticketId);
        body.put("ticket", ticket);
        return body;
    }

    public Seat[] getSeats(){
        return seats;
    }

    public boolean tokenExists(UUID token) {
        return tickets.containsKey(token);
    }

    public Ticket getTicket(UUID token) {
        Ticket ticket = tickets.get(token);
        tickets.remove(token);
        return ticket;
    }

    public Map<String, Object> stats() {
        int income = 0;
        int availableSeats = seats.length;
        int purchasedSeats = 0;
        for (Seat seat : seats) {
            if (seat.isPurchased()) {
                income += seat.getPrice();
                availableSeats--;
                purchasedSeats++;
            }
        }
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("income", income);
        body.put("available", availableSeats);
        body.put("purchased", purchasedSeats);
        return body;
    }
}
