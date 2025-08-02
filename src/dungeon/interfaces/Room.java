package dungeon.interfaces;

import java.util.List;

public interface Room {
    void enter();
    boolean isExplored();
    String getDescription();
    List<Integer> getAdjacentRooms(); // Nuevo método para salas adyacentes
    void setAdjacentRooms(List<Integer> adjacentRooms); // Establecer salas conectadas
}
