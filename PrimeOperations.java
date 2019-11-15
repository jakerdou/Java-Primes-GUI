import java.util.ArrayList; 
import java.math.BigInteger;


/**************************************************************
 * Github Repo: https://github.com/jakerdou/Fall2019-314.git
 * Class: CSCE 314 - 502
 * File:    PrimeOperations.java
 * Author : James Robinson
 * Date   : 11/13/19
 * E-mail:  jakerdou@tamu.edu
 * 
 *************************************************************/

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class PrimeOperations {
	

  //PROBABLY GOING TO NEED TO ADD VARIABLES THAT LIST THE PRIMES AND TWIN PRIMES SO AS TO ACCESS THEM
  private ArrayList<BigInteger> primeArray = new ArrayList<BigInteger>();
  private ArrayList<Pair<BigInteger>> twinArray = new ArrayList<Pair<BigInteger>>();
  private ArrayList<Pair<BigInteger>> hexArray = new ArrayList<Pair<BigInteger>>();
  
  // Pair class implementation.
  private class Pair<T> {
    
    //ArrayList with capacity of 2
    private ArrayList<T> twoElements = new ArrayList<T>(2);
    
    //constructor that adds elements to twoElements
    public Pair(T x, T y)
    {
      twoElements.add(x);
      twoElements.add(y);
    }   
  }
  
  
  // Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
  
  // Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
  public void addPrime(BigInteger x)
  {
    if(!(primeArray.contains(x))) {
      primeArray.add(x);
    }
  }
  
  // Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
  public void printPrimes()
  {
    //iterate through primeArray and output elements
    for(int i = 0; i < primeArray.size(); i++) {
      System.out.println(primeArray.get(i)); 
    }
    
    System.out.println("Total Primes: " + primeArray.size());
  }
  
  // Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
  public void printTwins()
  {
  //iterate through twinArray and output elements
    for(int i = 0; i < twinArray.size(); i++) {
      BigInteger firstVal = ((twinArray.get(i)).twoElements).get(0);
      BigInteger secondVal = ((twinArray.get(i)).twoElements).get(1);
      System.out.println(firstVal + ", " + secondVal); 
    }
    
    System.out.println("Total Twins: " + twinArray.size());
  }
  
  // Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
  public void printHexes()
  { 
    BigInteger bigOne = new BigInteger("1");
    
    //iterate through hexArray and output elements
    for(int i = 0; i < hexArray.size(); i++) {
      //Initialize first and second numbers of hex cross to avoid lots of typing
      BigInteger firstHex = (((hexArray.get(i)).twoElements).get(0));
      BigInteger secondHex = (((hexArray.get(i)).twoElements).get(1));
      
      //initialize the prime numbers that the hex cross numbers are between
      BigInteger firstPrime = firstHex.subtract(bigOne);
      BigInteger secondPrime = firstHex.add(bigOne);   
      BigInteger thirdPrime = secondHex.subtract(bigOne);
      BigInteger fourthPrime = secondHex.add(bigOne);
      
      //format output
      System.out.print("Prime Pairs: " + firstPrime + ", " + secondPrime + " and ");
      System.out.print(thirdPrime + ", " + fourthPrime + " separated by ");
      System.out.println(firstHex + ", " + secondHex);
    }
    
    System.out.println("Total Hexes: " + hexArray.size());
  }
  
  // Generate and store a list of primes.
  public void generatePrimes(int count)
  { 
    primeArray.clear();
    
    //base case
    if(count == 0) {
      return;
    } else {
      //first prime number will always be two
      BigInteger primeToAdd = new BigInteger("2");
      
      for(int i = 0; i < count; i++) {
        //add prime to list then generate the next prime
        addPrime(primeToAdd);
        primeToAdd = primeToAdd.nextProbablePrime();
      }
    }
  }
  
  // Generate and store a list of twin primes.
  public void generateTwinPrimes()
  {
    twinArray.clear();
    
    BigInteger bigTwo = new BigInteger("2");
    
    for(int i = 0; i < primeArray.size() - 1; i++) {
      //set current prime and next prime to avoid lots of typing
      BigInteger currPrime = primeArray.get(i);
      BigInteger nextPrime = primeArray.get(i + 1);
      
      //if currPrime + 2 = nextPrime
      if((currPrime.add(bigTwo)).equals(nextPrime)) {
        //create pair and add it to twin prime list
        Pair<BigInteger> twinPrimes = new Pair<BigInteger>(currPrime, nextPrime);
        twinArray.add(twinPrimes);
      }
    }
  }
  
  // Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
  public void generateHexPrimes()
  {
    hexArray.clear();
    
    BigInteger bigOne = new BigInteger("1");
    
    for(int i = 0; i < twinArray.size() - 1; i++) {
      //initialize firstHex as first element of first pair + 1
      BigInteger firstHex = (((twinArray.get(i)).twoElements).get(0)).add(bigOne);
      
      //we are looking for firstHex * 2
      BigInteger searchingFor = firstHex.add(firstHex);
      
      //initialize secondHex as first element of second pair + 1
      int j = i + 1;
      BigInteger secondHex = (((twinArray.get(j)).twoElements).get(0)).add(bigOne);
      
      //boolean indicating if we have iterated through all of twinArray
      boolean checkedAll = false;
      
      //while j is less than or equal to size and secondHex is less than or equal to searchingFor
      while(/*j < twinArray.size()*/!checkedAll && secondHex.compareTo(searchingFor) <= 0) {
        if(secondHex.equals(searchingFor)) {
          //create pair of hexagon cross and then add it to hexArray
          Pair<BigInteger> hexCross = new Pair<BigInteger>(firstHex, secondHex);
          hexArray.add(hexCross);
        }
        
        //if secondHex won't go out of bounds
        if(j < twinArray.size() - 1) {
          //set secondHex as first element in next pair
          j = j + 1;
          secondHex = (((twinArray.get(j)).twoElements).get(0)).add(bigOne);
        } else {
          checkedAll = true;
        }
      }
    }
  }
}
