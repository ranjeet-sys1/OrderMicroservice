package com.app.service;

import com.app.common.TransactionRequest;
import com.app.common.TransactionResponse;
import com.app.entity.Order;

public interface OrderService {
    TransactionResponse saveOrder(TransactionRequest transactionRequest);
}
