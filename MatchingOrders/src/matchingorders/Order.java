package matchingorders;


public class Order {
    private String currencyPair;
    private String dealtCurrency;
    private String direction;
    private double amount;
    private int valueDate;
    private String userId;

    // Constructor
    public Order(String currencyPair, String dealtCurrency, String direction, double amount, int valueDate, String userId) {
        this.currencyPair = currencyPair;
        this.dealtCurrency = dealtCurrency;
        this.direction = direction;
        this.amount = amount;
        this.valueDate = valueDate;
        this.userId = userId;
    }

    // Getters and Setters
    public String getCurrencyPair() {
        return currencyPair;
    }

    public String getDealtCurrency() {
        return dealtCurrency;
    }

    public String getDirection() {
        return direction;
    }

    public double getAmount() {
        return amount;
    }

    public int getValueDate() {
        return valueDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "currencyPair='" + currencyPair + '\'' +
                ", dealtCurrency='" + dealtCurrency + '\'' +
                ", direction='" + direction + '\'' +
                ", amount=" + amount +
                ", valueDate=" + valueDate +
                ", userId='" + userId + '\'' +
                '}';
    }
}