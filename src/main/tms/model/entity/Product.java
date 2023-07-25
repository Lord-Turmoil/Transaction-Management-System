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

    public int Id;
    public String Name;
    public int TotalStock;

    // Merchant Id
    public String OwnerId;

    private Product(String name, String ownerId) {
        this.Id = getNextId();
        this.Name = name;
        this.TotalStock = 0;
        this.OwnerId = ownerId;
    }

    public static Product create(String name, String ownerId) {
        return new Product(name, ownerId);
    }
}
