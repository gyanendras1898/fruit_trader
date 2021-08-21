# fruit_trader

Businessman trading fruits, Three main operations- buy, sell and calculate profit.

Understanding Problem Statement:

The problem statement says there is a buisnessman who is trading fruits, here mainly three operations are performed
BUYING, SELLING and calculating PROFIT out of it, all different types of fruits at different rates and quantities
are given, user has three choices to input BUY, SELL with the name of the fruit with quantity , rate and PROFIT
as a operation, on giving BUY as input , quantity of fruits bought with rate will be shown, on giving SELL as input,
quantity of fruits sold out with rate will be shown, and if user gives PROFIT as input, overall profit earned will be
displayed on the screen.

Logic Used:

1. At first I have created a class Stock which is having two properties : pricerate and quantity,these properties are storing
   quantity and rate of fruit.
2. Then I have created a Hashmap<fruit-cart> which is storing fruit name as a key and deque as a value, on the other hand deque is storing
   objects of class stock.
3. Application will continue take input from the user until he enters a invalid input.
4. If input='BUY',then it will check whether hashmap is already having fruit or not , if it is present then its corresponding
   deque<stock> will be picked and then this stock will be added in the end. If it is not present in hashmap, then application will
   create a new Deque of class Stock, then fruit name as key and new deque as value will be stored in the hashmap.
5. If input='SELL', application will check whether fruit is present in hashmap<fruit-cart> or not,if not it will throw error, if present
   application will pull out deque of that fruit and the quantity which needed to be sold out will be subtracted from head side in FIFO manner.
   Parallely application will compute total profit(which is a global variable) and it keep on checking whether sell quantity is greater than
   buy quantity or not, if it is greater than application will throw error that 'no such fruits left in the cart'.
   6.If input='PROFIT', application will print value of global variable totalProfit.

Handling Boundary test Cases:

1. If Selling quantity> Bought quantity, application will throw error.
2. If any fruit's all quantity are sold then that fruit will be removed from hashmap<fruit-cart>.
3. If any fruit is not even bought and user enters input to sell this fruit, application will throw error.
4. Constraints :(a)All input and output quantity must be in range of integer.(b) If user enters any input other than BUT, SELL or PROFIT,
   application will be terminated itself.

Steps To Execute The Application:

Requirements : User must have preinstalled the jdk on local computer to run this application. He can download it from
https://www.oracle.com/in/java/technologies/javase-jdk16-downloads.html. and He should paste both files fruit_trader.java
and fruit_trader.class inside bin folder of jdk folder and while running program directory must be bin.

Command to compile java file : javac fruit_traders.java
Command to run java file : java fruit_traders.java

<----------------------------------- IMAGE---------------------------------------------------------------->
![cmd-img](master/cmd-img.png)
