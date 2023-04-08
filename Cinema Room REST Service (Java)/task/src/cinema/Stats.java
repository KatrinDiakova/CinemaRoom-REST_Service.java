package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
    @JsonProperty("current_income")
    protected int currentIncome = 0;
    @JsonProperty("number_of_available_seats")
    protected int numAvailableSeats = 81;
    @JsonProperty("number_of_purchased_tickets")
    protected int numPurchasedSeats = 0;

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome += currentIncome;
    }

    public void minCurrentIncome(int currentIncome) {
        this.currentIncome -= currentIncome;
    }

    public int getNumAvailableSeats() {
        return numAvailableSeats;
    }

    public void setNumAvailableSeats(int numAvailableSeats) {
        this.numAvailableSeats = numAvailableSeats;
    }

    public int getNumPurchasedSeats() {
        return numPurchasedSeats;
    }

    public void setNumPurchasedSeats(int numPurchasedSeats) {
        this.numPurchasedSeats = numPurchasedSeats;
    }
}
