/*
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *
 * @Time    : 7/25/2023 21:12
 * @Author  : Tony Skywalker
 * @File    : ProductRepository.java
 */

package tms.model.repo;

import tms.model.TMSContext;
import tms.model.entity.Product;
import uow.DbContext;
import uow.Repository;

public class ProductRepository extends Repository<Product> {
    public ProductRepository(TMSContext dbContext) {
        super(dbContext, TMSContext.Product);
    }
}
