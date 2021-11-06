/*
 *     SurvivalGames - A simple and classic mini-game.
 *     Copyright (C) 2021
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.iiprocraft.sg.api.arena;

import dev.iiprocraft.sg.api.arena.vote.VotableArena;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SGArena {

    private final String name;
    private final UUID uniqueId;
    private boolean voting;
    private ArenaState state;
    private final List<VotableArena> votableArenas;

    public SGArena(String name) {
        this.name = name;
        this.state = ArenaState.WAITING;
        this.uniqueId = UUID.randomUUID();
        this.votableArenas = new ArrayList<>();
    }

    public List<VotableArena> getVotableArenas() {
        return votableArenas;
    }

    public ArenaState getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public boolean isVoting() {
        return voting;
    }

    public void setState(ArenaState state) {
        this.state = state;
    }

    public void setVoting(boolean voting) {
        this.voting = voting;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }
}
