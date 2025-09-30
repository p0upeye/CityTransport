package model.cargo;

public abstract class Cargo {
    private final int id;
    private final double weightKg;
    private final String description;

    public Cargo(int id, double weightKg, String description) {
        if(weightKg <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }

        this.id = id;
        this.weightKg = weightKg;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public double getWeightKg() {
        return weightKg;
    }
    public String getDescription() {
        return description;
    }

    public abstract void displayInfo();

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " ID: " + getId() +
                ", Weight: " + getWeightKg() + "kg" +
                ", Desc: " + getDescription();
    }
}
