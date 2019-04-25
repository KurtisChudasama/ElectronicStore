package kurtis.chudasama.controller;

import kurtis.chudasama.entity.Item;
import kurtis.chudasama.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = {"/homepage/additem"}, method= RequestMethod.POST)
    public ModelAndView addItem(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, @RequestParam("category") String category) {
        ModelAndView model = new ModelAndView();

        Item itemExists = itemService.findById(item.getId());

        if (itemExists != null) {
            bindingResult.rejectValue("itemName", "error.item");
            if (bindingResult.hasErrors()) {
                model.setViewName("add_item");
                String errorItemExists = "";
                model.addObject("errorItemExists", errorItemExists);
            }
        }
        else if (category.equals("default")) {
            bindingResult.rejectValue("category", "error.category");
            if (bindingResult.hasErrors()) {
                model.setViewName("add_item");
                String errorCategory = "";
                model.addObject("errorCategory", errorCategory);
            }
        }
        else {
            itemService.saveItem(item);

            String successMessage = "";
            model.addObject("successMessage", successMessage);
            model.addObject("item", new Item());

            model.setViewName("add_item");
        }

        return model;
    }

    @RequestMapping(value = {"/homepage/additem"}, method= RequestMethod.GET)
    public ModelAndView addItem() {
        ModelAndView model = new ModelAndView();

        Item item = new Item();

        model.addObject("item", item);
        model.setViewName("add_item");

        return model;
    }

    @GetMapping("/homepage/viewitems")
    public ModelAndView viewItems(@RequestParam(defaultValue = "") String itemName) {
        ModelAndView model = new ModelAndView();

        ArrayList<Item> items = itemService.findItemsByName(itemName);

        model.addObject("items", items);
        model.setViewName("view_items");

        return model;
    }

    @GetMapping("/homepage/updatestock/{id}")
    public ModelAndView updateStock(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView();
        Item item = itemService.findById(id);

        model.addObject("item", item);
        model.setViewName("update_item");

        return model;
    }

    @PutMapping("/homepage/updatestock")
    public ModelAndView updateStock(@RequestParam("id") int id, @RequestParam("stock") int stock) {
        ModelAndView model = new ModelAndView();

        Item item = itemService.findById(id);
        item.setStock(stock);
        itemService.saveItem(item);

        String successMessage = "";
        model.addObject("successMessage", successMessage);
        model.addObject("item", item);
        model.setViewName("update_item");

        return model;
    }

    @DeleteMapping("/homepage/deleteitem/{id}")
    public ModelAndView deleteItem(@PathVariable("id") int id, @RequestParam(defaultValue = "") String itemName) {
        ModelAndView model = new ModelAndView();
        Item item = itemService.findById(id);

        itemService.deleteItem(item);

        ArrayList<Item> items = itemService.findItemsByName(itemName);

        model.addObject("items", items);

        String deleteMessage = "";
        model.addObject("deleteMessage", deleteMessage);
        model.setViewName("view_items");

        return model;
    }
}
