/*
 * MIT License
 *
 * Copyright (c) 2021 iiProCraft
 * Copyright (c) 2021 Invvk
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

package dev.iiprocraft.sg.base.adapters;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dev.iiprocraft.sg.api.arena.ArenaWorld;
import dev.iiprocraft.sg.api.arena.adapter.WorldAdapter;
import org.bukkit.World;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class BukkitAdapter implements WorldAdapter {

    private final ExecutorService worldsWorker;

    public BukkitAdapter() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("worlds-worker").build();
        this.worldsWorker = Executors.newSingleThreadExecutor(threadFactory);
    }

    @Override
    public World createCopy(ArenaWorld arenaWorld) {
        return null;
    }

    @Override
    public World loadWorld(String name) {
        return null;
    }

    @Override
    public ExecutorService getWorker() {
        return worldsWorker;
    }
}
