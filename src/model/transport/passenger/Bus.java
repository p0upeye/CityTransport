package model.transport.passenger;

public class Bus extends PassengerTransport {
    private final boolean isDoubleDecker;  // double-decker or not

    public Bus(int id, String routeNumber, int capacity, boolean isDoubleDecker) {
        super(id, routeNumber, capacity);
        this.isDoubleDecker = isDoubleDecker;
    }

    public boolean isDoubleDecker() {
        return isDoubleDecker;
    }

    @Override
    protected void displaySpecificInfo() {
        System.out.println("Type: " + (isDoubleDecker ? "Double-decker Bus" : "Regular Bus"));
        System.out.println();
    }

    @Override
    public void move() {
        System.out.print("🚌 ");
        super.move();
    }
}
