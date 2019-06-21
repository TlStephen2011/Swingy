package za.co.wethinkcode.swingy;

import utilities.Coordinates;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Weapon;

public class App 
{
    public static void main( String[] args )
    {
    	Coordinates coords = new Coordinates(10, 12);
    	Hero me = new Hero("Wildithas", "Halfling", 1, 1563, 100, 50, 100, coords);
    	
    	System.out.println(me.toString());
    	
    	Weapon w = new Weapon("Short Sword", 15);
    	
    	System.out.println(me.getDamage());
    	
    	me.equip(w);
    	
    	System.out.println(me.getDamage());
    	
    	Weapon w2 = new Weapon("Long Sword", 30);
    	
    	me .equip(w2);
    	
    	System.out.println(me.getDamage());
    	
    }
}
