import java.io.*;
import java.util.*;

public class fruit_trader {

    /*
     * instance variable totalProfit to keep track of profit and a Hashmap which
     * store the fruits. Hashmap takes fruits as KEY and a deque as a VALUE. deque
     * contains object of stock class. deque data structure is used to perform
     * adding and removing items from both the end.
     */
    private static HashMap<String, Deque<Stock>> fruit_cart;
    private static int totalProfit;

    // Stock class to store the price and rate of perticular fruit combined.
    static class Stock {
        int priceRate;
        int quantity;

        Stock(int priceRate, int quantity) {
            this.priceRate = priceRate;
            this.quantity = quantity;
        }
    }

    // main function
    public static void main(String args[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // System.out.println("Enter \"VIEWCART\" to view your current fruit-items");
        fruit_cart = new HashMap<>();

        // it continues to take input until a wrong entry is given
        while (true) {
            try {
                String input = br.readLine();

                // check if user enter PROFIT the print total profit
                if (input.equals("PROFIT"))
                    System.out.println(totalProfit);

                // to knows the what are the items still in the cart
                else if (input.equals("VIEWCART"))
                    viewCart(fruit_cart);

                // else user must enter BUY or SELL
                else {
                    String[] inputArr = input.trim().split(" ");
                    String opperation = inputArr[0];
                    String fruitName = inputArr[1];
                    int priceRate = Integer.parseInt(inputArr[2]);
                    int quantity = Integer.parseInt(inputArr[3]);
                    Stock stock = new Stock(priceRate, quantity);

                    // calling buy function
                    if (opperation.equals("BUY")) {
                        System.out
                                .println("BOUGHT " + quantity + " KG " + fruitName + " AT " + priceRate + " RUPEES/KG");
                        buyFruit(fruitName, stock);
                    }

                    // calling sell function
                    else {
                        System.out.println("SOLD " + quantity + " KG " + fruitName + " AT " + priceRate + " RUPEES/KG");
                        sellFruit(fruitName, stock);
                    }

                }
            } catch (Exception e) {
                System.out.println(e);
                break;
            }
        }
    }

    // function to print the remaining items of cart
    private static void viewCart(HashMap<String, Deque<Stock>> fruit_cart) {
        for (String ele : fruit_cart.keySet()) {
            System.out.print(ele + " ");
            Deque<Stock> d = fruit_cart.get(ele);
            Iterator<Stock> iteratorVals = d.iterator();
            while (iteratorVals.hasNext()) {
                Stock f = iteratorVals.next();
                System.out.println(f.priceRate + " " + f.quantity);
            }
        }
    }

    // BUY function
    private static void buyFruit(String fruitName, Stock stock) {
        // checking if cart already contains this fruit the add a stock of new rate at
        // the end of the deck
        if (fruit_cart.containsKey(fruitName))
            fruit_cart.get(fruitName).add(stock);
        else {
            // else create a new deque of the current stock and put it with fruit ame into
            // the hashmap
            Deque<Stock> deque = new LinkedList<>();
            deque.add(stock);
            fruit_cart.put(fruitName, deque);
        }
    }

    private static void sellFruit(String fruitName, Stock stock) throws Exception {
        // if cart does not have current fruit the send an error
        if (!fruit_cart.containsKey(fruitName)) {
            throw new Exception("Error : You haven't bought " + fruitName);
        }
        int availableQuantity = stock.quantity;
        int SP = availableQuantity * stock.priceRate;
        int CP = 0;
        Deque<Stock> deque = fruit_cart.get(fruitName);

        // taking out the deque and sell the fruit stock from front end i.e. in FIFO
        // order
        while (availableQuantity != 0) {
            if (!deque.isEmpty()) {
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
            } else {
                // of current fruit stock get empty the remove this fruit from the hashmap i.e.
                // fruit-cart
                fruit_cart.remove(fruitName);
                throw new Exception("Error : there is no " + fruitName + " left to SELL");
            }
        }
        if (deque.isEmpty())
            fruit_cart.remove(fruitName);

        // calculating total profit simultaneously
        totalProfit += (SP - CP);
    }
}
