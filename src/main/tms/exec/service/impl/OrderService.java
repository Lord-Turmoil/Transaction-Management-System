/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl;

import host.exec.ExecutionException;
import ioc.IContainer;
import tms.exec.service.BaseService;
import tms.exec.service.impl.util.OrderUtil;
import tms.exec.service.impl.util.ProductUtil;
import tms.exec.service.impl.util.ShopUtil;
import tms.exec.service.impl.util.UserUtil;
import tms.model.entity.Commodity;
import tms.model.entity.Order;
import tms.model.entity.Shop;
import tms.model.entity.User;
import tms.shared.Errors;
import tms.shared.formatter.IFormatter;
import tms.shared.formatter.impl.OrderAdminFormatter;
import tms.shared.formatter.impl.OrderFormatter;
import tms.shared.formatter.impl.PrintHandler;
import tms.shared.validator.impl.IdValidator;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class OrderService extends BaseService implements IOrderService {
	public OrderService(IContainer container) {
		super(container);
	}

	@Override
	public void purchase(String shopIdString, String productIdString, String quantityString) throws ExecutionException {
		var user = getRequiredUser(User.Role.Customer);
		int shopId = ShopUtil.parseShopId(shopIdString);
		var shop = ShopUtil.getShop(unitOfWork.getRepository(Shop.class), shopId);
		int productId = ProductUtil.parseProductId(productIdString);
		var commodity = ProductUtil.getCommodity(unitOfWork.getRepository(Commodity.class), shopId, productId);
		int quantity = OrderUtil.parseBuyCount(quantityString);
		if (quantity > commodity.getStock()) {
			throw new ExecutionException(Errors.IllegalBuyCount);
		}

		var order = Order.create(shop, commodity, user, quantity);
		unitOfWork.getRepository(Order.class).add(order);

		commodity.updateStock(-quantity);

		printer.println("Buy commodity success (orderId: " + order.getId() + ")");
	}

	@Override
	public void cancel(String orderIdString) throws ExecutionException {
		var order = getValidOrder(orderIdString);
		order.status = Order.Status.Canceled;
		order.commodity.updateStock(order.quantity);

		printer.println("Cancel order success");
	}

	@Override
	public void confirm(String orderIdString) throws ExecutionException {
		var order = getValidOrder(orderIdString);
		order.status = Order.Status.Finished;

		printer.println("Finish order success");
	}

	private Order getValidOrder(String orderIdString) throws ExecutionException {
		var user = getRequiredUser(User.Role.Customer);
		int orderId = OrderUtil.parseOrderId(orderIdString);
		var order = OrderUtil.getOrder(unitOfWork.getRepository(Order.class), orderId, user.id);
		if (order.status == Order.Status.Canceled) {
			throw new ExecutionException(Errors.OrderCanceled);
		} else if (order.status == Order.Status.Finished) {
			throw new ExecutionException(Errors.OrderFinished);
		}
		return order;
	}

	@Override
	public void list() throws ExecutionException {
		var user = getCurrentUser();
		var orders = getOrders(user);
		listOrders(orders, user);
	}

	@Override
	public void listById(String id) throws ExecutionException {
		var user = getRequiredUser(User.Role.Administrator);
		if (!new IdValidator().check(id)) {
			throw new ExecutionException(Errors.IllegalId);
		}
		var target = UserUtil.getUser(unitOfWork.getRepository(User.class), id);
		if (!(target.role == User.Role.Customer || target.role == User.Role.Merchant)) {
			throw new ExecutionException(Errors.UserNotCustomerOrMerchant);
		}
		var orders = getOrders(target);
		listOrders(orders, user);
	}

	@Override
	public void listByShop(String shopIdString) throws ExecutionException {
		var user = getRequiredUser(User.Role.Administrator, User.Role.Merchant);
		int shopId = ShopUtil.parseShopId(shopIdString);
		var shop = ShopUtil.getShop(unitOfWork.getRepository(Shop.class), shopId);
		if (!shop.owner.equals(user) && user.role != User.Role.Administrator) {
			throw new ExecutionException(Errors.NoSuchShopId);
		}
		var orders = unitOfWork.getRepository(Order.class).findAll(x -> x.shop.equals(shop), Comparator.comparingInt(x -> x.id));
		listOrders(orders, user);
	}

	private List<Order> getOrders(User user) {
		Predicate<Order> predicate = switch (user.role) {
			case Customer -> (x -> x.buyer.equals(user));
			case Merchant -> (x -> x.commodity.product.owner.equals(user));
			case Administrator -> (x -> true);
		};
		return unitOfWork.getRepository(Order.class).findAll(predicate, Comparator.comparingInt(x -> x.id));
	}

	private void listOrders(Collection<Order> orders, User initiator) {
		if (orders.isEmpty()) {
			printer.println("Order not exists");
		}
		IFormatter formatter = switch (initiator.role) {
			case Customer, Merchant -> new OrderFormatter();
			case Administrator -> new OrderAdminFormatter();
		};
		new PrintHandler(printer).print(orders, formatter);
	}
}
