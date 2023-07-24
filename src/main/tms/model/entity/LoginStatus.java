/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: LoginStatus.java
 * Last Update: 7/24/23, 5:28 PM
 */

package tms.model.entity;

import tms.model.entity.User;

public class LoginStatus {
    private User user = null;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
