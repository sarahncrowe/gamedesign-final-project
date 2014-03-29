package scenetest;

//imports
//import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class gameWindow extends JFrame implements ActionListener {
	

	//initialize text area, scroll bar, buttons, radio buttons,
	// label and icon for pictures, and boolean variables.
	private static JTextArea display = new JTextArea(10, 75);
	private static JScrollPane scroll = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private static JButton next = new JButton("Next");
	private static JButton reply = new JButton("Reply");
	private static JLabel picture = new JLabel("");
	private static boolean pause;
	private static boolean isQuestion = false;
	
	private static JRadioButton choice1 = new JRadioButton();
	private static JRadioButton choice2 = new JRadioButton();
	private static JRadioButton choice3 = new JRadioButton();
	private static JRadioButton choice4 = new JRadioButton();
	
	private static ImageIcon sceneImage = new ImageIcon("C:\\Users\\Sarah\\Documents\\COSC\\GitHub\\team6\\gameImages\\");
	
	
	
	

	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////	CONTAINER AND PANEL CODE STARTS HERE	///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Layout and Panel info
	public gameWindow() {
		//make sure players can't type in the text area
		display.setEditable(false);
		display.setLineWrap(true);
		display.setWrapStyleWord(true);
		
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
		
		
		//Panel 1 --> panel for text area, scrollbar and image.
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(picture, BorderLayout.NORTH);
		//p1.setPreferredSize(512,200);
		p1.add(scroll, BorderLayout.SOUTH);
		p1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		//Panel p2 --> panel for radio buttons
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(4,1));
		//add buttons to GUI
		p2.add(choice1);
		p2.add(choice2);
		p2.add(choice3);
		p2.add(choice4);
		
		
		//Panel 3 --> panel for reply and next buttons.
		JPanel p3 = new JPanel();
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
		defaultVal();
		
		choice1.setVisible(false);
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		
		display.append(" ");
		
		//displays string letter by letter (typewriter effect)
		for (int i=0; i<dialogue.length(); i++){
			display.append(dialogue.substring(i, i+1));
			display.setCaretPosition(display.getDocument().getLength());
			
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
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void defaultVal(){
		/*resets to defaults to ensure that scene carries out correctly
		 * 
		 * pause = true				--> ensure the scene pauses to allow user to read it.
		 * isQuestion = false		--> ensure methods aren't accidentally pausing for reply
		 * next.setEnabled(false)	--> ensure disabled until needed
		 * reply.setEnabled(false)	--> ensure disabled until needed
		 * 
		 */
		pause = true;
		isQuestion = false;
		next.setEnabled(false);
		reply.setEnabled(false);
	}
	
	public static void newScene(){
		//clears text area for new scene
		display.setText("");
	}
	
	public static void changePicture(String imageName){
		// appends image name to path to retrieve picture,
		// and sets as icon on picture JLabel
		
		String path = "C:\\Users\\Sarah\\Documents\\COSC\\GitHub\\team6\\gameImages\\" + imageName;
		sceneImage = new ImageIcon(path);
		picture.setIcon(sceneImage);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void askQuestion(String q1){
		/* 
		 * player's reply options are set to the respective radio buttons
		 * and only the needed radio buttons become visible.
		 * Reply and next buttons are enabled and disabled (respectively)
		 * and isQuestion is set to true to wait for the user to make
		 * a selection.
		 * 
		 */
		
		choice1.setText(q1);
		choice1.setVisible(true);
		
		isQuestion = true;
		reply.setEnabled(true);
		next.setEnabled(false);
		
		//make sure conversation doesn't move on without a reply.
		//briefly sleeps between checking if isQuestion is still true.
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
		/*
		 * see public static void askQuestion(String q1) for details on
		 * how the method works and/or details on what each section does. 
		 * 
		 */
		
		choice1.setText(q1);
		choice2.setText(q2);
		
		choice1.setVisible(true);
		choice2.setVisible(true);
		
		isQuestion = true;
		reply.setEnabled(true);
		next.setEnabled(false);
		
		while (isQuestion){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}

		if (choice1.isSelected()) {
			display.append(q1);
		}
		if (choice2.isSelected()){
			display.append(q2);
		}
		display.append("\n");
	}
	
	public static void askQuestion(String q1, String q2, String q3){
		/*
		 * see public static void askQuestion(String q1) for details on
		 * how the method works and/or details on what each section does. 
		 * 
		 */
		
		choice1.setText(q1);
		choice2.setText(q2);
		choice3.setText(q3);
		
		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(true);
		
		isQuestion = true;
		reply.setEnabled(true);
		next.setEnabled(false);
		
		while (isQuestion){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
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
		/*
		 * see public static void askQuestion(String q1) for details on
		 * how the method works and/or details on what each section does. 
		 * 
		 */
		
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
		
		while (isQuestion){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
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
		changePicture("Scene1-1.png");
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
		
		changePicture("Scene1-3.png");
		
		askQuestion("Goodnight, friend.", "See you in the morning.");
	}
	
	public static int scene_two(){
		
		newScene();
		
		changePicture("Scene2-2.png");
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
		askQuestion("Oh no! Are you okay?");
		typewriter("The other boy wouldn't let me play with him");
		askQuestion("What happened");
		typewriter("I pushed him...but he hit me!");
		askQuestion("Did you hit him back?","That's not how we make friends.");
		if (choice1.isSelected()){
			typewriter("No...the teacher pulled me away");
		}
		if (choice2.isSelected()){
			typewriter("I tried really hard though! No one will talk to me now...");
			askQuestion("The other kids will still like you, but you have to be nice.", "I'll still talk to you.");
		}
		
		typewriter("Mommy is mad at me, and Dad told me to go to bed."
				+ " I don't want to get in more trouble.");
		askQuestion("I understand, Goodnight.", "I'm very disappointed. Go to sleep.");
		typewriter("Good night, Teddy");
	}
	
	public static void scene_3b(){
		//occurs when the player advises the boy to be himself
		
		newScene();
		askQuestion("What's wrong?", "What's up with you?", "What's your problem?");
		typewriter("No one played with me. No one wanted to be my friend.");
		askQuestion("I'm still your friend.", "Give it time, the others will play with you.", "I can see why. No one wants to play with a crybaby.");
		if (choice1.isSelected()){
			typewriter("But why didn't they play with me?");
		}
		if (choice2.isSelected()){
			typewriter("But how do I make them my friends?");
		}
		if (choice3.isSelected()){
			typewriter("That's really mean, Teddy");
			typewriter("But...is that really why they didn't play with me?");
			
		}
		askQuestion("Well, what did you do today?");
		typewriter("I played alone, that's what I always do. Why didn't they join me?");
		
		askQuestion("Give it a couple of days. The other kids will come play.", "Try saying hi to the others. All kids are shy.");
		if (choice1.isSelected()){
			typewriter("They will? Okay...");
		}
		if (choice2.isSelected()){
			typewriter("What if they're mean?");
			askQuestion("Find the good ones.", "Then keep playing alone.");
			if (choice1.isSelected()){
				typewriter("Okay, I'll try...");
			}
			if (choice2.isSelected()){
				typewriter("I don't want to do that! I'll try again!");
			}
		}
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
		frame.setTitle("A Boy and His Bear");
		frame.setSize(530, 575);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //call story methods to actually play the game
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
