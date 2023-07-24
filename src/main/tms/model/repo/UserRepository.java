/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: UserRepository.java
 * Last Update: 7/24/23, 4:57 PM
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
