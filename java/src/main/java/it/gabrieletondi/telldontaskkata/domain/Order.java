package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    public Order(int id , OrderStatus status){
        this.id = id;
        this.status = status;
    }

    public Order(BigDecimal total, String currency, List<OrderItem> items, BigDecimal tax, OrderStatus status) {
        this.total = total;
        this.currency = currency;
        this.items = items;
        this.tax = tax;
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getCurrency() {
        return currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void isApproved(OrderApprovalRequest request) {
        this.status = request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED;
    }

    public void ship(){
        this.status = OrderStatus.SHIPPED;
    }

    public void addItem(OrderItem orderItem){
        items.add(orderItem);

        total = total.add(orderItem.getTaxedAmount());
        tax = tax.add(orderItem.getTax());
    }

}
