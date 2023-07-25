/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.repo;

import tms.model.TMSContext;
import tms.model.entity.Shop;
import uow.Repository;

public class ShopRepository extends Repository<Shop> {
	public ShopRepository(TMSContext dbContext) {
		super(dbContext, TMSContext.Shop);
	}
}
