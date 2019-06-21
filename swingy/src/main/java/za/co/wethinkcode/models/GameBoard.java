package za.co.wethinkcode.models;

import java.util.ArrayList;

public class GameBoard {
	private int size;
	private enum typeOccupying {
		EMPTY,
		ENEMY,
		HERO
	};
	ArrayList<ArrayList<typeOccupying> > board = new ArrayList<ArrayList<typeOccupying>>(size);
}
