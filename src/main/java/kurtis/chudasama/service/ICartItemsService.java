package kurtis.chudasama.service;

import kurtis.chudasama.entity.CartItems;

public interface ICartItemsService {

    CartItems findByCartId(int cartId);

    CartItems findByItemId(int itemId);

    void saveCartItems(CartItems cartItems);
}
