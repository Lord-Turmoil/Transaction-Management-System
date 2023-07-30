/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

public class Commodity {
	public Shop shop;
	public Product product;
	private int stock;

	private Commodity(Shop shop, int stock, Product product) {
		this.shop = shop;
		this.stock = stock;
		this.product = product;
	}

	// Warning: This will change product!
	public static Commodity create(Shop shop, int stock, Product product) {
		product.totalStock += stock;
		return new Commodity(shop, stock, product);
	}

	public int getStock() {
		return stock;
	}

	public void updateStock(int stock) {
		this.stock += stock;
		this.product.totalStock += stock;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Commodity other) {
			return this.shop.equals(other.shop) && this.product.equals(other.product);
		}
		return false;
	}
}
