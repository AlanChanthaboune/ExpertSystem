package system;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class trainer {
	String bodypart;
	Scanner reader= new Scanner(System.in);
	public String key;
	public void addto(HashMap hmap,String key,String addition)
	{
		String list = null;
		if(hmap.containsKey(key)){
		    // if the key exist already, takes the existing string and append the additional information
		    list = (String) hmap.get(key);
		    list=list+","+addition;
		    hmap.put(key, list);
		} else {
		    //If the key does not exist, add inputted value to the key.
			if (hmap.get(key)==null)
			{
				list=addition;
				hmap.put(key, list);
			}
		}



	}
}

