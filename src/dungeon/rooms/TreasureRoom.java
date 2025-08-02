package dungeon.rooms;

import dungeon.interfaces.Room;
import dungeon.models.Treasure;

import java.util.List;

public class TreasureRoom implements Room {
    private final Treasure treasure;
    private boolean explored = false;

    public TreasureRoom(Treasure treasure) {
        this.treasure = treasure;
    }

    @Override
    public void enter() {
        System.out.println("¡Has encontrado un tesoro: " + treasure.getName() + "!");
        explored = true;
    }

    @Override
    public boolean isExplored() {
        return explored;
    }

    @Override
    public String getDescription() {
        return "Sala con tesoro: " + treasure.getName();
    }

    @Override
    public List<Integer> getAdjacentRooms() {
        return List.of();
    }

    @Override
    public void setAdjacentRooms(List<Integer> adjacentRooms) {

    }

    public Treasure getTreasure() {
        return treasure;
    }
}
