package scenetest;

//imports
//import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Visual_Outline extends JFrame implements ActionListener {

	//initialize text area, labels for different choices, and button
	private static JTextArea display = new JTextArea(20, 75);
	private JRadioButton choice1 = new JRadioButton();
	private JRadioButton choice2 = new JRadioButton();
	private JRadioButton choice3 = new JRadioButton();
	private JRadioButton choice4 = new JRadioButton();
	private JButton reply = new JButton("Reply");
	
	
	
	
	//Layout and Panel info
	public Visual_Outline() {
		display.setEditable(false);
		
		//Initialize Container
		Container cp = getContentPane();
		
		//Panel 1
		Panel p1 = new Panel();
		p1.setLayout(new FlowLayout());
		p1.add(display);
		
		
		//Panel p2
		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(4,1));
		//add buttons to group
		ButtonGroup choices = new ButtonGroup();
		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		choices.add(choice4);
		//add buttons to GUI
		p2.add(choice1);
		p2.add(choice2);
		p2.add(choice3);
		p2.add(choice4);
		
		
		
		//Panel 3
		Panel p3 = new Panel();
		p3.setLayout(new FlowLayout());
		p3.add(reply);
		
		//set layout
		cp.setLayout(new BorderLayout());
		cp.add(p1, BorderLayout.NORTH);
		cp.add(p2, BorderLayout.CENTER);
		cp.add(p3, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////	ALL METHODS AND SCRIPTS FOR SCENES BELOW	///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static void scene_one(){
		display.setText("Hello?");
		display.append("Hellooo?");
		System.out.println("I'm going to name you...Teddy");
		System.out.println("Today's my birthday! I'm four years old!");
		System.out.println("Will you be my friend?");
		System.out.println("I start school tomorrow.");
		System.out.println("I hope I make some new friends. I hope they like me "
				+ "and then lots of people will come to my party next year!");
		System.out.println("So...Will you be my friend?");
		System.out.println("Pleeeaaaseee?");
		System.out.println("I have to go to bed. Think about it, okay?");
	}
	
	
	
	public static void main(String[] args) {
		
		JFrame frame = new Visual_Outline();
		frame.pack();
		frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	

}
