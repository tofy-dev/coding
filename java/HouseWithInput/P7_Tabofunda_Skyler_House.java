public class P7_Tabofunda_Skyler_House {
    /*
     * Skyler Tabofunda
     * Intro Java
     * Lab 2b- HouseWithInput
     * Period 7
     * 1/20/22
     */
    
    int numDoors;
    int numWindows;
    int numBedrooms;
    int askingPrice;
    
    // constructors
    public P7_Tabofunda_Skyler_House() {
        numDoors = 10;
        numWindows = 8;
        numBedrooms = 3;
        askingPrice = 435000;
    }
    
    public P7_Tabofunda_Skyler_House(int numDoors, int numWindows, int numBedrooms, int askingPrice) {
        this.numDoors = numDoors;
        this.numWindows = numWindows;
        this.numBedrooms = numBedrooms;
        this.askingPrice = askingPrice;
    }
    
    // getters
    public int getDoors() {
        return numDoors;
    }
    
    public int getWindows() {
        return numWindows;
    }
    
    public int getBedrooms() {
        return numBedrooms;
    }
    
    public int getPrice() {
        return askingPrice;
    }
    
    // setters
    public void setPrice(int askingPrice) {
        this.askingPrice = askingPrice;
    }
    
    // other
    public double calculateMarketValue() {
    	double marketValue;
    	marketValue = 300000 + 55000*numBedrooms + 3000*numWindows;
    	return marketValue;
    }
    
    
    // Adds more windows to the house
    public void addWindows(int num) {
        numWindows += num;
    }
    
    
    // Adds more doors to the house
    public void addDoors(int num) {
    	numDoors += num;
    }
    
    
    /** Adds more bedrooms to the house
        Each new bedroom adds one new door and one new window */
    public void addBedrooms(int num) {
    	numBedrooms += num;
    	numDoors++;
    	numWindows++;
    }
    
    
    /** returns true if the asking price is less than the
        calculated market value and false otherwise */
    public boolean isBelowMarketValue() {
    	if (askingPrice < calculateMarketValue()) {
    	    return true;
        } else {
            return false;
        }
    }
    
    
    /** returns a String specifying this house's
    	 - number of bedrooms
    	 - market value
    	 - asking price */
    public String toString() {
    	String sentence = String.format("A " + numBedrooms
    	+ " bedroom house with a market value of " + calculateMarketValue()
    	+ " is for sale with an asking price of " + askingPrice);
        return sentence;    
    }
}
