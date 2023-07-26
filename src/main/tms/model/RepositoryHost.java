/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model;

import ioc.IContainer;
import tms.model.entity.Commodity;
import tms.model.entity.Product;
import tms.model.entity.Shop;
import tms.model.entity.User;
import tms.model.repo.CommodityRepository;
import tms.model.repo.ProductRepository;
import tms.model.repo.ShopRepository;
import tms.model.repo.UserRepository;

public class RepositoryHost {
	private RepositoryHost() {}

	public static void registerAll(IContainer container, TMSContext context) {
		container.addSingleton(User.class, () -> new UserRepository(context))
				.addSingleton(Shop.class, () -> new ShopRepository(context))
				.addSingleton(Commodity.class, () -> new CommodityRepository(context))
				.addSingleton(Product.class, () -> new ProductRepository(context));
	}
}
