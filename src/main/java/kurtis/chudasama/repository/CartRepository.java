package kurtis.chudasama.repository;

import kurtis.chudasama.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findById(int id);

    Cart findByUserId(int userId);
}
