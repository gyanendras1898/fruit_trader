import java.io.*;
import java.util.*;

public class fruit_trader {
    private static HashMap<String, Deque<Stock>> fruit_cart;
    private static int totalProfit;

    static class Stock {
        int priceRate;
        int quantity;

        Stock(int priceRate, int quantity) {
            this.priceRate = priceRate;
            this.quantity = quantity;
        }
    }

    public static void main(String args[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fruit_cart = new HashMap<>();
        while (true) {
            try {
                String input = br.readLine();
                if (input.equals("PROFIT"))
                    System.out.println(totalProfit);
                else {
                    String[] inputArr = input.trim().split(" ");
                    String opperation = inputArr[0];
                    String fruitName = inputArr[1];
                    int priceRate = Integer.parseInt(inputArr[2]);
                    int quantity = Integer.parseInt(inputArr[3]);
                    Stock stock = new Stock(priceRate, quantity);

                    if (opperation.equals("BUY")) {
                        System.out.println("BOUGHT " + quantity + " KG APPLE AT " + priceRate + " RUPEES/KG");
                        buyFruit(fruitName, stock);
                    }

                    else {
                        System.out.println("SOLD " + quantity + " KG APPLE AT " + priceRate + " RUPEES/KG");
                        sellFruit(fruitName, stock);
                    }

                }
            } catch (Exception e) {
                break;
            }
        }
    }

    private static void buyFruit(String fruitName, Stock stock) {
        if (fruit_cart.containsKey(fruitName))
            fruit_cart.get(fruitName).add(stock);
        else {
            Deque<Stock> deque = new LinkedList<>();
            deque.add(stock);
            fruit_cart.put(fruitName, deque);
        }

    }

    private static void sellFruit(String fruitName, Stock stock) {
        int availableQuantity = stock.quantity;
        int SP = availableQuantity * stock.priceRate;
        int CP = 0;
        Deque<Stock> deque = fruit_cart.get(fruitName);

        while (availableQuantity != 0) {
            Stock front = deque.poll();
            if (front.quantity <= availableQuantity) {
                int quantitySold = front.quantity;
                CP += quantitySold * front.priceRate;
                availableQuantity -= quantitySold;
            } else {
                int quantitySold = availableQuantity;
                CP += quantitySold * front.priceRate;
                int remainingQuantity = front.quantity - quantitySold;
                front.quantity = remainingQuantity;
                deque.addFirst(front);
                availableQuantity = 0;
            }
        }
        totalProfit += (SP - CP);
    }
}