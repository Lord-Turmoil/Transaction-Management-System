/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model;

import tms.model.entity.Commodity;
import tms.model.entity.Product;
import tms.model.entity.Shop;
import tms.model.entity.User;
import uow.DbContext;
import uow.DbSet;

public class TMSContext extends DbContext {
	public static final String User = "users";
	public static final String Shop = "shops";
	public static final String Product = "products";
	public static final String Commodity = "commodities";
	public DbSet<User> users;
	public DbSet<Shop> shops;
	public DbSet<Product> products;
	public DbSet<Commodity> commodities;
}
