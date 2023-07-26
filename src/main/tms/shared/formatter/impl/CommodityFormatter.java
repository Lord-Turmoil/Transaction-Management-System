/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.shared.formatter.impl;

import tms.model.entity.Commodity;
import tms.shared.formatter.IFormatter;

import java.text.DecimalFormat;

public class CommodityFormatter implements IFormatter {
	@Override
	public String format(Object obj) {
		Commodity commodity;
		try {
			commodity = (Commodity) obj;
		} catch (ClassCastException e) {
			throw new RuntimeException(obj + " is not Commodity", e);
		}

		// S-1: C-2 bottle 20.00yuan 20
		DecimalFormat format = new DecimalFormat();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		return commodity.shop.getId() + ": " +
				commodity.product.getId() + " " +
				commodity.product.name + " " +
				format.format(commodity.product.price) + "yuan " +
				commodity.stock;
	}
}
