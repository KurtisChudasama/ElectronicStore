package kurtis.chudasama.entity;

import java.util.ArrayList;
import java.util.Set;

public interface PurchaseServiceFacade {

    boolean placeOrder(ArrayList<CartItems> cart_items);

}
