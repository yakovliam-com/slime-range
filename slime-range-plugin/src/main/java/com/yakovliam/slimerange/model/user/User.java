package com.yakovliam.slimerange.model.user;

import java.util.UUID;

public class User {

    /**
     * UUID
     */
    private final UUID uuid;

    /**
     * Points
     */
    private double points;

    /**
     * User
     *
     * @param uuid   uuid
     * @param points points
     */
    public User(UUID uuid, double points) {
        this.uuid = uuid;
        this.points = points;
    }

    public UUID getUuid() {
        return uuid;
    }

    public double getPoints() {
        return points;
    }

    public User setPoints(double points) {
        this.points = points;
        return this;
    }
}
