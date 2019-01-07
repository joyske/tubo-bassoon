package edu.udo.cs.rvs;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread {
	
	private Socket socket;
	private File root;
	private int clientnumber;
	private boolean _terminate;
	
	public ServerThread(Socket s, File root, int n) {
		socket = s;
		this.root = root;
		clientnumber = n;
		_terminate = false;
		System.out.println( "Neuer Client: #" + clientnumber);
	}
	
	public void terminate(){
		_terminate = true;
	}
	public void run() {
		try {
			while(!_terminate)
			{
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedOutputStream outStr = new BufferedOutputStream(socket.getOutputStream());
				PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
				String inRd = input.readLine();
				validate(inRd, output, outStr);
				
				output.println("Du bist #" + clientnumber);
				
				terminate();
				
			}
		}
		catch(IOException e) {
			System.out.println("Fehler input output!");
		}
		finally{
			try{
				socket.close();
			}
			catch(IOException ioe){
				System.out.println("Fehler beim Schließen des Sockets!");
			}
		}
	}
	public void validate(String s, PrintWriter out, BufferedOutputStream dataOut)
	{
				StringTokenizer parse = new StringTokenizer(s);
				String command = parse.nextToken().toUpperCase();
				String fileRequested = parse.nextToken().toLowerCase();
				if(command.equals("GET")||command.equals("POST"))
				{
					 try{
                            
                            File file = new File("wwwroot","index.html");
                          
                            int fileLength = (int) file.length();
                            ByteArrayOutputStream dataByte = new ByteArrayOutputStream(fileLength);
                            byte[] fileData = new byte[fileLength];
                            FileInputStream fileIn = new FileInputStream(file);
                            System.out.println("vor read");
                            fileIn.read(fileData);
                            System.out.println("nach read");
                            if(fileIn != null) {
                             System.out.println("file != null");
                             fileIn.close();   
                            }
                            System.out.println("vor write");
                            dataOut.write(fileData, 0, fileLength);
                            System.out.println("vor flush");
                            dataOut.flush();
                            System.out.println("schließe fileIn");
                            fileIn.close();
                            System.out.println("schließe Dataout");
                            dataOut.close();
                            System.out.println("schließe Databyte");
                            dataByte.close();
                            System.out.println("alles wurde geschlossen!!");
							
                        } catch(IOException ioe) {
                            System.out.println("Fehler!");
                        }
				}
				
	}
}