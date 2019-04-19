package kurtis.chudasama.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@IdClass(CartItemsId.class)
public class CartItems {

    @Id
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @Column(name = "quantity")//, columnDefinition="Decimal(4,0) default '0'")
    private int quantity;

    public CartItems() {

    }

    public CartItems(Cart cart, Item item, int quantity) {
        this.cart = cart;
        this.item = item;
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
