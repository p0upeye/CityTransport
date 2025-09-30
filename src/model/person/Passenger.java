package model.person;

import model.transport.Transport;

public class Passenger extends Person {
    private String ticketNumber;
    private Transport currentTransport;

    // Constructor with ticket number
    public Passenger(String name, int id, String ticketNumber) {
        super(name, id);
        this.ticketNumber = ticketNumber;
    }

    // Constructor without ticket number
    public Passenger(String name, int id) {
        super(name, id);
    }

    public String getTicketNumber() {
        return ticketNumber;
    }
    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Transport getCurrentTransport() {
        return currentTransport;
    }
    public void setCurrentTransport(Transport currentTransport) {
        this.currentTransport = currentTransport;
    }

    public boolean isOnTransport() {
        return currentTransport != null;
    }
    public void leaveTransport() {
        this.currentTransport = null;
    }

    @Override
    public void displayInfo() {
        System.out.println(this +
                (getTicketNumber() != null ? ", Ticket Number: " + getTicketNumber() : ", No Ticket") +
                (isOnTransport() ? ", On Transport ID: " + getCurrentTransport().getId() : ", Not on any Transport"));
    }
}
