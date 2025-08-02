package dungeon.models;

import dungeon.interfaces.GameObject;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private final List<GameObject> inventory;

    public Player(String name, int initialHealth) {
        this.name = name;
        this.health = initialHealth;
        this.inventory = new ArrayList<>();
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    public void collectItem(GameObject item) {
        inventory.add(item);
        System.out.println("¡Has obtenido: " + item.getName() + "!");
    }

    public void showStatus() {
        System.out.println("\n=== ESTADO DEL JUGADOR ===");
        System.out.println("Nombre: " + name);
        System.out.println("Salud: " + health);
        System.out.println("Inventario (" + inventory.size() + " items):");
        inventory.forEach(item -> System.out.println("- " + item.getName()));
    }

    // Getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public boolean isAlive() { return health > 0; }
}
