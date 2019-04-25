package kurtis.chudasama.service;

import kurtis.chudasama.entity.Item;

import java.util.ArrayList;

public interface IItemService {

    Item findById(int id);

    ArrayList<Item> findItemsByName(String itemName);

    void saveItem(Item item);

    void deleteItem(Item item);
}
