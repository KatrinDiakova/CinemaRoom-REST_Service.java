package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Seats {
    int row;
    int column;
    int price;

    @JsonIgnore
    boolean isPurchased = false;

    public Seats(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
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
    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
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
