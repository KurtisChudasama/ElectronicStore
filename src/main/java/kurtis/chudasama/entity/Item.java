package kurtis.chudasama.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "category")
    private String category;

    @Column(name= "price")
    private double price;

    @Column(name = "stock")
    private int stock;

    @ManyToMany(mappedBy = "items")
    private Set<UserOrder> orders;

    @OneToMany(mappedBy = "item")
    private Set<CartItems> cartItems;

    @OneToMany
    @JoinColumn(name = "item_id")
    private Set<Rating> rating;

    public Item() {

    }

    public Item(String itemName, String manufacturer, String category, double price) {
        this.itemName = itemName;
        this.manufacturer = manufacturer;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public Set<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<UserOrder> orders) {
        this.orders = orders;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<Rating> getRating() {
        return rating;
    }

    public void setRating(Set<Rating> rating) {
        this.rating = rating;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
