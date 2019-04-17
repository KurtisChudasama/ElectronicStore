package kurtis.chudasama.service;

import kurtis.chudasama.entity.UserOrder;

import java.util.ArrayList;

public interface IOrderService {

    UserOrder findById(int id);

    UserOrder findByUserId(int userId);

    ArrayList<UserOrder> findOrdersByUserId(int userId);

    void saveOrder(UserOrder order);
}
