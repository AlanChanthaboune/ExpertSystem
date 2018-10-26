package system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Data{
	public String bodyPart;
	public String myFileName;
	Scanner reader= new Scanner(System.in);
	public HashMap<String,String> hmap=new HashMap<String,String>();
	
	public Data() {
		myFileName="data1.txt";
	}
	public Data (String bodyPart) {
		myFileName="data1.txt";
		this.bodyPart=bodyPart;
	
	}
	public void readHash( )
	{		
	    
		String line=null;
	    try(BufferedReader reader = new BufferedReader(new FileReader(myFileName)))
	    {
	    while ((line = reader.readLine()) != null)
	    {
	        String[] parts = line.split(":", 2);
	        if (parts.length >= 2)
	        {
	            String key = parts[0];
	            String value = parts[1];
	            hmap.put(key, value);
	           
	        } else {
	            System.out.println("ignoring line: " + line);
	        }
	    }
		} catch (IOException e) {
        e.printStackTrace();
		} 

	}
	public void writeHash()
	{try
	{
		
		FileWriter fw = new FileWriter(myFileName);

		BufferedWriter myOutfile = new BufferedWriter(fw);
		for (String key : hmap.keySet())
	    {
			String value=hmap.get(key);
	      myOutfile.write(key+":"+value+"\n");
	    }
		
		myOutfile.flush();
		myOutfile.close();
	}
	catch (Exception e) {System.err.println("Didn't save to " + myFileName);}
		
		
		
	}
	public HashMap getHash()
	{
		readHash();
		return hmap;
	}
	public String toString()
	{
		return hmap.get(bodyPart);
	}
	public void printAllWorkout(){
		 for (String key : hmap.keySet())
		    {
		        System.out.println(key + ":" + hmap.get(key));
		    }
	}



		
		
}

