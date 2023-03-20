package SEFTP;
/**
 * This class contains methods for an EAccount object.
 */
public class EAccount{
  private double balance;

  /**
   * This constructor accepts a balance and initializes it to the instance variable.
   * @param balance amount accepted by constructor to be initialized.
   */
  public EAccount(double balance){
    this.balance = balance;
    
  }
  
  /**
   * This method accetps an amount and return true when the amount is added to the balance.
   * @param amount value to be added to the balance of the account.
   * @return true if the balance is added.
   */
  public boolean deposit(double amount){
    this.balance += amount;
    return (balance>0);
  }
  
  /**
   * This method accepts an amount, checks if the balance is equal to or greater than the amount given, and returns true if the amount is subtracted from the balance. Returns false otherwise.
   * @param amount value to be subtracted from the balance, if the available balance is greater than the amount.
   * @return true if balance is greater than the amount, false if the balance is less than amount.
   */
    public boolean withdraw(double amount){
      if( this.balance>= amount){
        this.balance-= amount;
        return true;
      }
      else{
        return false;
      }
    }

  /**
   * The method accesses the account balance and returns it.
   * @return the balance of the account.
   */
  public double getBalance(){
    return this.balance;
    
  }
  
}