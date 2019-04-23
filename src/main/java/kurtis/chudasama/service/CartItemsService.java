package kurtis.chudasama.service;

import kurtis.chudasama.entity.CartItems;
import kurtis.chudasama.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartItemsService")
public class CartItemsService implements ICartItemsService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItems> findByCartId(int cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public CartItems findByItemId(int itemId) {
        return cartItemRepository.findByItemId(itemId);
    }

    @Override
    public void saveCartItems(CartItems cartItems) {
        cartItemRepository.save(cartItems);
    }

    public void emptyCart(List<CartItems> items) {
        cartItemRepository.deleteAll(items);
    }
}
