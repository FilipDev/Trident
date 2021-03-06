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
package net.tridentsdk.packets.play.in;

import io.netty.buffer.ByteBuf;
import net.tridentsdk.api.Location;
import net.tridentsdk.api.event.player.PlayerTabCompleteEvent;
import net.tridentsdk.packets.play.out.PacketPlayOutTabComplete;
import net.tridentsdk.player.PlayerConnection;
import net.tridentsdk.server.netty.ClientConnection;
import net.tridentsdk.server.netty.Codec;
import net.tridentsdk.server.netty.packet.InPacket;
import net.tridentsdk.server.netty.packet.Packet;

/**
 * Sent when the user presses tab while writing text. The payload contains all text behind the cursor.
 */
public class PacketPlayInTabComplete extends InPacket {

    /**
     * Text currently written
     */
    protected String text;
    /**
     * If player is looking at a specific block
     */
    protected boolean hasPosition;
    /**
     * Position of the block the player is looking at, only sent if hasPosition is true
     */
    protected Location lookedAtBlock;

    @Override
    public int getId() {
        return 0x14;
    }

    @Override
    public Packet decode(ByteBuf buf) {
        this.text = Codec.readString(buf);
        this.hasPosition = buf.readBoolean();

        if (this.hasPosition) {
            long encoded = buf.readLong();
            double x = (double) (encoded << 38);
            double y = (double) (encoded << 26 >> 52);
            double z = (double) (encoded << 38 >> 38);

            this.lookedAtBlock = new Location(null, x, y, z);
        }

        return this;
    }

    public String getText() {
        return this.text;
    }

    public boolean isHasPosition() {
        return this.hasPosition;
    }

    public Location getLookedAtBlock() {
        return this.lookedAtBlock;
    }

    @Override
    public void handleReceived(ClientConnection connection) {
        PlayerTabCompleteEvent event = new PlayerTabCompleteEvent(
                ((PlayerConnection) connection).getPlayer(), this.text);

        if (event.getSuggestions().length > 0) {
            connection.sendPacket(new PacketPlayOutTabComplete().set("matches", event.getSuggestions()));
        }
    }
}
