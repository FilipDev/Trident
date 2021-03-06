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
package net.tridentsdk.server.netty.protocol;

import net.tridentsdk.api.docs.AccessNoDoc;
import net.tridentsdk.packets.status.PacketStatusInPing;
import net.tridentsdk.packets.status.PacketStatusInRequest;
import net.tridentsdk.packets.status.PacketStatusOutPing;
import net.tridentsdk.packets.status.PacketStatusOutResponse;

@AccessNoDoc class Status extends PacketManager {
    Status() {
        this.inPackets.put(0x00, PacketStatusInRequest.class);
        this.inPackets.put(0x01, PacketStatusInPing.class);

        this.outPackets.put(0x00, PacketStatusOutResponse.class);
        this.outPackets.put(0x01, PacketStatusOutPing.class);
    }
}