package SEFTP;
/**
 * This class contains methods used by an EToken object.
 */
public class EToken{
  private String value;
  /**
   * This constructor accepts a token as a String and initializes its value.
   * @param s the unique token of a transaction.
   */
  public EToken(String s){
    this.value=s;
  }
  /**
   * This method accesses a String token and returns it.
   * @return the String value of a token.
   */
  public String getValue(){
    return value;
  }
}