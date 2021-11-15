package dev.iiprocraft.sg.base.player;

import lombok.Data;

import java.util.Objects;

/**
 * @author DirectPlan
 */
@Data
public class SGPlayerStatistics implements dev.iiprocraft.sg.api.player.SGPlayerStatistics {

    private int wins, loses, kills, coins;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SGPlayerStatistics that = (SGPlayerStatistics) o;
        return wins == that.wins && loses == that.loses && kills == that.kills && coins == that.coins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wins, loses, kills, coins);
    }
}
