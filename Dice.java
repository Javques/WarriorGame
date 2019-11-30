//--------------------------------------
// Assignment 4 (Dice class)
// Written by: Alexis Bolduc 40126092
// For COMP 248 Section FF - Fall 2019
//--------------------------------------


// Welcome to Dice class of assignment 4! 
// This class is the Dice class and is used for the Dice in the game
import java.util.Random;
public class Dice {
	//attributes
	private int die1;
	private int die2;
	
	//default constructor
	public Dice(){
		die1 = 1; 
		die2 = 1;
	}
	//Accessor methods
	public int getDie1() {
		return die1;
	}
	public int getDie2() {
		return die2;
	}
	// random between range
	public static int randomInt(int min, int max) {
	    Random ran = new Random();
	    return ran.nextInt((max - min) + 1) + min;
	}
	//rollDice method
	public int rollDice() {
	
		die1 = randomInt(1,6);
		die2 = randomInt(1,6);
		return (die1 + die2); 
	}
	//boolean method for a double rolled
	public boolean isDouble() {
		return die1 == die2;
	}
	//toString method 
	public String toString() {
		return "Die 1: " + die1 +" Die 2: " + die2;
	}
}
//This marks the end of the program for the Dice class, thank you!
