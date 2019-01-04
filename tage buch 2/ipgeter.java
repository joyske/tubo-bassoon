import java.io.*;
import java.lang.*;
import java.net.*;
import java.nio.*;
import java.security.*;
import java.util.*;
/**
 * Write a description of class ipgeter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ipgeter
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class ipgeter
     */
    public ipgeter()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public InetAddress ip()
    {
        // put your code here
        try{
           return InetAddress.getLocalHost();
        }
        catch(UnknownHostException uhe)
        {
            return null;
        }
    }
    
    public void printip()
    {
        System.out.println(ip());
    }
}
