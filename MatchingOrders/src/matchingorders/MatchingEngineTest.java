package matchingorders;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchingEngineTest {
    private MatchingEngine matchingEngine;

    @BeforeEach
    void setUp() {
        matchingEngine = new MatchingEngine();
    }

    @Test
    void testAddNewOrder() {
        String result = matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User1");
        assertTrue(result.contains("Order added successfully"));
    }

    @Test
    void testAggregateExistingOrder() {
        matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User1");
        String result = matchingEngine.addOrder("EURUSD", "USD", "BUY", 500.00, 20250130, "User1");
        assertTrue(result.contains("Order aggregated successfully"));
    }

    @Test
    void testMatchBuyAndSellOrders() {
        matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User1");
        matchingEngine.addOrder("EURUSD", "USD", "SELL", 1000.00, 20250130, "User2");
        String result = matchingEngine.getMatchedAmount("User1");
        assertTrue(result.contains("100.0%"));
    }

    @Test
    void testNoMatchingOrders() {
        matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User1");
        String result = matchingEngine.getMatchedAmount("User1");
        assertTrue(result.contains("0.0%"));
    }

    @Test
    void testZeroAmountOrder() {
        String result = matchingEngine.addOrder("EURUSD", "USD", "BUY", 0.00, 20250130, "User1");
        assertTrue(result.contains("Order added successfully"));
    }

    @Test
    void testInvalidDirectionOrder() {
        String result = matchingEngine.addOrder("EURUSD", "USD", "HOLD", 1000.00, 20250130, "User1");
        assertTrue(result.contains("Order added successfully")); // Since no validation is done in the class
    }

    @Test
    void testPartialOrderMatching() {
        matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User1");
        matchingEngine.addOrder("EURUSD", "USD", "SELL", 500.00, 20250130, "User2");
        String result = matchingEngine.getMatchedAmount("User1");
        assertTrue(result.contains("50.0%"));
    }

    @Test
    void testMultipleUsersWithMatchingOrders() {
        matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User1");
        matchingEngine.addOrder("EURUSD", "USD", "SELL", 500.00, 20250130, "User2");
        matchingEngine.addOrder("EURUSD", "USD", "SELL", 500.00, 20250130, "User3");
        String result = matchingEngine.getMatchedAmount("User1");
        assertTrue(result.contains("100.0%"));
    }

    @Test
    void testDuplicateOrdersForSameUser() {
        matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User1");
        String result = matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User1");
        assertTrue(result.contains("Order aggregated successfully"));
    }

    @Test
    void testNegativeAmountOrder() {
        String result = matchingEngine.addOrder("EURUSD", "USD", "BUY", -1000.00, 20250130, "User1");
        assertTrue(result.contains("Order added successfully")); // No validation for negative amounts
    }

    @Test
    void testEmptyCurrencyPair() {
        String result = matchingEngine.addOrder("", "USD", "BUY", 1000.00, 20250130, "User1");
        assertTrue(result.contains("Order added successfully")); // No validation for empty currency pair
    }

    @Test
    void testNullUserId() {
        String result = matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, null);
        assertTrue(result.contains("Order added successfully")); // No validation for null user ID
    }

    @Test
    void testFutureDateOrder() {
        String result = matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20350130, "User1");
        assertTrue(result.contains("Order added successfully")); // No validation for future date
    }

    @Test
    void testDuplicateOrdersDifferentUsers() {
        matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User1");
        String result = matchingEngine.addOrder("EURUSD", "USD", "BUY", 1000.00, 20250130, "User2");
        assertTrue(result.contains("Order added successfully")); // Orders should not aggregate for different users
    }
}
