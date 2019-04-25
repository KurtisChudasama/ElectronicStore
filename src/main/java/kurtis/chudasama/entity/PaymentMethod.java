package kurtis.chudasama.entity;

public interface PaymentMethod {

    public boolean pay(double amount);

    public boolean checkDate(String expiry);

    public boolean checkCardNumber(String cardNumber);
}
