package kurtis.chudasama.service;

import kurtis.chudasama.entity.UserOrder;
import kurtis.chudasama.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("orderService")
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public UserOrder findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public UserOrder findByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public ArrayList<UserOrder> findOrdersByUserId(int userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Override
    public void saveOrder(UserOrder order) {
        orderRepository.save(order);
    }
}
