package model.person;

import model.transport.Transport;
import service.CityTransportSystem;

public class Driver extends Person {
    private Transport currentTransport;

    public Driver(String name, int id) {
        super(name, id);
    }

    public Transport getCurrentTransport() {
        return currentTransport;
    }
    public void setCurrentTransport(Transport currentTransport) {
        this.currentTransport = currentTransport;
    }
    public boolean isDriving() {
        return currentTransport != null;
    }
    public void stopDriving() {
        this.currentTransport = null;
    }

    public void drive(Transport t, CityTransportSystem system) {
        if(t == null) {
            throw new IllegalArgumentException("Driver " + getName() + " has no Transport to drive.");
        }
        if(t != currentTransport) {
//            throw new IllegalArgumentException("Driver " + getName() + " is not assigned to Transport ID: " + t.getId());
            system.assignDriverToTransport(this, t);
        }

        System.out.println("Driver " + getName() + " is driving " + currentTransport.getClass().getSimpleName() + " ID: " + currentTransport.getId());
        t.move();
    }

    @Override
    public void displayInfo() {
        System.out.println(this +
                (isDriving() ? ", Driving Transport ID: " + getCurrentTransport().getId() : ", Not Driving"));
    }
}
