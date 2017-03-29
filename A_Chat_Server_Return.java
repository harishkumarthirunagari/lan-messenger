import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author harish
 */

        public class A_Chat_Server_Return implements Runnable 
{
   Socket SOCK;
   private Scanner INPUT;
   private PrintWriter OUT;
   String MESSAGE = "";
   //------------------------------------
public static BufferedOutputStream bos;
       public A_Chat_Server_Return(Socket x)
   {
       this.SOCK = X;
   }
   public void CheckConnection() throws IOException
   {
       if(!SOCK.isConnected())
       {
           for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)
           {
               if(A_Chat_Server.ConnectionArray.get(i) == SOCK)
               {
                   A_Chat_Server.ConnectionArray.remove(i);
               }
           }
           for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)
           {
              Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
               PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
               TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName() +"disconected");
               
               TEMP_OUT.flush();
          
           }
           
           
           }
       }
//------------------------------------------------------------
           
public void run()
{
    try
    {
        try
        {
            INPUT = new Scanner(SOCK.getInputStream());
            OUT = new PrintWriter(SOCK.getOutputStream());
             
            while (true)
            {
                CheckConnection();
                if(!INPUT.hasNext())
          
                {
                    return;
                }//close of if
            
         
                  
              MESSAGE = INPUT.nextLine();                 
            System.out.println("client said: " +MESSAGE);
            
            for(int i = 1;i<=A_Chat_Server.ConnectionArray.size();i++)
            {
              Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
               PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
               TEMP_OUT.println(MESSAGE);   
              System.out.println("sent to"+TEMP_SOCK.getLocalAddress().getHostName());               
                TEMP_OUT.flush();
            }//close of for
              
            
            
            }     
        } catch (IOException ex) {
            Logger.getLogger(A_Chat_Server_Return.class.getName()).log(Level.SEVERE, null, ex);
        }//close for while 
        } finally
        {
        try {
            SOCK.close();
        } catch (IOException ex) {
            Logger.getLogger(A_Chat_Server_Return.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
}
}
        }
