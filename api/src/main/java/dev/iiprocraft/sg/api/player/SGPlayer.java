package dev.iiprocraft.sg.api.player;

import com.google.common.base.Objects;
import java.util.UUID;

public class SGPlayer {

    private final UUID id;
    private int wins = 0, loses = 0, kills = 0, coins = 500;

    public SGPlayer(UUID id) {
        this.id = id;
    }

    public SGPlayer(UUID id, int wins, int loses, int kills, int coins) {
        this(id);
        this.wins = wins;
        this.loses = loses;
        this.kills = kills;
        this.coins = coins;

        //This will be used when loading the object from the storage provided
    }

    public UUID getId() {
        return id;
    }

    public int getCoins() {
        return coins;
    }

    public int getKills() {
        return kills;
    }

    public int getLoses() {
        return loses;
    }

    public int getWins() {
        return wins;
    }

    public void addKill() {
        kills++;
    }

    public void addWins() {
        wins++;
    }

    public void addLoses() {
        loses++;
    }

    public void addCoins(int extra) {
        coins+=extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SGPlayer)) return false;
        SGPlayer sgPlayer = (SGPlayer) o;
        return getWins() == sgPlayer.getWins() &&
                getLoses() == sgPlayer.getLoses() &&
                getKills() == sgPlayer.getKills() &&
                getCoins() == sgPlayer.getCoins() &&
                Objects.equal(getId(), sgPlayer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getWins(), getLoses(),
                getKills(), getCoins());
    }

}
