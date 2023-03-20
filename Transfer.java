package SEFTP;
/**
 * This class extends ETransaction and contains methods for Transfer objects.
 */
public class Transfer extends ETransaction {

  private double amount;
  

  /**
   * This constructor accepts arguments and initializes their value.
   * @param token the unique token for a transaction represented as an EToken object.
   * @param status the status of a transaction represented as an enumeration.
   * @param date the date of a transaction represented as a SimpleDate object.
   * @param amount the transaction amount.
   */
  public Transfer(EToken token, EStatus status,SimpleDate date,double amount) {
    super(token, status,date);
    this.amount = amount;
  }
  
  
  /**
   * This method accesses an amount and returns it.
   * @return the amount being transferred.
   */
  public double getAmount() {
     return amount;
  }
  
}