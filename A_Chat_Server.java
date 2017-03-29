import java.util.ArrayList; 
import java.util.Scanner;
import javax.swing.JOptionPane;  
import java.net.*;
import java.io.*;

/**
 *
 * @author harish
 */
public class A_Chat_Server
{
public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
public static ArrayList<String> currentusers = new ArrayList<String>();
//---------------------------------------------------------------

public static void main(String args[]) throws Exception,ConnectException
{
    try
    {
        final int port=6500;
        ServerSocket SERVER = new  ServerSocket(port);
         System.out.println("waiting for clients");
         while(true)
         {
             Socket SOCK = SERVER.accept();
             ConnectionArray.add(SOCK);
             System.out.println("client connected from "+SOCK.getLocalAddress().getHostName());
             AddUserName(SOCK);
             
             A_Chat_Server_Return CHAT = new A_Chat_Server_Return(SOCK);
             Thread X = new Thread(CHAT);
             X.start();
         }
         
    }
    catch(Exception X)
    {
        System.out.print("X");
    }
      
}
//-------------------------------------------
public static void AddUserName(Socket X) throws IOException

{
    Scanner  INPUT = new Scanner(X.getInputStream());
    String UserName = INPUT.nextLine();
    currentusers.add(UserName);
    
      for(int i = 1;i<=A_Chat_Server.ConnectionArray.size();i++)
      {
               Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
               PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
               OUT.println("#?!" + currentusers);
               OUT.flush();
           }
            
           
   
         }
    
}
//------------------------------------------