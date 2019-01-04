 

import java.io.*;
import java.lang.*;
import java.lang.annotation.*;
import java.lang.invoke.*;
import java.lang.ref.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.security.*;
import java.security.acl.*;
import java.security.cert.*;
import java.security.interfaces.*;
import java.security.spec.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.function.*;
import java.util.jar.*;
import java.util.regex.*;
import java.util.spi.*;
import java.util.stream.*;
import java.util.zip.*;

/**
 * Nutzen Sie diese Klasse um den HTTP Server zu implementieren. Sie duerfen
 * weitere Klassen erstellen, sowie Klassen aus den in der Aufgabe aufgelisteten
 * Paketen benutzen. Achten Sie darauf, Ihren Code zu dokumentieren und moegliche
 * Ausnahmen (Exceptions) sinnvoll zu behandeln.
 *
 * @author Florian Barczik,189337
 * @author 
 * @author
 */
public class HttpServer
{
    
    
    //Der Port, auf dem der HTTP-Server lauschen soll;
    private int port;
   

    /**
     * Dokumentation fuer Konstruktor:
     * 
     * 
     * @param port
     *            der Port auf dem der HTTP-Server lauschen soll
     * @param Th(read)l(i)st
     *            wird mit 0elemeten initialisiert da noch kein thread gestartet
     * @param S(ocket)L(i)st            
     *            wird mit 0elemeten initialisiert da noch kein socket geöffnet
     * @param n(ot)s(hut)d(own)t(hread)            
     *            nsdt wird als thread zum manuel abbbruch initialisssiert
     */
    public HttpServer(int port)
    {
        this.port = port;
       
     
    }
    
    /**
     * Beispiel Dokumentation fuer diese Methode:
     * Diese Methode oeffnet einen Port, auf dem der HTTP-Server lauscht.
     * Eingehende Verbindungen werden in einem eigenen Thread behandelt.
     */
    public void startServer()
    {
       mainThread m =new mainThread(port);
        System.out.println("mainthread wird gestartet ");
       m.start();
           try{
                 System.out.println("join has wird ausprobiert");
               m.join();
               System.out.println("join has functioned");
            }
        catch(InterruptedException ex)
        {
            System.out.println("join does not function");
        }
        
       
      
    }
}
