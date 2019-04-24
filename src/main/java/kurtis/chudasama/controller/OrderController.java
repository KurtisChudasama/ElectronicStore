package kurtis.chudasama.controller;

import kurtis.chudasama.entity.*;
import kurtis.chudasama.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private CartItemsService cartItemsService;

    PurchaseServiceFacadeImpl purchaseServiceFacade;

    @GetMapping("/homepage/order/{id}")
    public ModelAndView order(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView();

        Cart cart = cartService.findById(id);

        ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
        cart_items.addAll(cart.getCartItems());

        double total = cart.calculateTotal();

        Set<Item> items = new HashSet<>();

        for (int i = 0; i < cart_items.size(); i++) {
            CartItems cartItem = cart_items.get(i);
            Item item = itemService.findById(cartItem.getItem().getId());
            items.add(item);
        }

        model.addObject("cart", cart);
        model.addObject("items", items);
        model.addObject("total", total);
        model.setViewName("order");

        return model;
    }

    @PostMapping("/homepage/order")
    public ModelAndView order(@Valid @ModelAttribute("userOrder") UserOrder userOrder, @RequestParam("total") double total, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        Cart cart = cartService.findByUserId(user.getId());
        Set<Item> items = new HashSet<>();

        ArrayList<CartItems> cart_items = new ArrayList<CartItems>();
        cart_items.addAll(cart.getCartItems());

        purchaseServiceFacade = new PurchaseServiceFacadeImpl();

        if (purchaseServiceFacade.placeOrder(cart_items)) {

            items.addAll(items);

            UserOrder order = new UserOrder(total, user, items);

            if (request.getParameter("payment_method").equals("Visa")) {
                Visa visa = new Visa(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expires"));

                if (order.pay(visa, cart)){
                    orderService.saveOrder(order);
                    cartItemsService.emptyCart(cartItemsService.findByCartId(cart.getId()));

                    String visaSuccess = "";
                    model.addObject("visaSuccess", visaSuccess);
                    model.setViewName("homepage");
                }
                else {
                    String visaError = "";
                    model.addObject("total", total);
                    model.addObject("visaError", visaError);
                    model.setViewName("order");
                }
            } else if (request.getParameter("payment_method").equals("Mastercard")) {
                MasterCard mastercard = new MasterCard(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expires"));

                if (order.pay(mastercard, cart)) {
                    orderService.saveOrder(order);
                    cartItemsService.emptyCart(cartItemsService.findByCartId(cart.getId()));

                    String mastercardSuccess = "";
                    model.addObject("mastercardSuccess", mastercardSuccess);
                    model.setViewName("homepage");
                }
                else {
                    String mastercardError = "";
                    model.addObject("total", total);
                    model.addObject("mastercardError", mastercardError);
                    model.setViewName("order");
                }
            }
        }
        else {
            String errorMessage = "";
            model.addObject("errorMessage", errorMessage);
            model.setViewName("order");
            model.addObject("total", total);
        }

        return model;
    }
}