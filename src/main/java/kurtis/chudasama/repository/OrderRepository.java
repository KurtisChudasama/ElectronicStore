package kurtis.chudasama.repository;

import kurtis.chudasama.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface OrderRepository extends JpaRepository<UserOrder, Long> {

    UserOrder findById(int id);

    UserOrder findByUserId(int userId);

    ArrayList<UserOrder> findOrdersByUserId(int userId);
}
