package kurtis.chudasama.service;

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
    public Item findByCartId(int cartId) {
        return itemRepository.findByCartId(cartId);
    }

    @Override
    public Item findByOrderId(int orderId) {
        return itemRepository.findByOrderId(orderId);
    }

    @Override
    public ArrayList<Item> findItemsByCartId(int cartId) {
        return itemRepository.findItemsByCartId(cartId);
    }

    @Override
    public ArrayList<Item> findItemsByOrderId(int orderId) {
        return itemRepository.findItemsByOrderId(orderId);
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
}
