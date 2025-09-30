package service;

import interfaces.CargoCarrier;
import interfaces.PassengerCarrier;
import model.cargo.Cargo;
import model.person.Driver;
import model.person.Passenger;
import model.transport.cargo.CargoTransport;
import model.transport.passenger.PassengerTransport;
import model.transport.Transport;

import java.util.ArrayList;
import java.util.List;

public class CityTransportSystem {
    private final List<Transport> transports = new ArrayList<>();
    private final List<Driver> drivers = new ArrayList<>();
    private final List<Passenger> passengers = new ArrayList<>();
    private final List<Cargo> cargos = new ArrayList<>();

    public List<Transport> getTransports() {
        return transports;
    }
    public List<Driver> getDrivers() {
        return drivers;
    }
    public List<Passenger> getPassengers() {
        return passengers;
    }
    public List<Cargo> getCargos() {
        return cargos;
    }

    public void addTransport(Transport t) {
        transports.add(t);
    }
    public void removeTransport(Transport t) {
        transports.remove(t);
    }

    public void addDriver(Driver d) {
        drivers.add(d);
    }
    public void removeDriver(Driver d) {
        drivers.remove(d);
    }

    public void addPassenger(Passenger p) {
        passengers.add(p);
    }
    public void removePassenger(Passenger p) {
        passengers.remove(p);
    }

    public void addCargo(Cargo c) {
        cargos.add(c);
    }
    public void removeCargo(Cargo c) {
        cargos.remove(c);
    }

    /*
    * Assign & remove driver methods
    */
    public void assignDriverToTransport(Driver d, Transport t) {
        if(d.isDriving()) {
            throw new IllegalStateException("Driver " + d.getName() + " is already driving another transport.");
        }
        if(t.hasDriver()) {
            throw new IllegalStateException("Transport ID: " + t.getId() + " already has a driver assigned.");
        }

        t.setDriver(d);
        System.out.println("[+] Driver " + d.getName() + " assigned to Transport ID: " + t.getId());
    }

    public void removeDriverFromTransport(Transport t) {
        if(!t.hasDriver()) {
            throw new IllegalStateException("Transport ID: " + t.getId() + " has no driver to remove.");
        }

        Driver removedDriver = t.getDriver();
        t.removeDriver();
        System.out.println("[-] Driver " + removedDriver.getName() + " removed from Transport ID: " + t.getId());
    }

    /*
    * Board & disembark methods
    */
    public void boardPassenger(Transport t, Passenger p) {
        if(!(t instanceof PassengerCarrier)) {
            throw new IllegalArgumentException("Transport ID: " + t.getId() + " cannot carry passengers.");
        }

        ((PassengerCarrier) t).boardPassenger(p);
        System.out.println("[+] Passenger " + p.getName() + " boarded " + t.getClass().getSimpleName() + " ID: " + t.getId());

        processPendingMessages(t);
    }

    public void disembarkPassenger(Transport t, Passenger p) {
        if(!(t instanceof PassengerCarrier)) {
            throw new IllegalArgumentException("Transport ID: " + t.getId() + " cannot carry passengers.");
        }

        ((PassengerCarrier) t).disembarkPassenger(p);
        System.out.println("[-] Passenger " + p.getName() + " disembarked from " + t.getClass().getSimpleName() + " ID: " + t.getId());

        processPendingMessages(t);
    }

    /*
     * Cargo methods
     */
    public void loadCargo(Transport t, Cargo c) {
        if(!(t instanceof CargoCarrier)) {
            throw new IllegalArgumentException("Transport ID: " + t.getId() + " cannot carry cargo.");
        }

        ((CargoCarrier) t).loadCargo(c);
        System.out.println("[+] " + c.getClass().getSimpleName() + " ID: " + c.getId() + " loaded into " + t.getClass().getSimpleName() + " ID: " + t.getId());

        processPendingMessages(t);
    }

    public void unloadCargo(Transport t, Cargo c) {
        if(!(t instanceof CargoCarrier)) {
            throw new IllegalArgumentException("Transport ID: " + t.getId() + " cannot carry cargo.");
        }

        ((CargoCarrier) t).unloadCargo(c);
        System.out.println("[-] " + c.getClass().getSimpleName() + " ID: " + c.getId() + " unloaded from " + t.getClass().getSimpleName() + " ID: " + t.getId());

        processPendingMessages(t);
    }

    /*
    * Display methods
    */
    public void displayAllTransports() {
        System.out.println("▱▱▱ ALL TRANSPORTS ▱▱▱");

        for(Transport transport : transports) {
            transport.displayInfo();
        }
        System.out.println();
    }

    public void displayAllDrivers() {
        System.out.println("▱▱▱ ALL DRIVERS ▱▱▱");

        for(Driver driver : drivers) {
            driver.displayInfo();
        }
        System.out.println();
    }

    public void displayAllCargos() {
        System.out.println("▱▱▱ ALL CARGO ▱▱▱");

        for(Cargo cargo : cargos) {
            cargo.displayInfo();
        }
        System.out.println();
    }

    public void displayAllPassengers() {
        System.out.println("▱▱▱ ALL PASSENGERS ▱▱▱");

        for(Passenger passenger : passengers) {
            passenger.displayInfo();
        }
        System.out.println();
    }

    /*
    * Process pending messages for PassengerTransport
    */
    public void processPendingMessages(Transport t) {
        if(t instanceof PassengerTransport) {
            List<String> messages = ((PassengerTransport) t).getPendingMessages();

            for(String msg : messages) {
                System.out.println(msg);
            }
            ((PassengerTransport) t).clearPendingMessages();
        }

        if(t instanceof CargoTransport) {
            List<String> messages = ((CargoTransport) t).getPendingMessages();

            for(String msg : messages) {
                System.out.println(msg);
            }
            ((CargoTransport) t).clearPendingMessages();
        }
    }
}
