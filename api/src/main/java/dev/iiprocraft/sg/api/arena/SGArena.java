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
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.iiprocraft.sg.api.arena;

 import com.google.common.base.Objects;
 import dev.iiprocraft.sg.api.game.GameState;
 import org.bukkit.Location;
 import org.bukkit.Material;
 import org.bukkit.block.Block;
 import org.bukkit.block.Chest;
 import org.bukkit.inventory.ItemStack;

 import java.util.*;
 import java.util.concurrent.ThreadLocalRandom;

 public class SGArena {

    /**
     * @author Mqzn
     */

    private final String name;
    private String displayName;
    private  UUID uniqueId = UUID.randomUUID();
    private GameState state = GameState.WAITING;

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


    public GameState getState() {
        return state;
    }

    public String getName() {
        return name;
    }


    public void setState(GameState state) {
        this.state = state;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public static class ArenaVote {

        /**
         * @author Mqzn
         */

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

    public static class ItemsRandomizer {

        /**
         * @author Mqzn
         * @date 15/11/2021
         */


        private final List<ItemStack> items;
        private final Map<Location, List<Integer>> slots;
        private final static int CHEST_SIZE = 26;

        public ItemsRandomizer(Set<Location> chests, List<ItemStack> items) {
            this.items = items;

            slots = new HashMap<>();
            for(Location loc : chests) {
                slots.putIfAbsent(loc, new ArrayList<>());
            }
            Collections.shuffle(items);
        }


        public List<ItemStack> getRemainingItems() {
            return items;
        }

        public Set<Location> getChests() {
            return slots.keySet();
        }

        private void placeItemInChest(int slot, ItemStack item, Location location) {

            if(slot > CHEST_SIZE) return;

            Block block = location.getWorld().getBlockAt(location);
            if(block.getType() != Material.CHEST) return;

            Chest chest = (Chest)block;
            chest.getInventory().setItem(slot, item);

            chest.update();
        }

        public void process() {

            for(Location location : slots.keySet()) {
                List<ItemStack> itemsForChest = items.subList(0, CHEST_SIZE);
                items.removeIf(itemsForChest::contains);

                for(ItemStack item : itemsForChest) {
                    this.placeItemInChest(this.getNextRandomSlot(location), item, location);
                }
            }

        }

        private int getNextRandomSlot(Location chest) {
            List<Integer> slots = this.slots.get(chest);

            int result = ThreadLocalRandom.current().nextInt(0, CHEST_SIZE);
            while (slots.contains(result)) {
                result = ThreadLocalRandom.current().nextInt(0, CHEST_SIZE);
            }

            slots.add(result);
            this.slots.put(chest, slots);

            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ItemsRandomizer)) return false;
            ItemsRandomizer that = (ItemsRandomizer) o;
            return Objects.equal(items, that.items) &&
                    Objects.equal(slots, that.slots);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(items, slots);
        }

    }

}
