package scenetest;

//imports
//import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class gameWindow extends JFrame implements ActionListener {

	//initialize text area, labels for different choices, and button
	private static JTextArea display = new JTextArea(20, 75);
	private static JButton next = new JButton("Next");
	private static JButton reply = new JButton("Reply");
	private static boolean pause;
	private static boolean isQuestion = false;
	//private static JOptionPane question = new JOptionPane();
	
	private static JRadioButton choice1 = new JRadioButton();
	private static JRadioButton choice2 = new JRadioButton();
	private static JRadioButton choice3 = new JRadioButton();
	private static JRadioButton choice4 = new JRadioButton();
	
	
	
	
	

	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////	CONTAINER AND PANEL CODE STARTS HERE	///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Layout and Panel info
	public gameWindow() {
		//make sure players can't type in the text area
		display.setEditable(false);
		
		//add buttons to group, add actionListeners
		ButtonGroup choices = new ButtonGroup();
		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		choices.add(choice4);
		choice1.addActionListener(this);
		choice2.addActionListener(this);
		choice3.addActionListener(this);
		choice4.addActionListener(this);
		
		
		
		//Initialize Container
		Container cp = getContentPane();
		
		
		//Panel 1
		Panel p1 = new Panel();
		p1.setLayout(new FlowLayout());
		p1.add(display);
		
		
		//Panel p2
		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(4,1));
		//add buttons to GUI
		p2.add(choice1);
		p2.add(choice2);
		p2.add(choice3);
		p2.add(choice4);
		
		
		
		//Panel 3
		Panel p3 = new Panel();
		p3.setLayout(new FlowLayout());
		next.addActionListener(this);
		reply.addActionListener(this);
		p3.add(next);
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

		if (e.getSource() == next){
			pause = false;
		}
		if (e.getSource() == reply){
			isQuestion = false;
		}
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////  END OF ACTIONLISTENER. MISCELLANEOUS METHODS BELOW.	///////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void typewriter(String dialogue){
		//reset to defaults
		pause = true;
		isQuestion = false;
		next.setEnabled(false);
		reply.setEnabled(false);
		
		choice1.setVisible(false);
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		
		//displays string letter by letter (typewriter effect)
		for (int i=0; i<dialogue.length(); i++){
			display.append(dialogue.substring(i, i+1));
			
			//try and catch for Thread.sleep, just in case there are errors.
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
				System.out.println("Something went wrong in the sentence (Typewriter).");
			}
		}
		
		//enables next button again to proceed with dialogue
		next.setEnabled(true);
		
		//sleeps, checks if it needs to be paused and sleeps again if true
		while (pause == true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("The while loop sleep went wrong (Typewriter).");
			}
		}
		//newline for splitting up sentences
		display.append("\n");
	}
	
	
	
	public static void newScene(){
		//clears text area for new scene
		display.setText("");
	}
	
	public static void askQuestion(String q1){
		choice1.setText(q1);
		choice1.setVisible(true);
		
		isQuestion = true;
		reply.setEnabled(true);
		next.setEnabled(false);
		
		//make sure conversation doesn't move on without a reply
		while (isQuestion){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
		//print out player's reply
		display.append(q1);
		display.append("\n");
	}
	
	public static void askQuestion(String q1, String q2){
		choice1.setText(q1);
		choice2.setText(q2);
		
		choice1.setVisible(true);
		choice2.setVisible(true);
		
		isQuestion = true;
		reply.setEnabled(true);
		next.setEnabled(false);
		
		//make sure conversation doesn't move on without a reply
		while (isQuestion){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
		//print out player's reply
		if (choice1.isSelected()) {
			display.append(q1);
		}
		if (choice2.isSelected()){
			display.append(q2);
		}
		display.append("\n");
	}
	
	public static void askQuestion(String q1, String q2, String q3){
		choice1.setText(q1);
		choice2.setText(q2);
		choice3.setText(q3);
		
		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(true);
		
		isQuestion = true;
		reply.setEnabled(true);
		next.setEnabled(false);
		
		//make sure conversation doesn't move on without a reply
		while (isQuestion){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
		//print out player's reply
		if (choice1.isSelected()) {
			display.append(q1);
		}
		if (choice2.isSelected()){
			display.append(q2);
		}
		if (choice3.isSelected()){
			display.append(q3);
		}
		display.append("\n");
	}
	
	public static void askQuestion(String q1, String q2, String q3, String q4){
		choice1.setText(q1);
		choice2.setText(q2);
		choice3.setText(q3);
		choice4.setText(q4);
		
		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(true);
		choice4.setVisible(true);
		
		isQuestion = true;
		reply.setEnabled(true);
		next.setEnabled(false);
		
		//make sure conversation doesn't move on without a reply
		while (isQuestion){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
		//print out player's reply
		if (choice1.isSelected()) {
			display.append(q1);
		}
		if (choice2.isSelected()){
			display.append(q2);
		}
		if (choice3.isSelected()){
			display.append(q3);
		}
		if (choice4.isSelected()){
			display.append(q4);
		}
		display.append("\n");
	}
	
	

	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////  END OF MISCELLANEOUS. SCRIPT METHODS BELOW.	///////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void scene_one(){
		typewriter("Hello?");
		typewriter("Hellooo?");
		typewriter("I'm going to name you...Teddy.");
		typewriter("Today's my birthday! I'm four years old!");
		typewriter("Will you be my friend?");
		
		askQuestion("...");
		
		typewriter("I start school tomorrow.");
		typewriter("I hope I make some new friends. I hope they like me "
				+ "and then lots of people will come to my party next year!");
		typewriter("So...Will you be my friend?");
		
		askQuestion("...");
		
		typewriter("Pleeeaaaseee?");
		
		askQuestion("...");
		
		typewriter("I have to go to bed. Think about it, okay?");
		
		askQuestion("Goodnight, friend.", "See you in the morning.");
	}
	
	public static int scene_two(){
		
		newScene();
		
		typewriter("Good morning, Teddy!");
		askQuestion("Good morning, friend!", "Hi.");
		typewriter("Ha! I knew you could talk!");
		askQuestion("You're my friend. Of course I'll talk to you.", "You kept talking to me, I figured I should reply");
		
		if (choice1.isSelected()){
			typewriter("Yay! You're my friend! Forever?");
			askQuestion("Always.","Sure, whatever.");
		}
		if (choice2.isSelected()){
			typewriter("I'm happy you talk. Now I have someone to talk to.");
		}
		
		typewriter("Today's my first day at school. I'm kinda scared.");
		typewriter("I don't really know any other kids. What if they don't like me?");
		askQuestion("If you try hard, you'll make friends.", "Just be yourself, you'll find friends.");
		
		//deviation based on dialogue choice
		if (choice1.isSelected()){
			typewriter("Ah...ok. Then we can all play together!");
			if (choice2.isSelected()){
				typewriter("Why?");
				askQuestion("I'm...shy.", "I'm...not real.");
				typewriter("Haha, okay Teddy");
			}
			typewriter("I gotta go eat breakfast. Thanks Teddy, I'm not scared anymore. See you after school!");
			
			return 1;
		}
		
		//other path if choice 2 is chosen
		else {
		typewriter("Ah...ok. Then we can all play together!");
		askQuestion("Sounds like fun","Well, other kids won't be able to talk to me.");
		if (choice2.isSelected()){
			typewriter("Why?");
			askQuestion("I'm...shy.", "I'm...not real.");
			typewriter("Haha, okay Teddy");
		}
		
		typewriter("I gotta go eat breakfast. Thanks Teddy, I'm not scared anymore. See you after school!");
		
		return 0;
		}
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
		
		JFrame frame = new gameWindow();
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
