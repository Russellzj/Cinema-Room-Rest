package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.config.Task;

@RestController
public class TaskController {
    Theatre theatre = new Theatre();


    @GetMapping("/start")
    public ResponseEntity<Task> start() {
        return new ResponseEntity(HttpStatus.OK);
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
    public Seat getPurchase(
            @RequestParam(name = "row") int row,
            @RequestParam(name = "column") int column) {
        return theatre.purchaseSeat(row - 1, column - 1);
    }


}
