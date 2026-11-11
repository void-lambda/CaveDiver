package CaveDiver.src.CaveDiver.src;

public class Room {
    public int id;
    public String name;
    public String description;

    public int northId = -1;
    public int southId = -1;
    public int eastId = -1;
    public int westId = -1;

    // INIT
    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Room north(int id) { this.northId = id; return this; }
    public Room south(int id) { this.southId = id; return this; }
    public Room east(int id) { this.eastId = id; return this; }
    public Room west(int id) { this.westId = id; return this; }

    public int neighbourId(String dir) {
        return switch (dir.toLowerCase()) {
            case "n", "north" -> northId;
            case "s", "south" -> southId;
            case "e", "east" -> eastId;
            case "w", "west" -> westId;
            default -> -1;
        };
    }
}
