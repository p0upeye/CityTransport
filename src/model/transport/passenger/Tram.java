package model.transport.passenger;

import model.person.Passenger;

public class Tram extends PassengerTransport {
    private final double trackLength;  // length of the tram track in km

    public Tram(int id, String routeNumber, int capacity, double trackLength) {
        super(id, routeNumber, capacity);
        this.trackLength = trackLength;
    }

    public double getTrackLength() {
        return trackLength;
    }

    @Override
    public void boardPassenger(Passenger p) {
        if(p.getTicketNumber() == null || p.getTicketNumber().isEmpty()) {
            throw new IllegalArgumentException("Passenger " + p.getName() + " does not have a valid ticket.");
        }

        super.boardPassenger(p);
    }

    @Override
    protected void displaySpecificInfo() {
        System.out.println("Track Length: " + trackLength + " km");
        System.out.println();
    }

    @Override
    public void move() {
        System.out.print("🚋 ");
        super.move();
    }
}
