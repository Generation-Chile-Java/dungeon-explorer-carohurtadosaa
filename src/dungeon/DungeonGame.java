package dungeon;

import dungeon.interfaces.Room;
import dungeon.models.Enemy;
import dungeon.models.Player;
import dungeon.models.Treasure;
import dungeon.rooms.EmptyRoom;
import dungeon.rooms.EnemyRoom;
import dungeon.rooms.TreasureRoom;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DungeonGame {
    private final Player player;
    private final Map<Integer, Room> dungeon;
    private int currentRoomId;
    private static final int TOTAL_SALAS = 7; // Ahora tenemos 7 salas

    public DungeonGame() {
        mostrarInstrucciones();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngresa tu nombre, valiente aventurero: ");
        this.player = new Player(scanner.nextLine(), 100);

        // Construir mazmorra ampliada
        this.dungeon = new HashMap<>();

        // Crear salas (2 nuevas añadidas)
        Room room0 = new EmptyRoom();
        Room room1 = new TreasureRoom(new Treasure("Cofre dorado"));
        Room room2 = new EnemyRoom(new Enemy("Orco", 20));
        Room room3 = new TreasureRoom(new Treasure("Espada legendaria"));
        Room room4 = new EnemyRoom(new Enemy("Dragón", 40));
        Room room5 = new TreasureRoom(new Treasure("Corona real"));
        Room room6 = new TreasureRoom(new Treasure("Poción de vida")); // Nueva sala con objeto útil
        Room room7 = new EnemyRoom(new Enemy("Araña gigante", 15)); // Nueva sala con enemigo

        // Establecer conexiones entre salas (actualizado)
        room0.setAdjacentRooms(List.of(1, 2, 6)); // Sala inicial ahora conecta a 3 salas
        room1.setAdjacentRooms(List.of(0, 3));
        room2.setAdjacentRooms(List.of(0, 4));
        room3.setAdjacentRooms(List.of(1, 5));
        room4.setAdjacentRooms(List.of(2, 5));
        room5.setAdjacentRooms(new ArrayList<>()); // Sala final
        room6.setAdjacentRooms(List.of(0, 7)); // Nueva conexión
        room7.setAdjacentRooms(List.of(6, 3)); // Nueva conexión

        // Añadir salas al mapa
        dungeon.put(0, room0);
        dungeon.put(1, room1);
        dungeon.put(2, room2);
        dungeon.put(3, room3);
        dungeon.put(4, room4);
        dungeon.put(5, room5);
        dungeon.put(6, room6);
        dungeon.put(7, room7);

        currentRoomId = 0;
    }

    private void mostrarInstrucciones() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║         EXPLORACIÓN DE MAZMORRA        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ INSTRUCCIONES:                         ║");
        System.out.println("║                                        ║");
        System.out.println("║ • Explorarás una mazmorra con " + TOTAL_SALAS + " salas  ║");
        System.out.println("║   conectadas entre sí                   ║");
        System.out.println("║ • Tipos de salas:                      ║");
        System.out.println("║   - Vacías                             ║");
        System.out.println("║   - Con tesoros (3 tipos)               ║");
        System.out.println("║   - Con enemigos (3 tipos)              ║");
        System.out.println("║ • Tesoros se añaden a tu inventario    ║");
        System.out.println("║ • Enemigos reducen tu salud            ║");
        System.out.println("║                                        ║");
        System.out.println("║ CONTROLES:                             ║");
        System.out.println("║ • Ingresa el número de la sala a la    ║");
        System.out.println("║   que quieres moverte                  ║");
        System.out.println("║ • Ingresa 0 para salir del juego       ║");
        System.out.println("║                                        ║");
        System.out.println("║ OBJETIVO:                              ║");
        System.out.println("║ • Encontrar la sala final (Sala 5)     ║");
        System.out.println("║   con la Corona real                   ║");
        System.out.println("║ • Sobrevivir con salud > 0             ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    public void start() {
        System.out.println("\n¡Bienvenido a la mazmorra, " + player.getName() + "!");
        System.out.println("Tu aventura comienza en la Sala 0...");

        Scanner scanner = new Scanner(System.in);

        while (player.isAlive()) {
            Room currentRoom = dungeon.get(currentRoomId);
            System.out.println("\n=== Sala " + currentRoomId + " ===");

            // Solo mostrar descripción básica si no ha sido explorada
            if (!currentRoom.isExplored()) {
                System.out.println("Una sala desconocida...");
            } else {
                System.out.println("Descripción: " + currentRoom.getDescription());
            }

            // Mostrar opciones de movimiento primero
            List<Integer> adjacentRooms = currentRoom.getAdjacentRooms();
            System.out.println("\nSalas disponibles:");
            for (int i = 0; i < adjacentRooms.size(); i++) {
                Room room = dungeon.get(adjacentRooms.get(i));
                // No mostrar lo que hay en las salas adyacentes
                System.out.println((i + 1) + ". Sala " + adjacentRooms.get(i));
            }

            System.out.print("\nElige una sala (1-" + adjacentRooms.size() + "), 0 para salir: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("¡Abandonando la mazmorra!");
                break;
            } else if (choice > 0 && choice <= adjacentRooms.size()) {
                currentRoomId = adjacentRooms.get(choice - 1);
                // Ahora entramos a la sala y revelamos su contenido
                enterRoom(currentRoomId);
            } else {
                System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }

        System.out.println("\n=== FIN DEL JUEGO ===");
        player.showStatus();
    }

    private void enterRoom(int roomId) {
        Room room = dungeon.get(roomId);
        System.out.println("\nEntrando a la Sala " + roomId + "...");

        room.enter(); // Esto revelará el contenido de la sala

        // Manejar interacciones específicas
        if (room instanceof TreasureRoom) {
            player.collectItem(((TreasureRoom) room).getTreasure());
        } else if (room instanceof EnemyRoom) {
            Enemy enemy = ((EnemyRoom) room).getEnemy();
            player.takeDamage(enemy.getDamage());
            System.out.println("¡Has recibido " + enemy.getDamage() + " de daño!");
        }

        player.showStatus();
    }

    public static void main(String[] args) {
        DungeonGame game = new DungeonGame();
        game.start();
    }
}