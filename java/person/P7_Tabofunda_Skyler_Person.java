public class P7_Tabofunda_Skyler_Person {

	// Instance Variables - fields storing info about the state
	//                      of an object (attributes/properties)

	int myAge;			// Age in years
	double myHeight;	// Height in meters
	boolean isFemale;	// True if female, false if male
	String myName;		// P7_Tabofunda_Skyler_Person's name

	int myFavoriteNumber;
	String myFavoriteColor;
	boolean isSleeping;

	// Constructors - Specialized methods that set an object up
	//                and initialize instance variables

	public P7_Tabofunda_Skyler_Person() {        
		myAge = 17;
		myHeight = 1.53;
		isFemale = true;
		myName = "Frances";
	}

	public P7_Tabofunda_Skyler_Person(int age, double height, boolean female, String name, int favNum, String favCol, boolean sleep) {
		myAge = age;
		myHeight = height;
		isFemale = female;
		myName = name;
		
		myFavoriteNumber = favNum;
		myFavoriteColor = favCol;
		isSleeping = sleep;
	}

	// Methods - The things an object can do
	//           Subroutines, or code, that define how an object behaves
	public double heightToInches() {
		// 1 meter = 39.97 inches
		double answer = myHeight * 39.97;
		return answer;
	}

	public String getName() {
		return myName;
	}

	public void printInfo() {
		String response;
		response = "Hello, my name is " + myName;
		response = response + " and I'm " + myAge + " years old!";
		System.out.println(response);
	}

	public void increaseAge(int amount) {
		myAge = myAge + amount;
	}
	
	// Returns age in dog years (1 human year = 7 dog years)
public int ageInDogYears() {
    return myAge * 7;
}


// Returns this Person's favorite color
public String getFavoriteColor() {
    return myFavoriteColor;
}

  
// Prints this Person's name, age, and favorite color
public void printInfo2() {
    String response;
    response = "Hello, my name is " + myName;
    response += ". I'm " + myAge + ", and my favorite color is ";
    response += myFavoriteColor;
}


// Changes the state of this Person to sleeping
public void sleep() {
    isSleeping = true;
}


// Changes the state of this Person to awake
public void wakeUp() {
    isSleeping = false;
}


// Prints the sleep state of this Person
public void printSleepState() {
	if (isSleeping == true)
		System.out.println("I am sleeping....zzzz....");
	else
		System.out.println("I am awake!");
}

}
   