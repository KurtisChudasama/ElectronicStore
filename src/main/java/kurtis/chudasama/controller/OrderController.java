package kurtis.chudasama.controller;

import kurtis.chudasama.entity.*;
import kurtis.chudasama.service.CartService;
import kurtis.chudasama.service.ItemService;
import kurtis.chudasama.service.OrderService;
import kurtis.chudasama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/homepage/order/{id}")
    public ModelAndView order(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView();

        Cart cart = cartService.findById(id);

        ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
        cart_items.addAll(cart.getCartItems());

        double total = 0;

        Set<Item> items = new HashSet<>();

        for (int i = 0; i < cart_items.size(); i++) {
            CartItems cartItem = cart_items.get(i);
            Item item = itemService.findById(cartItem.getItem().getId());

            items.add(item);
            total = total + (item.getPrice() * cartItem.getQuantity());
        }

        model.addObject("cart", cart);
        model.addObject("items", items);
        model.addObject("total", total);
        model.setViewName("order");

        return model;
    }

    @PostMapping("/homepage/order")
    public ModelAndView order(@Valid @ModelAttribute("userOrder")UserOrder userOrder, @RequestParam("total") double total) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        Cart cart = cartService.findByUserId(user.getId());
        ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
        cart_items.addAll(cart.getCartItems());

        Set<Item> items = new HashSet<>();

        for (int i = 0; i < cart_items.size(); i++) {
            CartItems cartItem = cart_items.get(i);
            Item item = itemService.findById(cartItem.getItem().getId());

            items.add(item);
        }

        UserOrder order = new UserOrder(total, user, items);
        orderService.saveOrder(order);

        String successMessage = "";
        model.addObject("successMessage", successMessage);
        model.setViewName("order");

        return model;
    }
}
