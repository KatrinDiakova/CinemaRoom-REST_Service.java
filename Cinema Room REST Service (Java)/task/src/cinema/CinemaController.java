package cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaController {

    private CinemaRoom cinemaRoom;
    private final ObjectMapper objectMapper;

    public CinemaController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.cinemaRoom = new CinemaRoom();
    }

    @GetMapping("/seats")
    public CinemaRoom getSeats() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> buyTicket(@RequestBody Ticket ticket) {
        Seats purchasedSeat = null;
        ObjectNode objectNode = objectMapper.createObjectNode();
        if (ticket.getRow() > cinemaRoom.totalRows || ticket.getColumn() > cinemaRoom.getTotalColumns()
                || ticket.getRow() < 0 || ticket.getColumn() < 0) {
            objectNode.put("error", "The number of a row or a column is out of bounds!");
            return new ResponseEntity<>(objectNode, HttpStatus.BAD_REQUEST);
        }
        for (Seats seats : cinemaRoom.seatsList) {
            if (ticket.getRow() == seats.getRow() && ticket.getColumn() == seats.getColumn()) {
                if (!seats.isPurchased) {
                    purchasedSeat = seats;
                    seats.setPurchased(true);
                    break;
                } else {
                    objectNode.put("error", "The ticket has been already purchased!");
                    return new ResponseEntity<>(objectNode, HttpStatus.BAD_REQUEST);
                }
            }
        }
        return new ResponseEntity<>(purchasedSeat, HttpStatus.OK);
    }
}

