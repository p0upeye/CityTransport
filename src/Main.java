import model.cargo.BoxCargo;
import model.person.*;
import model.transport.cargo.Truck;
import model.transport.passenger.Bus;
import model.transport.passenger.Taxi;
import model.transport.passenger.Tram;
import service.CityTransportSystem;

public class Main {
    public static void main(String[] args) {
        CityTransportSystem system = new CityTransportSystem();

        // Create transports
        Bus bus = new Bus(1, "R101", 8, false);
        Bus DoubleDeckerBus = new Bus(2, "R102", 16, true);
        Tram tram = new Tram(3, "R103", 22, 15.5);
        Taxi premiumTaxi = new Taxi(4, "R104", 2, true);
        Taxi regularTaxi = new Taxi(5, "R105", 3, true);
        Truck truck = new Truck(6, "C201", 5000, 2000);
//      Truck truck2 = new Truck(7, "C202", 5000);  // Created by second constructor

        // Register transports
        system.addTransport(bus);
        system.addTransport(DoubleDeckerBus);
        system.addTransport(tram);
        system.addTransport(premiumTaxi);
        system.addTransport(regularTaxi);
        system.addTransport(truck);
//      system.addTransport(truck2);

        // Create drivers
        Driver dGustavo = new Driver("Gustavo Fring", 1);
        Driver dMike = new Driver("Mike Ehrmantraut", 2);
        Driver dNacho = new Driver("Nacho Varga", 3);
        Driver dSaul = new Driver("Saul Goodman", 4);
        Driver dKim = new Driver("Kim Wexler", 5);

        // Register drivers
        system.addDriver(dGustavo);
        system.addDriver(dMike);
        system.addDriver(dNacho);
        system.addDriver(dSaul);
        system.addDriver(dKim);

        // Create passengers
        Passenger pWalter = new Passenger("Walter White", 201, "T100");
        Passenger pJesse = new Passenger("Jesse Pinkman", 202, "T101");
        Passenger pSkyler = new Passenger("Skyler White", 203, "T102");
        Passenger pHank = new Passenger("Hank Schrader", 204, "T103");
        Passenger pMarie = new Passenger("Marie Schrader", 205);  // No ticket
        Passenger pHoward = new Passenger("Howard Hamlin", 206, "T104");
        Passenger pChuck = new Passenger("Chuck McGill", 207);    // No ticket

        // Register passengers
        system.addPassenger(pWalter);
        system.addPassenger(pJesse);
        system.addPassenger(pSkyler);
        system.addPassenger(pHank);
        system.addPassenger(pMarie);
        system.addPassenger(pHoward);
        system.addPassenger(pChuck);

        // Create cargo
        BoxCargo box = new BoxCargo(301, 1500, "Electronics");
        BoxCargo box2 = new BoxCargo(302, 5500, "Furniture");

        // Register cargo transports
        system.addCargo(box);
        system.addCargo(box2);

        /*
        * Demonstration
        */
        System.out.println("——— Driver Assignment Operations ———");

        system.assignDriverToTransport(dGustavo, premiumTaxi);  // true
        system.assignDriverToTransport(dMike, regularTaxi);     // true

//      system.assignDriverToTransport(dNacho, regularTaxi);    // false, already has a driver
        system.removeDriverFromTransport(regularTaxi);          // true
//      system.assignDriverToTransport(dGustavo, regularTaxi);  // false, already driving another taxi
        system.assignDriverToTransport(dMike, regularTaxi);     // true
        system.assignDriverToTransport(dKim, tram);             // true
        system.assignDriverToTransport(dNacho, truck);          // true

        System.out.println();
        System.out.println("——— Passenger Boarding & Disembarking Operations ———");

        system.boardPassenger(premiumTaxi, pMarie);  // true
//      system.boardPassenger(regularTaxi, pMarie);  // false, already on transport

        system.disembarkPassenger(premiumTaxi, pMarie);  // true
        system.boardPassenger(premiumTaxi, pMarie);      // true
        system.boardPassenger(premiumTaxi, pHoward);     // true
//      system.boardPassenger(premiumTaxi, pChuck);      // false, taxi full

        system.boardPassenger(tram, pWalter);  // true
        system.boardPassenger(tram, pJesse);   // true
        system.boardPassenger(tram, pSkyler);  // true
        system.boardPassenger(tram, pHank);    // true
//      system.boardPassenger(tram, pChuck);   // false, no ticket

        System.out.println();
        System.out.println("——— Cargo Loading Operations ———");

        system.loadCargo(truck, box);
//      system.loadCargo(truck, box);  // false, exceeds capacity
        system.loadCargo(truck, box2);
        system.unloadCargo(truck, box);

        System.out.println();
        System.out.println("——— Driving Operations ———");

        dGustavo.drive(premiumTaxi, system);  // true
        System.out.println();
        dMike.drive(regularTaxi, system);     // true
        System.out.println();
        dKim.drive(tram, system);             // true
        System.out.println();
        dSaul.drive(bus, system);             // true, auto-assign driver

        System.out.println();
        System.out.println("——— Display Info ———");

        system.displayAllTransports();
        system.displayAllPassengers();
        system.displayAllDrivers();
        system.displayAllCargos();
    }
}
