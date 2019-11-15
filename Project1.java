
/**************************************************************
 * Github Repo: https://github.com/jakerdou/Fall2019-314.git
 * Class: CSCE 314 - 502
 * File:    Project1.java
 * Author : James Robinson
 * Date   : 11/13/19
 * E-mail:  jakerdou@tamu.edu
 * 
 *************************************************************/

public class Project1 {
  public static void main(String[] args) 
  {
    // Instantiate Primes Class
    PrimeOperations testOne = new PrimeOperations();
    
    // Generate Primes and test.
    testOne.generatePrimes(21);
    testOne.printPrimes();
   
    // Generate and test Twin Primes
    PrimeOperations testTwo = new PrimeOperations();
    testTwo.generatePrimes(100);
    testTwo.generateTwinPrimes();
    testTwo.printTwins();
   
    // Generate and test Hexagonal crosses
    PrimeOperations testThree = new PrimeOperations();
    testThree.generatePrimes(2000);
    testThree.generateTwinPrimes();
    testThree.generateHexPrimes();
    testThree.printHexes();
    
    
    /************************My Test Cases are Below****************************************/
    /*
    PrimeOperations myTestOne = new PrimeOperations();
    myTestOne.generatePrimes(0);
    myTestOne.printPrimes();
    myTestOne.generateTwinPrimes();
    myTestOne.printTwins();
    myTestOne.generateHexPrimes();
    myTestOne.printHexes();
    
    PrimeOperations myTestTwo = new PrimeOperations();
    myTestTwo.generatePrimes(1);
    myTestTwo.printPrimes();
    myTestTwo.generateTwinPrimes();
    myTestTwo.printTwins();
    myTestTwo.generateHexPrimes();
    myTestTwo.printHexes();
    
    PrimeOperations myTestThree = new PrimeOperations();
    myTestThree.generatePrimes(10000);
    myTestThree.printPrimes();
    myTestThree.generateTwinPrimes();
    myTestThree.printTwins();
    myTestThree.generateHexPrimes();
    myTestThree.printHexes();
    */
  }
}
