package SEFTP;
/**
 * This abstract class contains methods to be utilized in the Owner class.
 */
public abstract class Person{
  /**
   * The date of birth of a person.
   */
  protected SimpleDate dob;
  /**
   * The gender of a person.
   */
  protected Sex sex;

  /**
   * This constructor accepts arguments and initializes their value.
   * @param dob the date of birth of a person represented as a SimpleDate object.
   * @param sex the sex of a person represented as an enumeration of Sex.
   */
  public  Person(SimpleDate dob,Sex sex){
    this.dob=dob;
    this.sex=sex;
  }

  /**
   * This method accesses the date of birth of a person and returns it as a SimpleDate object.
   * @return the date of birth.
   */
  public abstract SimpleDate getDOB();

  /**
   * This method accesses the gender of a person and returns it.
   * @return the sex of a person.
   */
  public abstract Sex getSex();
  
}


