package matchingorders;



//MatchingEngine.java
import java.util.ArrayList;
import java.util.List;

public class MatchingEngine {
private List<Order> orders;

// Constructor
public MatchingEngine() {
   this.orders = new ArrayList<>();
}

// Endpoint 1: Add an Order
public String addOrder(String currencyPair, String dealtCurrency, String direction, double amount, int valueDate, String userId) {
   // Check if the order is already in the list with the same details for aggregation
   for (Order existingOrder : orders) {
       if (existingOrder.getCurrencyPair().equals(currencyPair) &&
           existingOrder.getDealtCurrency().equals(dealtCurrency) &&
           existingOrder.getValueDate() == valueDate &&
           existingOrder.getDirection().equals(direction) &&
           existingOrder.getUserId().equals(userId)) {

           // Aggregate amounts of similar orders (same user and same attributes)
           existingOrder.setAmount(existingOrder.getAmount() + amount);
           return "Order aggregated successfully: " + existingOrder.toString();
       }
   }

   // If no match is found, add a new order
   Order newOrder = new Order(currencyPair, dealtCurrency, direction, amount, valueDate, userId);
   orders.add(newOrder);
   return "Order added successfully: " + newOrder.toString();
}

// Endpoint 2: Get Matched Amount
public String getMatchedAmount(String userId) {
   double totalMatchedAmount = 0.0;
   double totalOrderAmount = 0.0;

   // Loop through all orders to find matching orders for the given user
   for (Order order : orders) {
       if (!order.getUserId().equals(userId)) {
           continue; // Skip orders from other users
       }

       double orderAmount = order.getAmount();
       totalOrderAmount += orderAmount;

       // Match the order with others
       for (Order otherOrder : orders) {
           if (order == otherOrder) continue; // Skip matching with itself
           
           if (order.getCurrencyPair().equals(otherOrder.getCurrencyPair()) &&
               order.getDealtCurrency().equals(otherOrder.getDealtCurrency()) &&
               order.getValueDate() == otherOrder.getValueDate() &&
               !order.getUserId().equals(otherOrder.getUserId())) {

               // Match BUY-SELL or SELL-BUY orders
               if ((order.getDirection().equals("BUY") && otherOrder.getDirection().equals("SELL")) ||
                   (order.getDirection().equals("SELL") && otherOrder.getDirection().equals("BUY"))) {

                   // Calculate the matched amount
                   double matchedAmount = Math.min(orderAmount, otherOrder.getAmount());
                   totalMatchedAmount += matchedAmount;
               }
           }
       }
   }

   // Calculate the percentage of matched orders
   double matchedPercentage = totalOrderAmount > 0 ? (totalMatchedAmount / totalOrderAmount) * 100 : 0;
   return "Matched Amount for User " + userId + ": " + matchedPercentage + "%";
}
}
