/*******************************************************************************
 * Copyright (C) 2023 Tony Skywalker. All Rights Reserved
 */

package tms.model.entity;

public class Order {
    private static int nextId = 1;
    public int id;
    public Status status;
    public Shop shop;
    public Commodity commodity;
    // seller can be deducted by shop or commodity
    public User buyer;
    public int quantity;

    private Order(Shop shop, Commodity commodity, User buyer, int quantity) {
        this.id = getNextId();
        this.status = Status.Pending;
        this.shop = shop;
        this.commodity = commodity;
        this.buyer = buyer;
        this.quantity = quantity;
    }

    private static int getNextId() {
        return nextId++;
    }

    public static Order create(Shop shop, Commodity commodity, User buyer, int quantity) {
        return new Order(shop, commodity, buyer, quantity);
    }

    public String getId() {
        return "O-" + id;
    }

    public boolean isActive() {
        return this.status == Status.Pending;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order other) {
            return this.id == other.id;
        }
        return false;
    }

    public enum Status {
        Pending, Canceled, Finished
    }
}
