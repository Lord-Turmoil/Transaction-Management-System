/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.repo;

import tms.model.TMSContext;
import tms.model.entity.User;
import uow.Repository;

public class UserRepository extends Repository<User> {
    public UserRepository(TMSContext dbContext) {
        super(dbContext, TMSContext.User);
    }
}
