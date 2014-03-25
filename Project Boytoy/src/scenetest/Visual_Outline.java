package scenetest;

//import for JFramey stuffs
//import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Visual_Outline extends JFrame implements ActionListener {

	//initialize text area and button
	private JTextArea display;
	private JButton choice;
	
	public Visual_Outline() {
		JTextArea display = new JTextArea(20, 75);
		JButton choice = new JButton("Choose");
		
		//Initialize Container
		Container cp = getContentPane();
		
		//Panel 1
		Panel p1 = new Panel();
		p1.setLayout(new FlowLayout());
		p1.add(display);
		
		//Panel p2
		Panel p2 = new Panel();
		p2.setLayout(new FlowLayout());
		p2.add(choice);
		
		//set layout
		cp.setLayout(new BorderLayout());
		cp.add(p1, BorderLayout.NORTH);
		cp.add(p2, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		JFrame frame = new Visual_Outline();
		frame.pack();
		frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	

}
