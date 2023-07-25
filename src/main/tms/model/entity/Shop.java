/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

public class Shop {
    enum Status {
        Open,
        Closed
    }

    private static int nextId = 1;

    private static int getNextId() {
        return nextId++;
    }

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

    public static Shop create(String name, User owner) {
        return new Shop(name, owner);
    }
}
