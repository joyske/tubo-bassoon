import java.io.*;
import java.lang.*;
import java.net.*;
import java.nio.*;
import java.security.*;
import java.util.*;
public class notShutDownThread extends terminatableThreat
{
    public void run()
    {
        
       System.out.println("notshutdown");
        BufferedReader r = new BufferedReader( new InputStreamReader( System.in ) );
         System.out.println("buffer initialisiert");
        while(!isterminated())
        {
           
            
            Object lock1 = new Object();
            try
            {  System.out.println("try");
                synchronized(lock1)
                {
                     System.out.println("synchro");
                     String st=r.readLine();
                      System.out.println("st ist "+st);
                    if(st.equals("e"))
                    {
                         System.out.println("terminate");
                        terminate();
                         System.out.println("terminated");
                    }
                }
            }catch(IOException ex)
            {
                System.out.println("problem in nsdt 1");
                terminate();
            }
                try
                {
                    Thread.sleep(200);
                }
                catch(InterruptedException ex)
            {
                 System.out.println("problem in nsdt 1");
                terminate();
            }
            //
            
           
        }
    }
}
