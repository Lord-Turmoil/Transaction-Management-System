/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 21:12
 * @Author  : Tony Skywalker
 * @File    : ShopRepository.java
 */

package tms.model.repo;

import tms.model.TMSContext;
import tms.model.entity.Shop;
import uow.DbContext;
import uow.Repository;

public class ShopRepository extends Repository<Shop> {
    public ShopRepository(TMSContext dbContext) {
        super(dbContext, TMSContext.Shop);
    }
}
