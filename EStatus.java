package SEFTP;
/**
 * This class is an enumeration that contains the status of a transaction.
 */
public enum EStatus{
  /**
   * This is the status if a transaction has been completely acheived.
   */
  COMMITTED,
  /**
   * This is the status if a transaction is being processed.
   */
  PENDING,
  /**
   * This is the status if a transaction has not been acheived.
   */
  FAILED;
}