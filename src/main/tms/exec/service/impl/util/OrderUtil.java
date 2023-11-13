/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.exec.service.impl.util;

import host.exec.ExecutionException;
import tms.model.entity.Order;
import tms.shared.Errors;
import tms.shared.validator.impl.NumberValidator;
import uow.IRepository;

public class OrderUtil {
    private OrderUtil() {}

    public static int parseOrderId(String orderIdString) throws ExecutionException {
        try {
            return parseId(orderIdString);
        } catch (NumberFormatException e) {
            throw new ExecutionException(Errors.IllegalOrderId);
        }
    }

    private static int parseId(String value) throws NumberFormatException {
        if (!value.matches("^O-\\d+$")) {
            throw new NumberFormatException("Bad prefix");
        }
        return Integer.parseInt(value.substring(2));
    }

    public static Order getOrder(IRepository<Order> repo, int orderId) throws ExecutionException {
        var order = repo.find(x -> x.id == orderId);
        if (order == null) {
            throw new ExecutionException(Errors.NoSuchOrderId);
        }
        return order;
    }

    public static Order getOrder(IRepository<Order> repo, int orderId, String userId) throws ExecutionException {
        var order = getOrder(repo, orderId);
        if (!order.buyer.id.equals(userId)) {
            throw new ExecutionException(Errors.NoSuchOrderId);
        }
        return order;
    }

    public static int parseBuyCount(String quantityString) throws ExecutionException {
        if (!new NumberValidator().check(quantityString)) {
            throw new ExecutionException(Errors.IllegalBuyCount);
        }
        try {
            return Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new ExecutionException(Errors.IllegalBuyCount, e);
        }
    }
}
