
import java.io.*;
import java.lang.*;
import java.net.*;
import java.nio.*;
import java.security.*;
import java.util.*;
public class terminatableThreat extends Thread 
{
   private boolean _terminate;
   terminatableThreat() 
    {
        _terminate = false; 
    }
    public void terminate()
    {
        _terminate = true; 
    }
    public boolean isterminated()
    {
        return _terminate;
    }
    //@Override 
    public void run()
    {
       terminate();
      
    }
}
