package kurtis.chudasama.service;

import kurtis.chudasama.entity.Cart;
import kurtis.chudasama.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findById(int id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart findByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }
}
