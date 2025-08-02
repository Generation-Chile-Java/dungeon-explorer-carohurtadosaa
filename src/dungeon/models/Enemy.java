package dungeon.models;

import dungeon.interfaces.GameObject;

public class Enemy implements GameObject {
    private final String name;
    private final int damage;

    public Enemy(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void interact() {
        System.out.println("El " + name + " gruñe amenazadoramente");
    }

    public int getDamage() {
        return damage;
    }
}
