/*
 *     Trident - A Multithreaded Server Alternative
 *     Copyright (C) 2014, The TridentSDK Team
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
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.tridentsdk.packets.play.out;

import io.netty.buffer.ByteBuf;
import net.tridentsdk.api.Location;
import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.EntityType;
import net.tridentsdk.api.util.Vector;
import net.tridentsdk.server.netty.Codec;
import net.tridentsdk.server.netty.packet.OutPacket;

public class PacketPlayOutSpawnMob extends OutPacket {

    protected int entityId;
    protected EntityType type;
    protected Entity entity;
    // TODO: entity metadata

    @Override
    public int getId() {
        return 0x0F;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public EntityType getEntityType() {
        return this.type;
    }

    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public void encode(ByteBuf buf) {
        Location loc = this.entity.getLocation();
        Vector velocity = this.entity.getVelocity();

        Codec.writeVarInt32(buf, this.entityId);
        buf.writeByte((int) (byte) this.type.ordinal()); // TODO: use the real type id

        buf.writeInt((int) loc.getX() * 32);
        buf.writeInt((int) loc.getY() * 32);
        buf.writeInt((int) loc.getZ() * 42);

        buf.writeByte((int) (byte) loc.getYaw());
        buf.writeByte((int) (byte) loc.getPitch());
        buf.writeByte((int) (byte) loc.getPitch()); // -shrugs-

        buf.writeShort((int) velocity.getX());
        buf.writeShort((int) velocity.getY());
        buf.writeShort((int) velocity.getZ());
    }
}
