package scenetest;

public class Gametest {
	
	public static void scene_one(){
		System.out.println("Hello?");
		System.out.println("Hellooo?");
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
	
	public static int scene_two(){
		System.out.println("Good morning, Teddy!");
		System.out.println("Ha! I knew you could talk!");
		
		//if statement here for different responses
		System.out.println("Yay! You're my friend! Forever?");
		//reply 2
		System.out.println("I'm happy you talk. Now I have someone to talk to.");
		
		System.out.println("Today's my first day at school. I'm kinda scared.");
		System.out.println("I don't really know any other kids. What if they don't like me?");
		System.out.println("Ah...ok. Then we can all play together!");
		
		//additional dialogue based on response
		System.out.println("Why?");
		System.out.println("Haha, okay Teddy");
		
		System.out.println("I gotta go eat breakfast. Thanks Teddy, I'm not scared anymore. See you after school!");
		
		return 1;
	}
	
	public static void scene_3a(){
		//occurs when the player advised the boy to "try hard"
		
		System.out.println("The other boy wouldn't let me play with him");
		System.out.println("I pushed him...but he hit me!");
		
		//response to "did you hit him back?"
		System.out.println("No...the teacher pulled me away");
		
		//response to "that's not how we make friends"
		System.out.println("I tried really hard though! No one will talk to me now...");
		
		System.out.println("Okay. Mommy is mad at me, and Dad told me to go to bed."
				+ " I don't want to get in more trouble.");
		System.out.println("Good night, Teddy");
	}
	
	public static void scene_3b(){
		//occurs when the player advises the boy to be himself
		
		System.out.println("No one played with me. No one wanted to be my friend.");
		
		//response to "I'm still your friend"
		System.out.println("But why didn't they play with me?");
		//response to "Give it time, buddy. The other kids will play with you"
		System.out.println("But how do I make them my friends?");
		
		System.out.println("I played alone, that's what I always do. Why didn't they join me?");
		
		//response to "It'll take time, kids will come play"
		System.out.println("They will? Okay...");
		//response to "try saying hi to the others. all kids are shy"
		System.out.println("What if they're mean?");
		System.out.println("Okay...");
		
		System.out.println("Mommy wants me to go to bed.");
		System.out.println("Good night, Teddy.");
		
	}
	

	public static void main(String[] args) {
		
		int choiceReturn;
		 
		
		scene_one();
		choiceReturn = scene_two();
		//only scene_3a OR scene_3b depending on responses chosen in scene 2
		if (choiceReturn == 1) {
			scene_3a();
		}
		else {
			scene_3b();
		}
		

	}

}
