/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.cmd.impl;

import host.exec.ExecutionException;
import host.exec.IExecutable;
import host.exec.TerminationException;
import ioc.IContainer;
import tms.exec.cmd.BaseCommand;
import tms.exec.service.impl.ICommodityService;
import tms.model.entity.Product;
import tms.model.entity.Shop;
import tms.shared.Errors;
import tms.shared.validator.impl.PriceValidator;

import java.math.BigDecimal;
import java.util.List;

public class ReleaseCommodityCommand extends BaseCommand implements IExecutable {
	private final ICommodityService service;

	public ReleaseCommodityCommand(ICommodityService service, IContainer container) {
		super(container);
		this.service = service;
	}

	@Override
	public void execute(List<String> args) throws ExecutionException, TerminationException {
		switch (args.size()) {
			case 3 -> executeThree(args);
			case 4 -> executeFour(args);
			default -> throw new ExecutionException(Errors.IllegalArgumentCount);
		}
	}

	private void executeThree(List<String> args) throws ExecutionException {
		int shopId;
		try {
			shopId = Shop.parseId(args.get(0));
		} catch (NumberFormatException e) {
			throw new ExecutionException(Errors.IllegalShopId);
		}

		int productId;
		try {
			productId = Product.parseId(args.get(1));
		} catch (NumberFormatException e) {
			throw new ExecutionException(Errors.IllegalProductId);
		}

		int stock;
		try {
			stock = Integer.parseInt(args.get(2));
		} catch (NumberFormatException e) {
			throw new ExecutionException(Errors.IllegalProductCount);
		}

		service.release(shopId, productId, stock);
	}

	private void executeFour(List<String> args) throws ExecutionException {
		int shopId;
		try {
			shopId = Shop.parseId(args.get(0));
		} catch (NumberFormatException e) {
			throw new ExecutionException(Errors.IllegalShopId);
		}

		String name = args.get(1);

		if (!new PriceValidator().check(args.get(2))) {
			throw new ExecutionException(Errors.IllegalProductPrice);
		}
		BigDecimal price = new BigDecimal(args.get(2));

		int stock;
		try {
			stock = Integer.parseInt(args.get(2));
		} catch (NumberFormatException e) {
			throw new ExecutionException(Errors.IllegalProductCount);
		}

		service.release(shopId, name, price, stock);
	}
}
