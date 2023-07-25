/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 20:48
 * @Author  : Tony Skywalker
 * @File    : Shop.java
 */

package tms.model.entity;

public class Shop {
    enum Status {
        Open,
        Closed
    }

    private static int nextId = 1;

    public static int getNextId() {
        return nextId++;
    }

    public int id;
    public String name;
    public Status status;
}
