/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 21:02
 * @Author  : Tony Skywalker
 * @File    : Product.java
 */

package tms.model.entity;

import java.math.BigDecimal;

public class Product {
    private static int nextId = 1;

    private static int getNextId() {
        return nextId++;
    }

    public int id;
    public String name;

    // I'm afraid double may cause precision error
    public BigDecimal price;
    public int totalStock;

    // Merchant Id
    public User owner;

    private Product(String name, BigDecimal price, User owner) {
        this.id = getNextId();
        this.name = name;
        this.totalStock = 0;
        this.price = price;
        this.owner = owner;
    }

    public static Product create(String name, BigDecimal price, User owner) {
        return new Product(name, price, owner);
    }
}
