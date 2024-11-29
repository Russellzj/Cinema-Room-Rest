package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.config.Task;

@RestController
public class TaskController {

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
        Theatre theatre = new Theatre();
        return theatre;
    }
}
