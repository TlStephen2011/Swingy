package za.co.wethinkcode.swingy;

import java.util.ArrayList;

import za.co.wethinkcode.controllers.GameController;
import za.co.wethinkcode.database.HeroStorage;
import za.co.wethinkcode.models.Armor;
import za.co.wethinkcode.models.Artifact;
import za.co.wethinkcode.models.Helm;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Weapon;
import za.co.wethinkcode.views.GUI;
import za.co.wethinkcode.views.Viewable;

public class App 
{
    public static void main( String[] args )
    {
//    	GameController controller = new GameController();
//    	while (controller.run()) {}
    	
    	Hero myHero = new Hero("Stevez25", "Human");
    	Artifact weapon = new Weapon("Super Saiyanz", 123);
    	Artifact armor = new Armor("Battle Armor", 45);
    	Artifact helm = new Helm("Senzu Bean", 500);
    	
    	myHero.equip(weapon);
    	myHero.equip(armor);
    	myHero.equip(helm);
    	myHero.setId(1);
    	
    	HeroStorage.connect();
    	
    	HeroStorage.saveHero(myHero);    	
    	
    	ArrayList<Hero> heroes = HeroStorage.getAllHeroes();
    	for (int i = 0; i < heroes.size(); i++) {
    		System.out.println("ID: " + heroes.get(i).getId());
    		System.out.println(heroes.get(i).toString());
    		System.out.println();
    	}
    }
}
