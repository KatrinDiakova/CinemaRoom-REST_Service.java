package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController {
    private Cinema cinema;

    private Stats stats;

    public CinemaController() {
        this.cinema = new Cinema();
        this.stats = new Stats();
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
        }
        for (Seats s : cinema.getSeatsList()) {
            if (s.getRow() == seats.getRow() && s.getColumn() == seats.getColumn()) {
                if (!s.getIsAvailable()) {
                    return ResponseEntity.badRequest().body(
                            new ConcurrentHashMap<>(Map.of("error", "The ticket has been already purchased!")));
                }
                s.setAvailable(false);
                stats.setCurrentIncome(s.price);
                stats.numAvailableSeats--;
                stats.numPurchasedSeats++;
                return ResponseEntity.ok().body(
                        new ConcurrentHashMap<>(Map.of("token", s.getToken(), "ticket", seats)));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        for (Seats s : cinema.getSeatsList()) {
            if (Objects.equals(s.getToken().toString(), token)) {
                s.setAvailable(true);
                stats.minCurrentIncome(s.price);
                stats.numPurchasedSeats--;
                stats.numAvailableSeats++;
                return ResponseEntity.ok().body(
                        new ConcurrentHashMap<>(Map.of("returned_ticket", s)));
            }
        }
        return ResponseEntity.badRequest().body(
                new ConcurrentHashMap<>(Map.of("error", "Wrong token!")));
    }

    @PostMapping("/stats")
    public ResponseEntity statistics(@RequestParam(value = "password", required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ConcurrentHashMap<>(Map.of( "error", "The password is wrong!"))
            );
        }
        return ResponseEntity.ok().body(stats);
    }
}

