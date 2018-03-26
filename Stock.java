/**
DYLAN GRANDJEAN
This class can be used to represent a Stock item.
*/

public class Stock
{
	private String symbol;					// Stock ticker symbol
	private String name;					// Company name
	private int shares;						// Number of shares owned
	private double previousClosingPrice;	// Previous closing price
	private double currentPrice;			// Most recent price

	/**
	Stock class constructor
	@param sym		the stock ticker symbol
	@param company	the stock company name
	*/

	public Stock(String sym, String company)
	{
		// Set these as user chooses
		symbol = sym;
		name = company;

		// Remaining fields are set to their default values
	}

	/**
	Stock class constructor
	@param sym		the stock ticker symbol
	@param company	the stock company name
	@param sh		number of shares owned
	@param curr		current stock price
	*/

	public Stock(String sym, String company,
		int sh, double curr)
	{
		// Set these as user chooses
		symbol = sym;
		name = company;

		// Use the mutators to validate these user choices
		setShares(sh);
		setCurrentPrice(curr);

		// Remaining field set to its default value
	}

	/**
	Method getSymbol returns the ticker symbol
	@return		the ticker symbol for this stock
	*/

	public String getSymbol()
	{
		// Return the ticker symbol
		return symbol;
	}

	/**
	Method getName returns the name of the company
	@return		the company name
	*/

	public String getName()
	{
		// Return the company name
		return name;
	}

	/**
	Method getShares returns the number of shares owned
	@return		number of shares owned
	*/

	public int getShares()
	{
		// Return the number of shares owned
		return shares;
	}

	/**
	Method getPreviousClosingPrice returns the previous closing price
	@return		the previous closing price
	*/

	public double getPreviousClosingPrice()
	{
		// Return the previous closing price
		return previousClosingPrice;
	}

	/**
	Method getCurrentPrice returns the current price of the stock
	@return		the current stock price
	*/

	public double getCurrentPrice()
	{
		// Return the current stock price
		return currentPrice;
	}

	/**
	Method currentValue computes and returns the current value of the stock
	@return		the current value of the stock
	*/

	public double currentValue()
	{
		// Compute and return the current value of the stock
		return currentPrice * shares;
	}

	/**
	Method setShares changes the number of shares
	@param howMany	the new number of shares
	*/


	public void setShares(int howMany)
	{
		// The new number of shares (howMany) must be at least 0
		if ( howMany < 0 )
			System.out.println( "New Shares Invalid: "
				+ howMany );
		else
			// Valid "howMany" becomes new value of shares.
			shares = howMany;
	}

	/**
	Method setCurrentPrice changes the current price
	@param price		the new current price
	*/

	public void setCurrentPrice(double price)
	{
		// The new price must be at least 0
		if ( price < 0 )
			System.out.println( "New Current Price Invalid: "
				+ price );
		else
		{
			// Reset the previous closing price and then
			// change current price to "price"
			previousClosingPrice = currentPrice;
			currentPrice = price;
		}
	}

	/**
	Method changePercent computes the change percent from yesterday to today
	@return		the change percent
	*/

	public double changePercent()
	{
		double pct;

		// Make sure not to divide by 0
		if ( previousClosingPrice > 0 )
			pct = (currentPrice - previousClosingPrice) /
				previousClosingPrice;
		else
		{
			// When previous closing price is 0, return "infinity"
			pct = Double.POSITIVE_INFINITY;
		}

		// Return the change percent
		return pct;
	}

	/**
	Method toString returns a string formatted to represent the current
	state of the Stock object
	@return		the formatted string representing the current state of the
				Stock object
	*/

	public String toString()
	{
		// Format to show appropriate alignment, commas, decimal places
		// and return formatted String.
		String str =
			"Symbol:                 " + symbol
			+ "\nName:                   " + name
			+ "\nShares:                 "
			+ String.format( "%,d", shares )
			+ "\nPrevious Closing Price: "
			+ String.format( "$%,.2f", previousClosingPrice )
			+ "\nCurrent Price:          "
			+ String.format( "$%,.2f", currentPrice );

		// Return the formatted String
		return str;
	}
}
