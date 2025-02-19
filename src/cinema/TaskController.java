package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.config.Task;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class TaskController {
    Theatre theatre = new Theatre();

    @GetMapping("/start")
    public ResponseEntity<Task> start() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Simple server test
    @GetMapping("/test")
    public int returnOne() {
        return 1;
    }

    //Returns a json of the seats available
    @GetMapping(value = "/seats", produces = "application/json")
    public Theatre getSeats() {
        return theatre;
    }

    //Lets the user purchase a ticket based on its row and column location
    @GetMapping(value = "/purchase")
    public ResponseEntity<Object> getPurchase(
            @RequestParam(name = "row") int row,
            @RequestParam(name = "column") int column) {
        if (!theatre.seatExists(row, column)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The seat does not exist!");
        }
        if (theatre.getSeat(row, column).isPurchased()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The seat is already purchased!");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).
                body(theatre.purchaseSeat(row, column));
    }

    @GetMapping(value = "/return")
    public Ticket getReturn(
            @RequestParam(name = "token") String token) {
        UUID tokenUUID = UUID.fromString(token);
        if (theatre.tokenExists(tokenUUID)) {
            return theatre.getTicket(tokenUUID);
        } else {
            //return true;
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong token!");
        }
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<Object> stats(
        @RequestParam(name = "password") String password) {
        if (!password.equals("super_secret")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The password is wrong!");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).
                body(theatre.stats());
    }

}
