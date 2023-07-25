/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 *    Filename: User.java
 * Last Update: 7/24/23, 5:30 PM
 */

package tms.model.entity;

public class User {
    public enum Role {
        Administrator,
        Merchant,
        Customer
    }

    public String id;

    public String name;
    public String password;

    public Role role;
}
