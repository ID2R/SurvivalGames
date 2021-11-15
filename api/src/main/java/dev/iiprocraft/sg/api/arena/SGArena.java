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
 import org.bukkit.Bukkit;
 import org.bukkit.Location;
 import org.bukkit.Material;
 import org.bukkit.inventory.Inventory;
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


        private final RandomList<ItemStack> items;
        private final HashMap<Location, Inventory> slots;
        private final static int CHEST_SIZE = 26;

        public ItemsRandomizer(Set<Location> chests, RandomList<ItemStack> items) {
            this.items = items;

            slots = new HashMap<>();
            for(Location loc : chests) {
                slots.putIfAbsent(loc, Bukkit.createInventory(null, 27, "A Chest"));
            }
        }


        public RandomList<ItemStack> getRemainingItems() {
            return items;
        }

        public Set<Location> getChests() {
            return slots.keySet();
        }


        public void process() {

            for(Location location : slots.keySet()) {

                Inventory newInv = slots.get(location);


                for (int i = 0; i < CHEST_SIZE+1 ; i++) {
                    int slot = this.getNextRandomSlot(location);
                    newInv.setItem(slot, items.next());

                }

                slots.compute(location, (loc, inv) -> newInv);
            }

        }

        private int getNextRandomSlot(Location chest) {
            Inventory slots = this.slots.get(chest);

            int result = ThreadLocalRandom.current().nextInt(0, CHEST_SIZE);
            while (slots.getItem(result) != null && slots.getItem(result).getType() != Material.AIR) {
                result = ThreadLocalRandom.current().nextInt(0, CHEST_SIZE);
            }

            this.slots.put(chest, slots);

            return result;
        }

    }

}
