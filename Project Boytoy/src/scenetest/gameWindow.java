package scenetest;

//imports
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class gameWindow extends JFrame implements ActionListener {
	

	/**
	 * I'm not even sure what this is for but it makes the game not have warnings. So there.
	 */
	private static final long serialVersionUID = -5634949808226458557L;
	
	
	//initialize text area, scroll bar, buttons, radio buttons,
	// label and icon for pictures, and boolean variables.
	private static JTextArea display = new JTextArea(10, 75);
	private static JScrollPane scroll = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private static JButton next = new JButton("Next");
	private static JButton reply = new JButton("Reply");
	private static JLabel picture = new JLabel("");
	private static boolean pause;
	private static boolean isQuestion = false;
	
	//choice 1 through 4 are pretty obvs, unselect is the hidden
	//button that is selected at the start of each new choice.
	private static JRadioButton choice1 = new JRadioButton();
	private static JRadioButton choice2 = new JRadioButton();
	private static JRadioButton choice3 = new JRadioButton();
	private static JRadioButton choice4 = new JRadioButton();
	private static JRadioButton unselect = new JRadioButton();
	
	//the game has graphics!
	private static ImageIcon sceneImage;
	
	//flags for dialogue and appearance deviations
	private static boolean replay = true;
	private static boolean notReal = false;
	private static boolean blackEye = false;
 	
	private static int attachment = 0;
	

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
		choices.add(unselect);
		choice1.addActionListener(this);
		choice2.addActionListener(this);
		choice3.addActionListener(this);
		choice4.addActionListener(this);
		unselect.addActionListener(this);
		
		
		
		//Initialize Container
		Container cp = getContentPane();
		
		
		//Panel 1 --> panel for text area, scrollbar and image.
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(picture, BorderLayout.NORTH);
		p1.add(scroll, BorderLayout.SOUTH);
		p1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		//Panel p2 --> panel for radio buttons
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(5,1));
		//add buttons to GUI
		p2.add(choice1);
		p2.add(choice2);
		p2.add(choice3);
		p2.add(choice4);
		p2.add(unselect);
		
		
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
		defaultValue();
		
		choice1.setVisible(false);
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		unselect.setVisible(false);
		
		display.append(" ");
		
		//displays string letter by letter (typewriter effect)
		for (int i=0; i<dialogue.length(); i++){
			display.append(dialogue.substring(i, i+1));
			display.setCaretPosition(display.getDocument().getLength());
			
			//try and catch for Thread.sleep, just in case there are errors.
			try {
				Thread.sleep(40);
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
	
	
	public static void defaultValue(){
		/*resets to defaults to ensure that scene carries out correctly
		 * 
		 * pause = true				--> ensure the scene pauses to allow user to read it.
		 * isQuestion = false		--> ensure methods aren't accidentally pausing for reply
		 * next.setEnabled(false)	--> ensure disabled until needed
		 * reply.setEnabled(false)	--> ensure disabled until needed
		 * unselect.setEnabled(true)--> ensure the other replies aren't selected
		 * 
		 */
		pause = true;
		isQuestion = false;
		next.setEnabled(false);
		reply.setEnabled(false);
		unselect.setSelected(true);
	}
	
	public static void newScene(){
		//clears text area for new scene
		display.setText("");
		hideButtons();
	}
	
	public static void changePicture(String imageName){
		// appends image name to path to retrieve picture,
		// and sets as icon on picture JLabel
		
		
		sceneImage = new ImageIcon(gameWindow.class.getResource("/resources/images/"+imageName));
		picture.setIcon(sceneImage);
	}
	
	public static void pause(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.err.println("Something went wrong in the pause method.");
		}
	}
	
	public static void hideButtons(){
		choice1.setVisible(false);
		choice2.setVisible(false);
		choice3.setVisible(false);
		choice4.setVisible(false);
		unselect.setVisible(false);
		
		reply.setEnabled(false);
		next.setEnabled(false);
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
		next.setEnabled(false);
		
		
		//make sure conversation doesn't move on without a reply.
		//briefly sleeps between checking if isQuestion is still true.
		while (isQuestion){
			try {
				if (unselect.isSelected()){
					reply.setEnabled(false);
				}
				else {
					reply.setEnabled(true);
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
		//print out player's reply
		display.append(" ");
		display.append(q1);
		display.setCaretPosition(display.getDocument().getLength());
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
		next.setEnabled(false);
		
		while (isQuestion){
			try {
				if (unselect.isSelected()){
					reply.setEnabled(false);
				}
				else {
					reply.setEnabled(true);
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
		display.append(" ");
		if (choice1.isSelected()) {
			display.append(q1);
			
		}
		if (choice2.isSelected()){
			display.append(q2);
		}
		display.setCaretPosition(display.getDocument().getLength());
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
		next.setEnabled(false);
		
		while (isQuestion){
			try {
				if (unselect.isSelected()){
					reply.setEnabled(false);
				}
				else {
					reply.setEnabled(true);
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
		display.append(" ");
		if (choice1.isSelected()) {
			display.append(q1);
		}
		if (choice2.isSelected()){
			display.append(q2);
		}
		if (choice3.isSelected()){
			display.append(q3);
		}
		display.setCaretPosition(display.getDocument().getLength());
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
		next.setEnabled(false);
		
		while (isQuestion){
			try {
				if (unselect.isSelected()){
					reply.setEnabled(false);
				}
				else {
					reply.setEnabled(true);
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Something in the while loop went wrong (isQuestion).");
			}
		}
		display.append(" ");
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
		display.setCaretPosition(display.getDocument().getLength());
		display.append("\n");
	}
	
	

	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////  END OF MISCELLANEOUS. SCRIPT METHODS BELOW.	///////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void scene_one(){
		changePicture("Scene1-1.gif");
		hideButtons();
		pause(2000);
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
		
		changePicture("Scene1-2.png");
		
		typewriter("I have to go to bed. Think about it, okay?");
		
		changePicture("Scene1-3.png");
		
		askQuestion("...");
		hideButtons();
		pause(1500);
		askQuestion("Goodnight, friend.", "See you in the morning.");
		if (choice1.isSelected()){
			attachment++;
		}
		hideButtons();
		pause(2000);
	}
	
	//maximum attachment points after scene 1: 1
	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static int scene_two(){
		
		newScene();
		
		changePicture("Scene2-1.png");
		
		pause(2000);
		
		changePicture("Scene2-2.png");
		pause(100);
		changePicture("Scene2-1.png");
		pause(100);
		changePicture("Scene2-2.png");
		pause(500);
		typewriter("Good morning, Teddy!");
		
		askQuestion("Good morning, friend!", "Hi.");
		if (choice1.isSelected()){
			attachment++;
		}
		
		changePicture("Scene2-3.gif");
		typewriter("Ha! I knew you could talk!");
		
		askQuestion("You're my friend. Of course I'll talk to you.", "You kept talking to me, I figured I should reply.");
		
		if (choice1.isSelected()){
			typewriter("Yay! You're my friend! Forever?");
			askQuestion("Always.","Sure.", "Yeah, whatever.");
			if (choice1.isSelected()){
				attachment++;
			}
			if (choice3.isSelected()){
				attachment--;
			}
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
			askQuestion("Yeah! That sounds awesome!","Sounds like fun.","Well, other kids won't be able to talk to me.");
			if (choice1.isSelected()){
				attachment++;
			}
			if (choice3.isSelected()){
				typewriter("Why?");
				askQuestion("I'm...shy.", "I'm...not real.");
				if (choice2.isSelected()){
					notReal = true;
				}
				typewriter("Haha, okay Teddy.");
			}
			changePicture("Scene2-4.gif");
			typewriter("I gotta go eat breakfast. Thanks Teddy, I'm not scared anymore. See you after school!");
			changePicture("Scene2-5.gif");
			hideButtons();
			pause(3000);
			return 1;
		}
		
		//other path if choice 2 is chosen
		else {
		typewriter("Ah...ok. Then we can all play together!");
		askQuestion("Yeah! That sounds awesome!","Sounds like fun.","Well, other kids won't be able to talk to me.");
		if (choice1.isSelected()){
			attachment++;
		}
		if (choice3.isSelected()){
			typewriter("Why?");
			askQuestion("I'm...shy.", "I'm...not real.");
			if (choice2.isSelected()){
				notReal = true;
			}
			typewriter("Haha, okay Teddy.");
		}
		changePicture("Scene2-4.png");
		typewriter("I gotta go eat breakfast. Thanks Teddy, I'm not scared anymore. See you after school!");
		changePicture("Scene2-5.gif");
		hideButtons();
		pause(3000);
		return 0;
		}
	}
	
	
	//maximum attachment points after scene 2: 4
	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void scene_3a(){
		//occurs when the player advised the boy to "try hard"
		
		newScene();
		pause(1000);
		blackEye = true;
		changePicture("Scene3A-1.gif");
		
		askQuestion("Oh no! Are you okay?", "What's up with your face?");
		if (choice1.isSelected()){
			attachment++;
		}
		typewriter("The other boy wouldn't let me play with him.");
		askQuestion("What happened?", "Yeah, and...?");
		typewriter("I tried really hard to make friends. I pushed him...and he hit me!");
		askQuestion("Did you hit him back?","That's not how we make friends.");
		if (choice1.isSelected()){
			typewriter("No...the teacher pulled me away.");
		}
		if (choice2.isSelected()){
			typewriter("No one will talk to me now...");
			askQuestion("The other kids will still like you, but you have to be nice.", "I'll still talk to you.", "I'm the only one you need.");
			if (choice2.isSelected()){
				attachment++;
			}
			if (choice3.isSelected()){
				attachment = attachment + 2;
			}
		}
		
		typewriter("Mommy is mad at me, and Dad told me to go to bed."
				+ " I don't want to get in more trouble.");
		changePicture("Scene3A-2.png");
		askQuestion("I understand, Goodnight.", "I'm very disappointed. Go to sleep.", "You deserve it.");
		if (choice1.isSelected()){
			attachment++;
		}
		if (choice3.isSelected()){
			attachment = attachment - 2;
		}
		
		typewriter("Good night, Teddy.");
		changePicture("Scene3A-3.gif");
		pause(4000);
	}
	
	public static void scene_3b(){
		//occurs when the player advises the boy to be himself
		
		newScene();
		changePicture("Scene3B-1.gif");
		pause(1000);
		askQuestion("What's wrong?", "What's up with you?", "What's your problem?");
		typewriter("No one played with me. No one wanted to be my friend.");
		askQuestion("I'm the only friend you need.", "Give it time, the others will play with you.", "I can see why. No one wants to play with a crybaby.");
		if (choice1.isSelected()){
			attachment = attachment + 2;
			typewriter("But why didn't they play with me?");
		}
		if (choice2.isSelected()){
			typewriter("But how do I make them my friends?");
		}
		if (choice3.isSelected()){
			attachment = attachment - 3;
			typewriter("That's...that's really mean, Teddy...");
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
			askQuestion("Then you'll have more time to play with me!", "Find the nice ones.", "Then keep playing alone.");
			if (choice1.isSelected()){
				attachment++;
				typewriter("Ok. But it would be nice to have some other friends as well...");
			}
			if (choice2.isSelected()){
				typewriter("Okay, I'll try...");
			}
			if (choice3.isSelected()){
				typewriter("I don't want to do that! I'll try again!");
			}
		}
		typewriter("Mommy wants me to go to bed.");
		changePicture("Scene3B-2.png");
		pause(100);
		typewriter("Good night, Teddy.");
		changePicture("Scene3B-3.gif");
		pause(3000);
		
	}
	
	
	//maximum attachment points after scene 3a: 7
	//maximum attachment points after scene 3b: 7
	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int scene_four(boolean injured){
		
		newScene();
		
		//still shows injuries if obtained
		if (injured){
			changePicture("Scene4A-1.gif");
		}
		else {
			changePicture("Scene4B-1.gif");
		}
		
		typewriter("Hi Teddy. Guess what?");
		typewriter("I talked to someone today.");
		askQuestion("About time.", "That's great!", "You don't seem excited about it.");
		typewriter("Not really...she called me crazy.");
		typewriter("I'm not crazy! She's just mean!");
		askQuestion("Why did she call you crazy?", "Why is she mean?");
		if (choice1.isSelected()){
			typewriter("I told her about you, and that you talk to me.");
		}
		if (choice2.isSelected()){
			typewriter("We were playing, and I said you like playing too.");
			typewriter("I told her about you, and that you listen to me and help.");
			typewriter("She called me crazy and laughed at me.");
		}
		askQuestion("Others may not believe I exist and make fun of you.", "Don't worry, you're not crazy. You should only talk to me from now on.", "Don't talk about me to others, they won't understand.", "...I'm not real. I don't actually talk.");
		if (choice4.isSelected()){
			if (notReal){
				typewriter("Why do you keep saying that, Teddy? What do you mean?");
				askQuestion("Nothing, forget about it.", "I'm just your imagination.", "The girl was right, you are crazy.");
				if (choice3.isSelected()){
					if (injured){
						changePicture("Scene4A-2.gif");
					}
					else {
						changePicture("Scene4B-2.gif");
					}
					typewriter("Shut up, Teddy. I'm NOT crazy!");
					typewriter("...I don't want to talk to you anymore.");
					attachment = attachment-10;
					return 0; //ends with no attachment to bear
				}
				if (choice1.isSelected()){
					typewriter("Okay...maybe I should just keep you a secret to everyone.");
					if (attachment > 5){
						return 2; //ends with beginning of unhealthy attachment
					}
					else {
						return 1; //ends with healthy attachment
					}
				}
			}
			else {
				if (injured){
					changePicture("Scene4A-2.gif");
				}
				else {
					changePicture("Scene4B-2.gif");
				}
				typewriter("But you're my friend...my best friend.");
				askQuestion("I am, but I'm not real. I'm in your imagination.");
				typewriter("But you help me!");
				askQuestion("You're just talking to yourself.", "I'm a toy you turned into a friend.");
				typewriter("But...I love you, Teddy.");
				askQuestion("I love you too.", "...I don't think we should talk anymore.");
				
				if (choice2.isSelected()){
					typewriter("But then I'll be alone.");
					typewriter("...Teddy?");
					
					if (attachment > 5){
						typewriter("Don't leave me Teddy!");
						askQuestion("...", "I guess you really do need me.");
					
						if (choice1.isSelected()){
							return 0; //ends with no attachment to bear
						}
						else {
							typewriter("Yeah, I do.");
							return 2; //ends with beginning of unhealthy attachment to bear
						}
					}
				}
			}
		}
		else if (choice2.isSelected()){
			typewriter("Yeah...I don't need people that are going to call me crazy!");
			return 2;
		}
		
		else {
			typewriter("Okay. I'll just keep you a secret.");
		
			if (attachment > 5){
				return 2;
			}
			else {
				return 1;
			}
		}
		return 0;
	}
	
	public static void epilogue(int scene){
		/*
		 * scene = 0	-->	the boy has no attachment to the bear in the end
		 * scene = 1	--> the boy has a healthy attachment to the bear in the end
		 * scene = 2	--> the boy is starting to have an unhealthy attachment to the bear
		 */
		
		hideButtons();
		pause(1000);
		newScene();
		
		if (scene == 0) {
			changePicture("Scene5-1.gif");
			pause(8500);
			changePicture("Result1.gif");
			pause(10000);
		}
		if (scene == 1){
			changePicture("Scene5-2.gif");
			pause(9000);
			changePicture("Result2.gif");
			pause(10000);
		}
		if (scene == 2){
			changePicture("Scene5-3.gif");
			pause(9000);
			changePicture("Result3.gif");
			pause(10000);
		}
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
	    
	    while (replay){
	    	//call story methods to actually play the game
		    scene_one();
		    returnedValue = scene_two();
		    if (returnedValue == 1){
		    	scene_3a();
		    }
		    else {
		    	scene_3b();
		    }
		    returnedValue = scene_four(blackEye);
		    epilogue(returnedValue);
		    
		    typewriter("This is only one ending.");
		    typewriter("Would you like to play again?");
		    askQuestion("Yes", "No");
		    if (choice1.isSelected()){
		    	replay = true;
		    	display.setText("");
		    }
		    else if (choice2.isSelected()){
		    	replay = false;
		    	display.setText("");
		    	hideButtons();
		    }
	    }   
	}
}
