package za.co.wethinkcode.controllers;

import java.util.ArrayList;

import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;
import za.co.wethinkcode.utilities.Coordinates;
import za.co.wethinkcode.utilities.VillainBuilder;
import za.co.wethinkcode.views.Console;
import za.co.wethinkcode.views.Viewable;

public class GameController {
	
	private Hero hero;
	private ArrayList<Villain> villains;
	private GameBoard gameBoard;
	private Viewable view;
		
	public GameController() {
		this.view = new Console();
		this.hero = this.view.newHero();
		int heroLevel = this.hero.getLevel();
		this.gameBoard = new GameBoard((heroLevel - 1) * 5 + 10 - (heroLevel % 2));
		this.villains = VillainBuilder.buildVillains(this.hero.getLevel(), this.gameBoard.getSize());
		this.hero.setPosition(new Coordinates(this.gameBoard.getSize() / 2, this.gameBoard.getSize() / 2));
		
		try {
			this.gameBoard.place(this.hero.getPosition(), this.hero);
			for (int i = 0; i < this.villains.size(); i++) {
				this.gameBoard.place(this.villains.get(i).getPosition(), this.villains.get(i));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.gameBoard.printBoard();
	}
	
	public boolean run() {
		Viewable.inputType t = this.view.getInput();
		Coordinates pos = this.hero.getPosition();
		if (t == Viewable.inputType.NORTH) {
			try {
				Coordinates newCoords = new Coordinates(pos.getRow() - 1, pos.getCol());
				this.gameBoard.move(pos, newCoords);
				this.hero.setPosition(newCoords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			System.out.println();
			this.gameBoard.printBoard();
			return true;
		}
		return true;
	}
	
}
