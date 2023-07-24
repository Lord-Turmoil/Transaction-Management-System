package tms.model.repo;

import tms.model.TMSContext;
import tms.model.entity.User;
import uow.DbContext;
import uow.Repository;

public class UserRepository extends Repository<User> {
    public UserRepository(TMSContext dbContext) {
        super(dbContext, TMSContext.User);
    }
}
