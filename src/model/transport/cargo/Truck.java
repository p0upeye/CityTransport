package model.transport.cargo;

import model.cargo.Cargo;

public class Truck extends CargoTransport {
    private final boolean hasTrailer;
    private final double trailerCapacityKg;

    public Truck(int id, String routeNumber, double maxCargoWeightKg) {
        super(id, routeNumber, maxCargoWeightKg);
        this.hasTrailer = false;
        this.trailerCapacityKg = 0;
    }

    public Truck(int id, String routeNumber, double maxCargoWeightKg, double trailerCapacityKg) {
        super(id, routeNumber, maxCargoWeightKg);

        if(trailerCapacityKg <= 0) {
            throw new IllegalArgumentException("Trailer capacity must be positive.");
        }

        this.hasTrailer = true;
        this.trailerCapacityKg = trailerCapacityKg;
    }

    public boolean isHasTrailer() {
        return hasTrailer;
    }
    public double getTrailerCapacityKg() {
        return trailerCapacityKg;
    }

    @Override
    protected void onCargoLoaded(Cargo cargo) {
        if(getCurrentCargoWeight() >= getMaxCargoWeightKg()) {
            addPendingMessage("[💯] Truck ID: " + getId() + " fully loaded! Current load: " + getCurrentCargoWeight() + "/" + getMaxCargoWeightKg() + " kg");
        }
    }

    @Override
    protected void onCargoUnloaded(Cargo cargo) {
        if(getCurrentCargoWeight() < getMaxCargoWeightKg()) {
            addPendingMessage("[✅] Truck ID: " + getId() + " unloaded. Current load: " + getCurrentCargoWeight() + "/" + getMaxCargoWeightKg() + " kg");
        }
    }

    @Override
    public double getMaxCargoWeightKg() {
        return super.getMaxCargoWeightKg() + trailerCapacityKg;
    }

    @Override
    protected void displaySpecificInfo() {
        System.out.println("Has trailer: " + (hasTrailer ? "Yes (+" + trailerCapacityKg + " kg)" : "No"));
        System.out.println("Effective max cargo weight: " + getMaxCargoWeightKg() + " kg");
        System.out.println();
    }
}
