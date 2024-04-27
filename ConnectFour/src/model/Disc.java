package model;

public class Disc {
    private Player player;
     public Disc(Player player) {
        this.player = player;
    }

    // Setters & Getters ------------------------------

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
