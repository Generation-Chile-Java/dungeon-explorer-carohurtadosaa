package dungeon.models;

import dungeon.interfaces.GameObject;

public class Treasure implements GameObject {
    private final String name;

    public Treasure(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void interact() {
        System.out.println("El tesoro " + name + " brilla intensamente");
    }
}
