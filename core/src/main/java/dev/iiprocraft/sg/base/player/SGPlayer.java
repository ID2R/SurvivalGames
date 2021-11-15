package dev.iiprocraft.sg.base.player;

import com.google.common.base.Objects;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SGPlayer)) return false;
        SGPlayer sgPlayer = (SGPlayer) o;
        return sgPlayer.getPlayerStatistics() == playerStatistics && Objects.equal(getId(), sgPlayer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), playerStatistics);
    }
}
