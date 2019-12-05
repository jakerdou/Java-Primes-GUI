import java.math.BigInteger;
import java.lang.Math;

//referenced this website: https://brilliant.org/wiki/prime-testing/
public class AbsoluteTest {
  public static boolean isPrime(BigInteger candidate)
  {
    //convert candidate to integer and double for convenience's sake
    int c = candidate.intValue();
    double cd = c;
    
    //for i in range (2, sqrt(candidate))
    //Only need to check up to sqrt(candidate) because 
      //only one factor of a composite number can be less than sqrt(candidate)
    for(int i = 2; i < (int) (Math.ceil(Math.sqrt(cd)) + 1); i++) {
      //if i|candidate , return false
      if(c % i == 0) {
        return false;
      }
    }
    return true;
  }
}
