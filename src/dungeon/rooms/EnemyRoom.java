package dungeon.rooms;

import dungeon.interfaces.Room;
import dungeon.models.Enemy;

import java.util.List;

public class EnemyRoom implements Room {
    private final Enemy enemy;
    private boolean explored = false;

    public EnemyRoom(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void enter() {
        System.out.println("¡Un " + enemy.getName() + " te ataca!");
        explored = true;
    }

    @Override
    public boolean isExplored() {
        return explored;
    }

    @Override
    public String getDescription() {
        return "Sala con enemigo: " + enemy.getName();
    }

    @Override
    public List<Integer> getAdjacentRooms() {
        return List.of();
    }

    @Override
    public void setAdjacentRooms(List<Integer> adjacentRooms) {

    }

    public Enemy getEnemy() {
        return enemy;
    }
}