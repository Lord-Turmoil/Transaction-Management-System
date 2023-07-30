/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.service.BaseService;
import tms.exec.service.impl.util.ShopUtil;
import tms.model.entity.Order;
import tms.model.entity.Shop;
import tms.model.entity.User;
import tms.shared.Errors;
import tms.shared.Globals;
import tms.shared.formatter.IFormatter;
import tms.shared.formatter.impl.PrintHandler;
import tms.shared.formatter.impl.ShopAdminFormatter;
import tms.shared.formatter.impl.ShopFormatter;
import tms.shared.validator.impl.IdValidator;
import tms.shared.validator.impl.ShopNameValidator;
import uow.exception.NoSuchEntityException;

import java.util.Comparator;

public class ShopService extends BaseService implements IShopService {
	public ShopService(IContainer container) {
		super(container);
	}

	@Override
	public void register(String name) throws ExecutionException {
		var user = getRequiredUser(User.Role.Merchant);

		var repo = unitOfWork.getRepository(Shop.class);
		if (repo.count(x -> x.status == Shop.Status.Open) >= Globals.MAX_SHOP_NUM) {
			throw new ExecutionException("Shop number reached limit");
		}
		if (!new ShopNameValidator().check(name)) {
			throw new ExecutionException(Errors.IllegalShopName);
		}
		if (repo.exists(x -> x.name.equals(name))) {
			throw new ExecutionException(Errors.DuplicatedShopName);
		}

		var shop = Shop.create(name, user);
		repo.add(shop);

		printer.println("Register shop success (shopID:" + shop.getId() + ")");
	}

	@Override
	public void list() throws ExecutionException {
		var user = getCurrentUser();
		if (user == null) {
			throw new ExecutionException(Errors.NotLoggedIn);
		}
		if (user.role == User.Role.Merchant) {
			listById(user.id);
		} else {
			listAll(user);
		}
	}

	@Override
	public void list(String id) throws ExecutionException {
		if (!checkPermission(User.Role.Administrator)) {
			throw new ExecutionException(Errors.PermissionDenied);
		}

		if (!new IdValidator().check(id)) {
			throw new ExecutionException(Errors.IllegalId);
		}

		listById(id);
	}

	// list all shops of merchant
	private void listById(String id) throws ExecutionException {
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
	public void cancel(String shopIdString) throws ExecutionException {
		var user = getRequiredUser(User.Role.Merchant, User.Role.Administrator);

		int shopId = ShopUtil.parseShopId(shopIdString);
		var shop = ShopUtil.getShop(unitOfWork.getRepository(Shop.class), shopId);
		if (shop.status == Shop.Status.Closed) {
			throw new ExecutionException(Errors.NoSuchShopId);
		}
		if (user.role == User.Role.Merchant && !shop.owner.equals(user)) {
			throw new ExecutionException(Errors.NoSuchShopId);
		}

		var orderRepo = unitOfWork.getRepository(Order.class);
		if (orderRepo.exists(x -> x.shop.equals(shop) && x.isActive())) {
			throw new ExecutionException(Errors.UnfinishedOrderExists);
		}

		shop.status = Shop.Status.Closed;

		printer.println("Cancel shop success");
	}
}