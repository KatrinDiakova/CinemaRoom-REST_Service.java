package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController {
    private Cinema cinema;
    public CinemaController() {
        this.cinema = new Cinema();
    }
    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }
    @PostMapping("/purchase")
    public ResponseEntity buyTicket(@RequestBody Seats seats) {
        if (seats.getRow() > cinema.totalRows || seats.getColumn() > cinema.getTotalColumns()
                || seats.getRow() < 0 || seats.getColumn() < 0) {
            return ResponseEntity.badRequest().body(
                    new ConcurrentHashMap<>(Map.of("error", "The number of a row or a column is out of bounds!")));
        } else if (!cinema.getSeatsList().contains(seats)) {
            return ResponseEntity.badRequest().body(
                    new ConcurrentHashMap<>(Map.of("error", "The ticket has been already purchased!")));
        }
        cinema.deleteFromSeatsList(seats);
        return ResponseEntity.ok(seats);
    }
}

