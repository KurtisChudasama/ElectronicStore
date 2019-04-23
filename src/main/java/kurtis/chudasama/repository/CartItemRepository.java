package kurtis.chudasama.repository;

import kurtis.chudasama.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findByCartId(int cartId);

    CartItems findByItemId(int itemId);
}
