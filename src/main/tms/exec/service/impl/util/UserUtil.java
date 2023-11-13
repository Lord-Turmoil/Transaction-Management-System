/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl.util;

import host.exec.ExecutionException;
import tms.model.entity.User;
import tms.shared.Errors;
import uow.IRepository;

public class UserUtil {
    private UserUtil() {}

    public static User getUser(IRepository<User> repo, String id) throws ExecutionException {
        var user = repo.find(x -> x.id.equals(id));
        if (user == null) {
            throw new ExecutionException(Errors.NoSuchUser);
        }
        return user;
    }
}
