/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.formatter.impl;

import tms.model.entity.Order;
import tms.shared.formatter.IFormatter;

import java.text.DecimalFormat;

public class OrderAdminFormatter implements IFormatter {
	@Override
	public String format(Object obj) {
		Order order;
		try {
			order = (Order) obj;
		} catch (ClassCastException e) {
			throw new RuntimeException(obj + " is not Order", e);
		}

		DecimalFormat format = new DecimalFormat();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);

		// O-1: 000117861001 S-1 C-2 2 2.00yuan finished
		return order.buyer.id + " " +
				order.getId() + ": " +
				order.shop.getId() + " " +
				order.commodity.product.getId() + " " +
				order.quantity + " " +
				format.format(order.commodity.product.price) + "yuan " +
				order.status.toString().toLowerCase();
	}
}
