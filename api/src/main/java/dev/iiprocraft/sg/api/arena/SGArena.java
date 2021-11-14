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

 import com.google.common.base.Objects;

 import java.util.UUID;

public class SGArena {

    private final String name;
    private String displayName;
    private  UUID uniqueId = UUID.randomUUID();
    private ArenaState state = ArenaState.WAITING;

    public SGArena(String name) {
        this.name = name;
        this.displayName = name;
    }


    public SGArena(UUID uniqueId, String name, String display) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.displayName = display;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }


    public ArenaState getState() {
        return state;
    }

    public String getName() {
        return name;
    }


    public void setState(ArenaState state) {
        this.state = state;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public static class ArenaVote {

        private final UUID voter;
        private final SGArena arena;

        public ArenaVote(UUID voter, SGArena arena) {
            this.voter = voter;
            this.arena = arena;
        }

        public SGArena getArena() {
            return arena;
        }

        public UUID getVoter() {
            return voter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ArenaVote)) return false;
            ArenaVote arenaVote = (ArenaVote) o;
            return Objects.equal(getVoter(), arenaVote.getVoter()) &&
                    Objects.equal(getArena(), arenaVote.getArena());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getVoter(), getArena());
        }
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SGArena)) return false;
        SGArena sgArena = (SGArena) o;
        return
                Objects.equal(getName(), sgArena.getName()) &&
                Objects.equal(getDisplayName(), sgArena.getDisplayName()) &&
                Objects.equal(getUniqueId(), sgArena.getUniqueId()) &&
                getState() == sgArena.getState();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getDisplayName(),
                getUniqueId(), getState());

    }


}
