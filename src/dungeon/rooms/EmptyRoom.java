package dungeon.rooms;

import dungeon.interfaces.Room;
import java.util.ArrayList;
import java.util.List;

public class EmptyRoom implements Room {
    private boolean explored = false;
    private List<Integer> adjacentRooms = new ArrayList<>();

    // ... (métodos anteriores permanecen igual)

    @Override
    public void enter() {

    }

    @Override
    public boolean isExplored() {
        return false;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public List<Integer> getAdjacentRooms() {
        return adjacentRooms;
    }

    @Override
    public void setAdjacentRooms(List<Integer> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }
}
