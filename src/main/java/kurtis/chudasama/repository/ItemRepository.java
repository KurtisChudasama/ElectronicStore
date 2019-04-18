package kurtis.chudasama.repository;

import kurtis.chudasama.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findById(int id);

    /*Item findByCartId(int cartId);

    Item findByOrderId(int orderId);

    ArrayList<Item> findItemsByCartId(int cartId);

    ArrayList<Item> findItemsByOrderId(int orderId);*/
}
