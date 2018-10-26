package system;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Tester {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Panel();
				//Realize that this class should make a new jFrame but couldn't figure out how to create one with a card layout
				
			}
		});

	}		
	
}
