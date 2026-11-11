package CaveDiver;

import java.util.HashMap;
import java.util.Map;

import CaveDiver.src.CaveDiver.src.*;

public class Main {
    
    public class Game {
        public static final Map<Integer, Room> ROOMS = new HashMap<>();
        public static int  currentRoomId = 13;  // spawnpoint

        static {
            // 5x5 grid
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    int id = row * 5 + col + 1; // 1...25
                    String name = (id == 13) ? "Spawn" : "Room " + id;
                    Room r = new Room(id, name, "A Dark Chamber.");

                    if (row > 0) r.north(id - 5);
                    if (row < 4) r.south(id + 5);
                    if (col > 0) r.east(id - 1);
                    if (col < 4) r.west(id + 1);

                    ROOMS.put(id, r);
                }
            }

            // Bosses
            ROOMS.put(26, new Room(26, "Frost Lair", "An icy Guardian awaits.").south(3));
            ROOMS.put(27, new Room(27, "Flame Lair", "The heat is ubearable.").south(23));
            ROOMS.put(28, new Room(28, "Shadow Lair", "You hear whispers.").south(11));
            ROOMS.put(29, new Room(29, "Storm Lair", "Wind howls.").south(15));

            ROOMS.get(3).north(26);
            ROOMS.get(23).north(27);
            ROOMS.get(11).north(28);
            ROOMS.get(15).north(29);
        }

        // Current Room where you are
        public static Room current() {
            return ROOMS.get(currentRoomId);
        }

        public static void goTo(int id) {
            if (!ROOMS.containsKey(id)) {
                throw new IllegalArgumentException("No room with id " + id);
            }
            currentRoomId = id;
        }

        // Step or Wall
        public static boolean move(String dir) {
            int next = current().neighbourId(dir);
            if (next == -1) return false;
            currentRoomId = next;
            return true;

        }

        
        public static void look() {
            Room r = current();
            Utils.displayText("=== " + r.name + ", id=" + r.id + " ===", 30);
            Utils.displayText(r.description, 50);
            System.out.println("Exits: " + exits(r));
        }

        private static String exits(Room r) {
            StringBuilder sb = new StringBuilder();
            if (r.northId != -1) sb.append("N ");
            if (r.southId != -1) sb.append("S ");
            if (r.eastId != -1) sb.append("E ");
            if (r.westId != -1) sb.append("W ");
            return sb.toString().trim();
        }
    }
    
    public static void main(String[] args) {

        // Music
        SoundManager.playMusicLoop("/music/creepy_cave.mp3");

        
        Utils.clear();
        Utils.displayText("You wake up in the dark cave...", 50);
        Utils.scanner.nextLine();
        Utils.displayText("Endure.", 150);
        Utils.scanner.nextLine();

        Game.goTo(8);

        // Game Loop
        while (true) {
            Utils.clear();

            Game.look();
            System.out.print("> ");
            String input = Utils.scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                SoundManager.stopMusic();
                break;
            }

            if (!Game.move(input)) {
                Utils.displayText("A wall blocks your way", 50);
            }
        }
    }
}
