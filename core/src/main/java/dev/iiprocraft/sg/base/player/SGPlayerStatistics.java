package dev.iiprocraft.sg.base.player;

import lombok.Data;

/**
 * @author DirectPlan
 */
@Data
public class SGPlayerStatistics implements dev.iiprocraft.sg.api.player.SGPlayerStatistics {

    private int wins, loses, kills, coins, played;
}
