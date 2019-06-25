package za.co.wethinkcode.controllers;

import java.util.ArrayList;

import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;
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
	}
	
}
