import java .net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author harish
 */
public class A_Chat_Client implements Runnable
    {
        
    public static Socket SOCK;
    Scanner INPUT;
    Scanner SEND =  new Scanner(System.in);
    PrintWriter OUT;
    
    
    
    public A_Chat_Client(Socket x)
    {
        this.SOCK = x;
    }
    //--------------------------------------
    public void run()   
    {
        try
        {
            try
            {
                INPUT = new Scanner(SOCK.getInputStream());
                OUT = new PrintWriter(SOCK.getOutputStream());
                OUT.flush();
                CheckStream();
            }
            finally
                    {
                        SOCK.close();
                    }
            }
        
        
       catch(IOException x)
            {
                    System.out.print("x");
             }
        }
      //-------------------------------------------------------  
        
       public void DISCONNECT() throws IOException
        {
            OUT.println(A_Chat_Client_GUI.username +"has discoonected");
            OUT.flush();
            SOCK.close();
            JOptionPane.showInputDialog(null,"you discoonected");
            System.exit(0);
        } 
  //------------------------------------------------------------      
        public void CheckStream()
        {
           while(true)
            {
                RECEIVE();
            }
}    
//----------------------------------------------------------            
            public void RECEIVE()
            {
                if(INPUT.hasNext())
                {
                    String MESSAGE=INPUT.nextLine();
                    
                    if(MESSAGE.contains("#?!"))
                    {
                        String temp1=MESSAGE.substring(3);
                        temp1=temp1.replace("[","");
                        temp1=temp1.replace("]","");
                        
                        String[] currentusers = temp1.split(",");
                       A_Chat_Client_GUI.JL_ONLINE.setListData(currentusers);
                    }
                    else{
                        A_Chat_Client_GUI.TA_CONVERSATION.append(MESSAGE + "\n");
                    }
                    
                    
                    
                    }
            }
        

 //----------------------------------------
public  void SEND(String x)
{
OUT.println(A_Chat_Client_GUI.username + ":" +x);
OUT.flush();
A_Chat_Client_GUI.TF_Message.setText("");
}
public void SEND(int n)
{
    OUT.println("size" +n);

OUT.flush();
A_Chat_Client_GUI.TF_Message.setText("");
}
} 
//--------------------------------------------------------

