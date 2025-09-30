package interfaces;

import model.cargo.Cargo;

public interface CargoCarrier {
    void loadCargo(Cargo c);
    void unloadCargo(Cargo c);
}
