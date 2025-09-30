package model.transport.passenger;

import model.person.Passenger;

public class Taxi extends PassengerTransport {
    private boolean isAvailable;  // availability status

    public Taxi(int id, String routeNumber, int capacity, boolean isAvailable) {
        super(id, routeNumber, capacity);
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /*
    * Overriding methods to manage availability based on passenger count
    */
    @Override
    public void boardPassenger(Passenger p) {
        if(!isAvailable()) {
            throw new IllegalStateException("Taxi ID: " + getId() + " is not available.");
        }

        super.boardPassenger(p);
    }

    @Override
    protected void onPassengerBoarded(Passenger p) {
        if(getPassengersCount() >= getCapacity()) {
            setAvailable(false);
            addPendingMessage("[💯] Taxi ID: " + getId() + " is now unavailable (full).");
        }
    }

    @Override
    protected void onPassengerDisembarked(Passenger p) {
        if(!isAvailable() && getPassengersCount() < getCapacity()) {
            setAvailable(true);
            addPendingMessage("[✅] Taxi ID: " + getId() + " is now available.");
        }
    }

    @Override
    protected void displaySpecificInfo() {
        System.out.println("Status: " + (isAvailable ? "Available" : "Not Available"));
        System.out.println();
    }

    @Override
    public void move() {
        System.out.print("🚕 ");
        super.move();
    }
}
