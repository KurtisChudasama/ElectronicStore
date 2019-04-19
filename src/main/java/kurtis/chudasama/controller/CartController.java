package kurtis.chudasama.controller;

import kurtis.chudasama.entity.Cart;
import kurtis.chudasama.entity.CartItems;
import kurtis.chudasama.entity.Item;
import kurtis.chudasama.entity.User;
import kurtis.chudasama.service.CartItemsService;
import kurtis.chudasama.service.CartService;
import kurtis.chudasama.service.ItemService;
import kurtis.chudasama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartItemsService cartItemsService;

    @GetMapping("/homepage/addtocart/{id}")
    public ModelAndView addToCart(@PathVariable("id") int id, @RequestParam(defaultValue = "") String itemName) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        Cart cart = cartService.findByUserId(user.getId());
        Item item = itemService.findById(id);

        /*CartItems cartItemsExist = cartItemsService.findByItemId(item.getId());

        if(cartItemsExist == null) {
            CartItems cartItems = new CartItems(cart, item, 1);
            cartItemsService.saveCartItems(cartItems);
        }
        else {
            cartItemsExist.setQuantity(+1);
            cartItemsService.saveCartItems(cartItemsExist);
        }*/

        ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
        cart_items.addAll(cart.getCartItems());
        boolean exist = true;

        for (int i = 0; i < cart_items.size(); i++) {
            CartItems current = cart_items.get(i);
            if (current.getItem() == item) {

                int temp = cart_items.get(i).getQuantity();
                cart_items.get(i).setQuantity(temp+1);

                cartItemsService.saveCartItems(cart_items.get(i));
                Set<CartItems> updatedList = new HashSet<>(cart_items);
                cart.setCartItems(updatedList);
                exist = false;
            }
        }

        if (exist) {
            CartItems cartItems = new CartItems(cart, item, 1);
            cartItemsService.saveCartItems(cartItems);
            cart_items.add(cartItems);

            Set<CartItems> updatedList = new HashSet<>(cart_items);

            cart.setCartItems(updatedList);
        }

        cartService.saveCart(cart);

        String successMessage = "";
        model.addObject("successMessage", successMessage);

        ArrayList<Item> items = itemService.findItemsByName(itemName);
        model.addObject("items", items);
        model.setViewName("view_items");

        return model;
    }

    @GetMapping("/homepage/viewcart")
    public ModelAndView viewCart() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Cart cart = cartService.findByUserId(user.getId());

        ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
        cart_items.addAll(cart.getCartItems());

        model.addObject("cart", cart);
        model.addObject("cartitems", cart_items);
        model.setViewName("view_cart");

        return model;
    }
}
