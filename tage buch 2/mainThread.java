
import java.io.*;
import java.lang.*;
import java.net.*;
import java.nio.*;
import java.security.*;
import java.util.*;
public class mainThread extends terminatableThreat 
{
    //Dieses Attribut gibt den Basis-Ordner fuer den HTTP-Server an.
    private static final File wwwroot = new File("wwwroot");
    //Der Port, auf dem der HTTP-Server lauschen soll;
    private int port;
    //verwaltet alle threads die vom pogramm gestartet werden 
    ArrayList<terminatableThreat> ThLst;
    //verwaltet sockets 
    ArrayList<Socket> SLst;
    //threat der knopfdruck abbruch ermöglicht
    terminatableThreat nsdt;
    //socket der zum lauschen benutzt wird
    ServerSocket ssocket;
    boolean terminate;
  
   mainThread(int port) 
    {
        System.out.println("THREAD!ö");
        this.port = port;
        ThLst=new ArrayList<terminatableThreat>(0);
        SLst=new ArrayList<Socket>(0);
        nsdt = new notShutDownThread();
        terminate =false;
    }
  
    public void run()
    {
        try
        { 
            //throw new RuntimeException("Not yet implemented!");
            ssocket = new ServerSocket();
            ThLst.add(nsdt);
            nsdt.start();
            ssocket.bind( new InetSocketAddress( port ) );
            System.out.println("hauptschleife!");
            while(!nsdt.isterminated())
            {
                //warten
                //BufferedReader in = new BufferedReader( new InputStreamReader( mySocket.getInputStream()));
                 System.out.println("accept");
                Socket s= ssocket.accept();//blockierend
                System.out.println("accepted");
                inputThread ipt= new inputThread(s);
                SLst.add(s);
                ThLst.add(ipt);
                ipt.start();
                 System.out.println("3ter gestartet");
            }
            /*alle beteiligtten processe werden herunter gefahren da nsdt nicht mehr läuft
            * sprich das serverprogramm hatt per druck der esc taste die aiufforderung bekommen sich zuschliessen*/
            Object lock1 = new Object();
            synchronized(lock1)
            {
             progShutDown(true);
             this.terminate();
            }
    	}
        catch(IOException ex)
        {
            Object lock1 = new Object();
                synchronized(lock1)
                {
            progShutDown(false);
            this.terminate();
        }
        }
      
    }
    public void progShutDown(boolean b)
    {
        if(b=true)
        {
            try
            {
                ssocket.close();
            System.out.println("Der server wird aufgrund der betätigung der e teste herutegefahren");
            //sicherheits herunterfahren aller beteiligter threads
            terminateEveryThread();
    	    System.out.println("alle Laufenden threads wuren beendet");
    	    //sicherheits schliessen jedes beteiligten sockets sodas
    	    closeEverySocket();
    	    System.out.println("Alle sockets wurden geschlossen");
    	    //kommuniziert das programm beendet ist!
    	    System.out.println("Der server wurde aufgrund des betätigens der esc taste erfolgreich heruntergefahren");
    	    //ende
    	    System.out.println("Das pogramm ist nun beendet");
    	   }catch(IOException ex)
        {
            System.out.println("ssocketkonnte nicht geschlossen werden");
            progShutDown(false);
        }
        }
        else
        {
            System.out.println("Der server wird aufgrund eines fehlers heruntergefahren");
            //sicherheits herunterfahren aller beteiligter threads
    	    terminateEveryThread();
    	    //sicherheits schliessen jedes beteiligten sockets sodas
    	    closeEverySocket();
    	    //kommuniziert das programm beendet ist!
    	    System.out.println("Der server wurde erfolgreich aufgrund eines Fehlers heruntergefahren");
        }
    }
    
     public void terminateEveryThread()
    {
        if(!ThLst.isEmpty())
        {
           
            for(int n=0;n<ThLst.size();n++)
            {
                terminatableThreat t=ThLst.get(n);
                t.terminate();
            }
        }
    }
    public void closeEverySocket()
    {
        if(!SLst.isEmpty())
        {
             for(int n=0;n<SLst.size();n++)
            {
                Socket s=SLst.get(n);
                if(!s.isClosed())
                {
                    try
                    {
                        s.close();
                    }
                    catch(IOException ex)
                    {
                        System.out.println("socket konnte nicht geschlossen werden!");
                    }
                }
            }
        }
    }
    //am ende löschen nicht wictig fürs pogramm
    public void codeschschnipsel()
    {
        //codeschnipsel
    	 //mySocket.close();
    }
}
