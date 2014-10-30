package net.tridentsdk.server.updater;

import net.tridentsdk.api.event.Event;

public class UpdateEvent extends Event {

    private final UpdateType updateType;

    public UpdateEvent(UpdateType updateType) {
        this.updateType = updateType;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

}
