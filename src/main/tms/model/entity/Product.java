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

	private Product(String name, BigDecimal price, User owner) {
		this.id = getNextId();
		this.name = name;
		this.totalStock = 0;
		this.price = price;
		this.owner = owner;
	}

	private static int getNextId() {
		return nextId++;
	}

	public static int parseId(String value) throws NumberFormatException {
		if (!value.matches("^C-\\d+$")) {
			throw new NumberFormatException("Bad prefix");
		}
		return Integer.parseInt(value.substring(2));
	}

	public String getId() {
		return "C-" + id;
	}

	public static Product create(String name, BigDecimal price, User owner) {
		return new Product(name, price, owner);
	}
}
