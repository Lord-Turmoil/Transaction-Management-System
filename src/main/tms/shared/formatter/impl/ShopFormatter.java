/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.formatter.impl;

import tms.model.entity.Shop;
import tms.shared.formatter.IFormatter;

public class ShopFormatter implements IFormatter {
    @Override
    public String format(Object obj) {
        Shop shop;
        try {
            shop = (Shop) obj;
        } catch (ClassCastException e) {
            throw new RuntimeException(obj + " is not Shop", e);
        }

        return shop.getId() + " " + shop.name;
    }
}
