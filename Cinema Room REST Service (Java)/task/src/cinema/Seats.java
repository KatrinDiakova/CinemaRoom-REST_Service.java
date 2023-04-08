package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.UUID;

public class Seats {
    protected int row;
    protected int column;
    protected int price;
    @JsonIgnore
    protected UUID token;

    @JsonIgnore
    boolean isAvailable = true;

    public Seats(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.token = UUID.randomUUID();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    @JsonIgnore
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seats seats = (Seats) o;
        return row == seats.row && column == seats.column && price == seats.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, price);
    }

}
