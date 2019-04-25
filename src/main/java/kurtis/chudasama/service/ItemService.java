package kurtis.chudasama.service;

import kurtis.chudasama.entity.CartItems;
import kurtis.chudasama.entity.Item;
import kurtis.chudasama.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("itemService")
public class ItemService implements IItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item findById(int id) {
        return itemRepository.findById(id);
    }

    @Override
    public ArrayList<Item> findItemsByName(String itemName) {
        return itemRepository.findByItemNameLike("%" + itemName + "%");
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public void updateStock(ArrayList<CartItems> cart_items) {

        for (int i = 0; i < cart_items.size(); i++) {
            CartItems cartItem = cart_items.get(i);
            Item item = cart_items.get(i).getItem();

            int stockLevel = item.getStock() - cartItem.getQuantity();
            item.setStock(stockLevel);
            this.saveItem(item);
        }
    }

    @Override
    public void deleteItem(Item item) {

        itemRepository.delete(item);

    }
}
