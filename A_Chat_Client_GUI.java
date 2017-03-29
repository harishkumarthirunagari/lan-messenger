/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author harish
 */

public class A_Chat_Client_GUI {
//global variables    
public static String fileName=null;
private static  A_Chat_Client ChatClient;
public static String  username ="anonymus";

//global variables for gui
public static JFrame mainwindow = new JFrame();
private static  JButton B_ABOUT = new JButton();
private  static JButton B_CONNECT = new JButton();
private  static JButton B_SEND = new JButton();
private  static JButton B_DISCONNECT = new JButton();
private  static JButton F_SEND = new JButton();
private static  JLabel L_Message = new JLabel("message: ");
public static JTextField TF_Message = new JTextField(20);
public static JTextArea TA_CONVERSATION = new JTextArea();
private static JLabel L_Conversation = new JLabel();
private  static JScrollPane SP_CONVERSATION = new JScrollPane();
private static JLabel L_ONLINE = new JLabel();
public static JList JL_ONLINE = new JList();
private  static JScrollPane SP_ONLINE= new JScrollPane();
private static JLabel L_LoggedInAs = new JLabel();
private static JLabel L_LoggedInBox = new JLabel();
private static JButton browse = new JButton();
//GLOBAL VARIALES FOR A POP UP WINDOW 

public static JFrame LogInWindow = new JFrame();
public static  JTextField  TF_UserNameBox = new JTextField(20);
private  static JButton B_ENTER = new JButton("Enter");
private static  JLabel L_EnterUserName = new JLabel("enter user name ");
private static  JPanel P_Login = new JPanel();


//----------------------------------------------------------------

public static void main(String args[])
{
 
     BuildMainWindow();
     Initialize();
}
    
//------------------------------------------------

 public static void Connect()
 {
     try
     {
        B_DISCONNECT.setBackground(new java.awt.Color(0,0,255));
           B_DISCONNECT.setForeground(new java.awt.Color(255,255,255));
          B_DISCONNECT.setText("DISCONNECT");
          mainwindow.getContentPane().add(B_DISCONNECT);
          B_DISCONNECT.setBounds(130,40,110,25);
          B_CONNECT.setVisible(false);
         final int port=6500;
         final String HOST="harish-PC";
         Socket SOCK = new Socket(HOST,port);
         System.out.println("you connected to:"+HOST);
         
         ChatClient = new  A_Chat_Client(SOCK);
         
         PrintWriter OUT = new PrintWriter(SOCK.getOutputStream());
         OUT.println(username);
         OUT.flush();
         
         Thread X = new Thread(ChatClient);
           X.start();
     }
     catch(IOException X)
     {         
         System.out.println(X);
         JOptionPane.showMessageDialog(null,"server not responding");
     System.exit(0);
     
     }
         
     }

     public static void Initialize()
     {
         B_SEND.setEnabled(false);
         B_DISCONNECT.setEnabled(false);
         B_CONNECT.setEnabled(true);     
         browse.setEnabled(false);
     }
 
     public static void BuildLogInWindow()
     {
       LogInWindow.setTitle("whats ur name");
       LogInWindow.setSize(400,100);
       LogInWindow.setLocation(250,200);
       LogInWindow.setResizable(false);
      JPanel   P_LogIn = new JPanel();
       P_LogIn.add(L_EnterUserName);
       P_LogIn.add(TF_UserNameBox);
       P_LogIn.add(B_ENTER);
       LogInWindow.add(P_LogIn);
       
       Login_Action();
       LogInWindow.setVisible(true);
       
     }

     
     public static void BuildMainWindow()
     {
        
       mainwindow.setTitle(username +"'s chats box");
       mainwindow.setSize(650,550);
       mainwindow.setLocation(220,180);
       mainwindow.setResizable(true);
        ConfigureMainWindow();
        MainWindow_Action();
         mainwindow.setVisible(true);
     }
//---------------------------------------------------
      public static void  ConfigureMainWindow()
      { 
          mainwindow.setBackground(new java.awt.Color(255,255,255));
          mainwindow.setSize(500,320);
          mainwindow.getContentPane().setLayout(null);
          
          B_SEND.setBackground(new java.awt.Color(0,0,255));
          B_SEND.setForeground(new java.awt.Color(255,255,255));
          B_SEND.setText("SEND");
          mainwindow.getContentPane().add(B_SEND);
          B_SEND.setBounds(250,40,81,25);
          
          /*B_DISCONNECT.setBackground(new java.awt.Color(0,0,255));
           B_DISCONNECT.setForeground(new java.awt.Color(255,255,255));
          B_DISCONNECT.setText("DISCONNECT");
          mainwindow.getContentPane().add(B_DISCONNECT);
          B_DISCONNECT.setBounds(130,40,110,25);*/
          
          browse.setBackground(new java.awt.Color(0,0,255));
           browse.setForeground(new java.awt.Color(255,255,255));
          browse.setText("BROWSE");
          mainwindow.getContentPane().add(browse);
          browse.setBounds(10,40,110,25);
         
          
          B_CONNECT.setBackground(new java.awt.Color(0,0,255));
          B_CONNECT.setForeground(new java.awt.Color(255,255,255));
          B_CONNECT.setText("CONNECT");
          B_CONNECT.setToolTipText("");
          mainwindow.getContentPane().add(B_CONNECT);
          B_CONNECT.setBounds(130,40,110,25);
      
         F_SEND.setBackground(new java.awt.Color(0,0,255));
           F_SEND.setForeground(new java.awt.Color(255,255,255));
          F_SEND.setText("F_SEND");
          mainwindow.getContentPane().add(F_SEND);
          F_SEND.setBounds(420,40,70,25);
       
     
      B_ABOUT.setBackground(new java.awt.Color(0,0,255));
           B_ABOUT.setForeground(new java.awt.Color(255,255,255));
          B_ABOUT.setText("ABOUT");
          mainwindow.getContentPane().add(B_ABOUT);
          B_ABOUT.setBounds(340,40,75,25);
      
      L_Message.setText("MESSAGE");
          mainwindow.getContentPane().add(L_Message);
          L_Message.setBounds(10,10,60,20);
      
        TF_Message.setForeground(new java.awt.Color(0,0,255));
          TF_Message.requestFocus();
          mainwindow.getContentPane().add(TF_Message);
          TF_Message.setBounds(70,4,260,30);
      
      L_Conversation.setHorizontalAlignment(SwingConstants.CENTER);
      L_Conversation.setText("CONVERSATION");
          mainwindow.getContentPane().add(L_Conversation);
          L_Conversation.setBounds(100,70,140,16);
      
     
          TA_CONVERSATION.setColumns(20);
          TA_CONVERSATION.setFont(new java.awt.Font("Tahoma",0,12));
          TA_CONVERSATION.setForeground(new java.awt.Color(0,0,255));
          TA_CONVERSATION.setLineWrap(true);
          TA_CONVERSATION.setRows(5);
          TA_CONVERSATION.setEditable(false);
          
          
          SP_CONVERSATION.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
          SP_CONVERSATION.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
          SP_CONVERSATION.setViewportView(TA_CONVERSATION);
          mainwindow.getContentPane().add(SP_CONVERSATION);
           SP_CONVERSATION.setBounds(10, 90, 330, 180); 
          
            L_ONLINE.setHorizontalAlignment(SwingConstants.CENTER);
          L_ONLINE.setText("Currently online");
           L_ONLINE.setToolTipText("");
           mainwindow.getContentPane().add(B_SEND);
          L_ONLINE.setBounds(350,70,130,16);
          
         JL_ONLINE.setForeground(new java.awt.Color(0,0,255));
         
          
        SP_ONLINE.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_ONLINE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
          SP_ONLINE.setViewportView(JL_ONLINE);
          mainwindow.getContentPane().add(SP_ONLINE);
           SP_ONLINE.setBounds(350, 90, 130, 180); 
          
       
          
      L_LoggedInAs.setFont(new java.awt.Font("tahoma",0,12));
      L_LoggedInAs.setText("currentyl used as");
      mainwindow.getContentPane().add(L_LoggedInAs);
      L_LoggedInAs.setBounds(348, 0, 140, 15);
      
      L_LoggedInBox.setHorizontalAlignment(SwingConstants.CENTER);
      L_LoggedInBox.setFont(new java.awt.Font("tahoma",0,12));
      L_LoggedInBox.setForeground(new java.awt.Color(255,0,0));
      L_LoggedInBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
      mainwindow.getContentPane().add(L_LoggedInBox);
      L_LoggedInBox.setBounds(340, 17, 150, 20);
      
      }
     //------ ---------------------------   ----------------------------------------
      
      public static void Login_Action()
      {
          B_ENTER.addActionListener(
                  new java.awt.event.ActionListener() 
          {

              public void actionPerformed(java.awt.event.ActionEvent evt)
              {
                  ACTION_B_ENTER();
              }
          }
          );
      }
     
      
      
//--------------------------------------------------------




public static void ACTION_B_ENTER()
{
    if(!TF_UserNameBox.getText().equals(""))
            {
                username = TF_UserNameBox.getText().trim();
                L_LoggedInBox.setText(username);
               // A_Chat_Server.currentusers.add(username);
                mainwindow.setTitle(username + "'s chat box");
                LogInWindow.setVisible(false);
                B_SEND.setEnabled(true);
                B_DISCONNECT.setEnabled(true);
                B_CONNECT.setEnabled(false);
                browse.setEnabled(true);
                Connect();
            }
    else
    { JOptionPane.showMessageDialog(null, "please enter ur name");
                
    }
    
}
//-----------------------------------------------
public static void MainWindow_Action()
{
    B_SEND.addActionListener(
    new java.awt.event.ActionListener()
    {
      
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            ACTION_B_SEND();
        }
    }
);
 //---------------------------------------------------
    browse.addActionListener(
    new java.awt.event.ActionListener()
    {
      
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
             Chooser frame = new Chooser();
             TF_Message.setText(fileName);
        
        }
    }
);

//--------------    ---------------------------
B_DISCONNECT.addActionListener(
    new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            ACTION_B_DISCONNECT();
        }
    }
);

B_CONNECT.addActionListener(
    new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            BuildLogInWindow();
        }
    }
);

F_SEND.addActionListener(
    new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            ACTION_B_HELP();
        }
    }
);

B_ABOUT.addActionListener(
    new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            ACTION_B_HELP();
        }
    }
);
} 

public static void ACTION_B_SEND()
{
    if(!TF_Message.getText().equals(""))
    {
        ChatClient.SEND(TF_Message.getText());
        TF_Message.requestFocus();
        B_SEND.requestFocus(Boolean.TRUE);
    }
if(fileName!=null)
{      
    
    ChatClient.SEND(fileName);
    
}

}   
 
public static void ACTION_B_DISCONNECT()
        
{
try
{    
    ChatClient.DISCONNECT();
}
catch(Exception y)
{
    y.printStackTrace();

}

}

public static void  ACTION_B_HELP()
{
    JOptionPane.showMessageDialog(null, "hello ");
}


public   static  class Chooser  {

JFileChooser chooser;


public Chooser() {
chooser = new JFileChooser();
int r = chooser.showOpenDialog(new JFrame());
if (r == JFileChooser.APPROVE_OPTION) {
fileName = chooser.getSelectedFile().getPath();
}
}
}

}

    


