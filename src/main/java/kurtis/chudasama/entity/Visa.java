package kurtis.chudasama.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

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

        if (checkDate(this.expires) && checkCardNumber(this.cardNumber)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean checkCardNumber(String cardNumber) {

        if (cardNumber.length() != 16) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean checkDate(String expiry) {
        ZoneId zonedId = ZoneId.of("GMT");
        LocalDate today = LocalDate.now(zonedId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate expiryDate = LocalDate.parse(expiry, formatter);

        if (expiryDate.isBefore(today)) {
            return false;
        }
        else {
            return true;
        }
    }
}
