/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

public class Shop {
	private static int nextId = 1;
	public String id;
	public String name;
	public Status status;
	public User owner;
	private Shop(String name, User owner) {
		this.id = getNextId();
		this.name = name;
		this.status = Status.Open;
		this.owner = owner;
	}

	private static String getNextId() {
		return "S-" + nextId++;
	}

	public static Shop create(String name, User owner) {
		return new Shop(name, owner);
	}

	enum Status {
		Open,
		Closed
	}
}
