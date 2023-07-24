/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: TMSContext.java
 * Last Update: 7/24/23, 5:30 PM
 */

package tms.model;

import tms.model.entity.User;
import uow.DbContext;
import uow.DbSet;

public class TMSContext extends DbContext {
    public static final String User = "users";

    public DbSet<User> users;
}
