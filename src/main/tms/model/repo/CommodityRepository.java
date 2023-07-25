/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 21:13
 * @Author  : Tony Skywalker
 * @File    : CommodityRepository.java
 */

package tms.model.repo;

import tms.model.TMSContext;
import tms.model.entity.Commodity;
import uow.DbContext;
import uow.Repository;

public class CommodityRepository extends Repository<Commodity> {
    public CommodityRepository(TMSContext dbContext) {
        super(dbContext, TMSContext.Commodity);
    }
}
