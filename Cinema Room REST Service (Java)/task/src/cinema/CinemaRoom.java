package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class CinemaRoom {
    @JsonProperty("total_rows")
    protected int totalRows = 9;
    @JsonProperty("total_columns")
    protected int totalColumns = 9;
    @JsonProperty("available_seats")
    protected List<Seats> seatsList = new ArrayList<>();

    public CinemaRoom() {
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                if (row <= 4) {
                    seatsList.add(new Seats(row, column, 10));
                } else {
                    seatsList.add(new Seats(row, column, 8));
                }
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seats> getSeatsList() {
        return seatsList;
    }

    public void setSeatsList(List<Seats> ticketList) {
        this.seatsList = ticketList;
    }

}
