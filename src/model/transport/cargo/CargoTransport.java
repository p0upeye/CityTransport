package model.transport.cargo;

import interfaces.CargoCarrier;
import model.cargo.Cargo;
import model.transport.Transport;

import java.util.ArrayList;
import java.util.List;

public abstract class CargoTransport extends Transport implements CargoCarrier {
    private final double maxCargoWeightKg;
    private double currentCargoWeightKg = 0;

    private final List<Cargo> cargoList = new ArrayList<>();
    private final List<String> pendingMessages = new ArrayList<>();

    public CargoTransport(int id, String routeNumber, double maxCargoWeightKg) {
        super(id, routeNumber);

        if(maxCargoWeightKg <= 0) {
            throw new IllegalArgumentException("Max cargo weight must be positive.");
        }

        this.maxCargoWeightKg = maxCargoWeightKg;
    }

    public double getMaxCargoWeightKg() {
        return maxCargoWeightKg;
    }
    public double getCurrentCargoWeight() {
        return currentCargoWeightKg;
    }
    public List<Cargo> getCargoList() {
        return cargoList;
    }

    /*
     * Pending messages management
     */
    protected void addPendingMessage(String message) {
        pendingMessages.add(message);
    }
    public List<String> getPendingMessages() {
        return new ArrayList<>(pendingMessages);
    }
    public void clearPendingMessages() {
        pendingMessages.clear();
    }

    @Override
    public void loadCargo(Cargo c) {
        if(cargoList.contains(c)) {
            throw new IllegalStateException("Cargo ID: " + c.getId() + " is already loaded on Transport ID: " + getId());
        }
        if(currentCargoWeightKg + c.getWeightKg() > getMaxCargoWeightKg()) {
            throw new IllegalStateException("Cannot load cargo ID: " + c.getId() + ". Exceeds max weight capacity.");
        }

        cargoList.add(c);
        currentCargoWeightKg += c.getWeightKg();
        onCargoLoaded(c);
    }

    @Override
    public void unloadCargo(Cargo c) {
        if(!cargoList.contains(c)) {
            throw new IllegalStateException("Cargo ID: " + c.getId() + " is not on Transport ID: " + getId());
        }

        cargoList.remove(c);
        currentCargoWeightKg -= c.getWeightKg();
        onCargoUnloaded(c);
    }

    // Hook methods for subclasses to override
    protected void onCargoLoaded(Cargo cargo) {}
    protected void onCargoUnloaded(Cargo cargo) {}

    @Override
    public void move() {
        if(!hasDriver()) {
            throw new IllegalStateException(getClass().getSimpleName() + " ID: " + getId() + " cannot move without a driver.");
        }

        System.out.println(getClass().getSimpleName() + " " + getId() + " is moving on route " + getRouteNumber() +
                " with driver " + getDriver().getName() + ".");

        if(!cargoList.isEmpty()) {
            System.out.println("Cargo on board:");
            for(Cargo c : cargoList) {
                System.out.println(" - " + c);
            }
        } else {
            System.out.println("No cargo loaded.");
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
                "\nCargo weight: " + currentCargoWeightKg + "/" + maxCargoWeightKg + " kg" +
                "\nCargo items: " + cargoList.size();
    }
}
