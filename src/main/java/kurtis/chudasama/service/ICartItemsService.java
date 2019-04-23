package kurtis.chudasama.service;

import kurtis.chudasama.entity.CartItems;

import java.util.List;

public interface ICartItemsService {

    List<CartItems> findByCartId(int cartId);

    CartItems findByItemId(int itemId);

    void saveCartItems(CartItems cartItems);
}
