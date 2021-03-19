package com.stormnet.client.service;

import com.stormnet.data.Order;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public interface ClientService {

    List<Order> getAllOrders() throws IOException;

    Order getOrderById(Long orderId);

    Long saveOrder(Order order);

    void deleteOrder(Long orderId) throws IOException;
}
