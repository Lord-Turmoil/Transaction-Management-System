/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

import java.math.BigDecimal;

public class Product {
	private static int nextId = 1;
	public int id;

	public String name;

	// I'm afraid double may cause precision error
	public BigDecimal price;
	public int totalStock;
	// Merchant Id
	public User owner;
	public Status status;

	private Product(String name, BigDecimal price, User owner) {
		this.id = getNextId();
		this.name = name;
		this.totalStock = 0;
		this.price = price;
		this.owner = owner;
		this.status = Status.Available;
	}

	private static int getNextId() {
		return nextId++;
	}

	public static Product create(String name, BigDecimal price, User owner) {
		return new Product(name, price, owner);
	}

	public String getId() {
		return "C-" + id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product other) {
			return this.id == other.id;
		}
		return false;
	}

	public enum Status {
		Available,
		Unavailable
	}
}
