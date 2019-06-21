package za.co.wethinkcode.models;

import java.util.ArrayList;

public class GameBoard {
	private int size;
	ArrayList<ArrayList<typeOccupying> > board;
	
	private enum typeOccupying {
		EMPTY,
		ENEMY,
		HERO
	};

	public GameBoard() {
		ArrayList<ArrayList<typeOccupying> > board = new ArrayList<ArrayList<typeOccupying>>(size);
	}
}
