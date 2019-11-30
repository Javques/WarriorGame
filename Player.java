//--------------------------------------
// Assignment 4 (Player class)
// Written by: Alexis Bolduc 40126092
// For COMP 248 Section FF - Fall 2019
//--------------------------------------


// Welcome to Player class of assignment 4! 
// This class is the Player class and is used for the player in the game
public class Player {
	//attributes
	private String name;
	private int energy;
	private int Level;
	private int x;
	private int y;
	
	//default constructor
	public Player() {
		name = ""; 
		energy = 10;
		Level = 0;
		x = 0;
		y = 0;
		
		
	}
	//constructor with String parameter
	public Player(String name) {
		this.name = name ; 
		energy = 10;
		Level = 0;
		x = 0;
		y = 0;
		
		
	}
	//constructor with level, x, y parameters
	public Player(int Level, int x, int y) {
		name = "";
		energy = 10;
		this.Level = Level;
		this.x = x;
		this.y = y;
	}
	//Constructor that copies another player 
	public Player(Player Original) {
		name = Original.name; 
		energy = Original.energy;
		Level = Original.Level;
		x = Original.x;
		y = Original.y;
	}
	//mutators 
	public void setName(String name) {
		this.name = name;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public void setLevel(int Level) {
		this.Level = Level;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	//accessors
	public String getName() {
		return name;
	}
	public int getEnergy() {
		return energy;
	}
	public int getLevel() {
		return Level;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	//Method used to move a player to another player
	public void moveTo(Player p) {
		Player temporary = new Player(this.Level,this.x,this.y);
		
		this.Level = p.Level; 
		this.x = p.x;
		this.y = p.y;
		
		temporary.Level = p.Level;
		temporary.x = p.x;
		temporary.y = p.y;
		
		
	}
	//method to determine if a player won, i.e. is on the last possible tile
	public boolean won(Board b) {
		return ((b.getSize()-1)== this.y && (b.getSize()-1)== this.x && (b.getLevel()-1)== this.Level);
		}
	// method to determine if a player is at the same position as another
	public boolean equals(Player p) {
		return (this.Level == p.Level && this.x == p.x && this.y == p.y );
	}
	// method toString for the players description
	public String toString() {
		return name + " is on level " + Level + " at location (" + x +"," + y+") and has "+energy+" units of energy.";
	}
}
//This marks the end of the program for the Player class, thank you!
