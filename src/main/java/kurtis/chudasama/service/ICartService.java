package kurtis.chudasama.service;

import kurtis.chudasama.entity.Cart;

public interface ICartService {

    Cart findById(int id);

    Cart findByUserId(int userId);

    void saveCart(Cart cart);
}
