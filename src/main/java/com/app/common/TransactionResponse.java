package com.app.common;

import com.app.entity.Order;

public class TransactionResponse {
    private Order order;
    private Double price;
    private String transactionId;
    private String message;

    public TransactionResponse() {
    }

    public TransactionResponse(Order order, Double price, String transactionId,String message) {
        this.order = order;
        this.price = price;
        this.transactionId = transactionId;
        this.message=message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}