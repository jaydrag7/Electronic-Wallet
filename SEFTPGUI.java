package SEFTP;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SEFTPGUI extends JFrame{
    private static EWallet wallet;

    public SEFTPGUI(EWallet w){
        super("UWI Electronic Wallet");
        wallet=w;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new Panel(wallet));
        pack();
        setVisible(true);

    }

    public static void main(String[] args){
        new SEFTPGUI(wallet);

    }

}

class Panel extends JPanel{
    private JPanel Master,MPanel1,MPanel2,FPanel1,FPanel2,panelHead,FPanel1_1,FPanel1_2,FPanel1_3,FPanel2_1,FPanel2_2,FPanel2_3,FPanel2_4;
    private JLabel p1Label,p1Label2,p2Label1,p2Label2,p2Label3,p2Label4;
    private JTextField txt1,txt2,p1text1,p2text1,p2text2,p2text3,p2text4;
    private JButton p2button1,p2button2,p2button3,p2button4;
    private JRadioButton rbutton1,rbutton2,rbutton3,rbutton4;
    private JTable table;
    private EWallet wallet;
    public Panel(EWallet w){
        this.wallet=w;
        panelHead=new JPanel(new GridBagLayout());
        panelHead.setBackground(new Color(51,204,255));
        panelHead.setPreferredSize(new Dimension(700,80));
        JLabel label1=new JLabel("Owner:");
        txt1=new JTextField(10);

        JLabel label2=new JLabel("UWI Electronic Wallet Project");

        JLabel label3=new JLabel("Wallet Id:");
        txt2=new JTextField(10);
        txt2.addActionListener(new TextListener());

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


        FPanel1_1=new JPanel();
        FPanel1_1.setBackground(Color.white);
        FPanel1_1.add(p1Label);
        FPanel1_1.add(p1text1);
       

        p1Label2=new JLabel("Transactions:");
        rbutton1=new JRadioButton("All");
        rbutton1.setBackground(Color.white);
        /*rbutton1.setBorder(BorderFactory.createLineBorder(new Color(0,153,0),1));
        rbutton1.setBorderPainted(true);*/
        rbutton2=new JRadioButton("Committed");
        rbutton2.setBackground(Color.white);
        rbutton3=new JRadioButton("Uncommitted");
        rbutton3.setBackground(Color.white);
        rbutton4=new JRadioButton("Failed");
        rbutton4.setBackground(Color.white);

        ButtonGroup group=new ButtonGroup();
        group.add(rbutton1);
        group.add(rbutton2);
        group.add(rbutton3);
        group.add(rbutton4);

        FPanel1_2=new JPanel();
        FPanel1_2.setBackground(Color.white);
        FPanel1_2.add(p1Label2);
        FPanel1_2.add(rbutton1);
        FPanel1_2.add(rbutton2);
        FPanel1_2.add(rbutton3);
        FPanel1_2.add(rbutton4);

        
        String[] table_columns = { "","","","","" };
        String[][] table_data = {
            { "From","To","Amount","Status","ID"},
            { "","","","",""},
            { "","","","",""},
            { "","","","",""},
        };
        table=new JTable(table_data,table_columns);
        table.setBounds(30, 40, 210, 400);
        //JScrollPane jsp = new JScrollPane(table);
        FPanel1_3=new JPanel();
        FPanel1_3.add(table);

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
        p2text3=new JTextField(25);
        p2text3.setBorder(BorderFactory.createLineBorder(new Color(0,153,0),1));

        p2Label4=new JLabel("Outgoing Message:");
        p2text4=new JTextField(25);
        p2text4.setBorder(BorderFactory.createLineBorder(new Color(0,153,0),1));

        p2button1=new JButton("Send");
        p2button1.setBorder(BorderFactory.createRaisedBevelBorder());
        p2button1.setBackground(new Color(51,204,255));
        p2button1.setPreferredSize(new Dimension(50, 20));

        p2button2=new JButton("Respond");
        p2button2.setBorder(BorderFactory.createRaisedBevelBorder());
        p2button2.setBackground(new Color(51,204,255));
        p2button2.setPreferredSize(new Dimension(60, 20));

        p2button3=new JButton("Clear");
        p2button3.setBorder(BorderFactory.createRaisedBevelBorder());
        p2button3.setBackground(new Color(51,204,255));
        p2button3.setPreferredSize(new Dimension(50, 20));
        p2button3.addActionListener(new ButtonListener());

        p2button4=new JButton("Quit");
        p2button4.setBorder(BorderFactory.createRaisedBevelBorder());
        p2button4.setBackground(new Color(51,204,255));
        p2button4.setPreferredSize(new Dimension(50, 20));

        FPanel2_1=new JPanel();
        FPanel2_1.setBackground(Color.white);
        FPanel2_1.add(p2Label1);
        FPanel2_1.add(p2text1);
        FPanel2_1.add(p2Label2);
        FPanel2_1.add(p2text2);
        FPanel2_1.add(p2button1);

        FPanel2_2=new JPanel();
        FPanel2_2.setBackground(Color.white);
        FPanel2_2.add(p2Label3);
        FPanel2_2.add(p2text3);
        FPanel2_2.add(p2button2);

        FPanel2_3=new JPanel();
        FPanel2_3.setBackground(Color.white);
        FPanel2_3.add(p2Label4);
        FPanel2_3.add(p2text4);

        FPanel2_4=new JPanel();
        FPanel2_4.setBackground(Color.white);
        FPanel2_4.add(p2button3);
        FPanel2_4.add(p2button4);

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
        //Master.setBorder(BorderFactory.createLineBorder(Color.red,3));

        MPanel1=new JPanel();
        MPanel1.setBackground(Color.white);

        MPanel2=new JPanel();
        MPanel2.setBackground(Color.white);
        //MPanel2.setBorder(BorderFactory.createLineBorder(Color.red));
        MPanel1.add(FPanel1);
        MPanel2.add(FPanel2);
        
        add(Master);
        add(MPanel1);
        add(MPanel2);

        setLayout(new GridLayout(3,1));
        setBackground(Color.white);
        setPreferredSize(new Dimension(700,650));          

    }
    private class TextListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            txt2.setText(wallet.getWalletId());
        }

    }  
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent b){
            if(b.getSource().equals(p2button3)){
                txt1.setText("");
                txt2.setText("");
                p1text1.setText("");
                p2text1.setText("");
                p2text2.setText("");
                p2text3.setText("");
                p2text4.setText("");
            }
        }
    }
}