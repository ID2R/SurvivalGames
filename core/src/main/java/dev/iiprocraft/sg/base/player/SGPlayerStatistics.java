package dev.iiprocraft.sg.base.player;

import lombok.Data;
import lombok.Setter;

import java.util.UUID;

/**
 * @author DirectPlan
 */
@Data
public class SGPlayerStatistics implements dev.iiprocraft.sg.api.player.SGPlayerStatistics {

    @Setter
    private int wins, loses, kills, coins, played;

    public SGPlayerStatistics(int wins, int loses, int kills, int coins, int played) {
        this.wins = wins;
        this.loses = loses;
        this.kills = kills;
        this.coins = coins;
        this.played = played;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public int getLoses() {
        return loses;
    }

    @Override
    public int getKills() {
        return kills;
    }

    @Override
    public int getCoins() {
        return coins;
    }

    @Override
    public int getPlayed() {
        return played;
    }


}
