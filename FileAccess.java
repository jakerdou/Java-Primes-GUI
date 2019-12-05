// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;

public class FileAccess {
  public static boolean loadPrimes(Primes primes, String filename) {
    try {
      Scanner sc = new Scanner(new File(filename));
      
      primes.clearPrimes();
      
      //while not at end of file
      while(sc.hasNextLine()) {
        primes.addPrime(new BigInteger(sc.nextLine()));
      }
      
      sc.close();   
      return true;
    }
    catch(NullPointerException | IOException error) {
      return false;
    }
  }
  
  public static boolean loadCrosses(Primes primes, String filename) {
    try {
      Scanner sc = new Scanner(new File(filename));

      primes.clearCrosses();
      
      //while not at end of file
      while(sc.hasNextLine()) {
        String line = sc.nextLine();
        int commaInd = line.indexOf(",");
        
        BigInteger l = new BigInteger(line.substring(0, commaInd));
        BigInteger r = new BigInteger(line.substring(commaInd + 1));
        primes.addCross(new Pair<BigInteger>(l, r));
      }

      sc.close();   
      return true;
    }
    catch(NullPointerException | IOException error) {
      return false;
    }
	}
  
  public static boolean savePrimes(Primes primes, String filename)
  {
    try {
      FileWriter fw = new FileWriter(new File(filename));
      
      //iterate and output primes
      for(BigInteger p : primes.iteratePrimes())
      {
        fw.append(p.toString() + "\n");
      }
      
      fw.close();
      
      return true;
    }
    catch(IOException error) {
      return false;
    }
  }
  
  public static boolean saveCrosses(Primes primes, String filename)
  {
    try {
      FileWriter fw = new FileWriter(new File(filename));
      
      //iterate and output crosses
      for(Pair<BigInteger> c : primes.iterateCrosses())
      {
        fw.append(c.left().toString() + "," + c.right().toString() + "\n");
      }
      
      fw.close();
      
      return true;
    }
    catch(IOException error) {
      return false;
    }
  }
}