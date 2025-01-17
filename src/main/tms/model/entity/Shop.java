/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

public class Shop {
    private static int nextId = 1;
    public int id;
    public String name;
    public Status status;
    public User owner;

    private Shop(String name, User owner) {
        this.id = getNextId();
        this.name = name;
        this.status = Status.Open;
        this.owner = owner;
    }

    private static int getNextId() {
        return nextId++;
    }

    public static Shop create(String name, User owner) {
        return new Shop(name, owner);
    }

    public String getId() {
        return "S-" + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Shop other) {
            return this.id == other.id;
        }
        return false;
    }

    public enum Status {
        Open,
        Closed
    }
}
