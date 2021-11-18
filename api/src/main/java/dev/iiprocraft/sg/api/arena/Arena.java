package dev.iiprocraft.sg.api.arena;

import dev.iiprocraft.sg.api.util.RandomList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author DirectPlan
 */
public interface Arena {

    UUID getUniqueId();

    String getDisplayName();

    void setDisplayName(String displayName);

    void setUniqueId(UUID uniqueId);

    GameState getState();

    String getName();

    void setState(GameState state);

    class ItemsRandomizer {

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
                    if(slot == -1) break;
                    newInv.setItem(slot, items.next());

                }

                slots.compute(location, (loc, inv) -> newInv);
            }

        }

        private int getNextRandomSlot(Location chest) {
            Inventory slots = this.slots.get(chest);
            if(slots.firstEmpty() == -1) return -1;
            int result = ThreadLocalRandom.current().nextInt(0, CHEST_SIZE);
            while (slots.getItem(result) != null && slots.getItem(result).getType() != Material.AIR) {
                result = ThreadLocalRandom.current().nextInt(0, CHEST_SIZE);
            }

            this.slots.put(chest, slots);

            return result;
        }

    }

}
