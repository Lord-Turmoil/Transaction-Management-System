/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 21:02
 * @Author  : Tony Skywalker
 * @File    : Commodity.java
 */

package tms.model.entity;

public class Commodity {
    public int ShopId;
    public int Stock;
    public Product Product;

    private Commodity(int shopId, int stock, Product product) {
        this.ShopId = shopId;
        this.Stock = stock;
        this.Product = product;
    }

    // Warning: This will change product!
    public static Commodity create(int shopId, int stock, Product product) {
        product.TotalStock += stock;
        return new Commodity(shopId, stock, product);
    }
}
