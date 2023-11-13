/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model;

import ioc.IContainer;
import tms.model.entity.*;
import tms.model.repo.*;

public class RepositoryHost {
    private RepositoryHost() {}

    public static void registerAll(IContainer container, TMSContext context) {
        container.addSingleton(User.class, () -> new UserRepository(context))
                .addSingleton(Shop.class, () -> new ShopRepository(context))
                .addSingleton(Commodity.class, () -> new CommodityRepository(context))
                .addSingleton(Product.class, () -> new ProductRepository(context))
                .addSingleton(Order.class, () -> new OrderRepository(context));
    }
}
