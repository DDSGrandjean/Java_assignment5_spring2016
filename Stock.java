/**
	DYLAN GRANDJEAN -
	The Stock Class takes in information about a stock and calculates
	the value of that stock and its increase/decrease percentage.
*/
public class Stock
{
	//Field declaration
	private String symbol;
	private String name;
	private int shares;
	private double previousClosingPrice;
	private double currentPrice;

	/**
		Constructor #1
		@param aSymbol stores the value of aSymbol in the field symbol.
		@param aName stores the value of aName in the field name.
	*/
	public Stock(String aSymbol, String aName)
	{
		symbol = aSymbol;
		name = aName;
	}

	/**
		Constructor #2
		@param aSymbol stores the value of aSymbol in the field symbol.
		@param aName stores the value of aName in the field name.
		@param aShares stores the value of aShares in the field shares if it is valid.
		@param aCurrentPrice stores the value of aCurrentPrice in the field currentPrice if it is valid.
	*/
	public Stock(String aSymbol, String aName, int aShares, double aCurrentPrice)
	{
		symbol = aSymbol;
		name = aName;
		//Sends the parameters through mutators to test their validity
		setShares(aShares);
		setCurrentPrice(aCurrentPrice);
	}

	/**
		The currentValue method determines the current value of the shares and returns the total price.
		@return The total price.
	*/
	public double currentValue()
	{
		double currentValue = shares * currentPrice;
		return currentValue;
	}

	/**
		The changePercent method determines the increase or decrease percentage of the stock in comparison
		to the previous entry for that stock.
		@return The increase or decrease percentage of the stock in relation to its previous price.
	*/
	public double changePercent()
	{
		double percentage;

		//Check for a possible division by 0
		if (previousClosingPrice > 0)
			percentage = (currentPrice - previousClosingPrice) / previousClosingPrice;
		//If division by 0 occures, set the percentage to positive infinity
		else
			percentage = Double.POSITIVE_INFINITY;

		return percentage;
	}

	/**
		The toString method returns a formatted String.
		@return A formatted String containing information about the current stock.
	*/
	public String toString()
	{
		return String.format("Symbol:                 %s" +
			   				 "\nName:                   %s" +
		       				 "\nShares:                 %d" +
		     			     "\nPrevious closing price: %,.2f" +
		       				 "\nCurrent price:          %,.2f",
		       				 symbol, name, shares, previousClosingPrice, currentPrice);
	}

	/**
		The setCurrentPrice method stores a value in the currentPrice field.
		@param aCurrentPrice The value to store in currentPrice.
	*/
	public void setCurrentPrice(double aCurrentPrice)
	{
		//Only change the value of currentPrice if the new value is greater than or equal to 0
		if (aCurrentPrice >= 0)
		{
			previousClosingPrice = currentPrice;
			currentPrice = aCurrentPrice;
		}
		//If the value is less than 0, display an error
		else
			System.out.printf("New current price value invalid: %.2f\n", aCurrentPrice);
	}

	/**
		The setShares method stores a value in the shares field.
		@param aShares The value to store in shares.
	*/
	public void setShares(int aShares)
	{
		//Only change the value of shares if the new value is greater than or equal to 0
		if (aShares >= 0)
			shares = aShares;
		//If the value is less than 0, display an error
		else
			System.out.printf("New shares invalid: %d\n", aShares);
	}

	/**
		The getSymbol method returns a Stock object's symbol.
		@return The value in the symbol field.
	*/
	public String getSymbol()
	{
		return symbol;
	}

	/**
		The getName method returns a Stock object's name.
		@return The value in the name field.
	*/
	public String getName()
	{
		return name;
	}

	/**
		The getShares method returns a Stock object's shares.
		@return The value in the shares field.
	*/
	public int getShares()
	{
		return shares;
	}

	/**
		The getPreviousClosingPrice method returns a Stock object's previous closing price.
		@return The value in the previousClosingPrice field.
	*/
	public double getPreviousClosingPrice()
	{
		return previousClosingPrice;
	}

	/**
		The getCurrentPrice method returns a Stock object's current price.
		@return The value in the currentPrice field.
	*/
	public double getCurrentPrice()
	{
		return currentPrice;
	}
}
