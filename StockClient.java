/**
	DYLAN GRANDJEAN,
	Assignment 5, part 2
	This client is designed to allow the user to navigate
	through and edit a stock with the help of a menu.
*/

//Allows the use of scanner and file reader
import java.util.Scanner;
import java.io.*;

public class StockClient
{
	//Field declaration
	private static Scanner stockFile;
	private static Scanner keyboard = new Scanner(System.in);

	/**
		The main method: allows the user to access and edit stock.
		@param args Takes an array of String arguments.
	*/
	public static void main (String[] args)throws IOException
	{
		//Variable declaration
		Stock currentStock;				//Current Stock object read from the file
		String inputValue;				//User input
		int currentShares;				//Shares from the current Stock object
		int newShares;					//Amount of shares add or substracted from the Stock object by the user
		int stockProcessed = 0;			//Number of Stock processed

		String stockName = "";			//Name of the most valuable stock
		String stockSymbol;				//Symbol of the most valuable stock
		int stockShares = 0;			//Shares of the most valuable stock
		double stockMaxPrice = 0;		//Maximum price of the most valuable stock
		double value;					//Value to compare with the highest value
		double maxValue = 0;			//Highest value processed

		//Display programmer's name
		System.out.println("DYLAN GRANDJEAN");

		//Declare and instantiate file reader
		File file = new File("StockData.txt");
		//Test for the file's existance
		if (!file.exists())
		{
			//Display error message if no file is found
			System.out.printf("\n%s not found. Aborting\n\n", file);
			System.exit(0);
		}

		//Instantiate file scanner
		stockFile = new Scanner(file);
		//Create the first stock prior to user input
		currentStock = nextStock();

		//Take the first stock into account when comparing stock value
		value = currentStock.currentValue();
		if (value > maxValue)
		{
			maxValue = value;
			stockName = currentStock.getName();
			stockMaxPrice = currentStock.getCurrentPrice();
			stockShares = currentStock.getShares();
		}

		//Display menu and prompt for user input
		inputValue = menu();
		//Increment first stock if user does not quit immediately
		if (!inputValue.equals("8"))
			stockProcessed++;

		//Perform loop while the user has not chosen to quit
		while (!inputValue.equals("8"))
		{
			//Analyze the user's input and respond accordingly
			switch (inputValue)
			{
				case "1":
					//Test for next stock in text file
					if (stockFile.hasNext())
					{
						currentStock = nextStock();
						//Variable incrementation
						stockProcessed++;
					}
					else
						//Display error message if no more stock can be found
						System.out.println("\nEnd of stock file. No new stock found.");
					break;
				case "2":
					System.out.print("\nNew price: ");
					currentStock.setCurrentPrice(Double.parseDouble(keyboard.nextLine()));
					break;
				case "3":
					currentShares = currentStock.getShares();
					System.out.print("\nBuy how many shares? ");
					newShares = Integer.parseInt(keyboard.nextLine());
					//Test for the newShares' validity
					while (newShares <= 0)
					{
						System.out.printf("Invalid amount: %d\n", newShares);
						System.out.print("Buy how many shares? ");
					    newShares = Integer.parseInt(keyboard.nextLine());
					}
					currentStock.setShares(currentShares + newShares);
					break;
				case "4":
					currentShares = currentStock.getShares();
					System.out.print("\nSell how many shares? ");
					newShares = Integer.parseInt(keyboard.nextLine());
					//Test for the newShares' validity
					while(newShares > currentShares || newShares <= 0)
					{
						System.out.printf("Invalid number of shares: %d", newShares);
						System.out.print("\nSell how many shares? ");
						newShares = Integer.parseInt(keyboard.nextLine());
					}
					currentStock.setShares(currentShares - newShares);
					break;
				case "5":
					System.out.printf("\nCurrent value: $%,.2f\n", currentStock.currentValue());
					break;
				case "6":
					//Format the output according to whether or not percentage is infinite
					if (currentStock.changePercent() == Double.POSITIVE_INFINITY)
						System.out.printf("\nChange percent: %,.2f\n", currentStock.changePercent());
					else
						System.out.printf("\nChange percent: %,.2f%%\n", (currentStock.changePercent() * 100));
					break;
				case "7":
					System.out.println("\n" + currentStock.toString());
					break;
				default:
					System.out.printf("Invalid choice: %s\n", inputValue);
			}

			//Set value to the current value of the stock
			value = currentStock.currentValue();
			//If the value is greater than the prior, the stock becomes the most valuable
			if (value > maxValue)
			{
				maxValue = value;
				stockName = currentStock.getName();
				stockMaxPrice = currentStock.getCurrentPrice();
				stockShares = currentStock.getShares();
			}

			//Display menu and prompt for user input
			inputValue = menu();

		}

		//Determine if any stock has been processed
		if (stockProcessed != 0)
		{
			System.out.printf("\nStocks Processed:    %d\n" +
							  "Maximum stock value: $%,.2f\n" +
							  "Stock name:          %s\n" +
							  "Stock max price:     $%,.2f\n" +
							  "Stock max shares:    %s\n",
							  stockProcessed, maxValue, stockName,
							  stockMaxPrice, stockShares);
		}
		//Display message if no stock was processed
		else
			System.out.println("\nNo stock has been processed.");

		//Close the file at the end of the program
		stockFile.close();
	}

	/**
		The menu method displays a menu of option for the user to choose from.
		@return The user's choice.
	*/
	public static String menu()
	{
		String inputValue;

		//Prompts the user for next input
		System.out.println("\nStock Menu Choices\n" +
						   "1. Next stock\n" +
						   "2. Change current price\n" +
						   "3. Buy stock\n" +
						   "4. Sell stock\n" +
						   "5. Show current value\n" +
						   "6. Show change percent\n" +
						   "7. Show stock details\n" +
						   "8. Quit\n");
		System.out.print  ("Your choice: ");
		inputValue = keyboard.nextLine();

		return inputValue;
	}

	/**
		The nextStock method processes the next method in the text file.
		@return The Stock object instantiated from the text's data.
	*/
	public static Stock nextStock()
	{
		//Local variable declaration
		String symbol;
		String name;
		int shares;
		double currentPrice;

		//Display a loading message
		System.out.println("\nReading stock data...");

		//Read and assign text value to their corresponding variable
		symbol = stockFile.nextLine();
		name = stockFile.nextLine();
		shares = stockFile.nextInt();
		currentPrice = stockFile.nextDouble();

		//Declare and instantiate a new Stock object
		Stock stock = new Stock(symbol, name, shares, currentPrice);

		//Clean up the string input if any value follows
		if (stockFile.hasNext())
			symbol = stockFile.nextLine();

		//Return newly created Stock object
		return stock;
	}
}
