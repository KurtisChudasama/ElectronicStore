package kurtis.chudasama.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart")
    private Set<CartItems> cartItems;

    public Cart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public double calculateTotal() {
        double total = 0;
        ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
        cart_items.addAll(this.getCartItems());

        for (int i = 0; i < cart_items.size(); i++) {
            Item item = cart_items.get(i).getItem();
            total += item.getPrice() * cart_items.get(i).getQuantity();
        }

        return total;
    }
}
