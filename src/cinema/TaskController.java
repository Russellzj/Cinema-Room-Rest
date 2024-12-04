package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.config.Task;

import java.util.List;

@RestController
public class TaskController {
    Theatre theatre = new Theatre();


    @GetMapping("/start")
    public ResponseEntity<Task> start() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public int returnOne() {
        return 1;
    }

    @GetMapping(value = "/seats", produces = "application/json")
    public Theatre getSeats() {
        return theatre;
    }

    @GetMapping(value = "/purchase")
    public ResponseEntity<Object> getPurchase(
            @RequestParam(name = "row") int row,
            @RequestParam(name = "column") int column) {
        if (!theatre.seatExists(row, column)) {

            return ResponseEntity.badRequest().
                    contentType(MediaType.APPLICATION_JSON).
                    body(new Message("The seat does not exist!"));
        }
        if (theatre.getSeat(row, column).isPurchased()) {
            return ResponseEntity.badRequest().
                    contentType(MediaType.APPLICATION_JSON).
                    body(new Message("The seat is already purchased!"));
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).
                body(theatre.purchaseSeat(row, column));
    }
}
