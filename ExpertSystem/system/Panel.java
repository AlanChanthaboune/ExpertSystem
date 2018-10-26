package system;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;
import javax.swing.text.html.HTMLDocument.Iterator;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JLayeredPane;
import javax.swing.JScrollBar;

public class Panel extends JPanel{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public Panel() {
		Data d=new Data();
		trainer t=new trainer();
		HashMap<String,String> hmap=new HashMap<String,String>();
		hmap=d.getHash();
		setBackground(SystemColor.window);
		
		setLayout(null);
///////////////////////////////////////////////////////////
// Sets up ComboBoxes with the keys
////////////////////////////////////////////////////////////
		Vector<String> key=new Vector<String>();
		Set set =hmap.keySet();
		java.util.Iterator iter=set.iterator();

		 while (iter.hasNext()) {
			  key.addElement((String) iter.next());
		    }
		 JComboBox<String> comboBox = new JComboBox<String>(key);
		 comboBox.setBounds(60, 175, 208, 23);
		 comboBox.setRenderer(new MyComboBoxRenderer("Select Body Part"));
	     comboBox.setSelectedIndex(-1);
	     
	     JComboBox<String> comboBox2 = new JComboBox<String>(key);
		 comboBox2.setBounds(60, 175, 208, 23);
		 comboBox2.setRenderer(new MyComboBoxRenderer("Select Body Part"));
	     comboBox2.setSelectedIndex(-1);
///////////////////////////////////////////////////////////
//Creates two panel for the  card layout
////////////////////////////////////////////////////////////
	     JPanel panelFirst = new JPanel();
	     JPanel panelSecond=new JPanel();
	     panelSecond.setBackground(new Color(0, 128, 128));

	     
	     JButton button2 = new JButton("<Back");
	     button2.setBounds(415, 327, 159, 23);
	    
	     textField = new JTextField();
	     textField.setEditable(false);
		 textField.setBounds(60, 243, 484, 36);
		
		 
		 textField.setColumns(10);
//////////////////////////////////////////////////////////////////
//When combo box option is selected it returns the following data
//////////////////////////////////////////////////////////////////
		 comboBox.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) {
				 JComboBox<String> combo = (JComboBox<String>) event.getSource();
			     String bodyPart = (String) combo.getSelectedItem();
			     Data b=new Data(bodyPart);
			     b.getHash();
			     
			     textField.setText(b.toString());
			 }
		 });		
		 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//When a combo box option is selected it can add more item to the data or it can create a new field for the combo box
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 comboBox2.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) {
				 JLabel lblDone = new JLabel("Done!");
				 lblDone.setBounds(335, 228, 69, 23);
				 panelSecond.add(lblDone);
				 lblDone.setVisible(false);
				 JLabel lblDone_1 = new JLabel("Done!");
				 lblDone_1.setBounds(335, 291, 46, 14);
				 panelSecond.add(lblDone_1);
				 lblDone_1.setVisible(false);
				 JComboBox<String> combo = (JComboBox<String>) event.getSource();
			     String bodyPart = (String) combo.getSelectedItem();
			     
			    textField_1.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e){
					String input = textField_1.getText();
					
					t.addto(d.hmap,bodyPart,input);
					d.writeHash();
					textField_1.setText("");
					lblDone.setVisible(true);
	
				}
			    });	
				   textField_2.addActionListener(new ActionListener(){
				    	public void actionPerformed(ActionEvent e){
						String input = textField_2.getText();
						textField_2.setText("");
						input.toUpperCase();
						t.addto(d.hmap,input,"");
						d.writeHash();
						lblDone_1.setVisible(true);

						comboBox.addItem(input);
						d.writeHash();
						
					}
				    });	
				     
			 }
			});
		   
				

		 JFrame frame = new JFrame("Workout Expert System");
		 CardLayout cl = new CardLayout();
		 
		 JPanel panelCont = new JPanel();
		 panelCont.setLayout(cl);
		 panelFirst.setBackground(new Color(0, 128, 128));
		 panelFirst.setLayout(null);
		 panelFirst.add(comboBox);
		 panelFirst.add(textField);
///////////////////////////////////////////////////////////
//Creating labels and button to make it more user friendly
////////////////////////////////////////////////////////////
		 JButton button1 = new JButton("Developer Mode >");
		 button1.setBounds(415, 327, 159, 23);
		 panelFirst.add(button1);

		 
		 panelSecond.setLayout(null);
		 panelSecond.add(button2);
		 panelCont.add(panelFirst, "1");
		 panelSecond.add(comboBox2);
		 
		 JLabel lblWorkoutFinder = new JLabel("Workout Expert System");
		 lblWorkoutFinder.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 60));
		 lblWorkoutFinder.setBounds(60, 11, 484, 70);
		 panelFirst.add(lblWorkoutFinder);
		 
		 JLabel lblByAlanChanthaboune = new JLabel("by Alan Chanthaboune");
		 lblByAlanChanthaboune.setFont(new Font("Segoe UI Historic", Font.PLAIN, 23));
		 lblByAlanChanthaboune.setBounds(60, 92, 353, 30);
		 panelFirst.add(lblByAlanChanthaboune);
		 
		 JLabel lblSelectBodyPart = new JLabel("Body Part");
		 lblSelectBodyPart.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblSelectBodyPart.setBounds(60, 149, 208, 23);
		 panelFirst.add(lblSelectBodyPart);
		 
		 JLabel lblWorkout = new JLabel("Workout");
		 lblWorkout.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblWorkout.setBounds(60, 209, 208, 23);
		 panelFirst.add(lblWorkout);
		 
		 
		 
		 JLabel lblDeveloperMode = new JLabel("Developer Mode");
		 lblDeveloperMode.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 71));
		 lblDeveloperMode.setBounds(60, 11, 484, 70);
		 panelSecond.add(lblDeveloperMode);
		 
		 
		 
		 panelCont.add(panelSecond, "2");
		 
///////////////////////////////////////////////////////////
//Creates text field for user to add their own data
////////////////////////////////////////////////////////////
		 
		 JLabel lblSelectBodyPart_1 = new JLabel("Select body part to be changed");
		 lblSelectBodyPart_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblSelectBodyPart_1.setBounds(60, 145, 360, 23);
		 panelSecond.add(lblSelectBodyPart_1);
		 
		 JLabel lblWhatWouldYou = new JLabel("Type in the new workout and press enter");
		 lblWhatWouldYou.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblWhatWouldYou.setBounds(60, 208, 449, 23);
		 panelSecond.add(lblWhatWouldYou);
		 
		 textField_1 = new JTextField();
		 textField_1.setBounds(60, 228, 254, 23);
		 panelSecond.add(textField_1);
		 textField_1.setColumns(10);
		 
		 JLabel lblAddBodyPart = new JLabel("Type in new body part to be added and press enter");
		 lblAddBodyPart.setFont(new Font("Tahoma", Font.BOLD, 15));
		 lblAddBodyPart.setBounds(60, 262, 449, 23);
		 panelSecond.add(lblAddBodyPart);
		 
		 textField_2 = new JTextField();
		 textField_2.setColumns(10);
		 textField_2.setBounds(60, 287, 254, 23);
		 panelSecond.add(textField_2);
		 

		 
///////////////////////////////////////////////////////////
//Switch back and forth between panels
////////////////////////////////////////////////////////////
		 button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cl.show(panelCont, "2");
				}
			});
	
		button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cl.show(panelCont, "1");
				}
			});
		
		
		 
		 
	
		 
	
		 
		frame.setPreferredSize(new Dimension(600, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

			
			
		
	               
	    }
	}
	class MyComboBoxRenderer extends JLabel implements ListCellRenderer
    {
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//https://stackoverflow.com/questions/23882640/how-to-set-the-title-of-a-jcombobox-when-nothing-is-selected
		//Code received at ^
		//This code display a non selectable title for the combo box
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private String _title;

        public MyComboBoxRenderer(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }


