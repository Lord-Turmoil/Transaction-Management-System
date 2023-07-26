/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

public class User {
	public String id;
	public String name;
	public String password;
	public Role role;

	public enum Role {
		Administrator,
		Merchant,
		Customer
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return this.id.equals(((User) obj).id);
		}
		return false;
	}
}
