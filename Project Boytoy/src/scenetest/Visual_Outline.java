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
	
	
	
	
	

	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////	CONTAINER AND PANEL CODE STARTS HERE	///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
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
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////	END OF GUI. ACTIONLISTENER BELOW	///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////

	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////  END OF GUI. MISCELLANEOUS METHODS BELOW.	///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void typewriter(String dialogue){
		
		//displays string letter by letter (typewriter effect)
		for (int i=0; i<dialogue.length(); i++){
			display.append(dialogue.substring(i, i+1));
			
			//try and catch for Thread.sleep, just in case there are errors.
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				
				System.out.println("Something went wrong in the sentence.");
			}
		}
		
		//one more try and catch for the after sentence pause
		//CHANGE TO CLICK "NEXT" BUTTON FOR NEXT LINE
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			
			System.out.println("Something went wrong between sentences.");
		}
		
		//newline for splitting up sentences
		display.append("\n");
	}
	
	
	
	public static void newScene(){
		//clears text area for new scene
		display.setText("");
	}
	
	
	
	
	

	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////  END OF ACTIONLISTENER. SCRIPT METHODS BELOW.	///////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void scene_one(){
		typewriter("Hello?");
		typewriter("Hellooo?");
		typewriter("I'm going to name you...Teddy.");
		typewriter("Today's my birthday! I'm four years old!");
		typewriter("Will you be my friend?");
		typewriter("I start school tomorrow.");
		typewriter("I hope I make some new friends. I hope they like me "
				+ "and then lots of people will come to my party next year!");
		typewriter("So...Will you be my friend?");
		typewriter("Pleeeaaaseee?");
		typewriter("I have to go to bed. Think about it, okay?");
	}
	
	public static int scene_two(){
		
		newScene();
		
		typewriter("Good morning, Teddy!");
		typewriter("Ha! I knew you could talk!");
		
		//if statement here for different responses
		typewriter("Yay! You're my friend! Forever?");
		//reply 2
		typewriter("I'm happy you talk. Now I have someone to talk to.");
		
		typewriter("Today's my first day at school. I'm kinda scared.");
		typewriter("I don't really know any other kids. What if they don't like me?");
		typewriter("Ah...ok. Then we can all play together!");
		
		//additional dialogue based on response
		typewriter("Why?");
		typewriter("Haha, okay Teddy");
		
		typewriter("I gotta go eat breakfast. Thanks Teddy, I'm not scared anymore. See you after school!");
		
		return 1;
	}
	
	public static void scene_3a(){
		//occurs when the player advised the boy to "try hard"
		
		newScene();
		
		typewriter("The other boy wouldn't let me play with him");
		typewriter("I pushed him...but he hit me!");
		
		//response to "did you hit him back?"
		typewriter("No...the teacher pulled me away");
		
		//response to "that's not how we make friends"
		typewriter("I tried really hard though! No one will talk to me now...");
		
		typewriter("Okay. Mommy is mad at me, and Dad told me to go to bed."
				+ " I don't want to get in more trouble.");
		typewriter("Good night, Teddy");
	}
	
	public static void scene_3b(){
		//occurs when the player advises the boy to be himself
		
		//clear text area for new scene
		newScene();
		
		typewriter("No one played with me. No one wanted to be my friend.");
		
		//response to "I'm still your friend"
		typewriter("But why didn't they play with me?");
		//response to "Give it time, buddy. The other kids will play with you"
		typewriter("But how do I make them my friends?");
		
		typewriter("I played alone, that's what I always do. Why didn't they join me?");
		
		//response to "It'll take time, kids will come play"
		typewriter("They will? Okay...");
		//response to "try saying hi to the others. all kids are shy"
		typewriter("What if they're mean?");
		typewriter("Okay...");
		
		typewriter("Mommy wants me to go to bed.");
		typewriter("Good night, Teddy.");
		
	}
	

	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////	END OF SCRIPT. MAIN METHOD BELOW	///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void main(String[] args) {
		
		int returnedValue;
		
		JFrame frame = new Visual_Outline();
		frame.pack();
		frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    scene_one();
	    returnedValue = scene_two();
	    if (returnedValue == 1){
	    	scene_3a();
	    }
	    else {
	    	scene_3b();
	    }

	}

}
