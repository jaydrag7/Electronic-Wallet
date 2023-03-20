package SEFTP;
/**
 * This class contains methods for Name objects.
 */
public class Name {
 
  private String firstName;
  private String lastName;
  private String otherName;


 /**
  * This constructor accepts arguments and initializes their value.
  * @param fn the first name of the person.
  * @param ln the last name of the person.
  * @param on the alias of the person.
  */
 public Name(String fn, String ln,String on) {
  this.firstName = fn;
  this.lastName=ln;
  this.otherName=on;
 }
 /**
  * This method accesses the first name of a person and returns it.
  * @return the first name of a person.
  */
 public String getFName(){
    return firstName;
 }
  /**
   * This method accesses the last name of a person and returns it.
   * @return the last name of a person.
   */
  public String getLName(){
    return lastName;
  }
  /**
   * This method accesses the alias of a person and returns it.
   * @return the alias of a person.
   */
  public String getOName(){
    return otherName;
  }
}