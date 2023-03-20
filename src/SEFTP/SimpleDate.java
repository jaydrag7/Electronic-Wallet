package SEFTP;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * This class creates the embodiment of a date.
 */
public class SimpleDate extends GregorianCalendar{
  /**
   * This constructor accepts arguments and initializes their value.
   * @param year the year a person was born.
   * @param month the month a person was born.
   * @param day the day a person was born.
   */
  public SimpleDate(int year,int month,int day){
    super(year,month,day);
  }
  /**
   * This method sets the current date.
   */
  public SimpleDate(){
    super();
  }
  /**
   * This method formats the date (day/month/year) and returns it as a String.
   * @return the date in the format: day/month/year.
   */
  public String toString(){
    String str;
    str= String.valueOf(super.get(Calendar.DAY_OF_MONTH))+"/"+String.valueOf(super.get(Calendar.MONTH))+"/"+String.valueOf(super.get(Calendar.YEAR));
    return str;
  }

  
}