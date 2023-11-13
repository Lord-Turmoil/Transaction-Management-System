/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

public class User {
    public String id;
    public String name;
    public String password;
    public Role role;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User other) {
            return this.id.equals(other.id);
        }
        return false;
    }

    public enum Role {
        Administrator,
        Merchant,
        Customer
    }
}
