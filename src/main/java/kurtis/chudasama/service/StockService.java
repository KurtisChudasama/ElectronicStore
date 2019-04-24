package kurtis.chudasama.service;

import kurtis.chudasama.entity.CartItems;
import kurtis.chudasama.entity.Item;
import kurtis.chudasama.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("stockService")
public class StockService {

    public StockService() {

    }

    public boolean isAvailable(ArrayList<CartItems> cart_items) {

        for (int i = 0; i < cart_items.size(); i++) {
            CartItems cartItem = cart_items.get(i);
            Item item = cart_items.get(i).getItem();

            if (cartItem.getQuantity() > item.getStock()) {
                return false;
            }
        }

        return true;
    }
}
