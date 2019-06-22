package za.co.wethinkcode.swingy;

import za.co.wethinkcode.models.GameBoard;
import za.co.wethinkcode.models.Hero;
import za.co.wethinkcode.models.Villain;
import za.co.wethinkcode.models.Weapon;
import za.co.wethinkcode.utilities.Coordinates;

public class App 
{
    public static void main( String[] args )
    {
    	Coordinates coords = new Coordinates(5, 1);
    	Hero me = new Hero("Wildithas", "Halfling", 1, 1563, 100, 50, 100, coords);
//    	
//    	System.out.println(me.toString());
//    	
//    	Weapon w = new Weapon("Short Sword", 15);
//    	
//    	System.out.println(me.getDamage());
//    	
//    	me.equip(w);
//    	
//    	System.out.println(me.getDamage());
//    	
//    	Weapon w2 = new Weapon("Long Sword", 30);
//    	
//    	me.equip(w2);
//    	
//    	System.out.println(me.getDamage());
//    	
//    	System.out.println(me.getLevel());
//    	
//    	me.getXp(2500);
//    	
//    	System.out.println(me.getLevel());
//    	
//    	Villain v = new Villain("Tywin", 100, 100, 100, 100, coords);
//    	System.out.println("Attack: " + me.attack(v));
//    	System.out.println("Run: " + me.run(v));
//
//    	GameBoard game = new GameBoard(10);
//    	game.printBoard();
//    	System.out.println();
//    	try {
//			game.place(coords, me);
//			game.printBoard();
//			System.out.println();
//			game.move(coords, new Coordinates(9, 9));
//			game.printBoard();
//			System.out.println();
//			Villain v = new Villain("Tywin", 100, 100, 100, 100, new Coordinates(2, 2));
//			game.place(new Coordinates(2, 2), v);
//			game.printBoard();
//			System.out.println();
//			game.move(new Coordinates(2, 2), new Coordinates(6, 6));
//			game.printBoard();
//			System.out.println();
//			
//    	} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Something went wrong, for now we this message: " + e.getMessage());
//		}
    	
    	
    }
}
