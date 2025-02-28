import java.util.*;

public class CardCollection {
    private Map<String, List<Integer>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    // Method to add a card
    public void addCard(String symbol, int value) {
        cardMap.putIfAbsent(symbol, new ArrayList<>()); // Ensure key exists
        cardMap.get(symbol).add(value);
    }

    // Method to retrieve all cards of a given symbol
    public List<Integer> getCards(String symbol) {
        return cardMap.getOrDefault(symbol, new ArrayList<>()); // Avoid null return
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        // Adding some cards
        collection.addCard("Hearts", 1);
        collection.addCard("Hearts", 2);
        collection.addCard("Spades", 3);
        collection.addCard("Hearts", 5);

        System.out.println("Enter a symbol to search (e.g., Hearts, Spades): ");
        String inputSymbol = scanner.nextLine();

        List<Integer> result = collection.getCards(inputSymbol);
        if (result.isEmpty()) {
            System.out.println("No cards found for symbol: " + inputSymbol);
        } else {
            System.out.println("Cards for " + inputSymbol + ": " + result);
        }
    }
}
