package kurtis.chudasama.repository;

import kurtis.chudasama.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {

    CartItems findByCartId(int cartId);

    CartItems findByItemId(int itemId);
}
