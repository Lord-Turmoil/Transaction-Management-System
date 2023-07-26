/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.service.BaseService;
import tms.model.entity.Shop;
import tms.model.entity.User;
import tms.shared.Errors;
import tms.shared.Globals;
import tms.shared.formatter.IFormatter;
import tms.shared.formatter.impl.PrintHandler;
import tms.shared.formatter.impl.ShopAdminFormatter;
import tms.shared.formatter.impl.ShopFormatter;
import tms.shared.validator.impl.ShopNameValidator;
import uow.exception.NoSuchEntityException;

import java.util.Comparator;
import java.util.List;

public class ShopService extends BaseService implements IShopService {
	public ShopService(IContainer container) {
		super(container);
	}

	@Override
	public void register(List<String> args) throws ExecutionException {
		if (args.size() != 1) {
			throw new ExecutionException(Errors.IllegalArgumentCount);
		}
		var user = getCurrentUser();
		if (user == null) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}
		if (user.role != User.Role.Merchant) {
			throw new ExecutionException(Errors.PermissionDenied);
		}

		var repo = unitOfWork.getRepository(Shop.class);
		if (repo.count(x -> x.status == Shop.Status.Open) >= Globals.MAX_SHOP_NUM) {
			throw new ExecutionException("Shop number reached limit");
		}

		var name = args.get(0);
		if (!new ShopNameValidator().check(name)) {
			throw new ExecutionException(Errors.IllegalShopName);
		}

		repo.add(Shop.create(name, user));

		printer.println("Register shop success");
	}

	@Override
	public void list(List<String> args) throws ExecutionException {
		if (args.size() > 1) {
			throw new ExecutionException(Errors.IllegalArgumentCount);
		}
		var user = getCurrentUser();
		if (user == null) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}

		if (args.size() == 0) {
			if (user.role == User.Role.Merchant) {
				listById(user.id, user);
			} else {
				listAll(user);
			}
		} else {
			if (user.role != User.Role.Administrator) {
				throw new ExecutionException(Errors.PermissionDenied);
			}
			listById(args.get(1), user);
		}
	}

	// list all shops of merchant
	private void listById(String id, User initiator) throws ExecutionException {
		User user;
		try {
			user = unitOfWork.getRepository(User.class).get(x -> x.id.equals(id));
		} catch (NoSuchEntityException e) {
			throw new ExecutionException(Errors.NoSuchUser, e);
		}
		if (user.role != User.Role.Merchant) {
			throw new ExecutionException(Errors.UserNotMerchant);
		}
		// Technically, we can simply compare the address, since there are no copy.
		var shops = unitOfWork.getRepository(Shop.class).findAll(
				x -> x.owner.equals(user),
				Comparator.comparingInt(x -> x.id));
		new PrintHandler(printer).print(shops, new ShopFormatter());
	}

	// list all shops
	private void listAll(User initiator) throws ExecutionException {
		var shops = unitOfWork.getRepository(Shop.class).getAll(Comparator.comparingInt(x -> x.id));
		IFormatter formatter;
		if (initiator.role == User.Role.Administrator) {
			formatter = new ShopAdminFormatter();
		} else {
			formatter = new ShopFormatter();
		}
		new PrintHandler(printer).print(shops, formatter);
	}

	@Override
	public void cancel(List<String> args) throws ExecutionException {
		if (args.size() != 1) {
			throw new ExecutionException(Errors.IllegalArgumentCount);
		}
		var user = getCurrentUser();
		if (user == null) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}
		if (!(user.role == User.Role.Merchant || user.role == User.Role.Administrator)) {
			throw new ExecutionException(Errors.PermissionDenied);
		}
		int id;
		try {
			id = Shop.parseId(args.get(0));
		} catch (NumberFormatException e) {
			throw new ExecutionException(Errors.IllegalShopId);
		}
		var shop = unitOfWork.getRepository(Shop.class).find(x -> x.id == id);
		if (shop == null || shop.status == Shop.Status.Closed) {
			throw new ExecutionException(Errors.NoSuchShopId);
		}
		if (user.role == User.Role.Merchant && !shop.owner.equals(user)) {
			throw new ExecutionException(Errors.NoSuchShopId);
		}

		// TODO: pending order

		shop.status = Shop.Status.Closed;

		printer.println("Cancel shop success");
	}
}
