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

package dev.iiprocraft.sg.base.adapters.world;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.grinderwolf.swm.api.SlimePlugin;
import com.grinderwolf.swm.api.exceptions.CorruptedWorldException;
import com.grinderwolf.swm.api.exceptions.NewerFormatException;
import com.grinderwolf.swm.api.exceptions.UnknownWorldException;
import com.grinderwolf.swm.api.exceptions.WorldInUseException;
import com.grinderwolf.swm.api.world.SlimeWorld;
import com.grinderwolf.swm.api.world.properties.SlimeProperties;
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap;
import dev.iiprocraft.sg.base.game.arena.ArenaWorld;
import dev.iiprocraft.sg.base.adapters.WorldAdapter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class SlimeWorldManagerAdapter implements WorldAdapter {

    private final ExecutorService worldsWorker;

    private static final Map<String, SlimeWorld> worldMap = new HashMap<>();

    private final SlimePlugin slimePlugin;
    public SlimeWorldManagerAdapter(Plugin plugin) {
        this.slimePlugin = (SlimePlugin) plugin;
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("worlds-worker").build();
        this.worldsWorker = Executors.newSingleThreadExecutor(threadFactory);
    }

    @Override
    public World createCopy(ArenaWorld arenaWorld) {
        UUID uuid = UUID.randomUUID();
        String[] values = uuid.toString().split("-");
        String name = "sg_" + values[0] + values[2];
        if(!worldMap.containsKey(arenaWorld.getWorld().getName()))
            return null;
        worldMap.get(arenaWorld.getWorld().getName()).clone(name);
        return Bukkit.getWorld(name);
    }
    @Override
    public World loadWorld(String name) {
        SlimePropertyMap slimePropertyMap = new SlimePropertyMap();
        slimePropertyMap.setString(SlimeProperties.WORLD_TYPE, "flat");
        slimePropertyMap.setInt(SlimeProperties.SPAWN_X, 0);
        slimePropertyMap.setInt(SlimeProperties.SPAWN_Y, 0);
        slimePropertyMap.setInt(SlimeProperties.SPAWN_Z, 0);
        slimePropertyMap.setBoolean(SlimeProperties.ALLOW_ANIMALS, false);
        slimePropertyMap.setString(SlimeProperties.DIFFICULTY, "easy");
        try {
            worldMap.put(name, this.slimePlugin.loadWorld(slimePlugin.getLoader("file"), name, true, slimePropertyMap));
            return Bukkit.getWorld(name);
        } catch (UnknownWorldException | IOException | CorruptedWorldException | NewerFormatException | WorldInUseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExecutorService getWorker() {
        return worldsWorker;
    }
}
