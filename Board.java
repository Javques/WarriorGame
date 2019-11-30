//--------------------------------------
// Assignment 4 (Board class)
// Written by: Alexis Bolduc 40126092
// For COMP 248 Section FF - Fall 2019
//--------------------------------------


// Welcome to Board class of assignment 4! 
// This class is the Board class and is used for the Board in the game
public class Board {
	//attributes
	private int [][][] board; 
	public static final int  MIN_LEVEL = 3; 
	public static final int  MIN_SIZE = 3;
	private int Level;
	private int size;
		
	//constructor
	public Board() {
		Level = 3;
		size = 4; 
		createBoard(Level,size);
	}
	//constructor with level and size parameter
	public Board(int Level, int size) {
		this.Level = Level;
		this.size = size;
		createBoard(Level, size);
		
	}
	// create board method to create a board
	private void createBoard(int Level, int size) {
		
		board = new int [Level] [size] [size]; 
		for (int a = 0; a < Level; a++) { 
			for (int b = 0; b<size; b++) {
				for (int c = 0; c<size; c++) {
						if ((a+b+c)%3==0) {
							board [a][b][c] = -3;
		
							}
						else if ((a+b+c)%5==0) {
							board [a][b][c] = -2;
		
							}
						else if ((a+b+c)%7==0) {
							board [a][b][c] = 2;
			
							}
						else board [a][b][c] = 0;
					}
				}
			}
		board [0][0][0]= 0; 
	}
	//accessor 
	public int getLevel() {
		return Level; 
		}
	public int getSize() {
		return size;
		}
	public int getEnergyAdj(int L, int x, int y) {
		return board [L][x][y];
		}
	//toString method displaying the board 
	public String toString() {
		
			String boardLay = "";
			for (int a = 0; a<Level; a++){
				boardLay += "\n\nLevel " + a + "\n--------";
					for(int b = 0; b<size; b++) {
						boardLay += "\n";
						for (int c = 0; c<size; c++) {
						boardLay += "\t" + Integer.toString(board[a][b][c]);
						}
					}
				}
		return boardLay;
	}
}
//This marks the end of the program for the Board, thank you!
