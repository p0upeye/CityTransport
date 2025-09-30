package interfaces;

import model.person.Passenger;

public interface PassengerCarrier {
    void boardPassenger(Passenger p);
    void disembarkPassenger(Passenger p);
}
