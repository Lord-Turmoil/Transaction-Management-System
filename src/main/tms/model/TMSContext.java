package tms.model;

import tms.model.entity.User;
import uow.DbContext;
import uow.DbSet;

public class TMSContext extends DbContext {
    public static final String User = "users";

    public DbSet<User> users;
}
