//--------------------------------------
// Assignment 4 (Driver class)
// Written by: Alexis Bolduc 40126092
// For COMP 248 Section FF - Fall 2019
//--------------------------------------


// Welcome to driver class of assignment 4! 
// This class is the driver class and plays the game using 3 classes
import java.util.Scanner;
import java.util.Random;
public class LetUsPlay {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner (System.in);
		//choice for custom board
		int choice = 0;
		//decision for challenge
		int forChall = 0;
		//result of challenge roll
		int challengeRoll;
		//number of level if user wants to decide
		int newLevel = 0;
		//size of level if user wants to decide
		int newSize = 0;
		//dice sum
		int sum = 0;
		//Names of the player
		String nameP0 = "";
		String nameP1 = "";
		//input for nextRound
		String nextRound = "";
		//dice roll for start
		int start = 0;
		// initial board
		Board b = null;
		//initial player objects
		Player firstPlayer = null;
		Player secondPlayer = null;
		Dice dice = new Dice();
		// exit loop boolean 
		boolean ok = false; 
		//Welcome message
		System.out.println("\t  *_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*\n"
				
				+"\t  * \t\t\t\t              *\n"+
				"\t  *    WELCOME to Nancy's 3D Warrior Game!    *\n"
				+"\t  * \t\t\t\t              *\n"+
				"\t  *_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*\n");
		System.out.print("The default game board has 3 levels and each level has a 4x4 board.\n"+
				"You can use this default board size or change the size\n"
				+"\t 0 to use the default board size\n"+
				"\t-1 to enter your own size\n"+
				"==> What do you want to do? ");
		//user input
		choice = keyboard.nextInt();
		// loop of validity
		while (!ok) {
			if (choice == 0|| choice == -1 ) {
				ok = true;
				break;}
			System.out.print("Sorry but "+ choice+ " is not a legal choice.\n");
			choice = keyboard.nextInt();
		
		}
		
	// if modify 
	if (choice == -1) {
		// level modify
		System.out.print("How many levels would you like? (minimum size 3, max 10) ");	
		newLevel = keyboard.nextInt();
		ok = false; 
				// validity loop
				while (!ok) { 
					if (newLevel >= Board.MIN_LEVEL && newLevel <=10 ) {
						ok = true;
						break;}
					System.out.print("Sorry but "+ newLevel+ " is not a legal choice.\n");
					newLevel = keyboard.nextInt();
					}
		//size modify
		System.out.print("\nWhat size to you want the nxn boards on each level be?"+
					"\nMinimum size is 3x3, max is 10x10."+
					"\n==>Enter the value of n: ");	
		newSize = keyboard.nextInt();
		ok = false; 
				//validity loop
				while (!ok) { 
					if (newSize >= Board.MIN_SIZE && newSize <=10 ) {
						ok = true;
						break;}
					System.out.print("Sorry but "+ newSize+ " is not a legal choice.\n");
					newSize = keyboard.nextInt();
			}
		  
		}
	//default board
	if (choice == 0) {
		 b = new Board();
	System.out.println("Your 3D board has been set up and look like this:"+b);
	}
	//custom board
	else {
		 b = new Board(newLevel, newSize);
	System.out.println("Your 3D board has been set up and look like this:"+ b +"\n" );
	}
	//players identification
	System.out.print("\nWhat is player 0's name (one word only): ");
	nameP0 = keyboard.next();
	Player p0 = new Player(nameP0);
	System.out.print("\nWhat is player 1's name (one word only): "); 
	nameP1 = keyboard.next();
	Player p1 = new Player(nameP1);
	
	Random rng = new Random();
	// Random number between 0 and 1
	start = rng.nextInt(2); 
	
	//Decide who goes first
	System.out.print("\nThe game has started "+ ((start==0)?nameP0:nameP1)+ " goes first\n"+
	"============================\n\n");
	//Here I assign the position of each player
	if (start == 0) {
		 firstPlayer = p0;
		 secondPlayer = p1; 
	}
	else {
		firstPlayer = p1;
		secondPlayer = p0;
	}
	
	//********** LET THE GAME BEGIN *****************************************************
	label1: 
		while (!firstPlayer.won(b) && !secondPlayer.won(b)) {
		
		//****************First Player's turn***********************************************************
		System.out.println("\nIt is "+ firstPlayer.getName()+"'s turn");
		//Conditions if 0 or less energy
		if (firstPlayer.getEnergy()<=0) {
			System.out.println("\t"+firstPlayer.getName()+", you have "+ firstPlayer.getEnergy() +" units of energy, you toss the dice thrice.");
			int numDoub = 0;
			// the three rolls
			for (int i = 1; i<4; i++) {
					dice.rollDice();
					System.out.println("Roll "+i+": "+ dice);
					if(dice.isDouble())
						numDoub++; 
					}
			//adjusting energy depending on number of doubles rolled
			firstPlayer.setEnergy(firstPlayer.getEnergy()+ (numDoub*2));
			//Announcement message ==>Can the player play  by himself or Does he have to move to the other players' position?
			System.out.println("\tYou managed to roll "+numDoub+ " double" + ((numDoub>1)?"s":"") + ". " + 
			((firstPlayer.getEnergy()>0)?"Congratulations, you can toss the dice in order to advance":("Unfortunately, you do not"+
			" have enough energy to advance, better luck next round!.")));
		}
		if (firstPlayer.getEnergy()>0) {
			//NORMAL STUFF
			sum = dice.rollDice();
			System.out.println("\t"+firstPlayer.getName()+ " you rolled " +dice);
			//If rolling a double
				if (dice.isDouble()) {
					firstPlayer.setEnergy((firstPlayer.getEnergy()+2));
					System.out.println("\tCongratulations you rolled double "+ dice.getDie1()+ ". Your energy went up by 2 units.");
					}
			//temporary player for position evaluations
			Player tempPlayer1 = new Player(firstPlayer);
			//setting x and y
			tempPlayer1.setX((tempPlayer1.getX())+(sum/b.getSize()));
			tempPlayer1.setY((tempPlayer1.getY())+(sum%(b.getSize())));
			//if Y is out of board
			if (tempPlayer1.getY()>=b.getSize())
			{
				tempPlayer1.setX(tempPlayer1.getX()+((tempPlayer1.getY())/(b.getSize())));
				tempPlayer1.setY((tempPlayer1.getY())%(b.getSize()));
				
			}
			//x is out of board
			if (tempPlayer1.getX()>=b.getSize()) {
				tempPlayer1.setX((tempPlayer1.getX())%(b.getSize()));
				tempPlayer1.setLevel(tempPlayer1.getLevel()+1);
			}
			//If player on 2nd to last tile
			if (firstPlayer.getLevel()==(b.getLevel()-1)&&firstPlayer.getX()==(b.getSize()-1)&&firstPlayer.getY()==(b.getSize()-2)){
				// may be out of array bound (negative value)
				int futureX = firstPlayer.getX()-((sum-firstPlayer.getY()+b.getSize()-1)/b.getSize());
				int futureY = (firstPlayer.getY()-(sum%b.getSize()));
				tempPlayer1.setLevel(firstPlayer.getLevel());
				System.out.println("Since you are on the second to last tile, you will move backwards");
				//if roll throws player out of last level
				if (futureY<0) {
	
					futureY = ((futureY+(b.getSize())));
					
				}
				if (futureX<0) {
					futureX = ((futureX+(b.getSize())));
					tempPlayer1.setLevel(firstPlayer.getLevel()-1);
					
				}
				tempPlayer1.setX(futureX);
				tempPlayer1.setY(futureY);
			}
			//Challenge conditions
			if(tempPlayer1.equals(secondPlayer)) {
				//Announcement
				System.out.println("Player " + secondPlayer.getName()+ " is at your new location "+
				"\n What do you want to do?\n"+
						"\t0 - Challenge and risk loosing 50% of your energy units if you lose\n"+
						"\t      or move to new location and get 50% of other players energy units\n"+
						"\t1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy\n"
					  + "\t      units");
				 forChall = keyboard.nextInt();
				// validation of entry
				 ok = false; 
				 while (!ok) {
						if (forChall == 1|| forChall == 0 ) {
							ok = true;
							break;}
						System.out.print("Sorry but "+ forChall+ " is not a legal choice.\n");
						forChall = keyboard.nextInt();
						}
				 // FORFEIT
				 if (forChall == 1) {
				 firstPlayer.setEnergy((firstPlayer.getEnergy()-2));
				 //if still on level 0
					 if(tempPlayer1.getLevel()==0) {
						 tempPlayer1.setX(0);
						 tempPlayer1.setY(0);
						 firstPlayer.moveTo(tempPlayer1);
						 System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(firstPlayer.getLevel(), firstPlayer.getX(), firstPlayer.getY())+" for landing at ("+
									firstPlayer.getX() +", "+firstPlayer.getY()+") at level "+ firstPlayer.getLevel());
									firstPlayer.setEnergy(firstPlayer.getEnergy()+b.getEnergyAdj(firstPlayer.getLevel(), firstPlayer.getX(), firstPlayer.getY()));
					 }
					 //if on other level than 0
					 else{
						firstPlayer.moveTo(tempPlayer1);
						firstPlayer.setLevel((firstPlayer.getLevel()-1));
						System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(firstPlayer.getLevel(), firstPlayer.getX(), firstPlayer.getY())+" for landing at ("+
								firstPlayer.getX() +", "+firstPlayer.getY()+") at level "+ firstPlayer.getLevel());
								firstPlayer.setEnergy(firstPlayer.getEnergy()+b.getEnergyAdj(firstPlayer.getLevel(), firstPlayer.getX(), firstPlayer.getY()));
					 }
				
				 }
				 // CHALLENGE
				 if (forChall == 0) {
					 challengeRoll = rng.nextInt(11);
					 //LOSE
					 if(challengeRoll<6) {
						 System.out.println("\tOh no!! You lost the challenge.");
						 firstPlayer.setEnergy((firstPlayer.getEnergy())/2);
						 System.out.println("\tYou lose half your points and stay at ("+
						firstPlayer.getX() +", "+firstPlayer.getY()+") at level "+ firstPlayer.getLevel());
					 }
					 //WIN
					 else {
						 System.out.println("\tBravo!! You won the challenge.");
						 secondPlayer.setX(firstPlayer.getX());
						 secondPlayer.setY(firstPlayer.getY());
						 secondPlayer.setLevel(firstPlayer.getLevel());
						 firstPlayer.moveTo(tempPlayer1);
						 firstPlayer.setEnergy((firstPlayer.getEnergy())+(secondPlayer.getEnergy()/2));
						 secondPlayer.setEnergy((secondPlayer.getEnergy())/2);
						 System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(firstPlayer.getLevel(), firstPlayer.getX(), firstPlayer.getY())+" for landing at ("+
									firstPlayer.getX() +", "+firstPlayer.getY()+") at level "+ firstPlayer.getLevel());
									firstPlayer.setEnergy(firstPlayer.getEnergy()+b.getEnergyAdj(firstPlayer.getLevel(), firstPlayer.getX(), firstPlayer.getY()));
					 }
				 }
			}
			
			//Player1 movement if no challenges or
			// adjust energy
			else{
				//Too high roll
				if(tempPlayer1.getLevel()>= b.getLevel()) {
					firstPlayer.setEnergy((firstPlayer.getEnergy())-2);
					System.out.println("!!! Sorry you need to stay where you are - that throw takes you off the grid and you\n"+
					"loose two units of energy.");
				}
				//normal roll
				else {
					firstPlayer.moveTo(tempPlayer1);
					System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(firstPlayer.getLevel(), firstPlayer.getX(), firstPlayer.getY())+" for landing at ("+
					firstPlayer.getX() +", "+firstPlayer.getY()+") at level "+ firstPlayer.getLevel());
					firstPlayer.setEnergy(firstPlayer.getEnergy()+b.getEnergyAdj(firstPlayer.getLevel(), firstPlayer.getX(), firstPlayer.getY()));
				}
			}
			
		}
		//stop loop if player wins
		if (firstPlayer.won(b))
			 break label1; 
		
		
		//******************* Second Player's turn ************************************************
		System.out.println("It is "+ secondPlayer.getName()+"'s turn");
		//Conditions if 0 or less energy
		if (secondPlayer.getEnergy()<=0) {
			System.out.println("\t"+secondPlayer.getName()+", you have "+ secondPlayer.getEnergy() +" units of energy, you toss the dice thrice.");
			int numDoub = 0;
			// the three rolls
			for (int i = 1; i<4; i++) {
					dice.rollDice();
					System.out.println("Roll "+i+": "+ dice);
					if(dice.isDouble())
						numDoub++; 
					}
			//adjusting energy depending on number of doubles rolled
			secondPlayer.setEnergy(secondPlayer.getEnergy()+ (numDoub*2));
			//Announcement message ==>Can the player play  by himself or Does he have to move to the other players' position?
			System.out.println("\tYou managed to roll "+numDoub+ " double" + ((numDoub>1)?"s":"") + ". " + 
			((secondPlayer.getEnergy()>0)?"Congratulations, you can toss the dice in order to advance":("Unfortunately, you do not"+
			" have enough energy to advance, better luck next round!.")));
		}
		if (secondPlayer.getEnergy()>0) {
			//NORMAL STUFF
			sum = dice.rollDice();
			System.out.println("\t"+secondPlayer.getName()+ " you rolled " +dice);
			//If rolling a double
				if (dice.isDouble()) {
					secondPlayer.setEnergy((secondPlayer.getEnergy()+2));
					System.out.println("\tCongratulations you rolled double "+ dice.getDie1()+ ". Your energy went up by 2 units.");
					}
			// temporary player for position evaluation
			Player tempPlayer2 = new Player(secondPlayer);
			// setting x and y
			tempPlayer2.setX((tempPlayer2.getX())+(sum/b.getSize()));
			tempPlayer2.setY((tempPlayer2.getY())+(sum%(b.getSize())));
			//if Y is out of board
			if (tempPlayer2.getY()>=b.getSize())
			{
				tempPlayer2.setX(tempPlayer2.getX()+((tempPlayer2.getY())/(b.getSize())));
				tempPlayer2.setY((tempPlayer2.getY())%(b.getSize()));
				
			}
			//x is out of board
			if (tempPlayer2.getX()>=b.getSize()) {
				tempPlayer2.setX((tempPlayer2.getX())%(b.getSize()));
				tempPlayer2.setLevel(tempPlayer2.getLevel()+1);
			}
			//If player on 2nd to last tile
			if (secondPlayer.getLevel()==(b.getLevel()-1) && secondPlayer.getX()==(b.getSize()-1) && secondPlayer.getY()==(b.getSize()-2)){
				int futureX = secondPlayer.getX()-((sum-secondPlayer.getY()+b.getSize()-1)/b.getSize());
				int futureY = (secondPlayer.getY()-(sum%b.getSize()));
				tempPlayer2.setLevel(secondPlayer.getLevel());
				System.out.println("Since you are on the second to last tile, you will move backwards");
				//if roll throws player out of last level
				if (futureY<0) {
	
					futureY = ((futureY+(b.getSize())));
					
				}
				if (futureX<0) {
					futureX = ((futureX+(b.getSize())));
					tempPlayer2.setLevel(secondPlayer.getLevel()-1);
					
				}
				tempPlayer2.setX(futureX);
				tempPlayer2.setY(futureY);
			}
			
			//Challenge conditions
			if(tempPlayer2.equals(firstPlayer)) {
				//Announcement
				System.out.println("Player " + firstPlayer.getName()+ " is at your new location "+
				"\n What do you want to do?\n"+
						"\t0 - Challenge and risk loosing 50% of your energy units if you lose\n"+
						"\t      or move to new location and get 50% of other players energy units\n"+
						"\t1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy\n"
					  + "\t      units");
				 forChall = keyboard.nextInt();
				// validation of entry
				 ok = false; 
				 //loop validation of entry
				 while (!ok) {
						if (forChall == 1|| forChall == 0 ) {
							ok = true;
							break;}
						System.out.print("Sorry but "+ forChall+ " is not a legal choice.\n");
						forChall = keyboard.nextInt();
						}
				 // FORFEIT
				 if (forChall == 1) {
				 secondPlayer.setEnergy((secondPlayer.getEnergy()-2));
				 	// if on level 0 when forfeit
					 if(tempPlayer2.getLevel()==0) {
						 tempPlayer2.setX(0);
						 tempPlayer2.setY(0);
						 secondPlayer.moveTo(tempPlayer2);
						 System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(secondPlayer.getLevel(), secondPlayer.getX(), secondPlayer.getY())+" for landing at ("+
									secondPlayer.getX() +", "+secondPlayer.getY()+") at level "+ secondPlayer.getLevel());
									secondPlayer.setEnergy(secondPlayer.getEnergy()+b.getEnergyAdj(secondPlayer.getLevel(), secondPlayer.getX(), secondPlayer.getY()));
					 }
					 //if on other level than 0
					 else{
						 secondPlayer.moveTo(tempPlayer2);
						secondPlayer.setLevel((secondPlayer.getLevel()-1));
						
						System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(secondPlayer.getLevel(), secondPlayer.getX(), secondPlayer.getY())+" for landing at ("+
								secondPlayer.getX() +", "+secondPlayer.getY()+") at level "+ secondPlayer.getLevel());
								secondPlayer.setEnergy(secondPlayer.getEnergy()+b.getEnergyAdj(secondPlayer.getLevel(), secondPlayer.getX(), secondPlayer.getY()));
					 }
				
				 }
				 // CHALLENGE
				 if (forChall == 0) {
					 challengeRoll = rng.nextInt(11);
					 //WON
					 if(challengeRoll<6) {
						 System.out.println("\tOh no!! You lost the challenge.");
						 secondPlayer.setEnergy((secondPlayer.getEnergy())/2);
						 System.out.println("\tYou lose half your points and stay at ("+
						secondPlayer.getX() +", "+secondPlayer.getY()+") at level "+ secondPlayer.getLevel());
					 }
					 //LOSE
					 else {
						 System.out.println("\tBravo!! You won the challenge.");
						 firstPlayer.setX(secondPlayer.getX());
						 firstPlayer.setY(secondPlayer.getY());
						 firstPlayer.setLevel(secondPlayer.getLevel());
						 secondPlayer.moveTo(tempPlayer2);
						 secondPlayer.setEnergy((secondPlayer.getEnergy())+(firstPlayer.getEnergy()/2));
						 firstPlayer.setEnergy((firstPlayer.getEnergy())/2);
						
						 System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(secondPlayer.getLevel(), secondPlayer.getX(), secondPlayer.getY())+" for landing at ("+
									secondPlayer.getX() +", "+secondPlayer.getY()+") at level "+ firstPlayer.getLevel());
									secondPlayer.setEnergy(secondPlayer.getEnergy()+b.getEnergyAdj(secondPlayer.getLevel(), secondPlayer.getX(), secondPlayer.getY()));
					 }
				 }
			}
			
			//Player1 movement if no challenges or
			// adjust energy
			else{
				//if too high of a roll
				if(tempPlayer2.getLevel()>= b.getLevel()) {
					secondPlayer.setEnergy((secondPlayer.getEnergy())-2);
					System.out.println("!!! Sorry you need to stay where you are - that throw takes you off the grid and you\n"+
					"loose two units of energy.");
				}
				//normal roll 
				else {
					secondPlayer.moveTo(tempPlayer2);
					System.out.println("\tYour energy is adjusted by " + b.getEnergyAdj(secondPlayer.getLevel(), secondPlayer.getX(), secondPlayer.getY())+" for landing at ("+
					secondPlayer.getX() +", "+secondPlayer.getY()+") at level "+ secondPlayer.getLevel());
					secondPlayer.setEnergy(secondPlayer.getEnergy()+b.getEnergyAdj(secondPlayer.getLevel(), secondPlayer.getX(), secondPlayer.getY()));
				}
			}
			//evaluate if second player wins
			if (secondPlayer.won(b))
				 break label1; 
			
		}
		//end of round message and input
		System.out.println("\nAt the end of this round:\n\t"+firstPlayer+"\n\t"+secondPlayer);
		System.out.print("Any key to continue...");
		nextRound = keyboard.next();
		//useless step to get rid of error message
		nextRound =  nextRound+"";
	}
	//win message
	System.out.println("The winner is player " +(firstPlayer.won(b)?firstPlayer.getName():secondPlayer.getName())+ ". Well done!!!");
	
	}
	

}
//This marks the end of the program for the driver class, thank you!