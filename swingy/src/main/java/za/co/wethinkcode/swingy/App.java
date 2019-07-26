package za.co.wethinkcode.swingy;

import java.util.ArrayList;

import za.co.wethinkcode.controllers.GameController;
import za.co.wethinkcode.database.HeroStorage;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.views.GUI;
import za.co.wethinkcode.views.Viewable;

public class App 
{
    public static void main( String[] args )
    {
//    	GameController controller = new GameController();
//    	while (controller.run()) {}
    	
    	HeroStorage.connect();
    	HeroStorage.saveHero(new Hero("Quintin", "Magician"));
    	HeroStorage.saveHero(new Hero("Alice", "Nyphin"));
    	HeroStorage.saveHero(new Hero("Penny", "Traveler"));
    	HeroStorage.saveHero(new Hero("Katie", "Hedge"));
    	
    	ArrayList<Hero> heroes = HeroStorage.getAllHeroes();
    	for (int i = 0; i < heroes.size(); i++) {
    		System.out.println("ID: " + heroes.get(i).getId());
    		System.out.println(heroes.get(i).toString());
    	}
    }
}
