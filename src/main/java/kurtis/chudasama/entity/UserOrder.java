package kurtis.chudasama.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;

    public UserOrder() {

    }

    public UserOrder(double total, User user, Set<Item> items) {
        this.total = total;
        this.user = user;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public boolean pay(PaymentMethod method, Cart cart) {
        double totalCost = cart.calculateTotal();
        return method.pay(totalCost);
    }
}
