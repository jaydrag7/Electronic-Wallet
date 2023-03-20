package SEFTP;
/**
 * This class extends Person and contains methods for Owner objects.
 */
public class Owner extends Person {
    
    private String id;
    private Name name;
    private static int count=0;//test
  
   /**
    * This constructor accepts arguments and initializes their value.
    * @param fn the first name of a person.
    * @param on the alias of a person.
    * @param ln the last name of a person.
    * @param dob the date of birth of a person represented as a SimpleDate object.
    * @param sex the sex of a person represented an enumeration of Sex.
    */
  public Owner(String fn, String on, String ln, SimpleDate dob, Sex sex) {
    super(dob,sex);
    this.name=new Name(fn,ln,on);
    count++;
    String id_= String.valueOf(count);
    this.id=id_;
    
    
}

  /**
   * This constructor accepts arguments and initializes their value.
   * @param id the id of a person.
   * @param fn the first name of a person.
   * @param on the alias of a person.
   * @param ln the last name of a person.
   * @param dob the date of birth of a person represented as a SimpleDate object.
   * @param sex the sex of a person represented an enumeration of Sex.
   */
  public Owner(String id,String fn, String on, String ln, SimpleDate dob, Sex sex){
  super(dob,sex);
  this.name=new Name(fn,ln,on);
  this.id = id;
}
 
  /**
   * This method accesses the id of a person and returns it in an eight digit format.
   * @return the  eight digit id of an owner.
   */
  public String getId(){
    if(id.length()==1){
      return "0000000"+id;
    }
    else if(id.length()==2){
      return "000000"+id;
    }
    else if(id.length()==3){
      return "00000"+id;
    }
    else if(id.length()==4){
      return "0000"+id;
    }
    else if(id.length()==5){
      return "000"+id;
    }
    else if(id.length()==6){
      return "00"+id;
    }
    else if(id.length()==7){
      return "0"+id;
    }
    return id;
}

  /**
   * This method formats the last name, first name, and alias (if any) of a person and returns it.
   * @return the name in the form: Last name, First Name [Other name if this exists].
   */
  public String getName(){
    String str;
    str=name.getLName()+",";
    str+=name.getFName();
    str+= "["+name.getOName()+"]";
    return str;
  }
  /**
   * This method accesses the date of birth of a person and returns it as a SimpleDate object.
   * @return the date of birth.
   */
  public SimpleDate getDOB(){
    return dob;
  }
  /**
   * This method accesses the gender of a person and returns it.
   * @return the sex of a person.
   */
  public Sex getSex(){
    return sex;
  }
}