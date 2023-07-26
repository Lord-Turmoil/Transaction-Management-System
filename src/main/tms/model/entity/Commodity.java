/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

public class Commodity {
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

	public enum Status {
		Available,
		Unavailable
	}
}
