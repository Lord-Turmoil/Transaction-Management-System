/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.repo;

import tms.model.TMSContext;
import tms.model.entity.Commodity;
import uow.Repository;

public class CommodityRepository extends Repository<Commodity> {
	public CommodityRepository(TMSContext dbContext) {
		super(dbContext, TMSContext.Commodity);
	}
}
