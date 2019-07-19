package za.co.wethinkcode.models;

import java.util.ArrayList;

import za.co.wethinkcode.exceptions.OccupiedByVillainException;
import za.co.wethinkcode.utilities.Coordinates;

public class GameBoard {
	private int size;
	private ArrayList< ArrayList<Character> > board;
	
	public GameBoard(int s) {
		this.size = s;
		this.board = new ArrayList<ArrayList<Character>>(size);
		
		// Creates an empty board with space for Characters
		for (int i = 0; i < size; i++) {
			ArrayList<Character> cList = new ArrayList<Character>(size);
			for (int j = 0; j < size; j++) {
				cList.add(null);
			}
			board.add(cList);
		}
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void printBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {				
				if (this.board.get(i).get(j) == null) {
					System.out.print(". ");
				} else if (this.board.get(i).get(j) instanceof Hero) {
					System.out.print("H ");
				} else if (this.board.get(i).get(j) instanceof Villain) {
					System.out.print("V ");
				} else {
					System.out.print("N ");
				}
			}
			System.out.println();
		}
	}
	
	public Character get(Coordinates c) {
		return this.board.get(c.getRow()).get(c.getCol());
	}
	
	public void set(Coordinates c, Character bePlaced) {
		this.board.get(c.getRow()).set(c.getCol(), bePlaced);
	}
	
	public void place(Coordinates c, Character character) throws Exception {
		if (c.getRow() >= this.size || c.getCol() >= this.size) {
			throw new IndexOutOfBoundsException("Reached end of map");
		}
		
		if (this.board.get(c.getRow()).get(c.getCol()) != null) {
			throw new Exception("Space is already occupied");
		}
	
		this.board.get(c.getRow()).set(c.getCol(), character);
	}
	
	public void move(Coordinates oldCoord, Coordinates newCoord) throws Exception {
		
		if (oldCoord.getRow() >= this.size || oldCoord.getCol() >= this.size || oldCoord.getRow() < 0 || oldCoord.getCol() < 0) {
			throw new Exception("[From] coordinate is invalid");
		}
		
		if (newCoord.getRow() >= this.size || newCoord.getCol() >= this.size || newCoord.getRow() < 0 || newCoord.getCol() < 0) {
			throw new IndexOutOfBoundsException("can't move Character outside of grid idiot");
		}
		
		if (this.board.get(oldCoord.getRow()).get(oldCoord.getCol()) == null) {
			throw new Exception("Nothing to moved");
		}
		
		if (this.get(newCoord) instanceof Villain) {
			throw new OccupiedByVillainException((Villain)this.get(newCoord));
		}
		
		Character temp = this.get(oldCoord);
		this.set(oldCoord, null);
		this.set(newCoord, temp);
	}

	public void dropPosition(Coordinates co) {
		this.board.get(co.getRow()).set(co.getCol(), null);
	}
}
