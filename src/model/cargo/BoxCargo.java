package model.cargo;

public class BoxCargo extends Cargo {
    public BoxCargo(int id, double weightKg, String description) {
        super(id, weightKg, description);
    }

    @Override
    public void displayInfo() {
        System.out.println(this);
    }
}
