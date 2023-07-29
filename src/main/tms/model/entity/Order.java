/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

public class Order {
	private static int nextId = 1;
	int id;
	Status status;
	Shop shop;
	Commodity commodity;
	// seller can be deducted by shop or commodity
	User buyer;
	int quantity;
	private Order(Shop shop, Commodity commodity, User buyer, int quantity) {
		this.id = getNextId();
		this.status = Status.Pending;
		this.shop = shop;
		this.commodity = commodity;
		this.buyer = buyer;
		this.quantity = quantity;
	}

	private static int getNextId() {
		return nextId++;
	}

	public static int parseId(String value) throws NumberFormatException {
		if (!value.matches("^O-\\d+$")) {
			throw new NumberFormatException("Bad prefix");
		}
		return Integer.parseInt(value.substring(2));
	}

	public static Order create(Shop shop, Commodity commodity, User buyer, int quantity) {
		return new Order(shop, commodity, buyer, quantity);
	}

	public enum Status {
		Pending,
		Canceled,
		Finished
	}
}
