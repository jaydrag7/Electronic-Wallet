package SEFTP;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

 /**
  * This class tests the methods of the EWallet class.
  */
public class TestEWallet {
    private static String str;
    private static Scanner scan;
    private static EWallet wallet;

    /**
     * This method returns a String menu.
     * @return a String menu.
     */
    private static String menu(){
        String str;
        str="1. Display wallet information."+"\n";
        str+="2. Send Funds to another wallet."+"\n";
        str+="3. Process a message from another wallet."+"\n";
        str+="4. Quit.";
        return str;
    }
    /**
     * This method accepts a EWallet object and a mode, then returns a string based on the mode given.
     * @param wallet the EWallet object.
     * @param option the mode of the wallet.
     * @return a String based on the mode given.
     */
    private static String forOption(EWallet wallet,int option){
        try{
            if(option==1){
                str = wallet.getInfo()+"\n";
                str+=wallet.getTransactions(0);
            }    
            
            else if(option==2){
                System.out.print("Please enter wallet id:");
                String id=scan.nextLine();
                System.out.print("Please enter amount:");
                String amt=scan.nextLine();
                double amount=Double.parseDouble(amt);
                str=wallet.sendFunds(id, amount);

            }
            else if(option==3){
                System.out.print("Please enter the transaction Id:");
                String transId=scan.nextLine();
                System.out.print("Please enter the subject of the message:");
                String subject=scan.nextLine();
                System.out.print("Please enter the token:");
                String token=scan.nextLine();
                System.out.print("Please enter the Id of the source wallet :");
                String sourceId=scan.nextLine();
                System.out.print("Please enter the Id of the destination wallet:");
                String destinationId=scan.nextLine();
                System.out.print("Please enter the transaction amount:");
                String trans_amount=scan.nextLine();
                String sub=subject.toUpperCase();
                String msg=transId+","+sub+","+token+","+sourceId+","+destinationId+","+trans_amount;
                str=wallet.respond(msg);
            }
            
        }
        catch(NumberFormatException e){
            System.out.println("An exception occurred:"+ e.getMessage());
            e.printStackTrace();
        }
        catch(Exception b){
            System.out.println("An exception occurred:"+ b.getMessage());
            b.printStackTrace();
        }        
        return str;
        
    }

    /**
     * This method accepts arguments that builds an EWallet object, which it then returns.
     * @param id the id of a wallet.
     * @param fn the first name of the person.
     * @param on the alias of a person.
     * @param ln the last name of a person.
     * @param dob the date of birth of a person.
     * @param gender the gender of a person.
     * @return a wallet.
     */
    private static EWallet forNew(String id,String fn,String on,String ln, SimpleDate dob,String gender){
        if(gender.equals("MALE")){
            wallet=new EWallet(id,fn,on,ln,dob,Sex.MALE);
        }
        else if(gender.equals("FEMALE")){
            wallet=new EWallet(id,fn,on,ln,dob,Sex.FEMALE);
        }
        return wallet;  
    }

    /**
     * This method accepts a mode, then runs in that particular mode given.
     * @param args the parameter/mode of the program.
     */
    public static void main(String[] args){
        if(args.length==1 && args[0].equals("demo")){
            try{
                wallet=new EWallet();
                new GUI(wallet);
                scan=new Scanner(System.in);
                Boolean test=true;
                while(test){
                    System.out.println(menu());
                    System.out.print("Select Option:");
                    String option =scan.nextLine();
                    int op=Integer.parseInt(option);
                    if (op!=4){
                        System.out.println(forOption(wallet,op));
                        
                    }
                    else{
                        System.out.print("Are you sure you want to quit:");
                        String response=scan.nextLine();
                        String res=response.toUpperCase();
                        if (res.equals("NO")){
                            test=true;

                        }
                        else if(res.equals("YES")){
                            wallet.saveToFile();
                            scan.close();
                            str="Goodbye";
                            System.out.println(str);
                            test=false;
                        }
                    }
                }
            }
            catch(NumberFormatException e){
                System.out.println("An exception occurred:"+e.getMessage());
                e.printStackTrace();
            }
            catch(Exception b){
                System.out.println("An exception occurred:"+b.getMessage());
                b.printStackTrace();
            }
        }
    
        else if(args.length==1 && args[0].equals("new")){
            try{
                scan=new Scanner(System.in);
                System.out.print("Please enter your wallet id: ");
                String id=scan.nextLine();
                System.out.print("Please enter your first name: ");
                String fn=scan.nextLine();
                System.out.print("Please enter your alias (optional): ");
                String on=scan.nextLine();
                System.out.print("Please enter your last name: ");
                String ln=scan.nextLine();
                System.out.print("Please enter your day of birth (dd): ");
                String day=scan.nextLine();
                System.out.print("Please enter your month of birth (mm): ");
                String month=scan.nextLine();
                System.out.print("Please enter your year of birth (yyyy): ");
                String year=scan.nextLine();
                System.out.print("Please enter your gender: ");
                String sex=scan.nextLine();
                String gender=sex.toUpperCase();
                int day_=Integer.parseInt(day);
                int month_=Integer.parseInt(month);
                int yr_=Integer.parseInt(year);
                SimpleDate dob=new SimpleDate(yr_,month_,day_);
                new GUI(forNew(id,fn,on,ln,dob,gender));
                Boolean test=true;
                while(test){
                    System.out.println(menu());
                    System.out.print("Select Option:");
                    String option =scan.nextLine();
                    int op=Integer.parseInt(option);
                    if (op!=4){
                        System.out.println(forOption(forNew(id,fn,on,ln,dob,gender),op)); 
                    }
                    else{
                        System.out.print("Are you sure you want to quit:");
                        String response=scan.nextLine();
                        String res=response.toUpperCase();
                        if (res.equals("NO")){
                            test=true;
        
                        }
                        else if(res.equals("YES")){
                            forNew(id,fn,on,ln,dob,gender).saveToFile();
                            scan.close();
                            str="Goodbye";
                            System.out.println(str);
                            test=false;
                        }
                    }
                }
            }
            catch(NumberFormatException e){
                System.out.println("An exception occurred:"+e.getMessage());
                e.printStackTrace();
            }
            catch(Exception b){
                System.out.println("An exception occurred:"+b.getMessage());
                b.printStackTrace();
            } 
        }
        else{
            try{
                wallet=new EWallet(args[0]);
                new GUI(wallet);
                scan=new Scanner(System.in);
                Boolean test=true;
                while(test){
                    System.out.println(menu());
                    System.out.print("Select Option:");
                    String option =scan.nextLine();
                    int op=Integer.parseInt(option);
                    if (op!=4){
                        System.out.println(forOption(wallet,op));
                        
                    }
                    else{
                        System.out.print("Are you sure you want to quit:");
                        String response=scan.nextLine();
                        String res=response.toUpperCase();
                        if (res.equals("NO")){
                            test=true;

                        }
                        else if(res.equals("YES")){
                            wallet.saveToFile();
                            scan.close();
                            str="Goodbye";
                            System.out.println(str);
                            test=false;
                        }
                    }


                }
            }
            catch(NumberFormatException e){
                System.out.println("An exception occurred:"+e.getMessage());
                e.printStackTrace();
            }
            catch(Exception b){
                System.out.println("An exception occurred:"+b.getMessage());
                b.printStackTrace();
            }  
        
        }
    }
}
/**
 * This class sets the frame and panel for the GUI.
 */

class GUI extends JFrame{
    private EWallet wallet;

    public GUI(EWallet w){
        super("UWI Electronic Wallet");
        wallet=w;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new Panel(wallet));

        pack();
        setVisible(true);
    }
    


/**
 * This class sets the relevant panels to be added to the frame of the GUI.
 */
class Panel extends JPanel{
    private JPanel Master,MPanel1,MPanel2,FPanel1,FPanel2,panelHead,FPanel1_1,FPanel1_2,FPanel1_3,FPanel2_1,FPanel2_2,FPanel2_3,FPanel2_4;
    private JLabel p1Label,p1Label2,p2Label1,p2Label2,p2Label3,p2Label4;
    private JTextField txt1,txt2,p1text1,p2text1,p2text2,p2text3,p2text4;
    private JButton send_button,respond_button,clear_button,quit_button;
    private JRadioButton all_button,committed_button,uncommitted_button,failed_button;
    private JTable table;
    private DefaultTableModel model;
    private EWallet wallet;
    /**
     * This constructor accepts an EWallet object and extracts the relevant data from the chosen wallet.
     * @param w represents the wallet object.
     */
    public Panel(EWallet w){
        this.wallet=w;
        panelHead=new JPanel(new GridBagLayout());
        panelHead.setBackground(new Color(51,204,255));
        panelHead.setPreferredSize(new Dimension(2000,80));
        JLabel label1=new JLabel("Owner:");
        txt1=new JTextField(15);
        String[] str=wallet.getInfo().split("Wallet:|\\Account Balance:|\\(|\\)");
        String[] str2=str[1].split(",|\\[|\\]|\\(|\\)");
        String[] fn=str2[0].split("\\s+");
        if(str2.length>=3){
            String name=fn[1]+","+str2[1]+"["+str2[2]+"]";
            txt1.setText(name); 
        }else{
            String name=fn[1]+","+str2[1]+"[]";
            txt1.setText(name);
        }
        

        JLabel label2=new JLabel("UWI Electronic Wallet Project");

        JLabel label3=new JLabel("Wallet Id:");
        txt2=new JTextField(15);
        txt2.setText(wallet.getWalletId());
        

        GridBagConstraints a=new GridBagConstraints();
        a.anchor = GridBagConstraints.FIRST_LINE_START;
        a.insets = new Insets(10, 10, 10, 10);
        a.weightx = 0;

        GridBagConstraints b=new GridBagConstraints();
        b.anchor = GridBagConstraints.FIRST_LINE_END;
        b.insets = new Insets(10, 10, 10, 10);
        b.weightx = 0;

        GridBagConstraints c= new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 10, 10, 10);
        c.weightx = 0;

        JPanel pan1=new JPanel();
        pan1.setBackground(new Color(51,204,255));
        pan1.add(label1);
        pan1.add(txt1);

        JPanel pan2=new JPanel();
        pan2.setBackground(new Color(51,204,255));
        pan2.add(label2);

        JPanel pan3=new JPanel();
        pan3.setBackground(new Color(51,204,255));
        pan3.add(label3);
        pan3.add(txt2);

        a.gridy=0;
        panelHead.add(pan1,a);
        c.gridy=0;
        panelHead.add(pan2,c);
        b.gridy=0;
        panelHead.add(pan3,b);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 0;
        
        FPanel1=new JPanel();
        FPanel1.setLayout(new GridBagLayout());
        FPanel1.setBackground(Color.white);
        FPanel1.setBorder(BorderFactory.createLineBorder(new Color(0,153,0),2));       


        p1Label=new JLabel("Balance:");
        p1text1=new JTextField(10);
        p1text1.setBorder(BorderFactory.createLineBorder(new Color(0,153,0),1));
        String[] balance=str[3].split("\\s+");
        p1text1.setText("$"+balance[3]);


        FPanel1_1=new JPanel();
        FPanel1_1.setBackground(Color.white);
        FPanel1_1.add(p1Label);
        FPanel1_1.add(p1text1);
       

        p1Label2=new JLabel("Transactions:");
        all_button=new JRadioButton("All");
        all_button.setBackground(Color.white);
        all_button.addActionListener(new ButtonListener());

        committed_button=new JRadioButton("Committed");
        committed_button.setBackground(Color.white);
        committed_button.addActionListener(new ButtonListener());

        uncommitted_button=new JRadioButton("Uncommitted");
        uncommitted_button.setBackground(Color.white);
        uncommitted_button.addActionListener(new ButtonListener());

        failed_button=new JRadioButton("Failed");
        failed_button.setBackground(Color.white);
        failed_button.addActionListener(new ButtonListener());

        ButtonGroup group=new ButtonGroup();
        group.add(all_button);
        group.add(committed_button);
        group.add(uncommitted_button);
        group.add(failed_button);

        FPanel1_2=new JPanel();
        FPanel1_2.setBackground(Color.white);
        FPanel1_2.add(p1Label2);
        FPanel1_2.add(all_button);
        FPanel1_2.add(committed_button);
        FPanel1_2.add(uncommitted_button);
        FPanel1_2.add(failed_button);

        model=new DefaultTableModel();
        model.addColumn("From");
        model.addColumn("To");
        model.addColumn("Amount");
        model.addColumn("Status");
        model.addColumn("ID");
        

        table=new JTable(model);
        

        JScrollPane pane=new JScrollPane(table);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        pane.setPreferredSize(new Dimension(500,80));

        FPanel1_3=new JPanel();
        
        FPanel1_3.add(pane);
        FPanel1_3.setPreferredSize(new Dimension(500,80));
        FPanel1_3.setBackground(Color.gray);
        

        gbc.gridy = 0;
        FPanel1.add(FPanel1_1,gbc);
        gbc.gridy = 1;
        FPanel1.add(FPanel1_2,gbc);  
        gbc.gridy = 2; 
        FPanel1.add(FPanel1_3,gbc);

        FPanel2=new JPanel();
        FPanel2.setBackground(Color.white);
        FPanel2.setLayout(new GridBagLayout());
        

        p2Label1=new JLabel("Send To:");
        p2text1=new JTextField(12);
        p2text1.setBorder(BorderFactory.createLineBorder(new Color(0,153,0),1));


        p2Label2=new JLabel("Amount:");
        p2text2=new JTextField(12);
        p2text2.setBorder(BorderFactory.createLineBorder(new Color(0,153,0),1));

        p2Label3=new JLabel("Incoming Message:");
        p2text3=new JTextField(50);
        p2text3.setBorder(BorderFactory.createLineBorder(new Color(0,153,0),1));

        p2Label4=new JLabel("Outgoing Message:");
        p2text4=new JTextField(50);
        p2text4.setBorder(BorderFactory.createLineBorder(new Color(0,153,0),1));

        send_button=new JButton("Send");
        send_button.setBorder(BorderFactory.createRaisedBevelBorder());
        send_button.setBackground(new Color(51,204,255));
        send_button.setPreferredSize(new Dimension(50, 20));
        send_button.addActionListener(new ButtonListener());

        respond_button=new JButton("Respond");
        respond_button.setBorder(BorderFactory.createRaisedBevelBorder());
        respond_button.setBackground(new Color(51,204,255));
        respond_button.setPreferredSize(new Dimension(60, 20));
        respond_button.addActionListener(new ButtonListener());

        clear_button=new JButton("Clear");
        clear_button.setBorder(BorderFactory.createRaisedBevelBorder());
        clear_button.setBackground(new Color(51,204,255));
        clear_button.setPreferredSize(new Dimension(50, 20));
        clear_button.addActionListener(new ButtonListener());

        quit_button=new JButton("Quit");
        quit_button.setBorder(BorderFactory.createRaisedBevelBorder());
        quit_button.setBackground(new Color(51,204,255));
        quit_button.setPreferredSize(new Dimension(50, 20));
        quit_button.addActionListener(new ButtonListener());

        FPanel2_1=new JPanel();
        FPanel2_1.setBackground(Color.white);
        FPanel2_1.add(p2Label1);
        FPanel2_1.add(p2text1);
        FPanel2_1.add(p2Label2);
        FPanel2_1.add(p2text2);
        FPanel2_1.add(send_button);

        FPanel2_2=new JPanel();
        FPanel2_2.setBackground(Color.white);
        FPanel2_2.add(p2Label3);
        FPanel2_2.add(p2text3);
        FPanel2_2.add(respond_button);

        FPanel2_3=new JPanel();
        FPanel2_3.setBackground(Color.white);
        FPanel2_3.add(p2Label4);
        FPanel2_3.add(p2text4);

        FPanel2_4=new JPanel();
        FPanel2_4.setBackground(Color.white);
        FPanel2_4.add(clear_button);
        FPanel2_4.add(quit_button);

        GridBagConstraints constr= new GridBagConstraints();
        constr.anchor = GridBagConstraints.CENTER;
        constr.insets = new Insets(5, 10, 10, 10);
        constr.weightx = 0;

        constr.gridy = 0;
        FPanel2.add(FPanel2_1,constr);
        gbc.gridy = 1;
        FPanel2.add(FPanel2_2,gbc);
        gbc.gridy = 2;
        FPanel2.add(FPanel2_3,gbc);
        constr.gridy = 3;
        FPanel2.add(FPanel2_4,constr);

        Master=new JPanel();
        Master.add(panelHead);
        Master.setBackground(Color.white);
        

        MPanel1=new JPanel();
        MPanel1.setBackground(Color.white);

        MPanel2=new JPanel();
        MPanel2.setBackground(Color.white);
        MPanel1.add(FPanel1);
        MPanel2.add(FPanel2);
        
        add(Master);
        add(MPanel1);
        add(MPanel2);

        setLayout (new GridLayout(3,0));
        setBackground(Color.white);
        setPreferredSize(new Dimension(700,650));          

    }
      
    /**
     * This private class implements the ActionListener class.
     */
    private class ButtonListener implements ActionListener{
        private ArrayList<String[]> m=new ArrayList<String[]>();
        String from;
        String to;
        String id;
        String amt;
        String status;
        /**
         * This method accepts an ActionEvent object, gets the source where the object was created, then
         * executes the appropriate commands.
         * @param b represents an ActionEvent object when a button is selected.
         */
        public void actionPerformed(ActionEvent b){
            try{
                
                if(b.getSource().equals(clear_button)){
                    p2text1.setText("");
                    p2text2.setText("");
                    p2text3.setText("");
                    p2text4.setText("");
                }
                else if(b.getSource().equals(send_button)){
                    String destination=p2text1.getText();
                    Double amount=Double.parseDouble(p2text2.getText());
                    p2text4.setText(wallet.sendFunds(destination, amount));
                    String[] message=p2text4.getText().split(",");
                    m.add(message);
                    while (model.getRowCount() > 0){
                        model.removeRow(0);
                    }
                    for(String[] ms:m){
                    from=ms[3];
                     to=ms[4];
                     id=ms[0];
                     amt=ms[5];
                     status="PENDING";
                    model.insertRow(model.getRowCount(), new Object[]{from,to,amt,status,id});
                    }
                                  
                }
                
                else if(b.getSource().equals(respond_button)){
                    p2text4.setText(wallet.respond(p2text3.getText()));
                    String[] message=p2text3.getText().split(",");
                    while (model.getRowCount() > 0){
                        model.removeRow(0);
                    }
                    
                    
                    if(message[1].equals("THANKS")|| message[1].equals("WELCOME")){
                         from=message[3];
                         to=message[4];
                         id=message[0];
                         amt=message[5];
                         status="COMMITTED";
                         model.insertRow(model.getRowCount(), new Object[]{from,to,amt,status,id});
                    }
                    
                    else if(message[1].equals("FAILED")){
                         from=message[3];
                         to=message[4];
                         id=message[0];
                         amt=message[5];
                        
                         status="FAILED";
                         p2text4.setText("");
                        model.insertRow(model.getRowCount(), new Object[]{from,to,amt,status,id});
                    
                    }              
                     
                    
                }
                else if(b.getSource().equals(uncommitted_button)){
                    while (model.getRowCount() > 0){
                        model.removeRow(0);
                    }
                    String f=wallet.getTransactions(1).get(1);
                    String t=wallet.getTransactions(1).get(2);
                    String i=wallet.getTransactions(1).get(3);
                    String a=wallet.getTransactions(1).get(5);
                    String s="PENDING";
                    model.insertRow(model.getRowCount(), new String[]{f,t,a,s,i});
                    
                }
                
                else if(b.getSource().equals(committed_button)){
                    while (model.getRowCount() > 0) {
                        model.removeRow(0);
                    }
                   String f=wallet.getTransactions(2).get(1);
                   String t=wallet.getTransactions(2).get(2);
                   String i=wallet.getTransactions(2).get(3);
                   String a=wallet.getTransactions(2).get(5);
                   String s="COMMITTED";
                   model.insertRow(model.getRowCount(), new String[]{f,t,a,s,i}); 
                    
                        
                }
                else if (b.getSource().equals(all_button)){
                    int amt=5;
                    int from=1;
                    int to=2;
                    int id=3;
                    int status=4;
                    while((wallet.getTransactions(0).size()-amt)==2){
                        String f=wallet.getTransactions(0).get(from);
                        String t=wallet.getTransactions(0).get(to);
                        String i=wallet.getTransactions(0).get(id);
                        String s=wallet.getTransactions(0).get(status);
                        String a=wallet.getTransactions(0).get(amt);
                        model.insertRow(model.getRowCount(), new Object[]{f,t,a,s,i});
                        from+=7;
                        to+=7;
                        amt+=7;
                        status+=7;
                        id+=7;

                    }
                }
                else if (b.getSource().equals(failed_button)){
                    while (model.getRowCount() > 0) {
                        model.removeRow(0);
                    }
                    String[] message=p2text3.getText().split(",");
                    if(message[1].equals("FAILED")){
                        from=message[3];
                        to=message[4];
                        id=message[0];
                        amt=message[5];
                        status="FAILED";
                        model.insertRow(model.getRowCount(), new Object[]{from,to,amt,status,id});
                   }
                }
                else if(b.getSource().equals(quit_button)){
                    int answer=JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
                    "Quit", JOptionPane.YES_NO_OPTION);
                    if(answer==JOptionPane.YES_OPTION){
                        wallet.saveToFile();
                        JOptionPane.showMessageDialog(null, "Your information has been saved.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                }
            }
            catch(NumberFormatException e){
                System.out.println("An exception occurred:"+e.getMessage());
                e.printStackTrace();
            }
            catch(StringIndexOutOfBoundsException i){
                System.out.println("An exception occurred:"+i.getMessage());
                i.printStackTrace();
            }
  
                
        }
    }
}
}

