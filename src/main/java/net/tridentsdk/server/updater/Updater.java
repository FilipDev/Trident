package net.tridentsdk.server.updater;

import net.tridentsdk.api.Trident;

public class Updater {

    public void update() {
        for (UpdateType updateType : UpdateType.values())
            if (updateType.elapsed()) Trident.getServer().getEventManager().call(new UpdateEvent(updateType));
    }

}
