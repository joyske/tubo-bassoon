import java.io.*;
import java.lang.*;
import java.net.*;
import java.nio.*;
import java.security.*;
import java.util.*;

public class inputThread extends terminatableThreat
{
    
    private boolean _terminate;
    private Socket c;
    inputThread(Socket c) 
    {
        System.out.println("THREAD");
        _terminate = false;
        this.c=c;
    }
    public void run()
    {
        // put your code here
        System.out.println("input thread");
        try
        {
             PrintWriter out = new PrintWriter( c.getOutputStream(), true );
                System.out.println("printwriter initialisiert");
                System.out.println("zeile wurde eingelesen");
               Object lock1 = new Object();
              
            while(!isterminated())
            {
               BufferedReader in = new BufferedReader( new InputStreamReader( c.getInputStream()));
               System.out.println("bufferedReader initialisiert");
               String command=in.readLine();
               
                synchronized(lock1)
                {
                   
                    System.out.println("anfrage wird überprüft");
                    validate1(command,out);
                }
                
            }
        }
        catch(IOException ex)
        {
            System.out.println("writer funktioniert nicht");
        }
    }
    public void validate1(String s,PrintWriter out)
    {
        System.out.println(s);
            String[]sa=s.split("/");
        printArr(sa);
        if(sa.length==3||sa.length==4)
        {
            if(sa[0].equals("GET ")||sa[0].equals("POST "))
            {
                if(sa[2].equals("1.1")||sa[2].equals("1.0"))//(sa[1].equals(" HTTP")||sa[1].equals("index.html HTTP"))
                {
                    if(sa[1].equals(" HTTP")||sa[1].equals("index.html HTTP"))
                    {
                        //indexseite
                        try{
                            BufferedOutputStream dataOut = new BufferedOutputStream(c.getOutputStream());
                            File file = new File("wwwroot","index.html");
                            int fileLength = (int) file.length();
                            ByteArrayOutputStream dataByte = new ByteArrayOutputStream(fileLength);
                            byte[] fileData = new byte[fileLength];
                            FileInputStream fileIn = new FileInputStream(file);
                            fileIn.read(fileData);
                            if(fileIn != null) {
                             fileIn.close();   
                            }
                            dataOut.write(fileData, 0, fileLength);
                            dataOut.flush();
                        } catch(IOException ioe) {
                            System.out.println("Fehler!");
                        }
                    }
                    else
                    {
                        if(sa[1].equals("page.html HTTP"))
                        {
                            //pageseite

                        }
                        else
                        {
                            //page not found
                             out.println("403 Forbidden or 404 Not Found ");
                        }
                    }
                       
                          
                        
                }
                else
                {
                      if(sa.length==4&&sa[1].equals("subfolder")&&sa[2].equals("subpage.html HTTP")&&(sa[3].equals("1.1")||sa[3].equals("1.0")))
                      {
                                //subfolder/subpage
                                  out.println("subfolder/subpage site");
                      }
                      else
                      {
                          //page not found
                           out.println("403 Forbidden or 404 Not Found ");
                        }
                    }
                }
                else
                {
                    // PUT, DELETE, LINK und UNLINK 
                    if(sa[0].equals("PUT ")||sa[0].equals("DELETE ")||sa[0].equals("LINK ")||sa[0].equals("UNLINK "))
                    {
                         out.println("501 Not Implemented ");
                    }
                
            
                
        
        }
        }
    }
    
     public void printArr(String[] sa)
    {
      for(int i=0;i<sa.length;i++)
      {
          System.out.println(sa[i]);
        }
    }
       
    
   
    
    
    
    
    
    
    
    
    
    //
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //nicht aktuell
   /* public void validate2(String s,PrintWriter out)
    {
        System.out.println(s);
        String[]sa=s.split("/");
        printArr(sa);
        try
        {
            if (sa.length>5||sa.length<3||sa[0]!="http"||sa[1]!=""||!sa[2].equals(InetAddress.getLocalHost()))
            {
                out.println("404 not found");
                if(sa.length>5)
                {
                    out.println("zulanges request zu viele /(<5)");
                }
                if(sa.length<3)
                {
                    out.println("zu kurzes request zu wenig /(>3)");
                }
                if(sa[0]!="http")
                {
                    out.println("form:http//...../.......");
                }
                if(sa[1]!="")
                {
                    out.println("form:....//....../......");
                }
                if(!sa[2].equals(InetAddress.getLocalHost()))
                {
                    out.println("falsche ip adresse!");
                }
            }
            else
            {
                if(sa.length==3||sa.length==4&&sa[3]=="index.html")
                {
                    //request Ordner/wwwroot/index.html
                    //out.print(wwwroot/inde);
                }
                else
                {
                    if(sa.length==4&&sa[3]=="page.html")
                    {
                        //request Ordner/wwwroot/page.html
                    }
                    else
                    {
                        if(sa.length==5&&sa[3]=="subfolder"&&sa[4]=="subpage.html")
                        {
                            //request Ordner/wwwroot/subfolder/subpage.html
                        }
                        else
                        {
                            //bad request
                            out.print("403 forbidden");
                        }
                    }
                }
                // //weil regex meta charachter
            }
        }
        catch(UnknownHostException uhe)
        {
            validateWithOutIp(s,out);
        }
    }
    public void validateWithOutIp(String s,PrintWriter out)
    {
        System.out.println(s);
        String[]sa=s.split("/");
        if (sa.length>5||sa.length<3||sa[0]!="http"||sa[1]!="")
            {
                out.println("404 not found");
                if(sa.length>5)
                {
                    out.println("zulanges request zu viele /(<5)");
                }
                if(sa.length<3)
                {
                    out.println("zu kurzes request zu wenig /(>3)");
                }
                if(sa[0]!="http")
                {
                    out.println("form:http//...../.......");
                }
                if(sa[1]!="")
                {
                    out.println("form:....//....../......");
                }
              
            }else
            {
        if(sa.length==3||sa.length==4&&sa[3]=="index.html")
       {
           //request Ordner/wwwroot/index.html
       }
       else
       {
           if(sa.length==4&&sa[3]=="page.html")
           {
               //request Ordner/wwwroot/page.html
            }
            else
            {
                if(sa.length==5&&sa[3]=="subfolder"&&sa[4]=="subpage.html")
                {
                    //request Ordner/wwwroot/subfolder/subpage.html
                }
                else
                {
                     out.print("403 forbidden");
                }
            }
        } 
    }
    }
    */
}
//get /page.html HTTP/1.1
