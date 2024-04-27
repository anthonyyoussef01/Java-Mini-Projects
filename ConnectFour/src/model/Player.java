package model;

public class Player {
    private PlayerType type;
    private String name;

    public Player(PlayerType type, String name) {
        this.type = type;
        this.name = name;
    }

    // Getters & Setters ------------------------------

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
