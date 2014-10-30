package net.tridentsdk.server.updater;

public enum UpdateType {

    SECOND(20L), MINUTE(1200L), HOUR(72000L);

    private long ticks;
    private long lastTime;

    UpdateType(long ticks) {
        this.ticks = ticks * 50L;
        this.lastTime = System.currentTimeMillis();
    }

    public static boolean elapsed(long time, long required) {
        return System.currentTimeMillis() - time >= required;
    }

    public long getTicks() {
        return ticks;
    }

    protected boolean elapsed() {
        if (elapsed(this.lastTime, this.ticks)) {
            this.lastTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
