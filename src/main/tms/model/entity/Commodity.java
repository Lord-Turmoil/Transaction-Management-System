/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 21:02
 * @Author  : Tony Skywalker
 * @File    : Commodity.java
 */

package tms.model.entity;

public class Commodity {
    public enum Status {
        Available,
        Unavailable
    }

    public int shopId;
    public int stock;
    public Status status;
    public Product product;

    private Commodity(int shopId, int stock, Product product) {
        this.shopId = shopId;
        this.stock = stock;
        this.status = Status.Available;
        this.product = product;
    }

    // Warning: This will change product!
    public static Commodity create(int shopId, int stock, Product product) {
        product.totalStock += stock;
        return new Commodity(shopId, stock, product);
    }
}
