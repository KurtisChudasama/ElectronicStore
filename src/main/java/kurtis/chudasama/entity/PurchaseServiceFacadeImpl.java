package kurtis.chudasama.entity;

import kurtis.chudasama.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class PurchaseServiceFacadeImpl implements PurchaseServiceFacade {

    StockService stockService = new StockService();

    public PurchaseServiceFacadeImpl() {

    }

    @Override
    public boolean placeOrder(ArrayList<CartItems> cart_items) {

        boolean doPlace = false;

        if (stockService.isAvailable(cart_items)) {
            doPlace = true;
        }

        return doPlace;
    }
}
