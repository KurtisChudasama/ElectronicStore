package kurtis.chudasama.entity;

import java.util.Date;

public class Visa implements PaymentMethod {

    private String name;
    private String cardNumber;
    private String expires;

    public Visa (String name, String cardNumber, String expires) {
        super();
        this.name = name;
        this.cardNumber = cardNumber;
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    @Override
    public boolean pay(double amount) {
        if (this.cardNumber.length() != 16) {
            return false;
        }
        else {
            return true;
        }
    }
}
