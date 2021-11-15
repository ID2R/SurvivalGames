package dev.iiprocraft.sg.base.player;

import lombok.Data;
import lombok.Setter;
import java.util.UUID;

@Data
public class SGPlayer implements dev.iiprocraft.sg.api.player.SGPlayer {

    private final UUID id;
    @Setter private SGPlayerStatistics playerStatistics;

    public SGPlayer(UUID id) {
        this.id = id;
    }

    @Override
    public dev.iiprocraft.sg.api.player.SGPlayerStatistics getPlayerStatistics() {
        return null;
    }
}
