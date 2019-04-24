package kurtis.chudasama.service;

import kurtis.chudasama.entity.CartItems;
import kurtis.chudasama.entity.Item;

import java.util.ArrayList;

public class StockService {

    public StockService() {

    }

    public boolean isAvailable(ArrayList<CartItems> cart_items) {

        boolean available = true;

        for (int i = 0; i < cart_items.size(); i++) {
            CartItems cartItem = cart_items.get(i);
            Item item = cart_items.get(i).getItem();

            if (cartItem.getQuantity() > item.getStock()) {
                available = false;
            }
        }

        return available;
    }
}
