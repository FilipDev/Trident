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
import net.tridentsdk.server.netty.packet.OutPacket;

public class PacketPlayOutAttachEntity extends OutPacket {

    protected int entityId;
    protected int vehicleId;
    protected boolean leash;

    @Override
    public int getId() {
        return 0x0B;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public int getVehicleId() {
        return this.vehicleId;
    }

    public boolean isLeashed() {
        return this.leash;
    }

    @Override
    public void encode(ByteBuf buf) {
        buf.writeInt(this.entityId); // Well, that's a first
        buf.writeInt(this.vehicleId); // AGAIN
        // rip in peace varints

        buf.writeBoolean(this.leash);
    }
}
