

package matchingorders;
public class Main {
    public static void main(String[] args) {
        MatchingEngine matchingEngine = new MatchingEngine();

        // Scenario A: Adding Orders
        System.out.println(matchingEngine.addOrder("EURUSD", "USD", "SELL", 10000.00, 20250130, "User A"));
        System.out.println(matchingEngine.addOrder("EURUSD", "USD", "BUY", 5000.00, 20250130, "User A"));
        System.out.println(matchingEngine.addOrder("EURUSD", "USD", "BUY", 5000.00, 20250130, "User B"));

        // Scenario B: Query matched amounts
        System.out.println(matchingEngine.getMatchedAmount("User A"));
        System.out.println(matchingEngine.getMatchedAmount("User B"));
    }
}