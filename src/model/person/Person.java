package model.person;

public abstract class Person {
    private final String name;
    private final int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public abstract void displayInfo();

    @Override
    public String toString() {
        return getName() + " ID: " + getId();
    }
}
