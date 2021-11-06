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

package dev.iiprocraft.sg.api.arena.vote;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VotableArena {
    private final String name;
    private final List<Player> voters;
    public VotableArena(String name) {
        this.name = name;
        this.voters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Player> getVoters() {
        return voters;
    }
}
