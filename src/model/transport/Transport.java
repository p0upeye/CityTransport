package model.transport;

import model.person.Driver;

public abstract class Transport {
    private final int id;              // Unique identifier for the transport
    private final String routeNumber;  // Route number

    private Driver driver;             // Driver assigned to the transport

    public Transport(int id, String routeNumber) {
        this.id = id;
        this.routeNumber = routeNumber;
    }

    public int getId() {
        return id;
    }
    public String getRouteNumber() {
        return routeNumber;
    }

    /*
    * Driver management methods
    */
    public Driver getDriver() {
        return driver;
    }
    public boolean hasDriver() {
        return driver != null;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
        driver.setCurrentTransport(this);
    }
    public void removeDriver() {
        if(this.driver == null) {
            throw new IllegalStateException("No driver to remove from Transport ID: " + getId());
        }

        this.driver.stopDriving();
        this.driver = null;
    }

    public void move() {}
    public abstract void displayInfo();

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " ID: " + getId() +
                (hasDriver() ? "\nDriver: " + getDriver().getName() : "\nNo Driver") +
                "\nRoute Number: " + getRouteNumber();
    }
}
