/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model;

import tms.model.entity.*;
import uow.DbContext;
import uow.DbSet;

public class TMSContext extends DbContext {
	public static final String UserTable = "users";
	public static final String ShopTable = "shops";
	public static final String ProductTable = "products";
	public static final String CommodityTable = "commodities";
	public static final String OrderTable = "orders";

	public DbSet<User> users;
	public DbSet<Shop> shops;
	public DbSet<Product> products;
	public DbSet<Commodity> commodities;
	public DbSet<Order> orders;
}
