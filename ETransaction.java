package SEFTP;
/**
 * This class implements EAudit and provides methods for an ETransaction object.
 */
public class ETransaction implements EAudit{
  /**
   * This is the status of a transaction (PENDING, COMMITTED, FAILED).
   */
  protected EStatus status;
  /**
   * This is the source wallet id of a transaction.
   */
  protected String from;
  /**
   * This is the destination wallet id of a transaction.
   */
  protected String to_;
  private EToken token;
  private String transid;
  private SimpleDate date;

  /**
   * This constructor accepts arguments and initializes their values.
   * @param token the unique token for a transaction represented as an EToken object.
   * @param status the status of a transaction represented as an enumeration.
   * @param date the date of a transaction represented as a SimpleDate object.
   */
  public ETransaction(EToken token, EStatus status,SimpleDate date){
    this.token=token;
    this.status=status;
    this.from=token.getValue().substring(0,8);
    this.to_=token.getValue().substring(8,16);
    this.transid=token.getValue().substring(16,24);
    this.date=date;
  }

  /**
   * This method accesses the unique transaction Id of a wallet and returns it.
   * @return the id of a transaction.
   */
  public String getTranid(){
    return this.transid;
  }
  /**
   * This method accesses the date of a transaction and returns it as a SimpleDate object.
   *@return the date of a transaction.
   */
  public SimpleDate getDate(){
    return this.date;
  }

  /**
   * This method accesses an EToken object and returns it.
   * @return the token of a transaction.
   */
  public EToken getToken(){
    return token;
  }

  /**
   * This method accesses the status of a transaction and returns it.
   * @return the status of a transaction.
   */
  public EStatus getStatus(){
    return this.status;
  }
  /**
   * This method sets a status for a transaction.
   * @param status the new/updated status for a transaction.
   */
  public void setStatus(EStatus status){
    this.status=status;
  }
  /**
   * This method accesses the source wallet id of a transaction and returns it.
   * @return the id of the wallet that starts the transaction.
   */
  public String getFrom(){
    return this.from;
  }
  /**
   * This method accesses the destination wallet id of a transaction and returns it.
   * @return the id of the wallet that receives a transaction.
   */
  public String getTo(){
    return this.to_;
  }

}