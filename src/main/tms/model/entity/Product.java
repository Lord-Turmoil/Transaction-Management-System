/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 21:02
 * @Author  : Tony Skywalker
 * @File    : Product.java
 */

package tms.model.entity;

public class Product {
    private static int nextId = 1;

    public static int getNextId() {
        return nextId++;
    }

    public int id;
    public String name;
    public int totalStock;

    // Merchant Id
    public String ownerId;

    private Product(String name, String ownerId) {
        this.id = getNextId();
        this.name = name;
        this.totalStock = 0;
        this.ownerId = ownerId;
    }

    public static Product create(String name, String ownerId) {
        return new Product(name, ownerId);
    }
}
