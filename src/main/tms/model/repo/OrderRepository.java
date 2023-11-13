/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.repo;

import tms.model.TMSContext;
import tms.model.entity.Order;
import uow.Repository;

public class OrderRepository extends Repository<Order> {
    public OrderRepository(TMSContext dbContext) {
        super(dbContext, TMSContext.OrderTable);
    }
}
