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

package dev.iiprocraft.sg.nms.block;

import dev.iiprocraft.sg.common.nms.block.BlockAnimationNMS;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.TileEntityChest;
import net.minecraft.server.v1_8_R3.TileEntityEnderChest;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

/**
 * @author iiProCraft
 */
public class BlockAnimation1_R_R3 implements BlockAnimationNMS {

    @Override
    public void sendChestActionPacket(Location location, boolean open) {
        Material type = location.getBlock().getType();
        switch (type) {
            case CHEST: {
                World world = ((CraftWorld) location.getWorld()).getHandle();
                BlockPosition position = new BlockPosition(location.getX(), location.getY(), location.getZ());
                TileEntityChest tileChest = (TileEntityChest) world.getTileEntity(position);
                world.playBlockAction(position, tileChest.w(), 1, open ? 1 : 0);
                break;
            }
            case ENDER_CHEST: {
                World world = ((CraftWorld) location.getWorld()).getHandle();
                BlockPosition position = new BlockPosition(location.getX(), location.getY(), location.getZ());
                TileEntityEnderChest tileChest = (TileEntityEnderChest) world.getTileEntity(position);
                world.playBlockAction(position, tileChest.w(), 1, open ? 1 : 0);
                break;
            }
            default: {
                break;
            }
        }
    }

}
