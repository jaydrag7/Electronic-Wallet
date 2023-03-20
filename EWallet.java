package SEFTP;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 * This class contains methods for EWallet objects.
 */
public class EWallet{
  private String walletId;
  private EAccount pocket;
  Owner owner;
  private ArrayList<ETransaction> transactions= new ArrayList<ETransaction>();
  private static int idsar= 0;
  private String transactionId;
  private ArrayList<String>transacs=new ArrayList<String>();
  private String st;
  
 

  /**
   * Default constructor that initializes the attributes.
   */
  public EWallet(){
    this.walletId="DEMO0000";
    SimpleDate date=new SimpleDate();
    this.owner=new Owner("John","", "Doe",date,Sex.MALE);
    this.pocket=new EAccount(250.00);
    this.transactionId="00001000";
  }

  /**
   * This constructor takes a wallet id, finds the appropriate file, and initializes the attributes.
   * @param id the id that corresponds with a text file.
   */
  public EWallet(String id){
    try{
      if(new File(id+".ewt").exists()){
        String line="";
        BufferedReader buff=new BufferedReader(new FileReader(id+".ewt"));
        line=buff.readLine();
        while( line!= null){
          String[] lst=line.split(",|\\[|\\],");
          this.walletId=lst[0];
          String[] date=lst[4].split("/");
          int day=Integer.parseInt(date[0]);
          int month=Integer.parseInt(date[1]);
          int year=Integer.parseInt(date[2]);
          SimpleDate dob=new SimpleDate(year, month, day);
          Sex sex=Sex.valueOf(lst[5]);
          this.owner=new Owner(lst[2], lst[3], lst[1], dob, sex);
          this.transactionId=lst[6];
          double amount=Double.parseDouble(lst[7]);
          this.pocket=new EAccount(amount);
          break;
        }
        buff.close();
  
      }
      else{
        System.out.println("File does not exist.");
      }
    }
    catch(IOException e){
      System.out.println("An exception occurred:"+ e.getMessage());
      e.printStackTrace();
    }
    catch(Exception b){
      System.out.println("An exception occurred:"+ b.getMessage());
      b.printStackTrace();
    }

  }

  /**
   * This constructor takes arguments representing the owner, account, wallet id, and initializes them.
   * @param id the id of the wallet.
   * @param fn the first name of a person.
   * @param on the alias of a person.
   * @param ln the last name of the person.
   * @param dob the date of birth of a person represented as a SimpleDate object.
   * @param sex the sex of a person represented an enumeration of Sex.
   */
  public EWallet(String id,String fn,String on,String ln,SimpleDate dob ,Sex sex){
    this.walletId=id;
    this.owner=new Owner(fn,on,ln,dob,sex);
    this.pocket=new EAccount(0.00);
    idsar++;
    if(String.valueOf(idsar).length()==1){
      String trans ="0000000"+ String.valueOf(idsar);
      this.transactionId = trans;
    }
    else if(String.valueOf(idsar).length()==2){
      String trans ="000000"+ String.valueOf(idsar);
      this.transactionId = trans;
    }
    else if(String.valueOf(idsar).length()==3){
      String trans ="00000"+ String.valueOf(idsar);
      this.transactionId = trans;
    }
    else if(String.valueOf(idsar).length()==4){
      String trans ="0000"+ String.valueOf(idsar);
      this.transactionId = trans;
    }
    else if(String.valueOf(idsar).length()==5){
      String trans ="000"+ String.valueOf(idsar);
      this.transactionId = trans;
    }
    else if(String.valueOf(idsar).length()==6){
      String trans ="00"+ String.valueOf(idsar);
      this.transactionId = trans;
    }
    else if(String.valueOf(idsar).length()==7){
      String trans ="0"+ String.valueOf(idsar);
      this.transactionId = trans;
    }
    else{
      String trans =String.valueOf(idsar);
      this.transactionId = trans;
    }
  }

  /**
   * This methods saves wallet data to a text file.
   */
  public void saveToFile(){
    try{
      File fl=new File(this.walletId+".ewt");
      if(fl.createNewFile()){
        PrintWriter w=new PrintWriter(new FileOutputStream(this.walletId+".ewt"));
        String data=walletId+","+owner.getName()+","+owner.getDOB()+","+owner.getSex()+","+transactionId+","+pocket.getBalance();
        w.println(data);
        w.close();
        System.out.println("File successfully created:"+fl.getName());
      }
      else{
        PrintWriter w=new PrintWriter(new FileOutputStream(this.walletId+".ewt"));
        String data=walletId+","+owner.getName()+","+owner.getDOB()+","+owner.getSex()+","+transactionId+","+pocket.getBalance();
        w.println(data);
        w.close();
        System.out.println("The wallet data has been saved");
      }
    }
      catch(IOException e){
        System.out.println("An exception occured:"+e.getMessage());
        e.printStackTrace();
      }

  }

  /**
   * This method accesses the wallet id and returns it.
   * @return the id of a wallet.
   */
  public String getWalletId(){
    return walletId;
  }

  /**
   * This method returns the information about a wallet.
   * @return the information about a wallet.
   */
  public String getInfo(){
    String str;
    int count=0;
    str="Wallet:"+getWalletId()+"\t";
    str+=owner.getName()+"("+owner.getSex()+")"+"\n";
    str+="Account Balance:"+"\t"; 
    str+=pocket.getBalance()+"\n";
    for(ETransaction t:transactions){
      if(t.getStatus()==EStatus.FAILED){
        count++;
      }
    }
    str+="Failed Transfers:"+"\t";
    str+=String.valueOf(count);
    return str;
  }

  /**
   * This method accepts a wallet id, an amount to be sent, and returns an appropriate "SEND" message given the balance is sufficient. Otherwise returns an empty string.
   * @param wallet the id of a wallet.
   * @param amt the amount to be transferred.
   * @return a message to the receiving wallet.
   */
  public  String sendFunds(String wallet,double amt){
    if(this.pocket.withdraw(amt)==true){
      SimpleDate date=new SimpleDate();
      EToken token=new EToken(this.walletId+wallet+this.transactionId);
      ETransaction transfer=new Transfer(token,EStatus.PENDING,date,amt);
      transactions.add(transfer);
      String str=transactionId+","+"SEND"+","+token.getValue()+","+walletId+","+wallet+","+String.valueOf(amt);
      return str;
      
    }
    else{
      String str="";
      return str;
    }
  }
  
  /**
   * This method accesses the unique transaction id of a wallet and returns it.
   * @return the id of a transaction.
   */
  public String gettransid(){
    return transactionId;
  }

  /**
   * This method accepts a mode, then iterates through the ETransaction list and returns an ArrayList of type String.
   * @param mode the type of transactions list.
   * @return a list a transactions represented as a String.
   */  
  public ArrayList<String> getTransactions(int mode){
    if(mode==1){
      for(ETransaction t: transactions){
        if(t.getStatus().equals(EStatus.PENDING)){
          transacs.add(t.getToken().getValue());
          transacs.add( t.getFrom());
          transacs.add(t.getTo());
          transacs.add(t.getTranid());
          transacs.add(String.valueOf(t.getStatus()));
          transacs.add(String.valueOf(((Transfer)t).getAmount()));
          transacs.add(String.valueOf(t.getDate()));
            
        }
      }
    }        
    else if(mode==2){
      for(ETransaction t: transactions){
        if(t.getStatus().equals(EStatus.COMMITTED)){
          transacs.add(t.getToken().getValue());
          transacs.add( t.getFrom());
          transacs.add(t.getTo());
          transacs.add(t.getTranid());
          transacs.add(String.valueOf(t.getStatus()));
          transacs.add(String.valueOf(((Transfer)t).getAmount()));
          transacs.add(String.valueOf(t.getDate()));
            
        }
      }
    }
    else if(mode==0){
      for(ETransaction t: transactions){
        transacs.add(t.getToken().getValue());
        transacs.add( t.getFrom());
        transacs.add(t.getTo());
        transacs.add(t.getTranid());
        transacs.add(String.valueOf(t.getStatus()));
        transacs.add(String.valueOf(((Transfer)t).getAmount()));
        transacs.add(String.valueOf(t.getDate()));
          
      }
    }
    return transacs;
    
  }

  /**
   * This method accepts a message as a String, iterates through the ETransaction list and returns an appropriate message depending on the subject of the original message.
   * @param imsg the message sent by a wallet.
   * @return the response.
   */
  public String respond(String imsg){
    String [] lst=imsg.split(",");
    for(ETransaction t: transactions){
      if((t.getStatus().equals(EStatus.PENDING) && t.getToken().getValue().equals(lst[2])) && lst[1].equals("SEND")){
        SimpleDate date=new SimpleDate();
        double at= Double.parseDouble(lst[5]);
        EToken token=new EToken(lst[2]);
        ETransaction obj=new Transfer(token,EStatus.PENDING,date,at);
        transactions.add(obj);
        st=transactionId+","+"THANKS"+","+lst[2]+","+lst[3]+","+lst[4]+","+lst[5];
       break;
      }
      else if(t.getStatus().equals(EStatus.PENDING) && t.getToken().getValue().equals(lst[2]) && lst[1].equals("THANKS")){
        double at= Double.parseDouble(lst[5]);
        pocket.withdraw(at);
        t.setStatus(EStatus.COMMITTED);
        st=transactionId+","+"WELCOME"+","+lst[2]+","+lst[3]+","+lst[4]+","+lst[5];
        break;
      }
      else if(t.getStatus().equals(EStatus.PENDING) && t.getToken().getValue().equals(lst[2]) && (lst[1].equals("WELCOME"))){
        double at= Double.parseDouble(lst[5]);
        pocket.deposit(at);
        t.setStatus(EStatus.COMMITTED);
        st="";  
        break;   
       }
      else if(t.getStatus().equals(EStatus.PENDING) && t.getToken().getValue().equals(lst[2]) && lst[1].equals("FAILED")){
        t.setStatus(EStatus.FAILED);
        st="";
        break;
      }
    }if(st==null){
      st=transactionId+","+"FAILED"+","+lst[2]+","+lst[3]+","+lst[4]+","+lst[5];
      return st;
    }
    else{
    return st;
    }
    
    
     
  }


}
  
