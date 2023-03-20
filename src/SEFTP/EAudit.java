package SEFTP;
/**
 * Interface that specifies the methods for an ETransaction object.
 */
public  interface EAudit{
  /**
   * This method accesses the unique transaction Id of a wallet and returns it.
   * @return the Transaction Id.
   */ 
  public String getTranid();

  /**
   * This method accesses the date of a transaction and returns it as a SimpleDate object.
   * @return the date of the transaction as a SimpleDate object.
   */
  public SimpleDate getDate();

  
}