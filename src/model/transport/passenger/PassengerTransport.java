package model.transport.passenger;

import interfaces.PassengerCarrier;
import model.person.Passenger;
import model.transport.Transport;

import java.util.ArrayList;
import java.util.List;

public abstract class PassengerTransport extends Transport implements PassengerCarrier {
    private final int capacity;  // Capacity of the transport

    private final List<Passenger> passengers = new ArrayList<>();
    private final List<String> pendingMessages = new ArrayList<>();

    public PassengerTransport(int id, String routeNumber, int capacity) {
        super(id, routeNumber);

        if(capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }

        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    /*
     * Pending messages management
     */
    protected void addPendingMessage(String message) {
        pendingMessages.add(message);
    }
    public List<String> getPendingMessages() {
        return pendingMessages;
    }
    public void clearPendingMessages() {
        pendingMessages.clear();
    }

    @Override
    public void boardPassenger(Passenger p) {
        if(passengers.size() >= getCapacity()) {
            throw new IllegalStateException(getClass().getSimpleName() + " ID: " + getId() + " is at full capacity.");
        }
        if(p.isOnTransport()) {
            throw new IllegalStateException("Passenger " + p.getName() + " is already on another transport.");
        }

        passengers.add(p);
        p.setCurrentTransport(this);
        onPassengerBoarded(p);
    }

    @Override
    public void disembarkPassenger(Passenger p) {
        if(!passengers.contains(p)) {
            throw new IllegalStateException("Passenger " + p.getName() + " is not on " + getClass().getSimpleName() + " ID: " + getId());
        }

        passengers.remove(p);
        p.leaveTransport();
        onPassengerDisembarked(p);
    }

    // Hook methods for subclasses to override
    protected void onPassengerBoarded(Passenger passenger) {}
    protected void onPassengerDisembarked(Passenger passenger) {}

    public int getPassengersCount() {
        return passengers.size();
    }

    @Override
    public void move() {
        if(!hasDriver()) {
            throw new IllegalStateException(getClass().getSimpleName() + " ID: " + getId() + " cannot move without a driver.");
        }

        System.out.println(getClass().getSimpleName() + " " + getId() + " is moving on route " + getRouteNumber() +
                " with driver " + getDriver().getName() + ".");

        if(!passengers.isEmpty()) {
            System.out.println("Passengers on board:");

            for(Passenger p : passengers) {
                System.out.println(" - " + p.getName());
            }
        }
        else {
            System.out.println("No passengers on board.");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("▰▰▰ " + getClass().getSimpleName() + " " + getId() + " Info ▰▰▰\n" + this);
        displaySpecificInfo();
    }

    protected abstract void displaySpecificInfo();

    @Override
    public String toString() {
        return super.toString() +
                "\nPassengers on board: " + getPassengersCount() + "/" + getCapacity();
    }
}
