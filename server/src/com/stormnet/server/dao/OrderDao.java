package com.stormnet.server.dao;

import com.stormnet.data.Order;
import javax.xml.transform.TransformerException;
import java.util.List;

public interface OrderDao {

        List<Order> getAllOrders();

        Order getOrderById(Long id);

        Long saveOrder(Order order);

        void editOrder(Order order);

        void deleteClient(Long id) throws TransformerException;
}
