 /*
 * MIT License
 *
 * Copyright (c) 2021 iiProCraft & contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
