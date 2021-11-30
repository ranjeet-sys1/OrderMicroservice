package com.app.service.impl;

import com.app.common.Payment;
import com.app.common.TransactionRequest;
import com.app.common.TransactionResponse;
import com.app.entity.Order;
import com.app.repo.OrderRepository;
import com.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    @Lazy
    private RestTemplate restTemplate;
    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;
    @Override
    public TransactionResponse saveOrder(TransactionRequest transactionRequest) {
        String response="";
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setPrice(order.getPrice());
        //rest call
        Payment paymentResponse = restTemplate.postForObject(ENDPOINT_URL, payment, Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success") ?"payment processing and order is placed":"there is failure in payment api, items are added into the cart";
        orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getPrice(), paymentResponse.getTransactionId(),response);
    }
}
